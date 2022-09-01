package com.example.demo.controller;

import com.example.demo.entity.Song;
import com.example.demo.repository.SongRepository;
import com.example.demo.service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class SongControllerTest {
    @MockBean
    SongService songService;

    @MockBean
    SongRepository songRepository;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
       }

    @Test
    void createSong() throws Exception{
        Song songCreated=new Song(2L,"Cancion02", "Album02", 2L);
        when(songService.createSong(any())).thenReturn(songCreated);
        mockMvc.perform(MockMvcRequestBuilders.post("/song/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(songCreated)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.album").value("Album02"));
    }

    @Test
    void findAll() throws Exception {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song(1L,"Cancion01", "Album01", 2L));
        songList.add(new Song(2L,"Cancion02", "Album02", 2L));
        when(songService.findAll()).thenReturn(songList);
        mockMvc.perform(MockMvcRequestBuilders.get("/song/getAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$[0].name").value("Cancion01"))
                .andExpect(jsonPath("$[1].album").value("Album02"));

    }

    @Test
    void getOneById() throws Exception {
        Song song = new Song(1L, "Cancion01", "Album01", 2L);
        when(songService.findById(1L)).thenReturn(Optional.of(song));
        mockMvc.perform(MockMvcRequestBuilders.get("/song/getOne/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.name").value("Cancion01"))
                .andExpect(jsonPath("$.album").value("Album01"));

    }

    @Test
    void deleteSong() throws Exception {
        doNothing().when(songService).deleteSong(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/song/delete/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update() throws Exception {
        Song newSong = new Song(1L, "Cancion01", "Album01", 2L);
        Song updateSong = new Song(2L, "Cancion02", "Album02", 2L);
        when(songService.updateSong(1L, newSong)).thenReturn(updateSong);
        mockMvc.perform(MockMvcRequestBuilders.put("/song/update/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateSong)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}