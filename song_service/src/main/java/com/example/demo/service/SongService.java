package com.example.demo.service;

import com.example.demo.model.Song;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Song update(Long id, Song song) {
        Optional<Song> songOld = songRepository.findById(id);
        Song songNew= songOld.get();
        return songNew = songRepository.save(song);
    }

}
