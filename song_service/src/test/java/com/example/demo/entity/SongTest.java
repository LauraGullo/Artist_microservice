package com.example.demo.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    Song song;
    @BeforeEach
    void setUp() {

        song = new Song(1L, "Cancion01", "album01", 2L);
    }

    @Test
    void getId() {
        assertEquals(1L, song.getId());
        assertEquals(1,song.getId());
        assertTrue(song.getId()>0);
    }

    @Test
    void getName() {
        assertEquals("Cancion01",song.getName());

    }

    @Test
    void getAlbum() {
        assertEquals("album01", song.getAlbum());

    }

    @Test
    void setId() {
        song.setId(Long.parseLong("5"));
        assertEquals(5, song.getId());
        assertTrue(song.getId()>0);
    }

    @Test
    void setName() {
        song.setName("cancion01");
        assertEquals("cancion01", song.getName());
        assertNotNull(song.getName());
    }

    @Test
    void setAlbum() {
        song.setAlbum("album01");
        assertEquals("album01", song.getAlbum());

    }

    @Test
    void testToString() {
        assertEquals("Song(id=1, name=Cancion01, album=album01, idArtist=2)", song.toString());

    }
}