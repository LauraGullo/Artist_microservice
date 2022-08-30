package com.example.demo.service;

import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ArtistServiceTest {
    @Mock
    ArtistRepository artistRepository;

    @InjectMocks
    ArtistService artistService;

    @Test
    void createArtist() {
        Artist newArtist = new Artist(null, "Maria" , LocalDate.now());
        Artist createdArtist = new Artist(2L, "Martin" , LocalDate.now());
        when(artistRepository.save(newArtist)).thenReturn(createdArtist);
        assertNotNull(artistService.createArtist(newArtist));

    }

    @Test
    void findAll() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist(1L,"Maria", LocalDate.of(1986,01,01)));
        artistList.add(new Artist(2L,"Martin", LocalDate.of(1980,12,12)));
        when(artistRepository.findAll()).thenReturn(artistList);
        assertFalse(artistService.findAll().isEmpty());

    }

    @Test
    void findById() {
        Artist artist=new Artist(1L,"Martin", LocalDate.of(1980,12,12));
        when(artistRepository.findById(anyLong())).thenReturn(of(artist));
        assertNotNull(artistService.findById(1L));

    }

    @Test
    void deleteArtist() {
        Artist artist=new Artist(1L,"Martin", LocalDate.of(1980,12,12));
        doNothing().when(artistRepository).deleteById(artist.getId());
        artistService.deleteArtist(artist.getId());
        verify(artistRepository).deleteById(1L);

    }

    @Test
    void update() {
        Artist artist=new Artist(1L,"Martin", LocalDate.of(1980,12,12));
        when(artistRepository.save(artist)).thenReturn(artist);
        when(artistRepository.findById(1L)).thenReturn(ofNullable(artist));
        Artist updateArtist = artistService.update(1L,artist);
        assertNotNull(updateArtist);
        assertNotNull(artistService.update(1L, artist));

    }
}