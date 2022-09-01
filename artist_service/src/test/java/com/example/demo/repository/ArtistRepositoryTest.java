package com.example.demo.repository;

import com.example.demo.entity.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArtistRepositoryTest {

    @Autowired
    ArtistRepository artistRepository;

    @Test
    void findById(){
        assertTrue(artistRepository.findById(1L).isPresent());
    }

    @Test
    void findAll(){
        List<Artist> artistList = artistRepository.findAll();
        assertFalse(artistList.isEmpty());
        assertEquals(2, artistList.size());
    }

    @Test
    void create(){
        Artist artist1 = artistRepository.save(new Artist(1L, "laura", LocalDate.of(1986,11,12)));
        assertTrue(artist1.getId()>0);
    }

    @Test
    public void delete(){
        Artist artist = artistRepository.save(new Artist(1L, "laura", LocalDate.of(1986,11,12)));
        artistRepository.delete(artist);
        assertFalse(artistRepository.findById(1L).isPresent());
    }

}