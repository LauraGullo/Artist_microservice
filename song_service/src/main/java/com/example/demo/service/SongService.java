package com.example.demo.service;

import com.example.demo.entity.Song;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    @Transactional
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Transactional
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Transactional
    public Optional<Song> findById(Long id)  {
        return songRepository.findById(id);
    }

    @Transactional
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Transactional
    public Song updateSong(Long id, Song song){
        Song songOld = songRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Song not found by id: " + id));
        songOld.setName(song.getName());
        songOld.setAlbum(song.getAlbum());
        songOld.setIdArtist(song.getIdArtist());
        return songRepository.save(songOld);
    }

    public List<Song> findSongByIdArtist(Long id){
        return songRepository.findByIdArtist(id);
    }
}
