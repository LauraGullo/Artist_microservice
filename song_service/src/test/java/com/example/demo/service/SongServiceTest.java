package com.example.demo.service;

import com.example.demo.model.Song;
import com.example.demo.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class SongServiceTest {
    @Mock
    SongRepository songRepository;

    @InjectMocks
    SongService songService;


    @Test
    void createSong() {
        Song newSong = new Song(1L, "Cancion01" , "Album01");
        Song songCreated = new Song(2L, "Cancion02" , "Album02");
        when(songRepository.save(newSong)).thenReturn(songCreated);
        assertNotNull(songService.createSong(newSong));
        assertTrue(songService.createSong(newSong).getId()>0);

    }

    @Test
    void findAll() {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song(1L,"Cancion01", "Album01"));
        songList.add(new Song(2L,"Cancion02", "Album02"));
        when(songRepository.findAll()).thenReturn(songList);
        assertTrue(songService.findAll().size()>0);

    }

    @Test
    void findById() {
        Song song=new Song(1L,"Cancion01", "Album01");
        when(songRepository.findById(anyLong())).thenReturn(of(song));
        assertNotNull(songService.findById(1L));
        assertEquals("Cancion01", songService.findById(1L).get().getName());
        assertTrue(songService.findById(1L).isPresent());
    }

    @Test
    void deleteSong() {
        Song song=new Song(1L,"Cancion01", "Album01");
        when (songRepository.findById(1L)).thenReturn(Optional.empty());
        doNothing().when(songRepository).delete(song);
        assertFalse(songService.findById(song.getId()).isPresent());
    }

    @Test
    void update() {
        Song song=new Song(1L,"Cancion01", "Album01");
        when(songRepository.save(song)).thenReturn(song);
        when(songRepository.findById(1L)).thenReturn(Optional.ofNullable(song));
        Song song1 = songService.update(1L,song);
        assertNotNull(song1);
        assertNotNull(song1.getId());

    }
}