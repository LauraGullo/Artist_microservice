package com.example.demo.service;

import com.example.demo.dto.ArtistDTO;
import com.example.demo.entity.Artist;
import com.example.demo.exception.ArtistNotFound;
import com.example.demo.feignclients.SongFeignClient;
import com.example.demo.model.Song;
import com.example.demo.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final SongFeignClient songFeignClient;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        log.info("The artist is being created...");
        Artist artist = modelMapper.map(artistDTO, Artist.class);
        artistRepository.save(artist);
        log.info("Successfully saved artist in database");
        return modelMapper.map(artist, ArtistDTO.class);
    }

    @Transactional
    public List<ArtistDTO> findAll() {
        log.info("Looking for artists...");
        List<Artist> artists = artistRepository.findAll();
        log.info(artists.isEmpty() ? "The list is empty" : "List of artists found");
        return artists
                .stream()
                .map(artist -> modelMapper.map(artist, ArtistDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ArtistDTO findById(Long id)  {
        log.info("Looking for artist by id: " + id);
        Artist artist = artistRepository.findById(id)
                .orElseThrow(()->new ArtistNotFound("Artist not found by id: " + id));
        return modelMapper.map(artist, ArtistDTO.class);
    }

    @Transactional
    public void deleteArtist(Long id) {
        log.info("Looking for artist to delete by id: " + id);
        artistRepository.deleteById(this.findById(id).getId());
    }

    @Transactional
    public ArtistDTO updateArtist(Long id, ArtistDTO artistDTO) {
        log.info("Looking for artist to update by id: " + id);
        Artist artistOld = artistRepository.findById(id)
                .orElseThrow(()-> new ArtistNotFound("Artist not found by id: " + id));;
        artistOld.setName(artistDTO.getName());
        artistOld.setBirthday(artistDTO.getBirthday());
        artistRepository.save(artistOld);
        log.info("Artist successfully updated");
        return modelMapper.map(artistOld, ArtistDTO.class);
    }

    public Song saveSong(Song song){
        return songFeignClient.createSong(song);
    }

    public List<Song> findSongsByArtist(Long id){
        log.info("The artist's songs are being searched by id: " + id);
        return songFeignClient.findSongByIdArtist(id);
    }

    public Map<String, Object> countOfSongByArtist(){
        List<Artist> artists = artistRepository.findAll();
        log.info(artists.isEmpty() ? "The list is empty" : "List of artists found");
        List<ArtistDTO> artistDTO = artists.stream()
                .map(artist -> modelMapper.map(artist, ArtistDTO.class))
                .collect(Collectors.toList());
        return artistDTO.stream()
                .collect(Collectors.toMap(ArtistDTO::getName, artist -> findSongsByArtist(artist.getId())));
    }
}
