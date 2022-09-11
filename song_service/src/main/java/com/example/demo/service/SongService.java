package com.example.demo.service;

import com.example.demo.dto.SongDTO;
import com.example.demo.entity.Song;
import com.example.demo.exception.SongNotFound;
import com.example.demo.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {

    private final SongRepository songRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public SongDTO createSong(SongDTO songDTO) {
        log.info("The song is being created...");
        Song song = modelMapper.map(songDTO, Song.class);
        songRepository.save(song);
        log.info("Successfully saved song in database");
        return modelMapper.map(song, SongDTO.class);
    }

    @Transactional
    public List<SongDTO> findAll() {
        log.info("Looking for songs...");
        List<Song> songs = songRepository.findAll();
        log.info(songs.isEmpty() ? "The list is empty" : "List of songs found");
        return songs
                .stream()
                .map(song -> modelMapper.map(song, SongDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public SongDTO findById(Long id)  {
        log.info("Looking for song by id: " + id);
        Song song = songRepository.findById(id)
                .orElseThrow(()-> new SongNotFound("Song not found by id: " + id));

        return modelMapper.map(song, SongDTO.class);
    }

    @Transactional
    public void deleteSong(Long id) {
        log.info("Looking for song to delete by id: " + id);
        Optional<Song> song = songRepository.findById(id);
        if (!song.isPresent()){
            log.warn("The song was not found by the id");
            throw new SongNotFound("Cannot delete song by id: " + id);
        }
        songRepository.deleteById(id);
        log.info("Song successfully deleted");
    }

    @Transactional
    public SongDTO updateSong(Long id, SongDTO songDTO){
        log.info("Looking for song to update by id: " + id);
        Song songOld = songRepository.findById(id)
                .orElseThrow(()-> new SongNotFound("Song not found by id: " + id));
        songOld.setName(songDTO.getName());
        songOld.setAlbum(songDTO.getAlbum());
        songOld.setIdArtist(songDTO.getIdArtist());
        songRepository.save(songOld);
        log.info("Song successfully updated");
        return modelMapper.map(songOld, SongDTO.class);
    }

    public List<SongDTO> findSongByIdArtist(Long id){
        List<Song> songs =  songRepository.findByIdArtist(id);
        log.info(songs.isEmpty() ? "The list is empty" : "List of songs found");
        return songs.stream()
                .map(song ->  modelMapper.map(song, SongDTO.class))
                .collect(Collectors.toList());
    }
}
