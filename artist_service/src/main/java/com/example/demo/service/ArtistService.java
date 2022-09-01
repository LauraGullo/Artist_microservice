package com.example.demo.service;

import com.example.demo.entity.Artist;
import com.example.demo.feignclients.SongFeignClient;
import com.example.demo.model.Song;
import com.example.demo.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final SongFeignClient songFeignClient;

    @Transactional
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Transactional
    public Optional<Artist> findById(Long id)  {
        return artistRepository.findById(id);
    }

    @Transactional
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    @Transactional
    public Artist updateArtist(Long id, Artist artist) {
        Artist artistOld = artistRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Song not found by id: " + id));;
        artistOld.setName(artist.getName());
        artistOld.setBirthday(artist.getBirthday());
        return artistRepository.save(artistOld);
    }

    public Song saveSong(Song song){
        return songFeignClient.createSong(song);
    }

    public List<Song> findSongsByArtist(Long id){
        return songFeignClient.findSongByIdArtist(id);
    }

    public String getDatos(){
        List<Artist> artists = artistRepository.findAll();
        return artists.stream().map( a -> a.getName() + " " + a.getBirthday()).collect(Collectors.joining("\n"));
    }

    public List<Artist> getArtitsMayor(){
        List<Artist> artistList = artistRepository.findAll();
        return artistList.stream().filter(a -> Period.between(a.getBirthday(),
                LocalDate.now()).getYears() > 25).collect(Collectors.toList());
    }
}
