package com.example.demo.repository;

import com.example.demo.entity.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class SongRepositoryTest {

    @Autowired
    SongRepository songRepository;

    @Test
    void findById(){
        assertTrue(songRepository.findById(1L).isPresent());
        assertNotNull(songRepository.findById(1L));
    }

    @Test
    void findAll(){
        List<Song> songList = songRepository.findAll();
        assertFalse(songList.isEmpty());
    }

    @Test
    void create(){
        Song song = songRepository.save(new Song(1L, "Cancion01", "Album01", 2L));
        assertTrue(song.getId()>0);
        assertEquals("Album01",song.getAlbum());
    }

    @Test
    public void delete(){
        Song song = songRepository.save(new Song(1L, "Cancion01", "Album01", 2L));
        songRepository.delete(song);
        assertFalse(songRepository.findById(1L).isPresent());
    }

}