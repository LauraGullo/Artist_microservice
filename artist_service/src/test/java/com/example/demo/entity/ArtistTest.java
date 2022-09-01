package com.example.demo.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {

    Artist artist;
    @BeforeEach
    void setUp() {

        artist = new Artist(1L, "Laura", LocalDate.of(1986,4,12));
    }



    @Test
    void getId() {
        assertTrue(artist.getId()== 1);
        assertEquals(1,artist.getId());

    }

    @Test
    void getName() {
        assertEquals("Laura",artist.getName());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(1986,04,12),artist.getBirthday());
    }

    @Test
    void setId() {
        artist.setId(Long.parseLong("6"));
        assertEquals(6,artist.getId());

    }

    @Test
    void setName() {
        artist.setName("Martin");
        assertEquals("Martin",artist.getName());

    }

    @Test
    void setBirthday() {
        artist.setBirthday(LocalDate.now());
        assertEquals(LocalDate.now(),artist.getBirthday());

    }

     @Test
    void testToString() {
         assertEquals("Artist(id=1, name=Laura, birthday=1986-04-12)",artist.toString());

     }
}