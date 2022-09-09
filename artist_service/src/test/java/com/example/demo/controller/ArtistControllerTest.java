package com.example.demo.controller;

import com.example.demo.entity.Artist;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.service.ArtistService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class ArtistControllerTest {
    @MockBean
    ArtistService artistService;

    @MockBean
    ArtistRepository artistRepository;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void createArtist() throws Exception {
        Artist newArtist = new Artist(null, "Maria", LocalDate.of(1986,11,12));
        Artist createdArtist = new Artist(1L, "Maria", LocalDate.of(1986,11,12));
        when(artistService.createArtist(any())).thenReturn(createdArtist);
        mockMvc.perform(MockMvcRequestBuilders.post("/artist/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newArtist)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.name").value("Maria"));
    }

    @Test
    void findAll() throws Exception {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist(1L,"Laura", LocalDate.of(1986,11,12)));
        artistList.add(new Artist(2L,"Martin", LocalDate.of(1982,10,11)));
        when(artistService.findAll()).thenReturn(artistList);
        mockMvc.perform(MockMvcRequestBuilders.get("/artist/getAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$[0].name").value("Laura"))
                .andExpect(jsonPath("$[1].name").value("Martin"));
    }



    @Test
    void getOneById() throws Exception {
        Artist artist = new Artist(1L, "Maria", LocalDate.of(1986,11,12));
        when(artistService.findById(1L)).thenReturn(artist);
        mockMvc.perform(MockMvcRequestBuilders.get("/artist/getOne/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.name").value("Maria"));
    }

    @Test
    void deleteArtist() throws Exception {
        doNothing().when(artistService).deleteArtist(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/artist/delete/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update() throws Exception {
        Artist newArtist = new Artist(1L, "Laura", LocalDate.of(1986,11,12));
        Artist updateArtist = new Artist(1L, "Martin", LocalDate.of(1982,10,12));
        when(artistService.updateArtist(1L, newArtist)).thenReturn(updateArtist);
        mockMvc.perform(MockMvcRequestBuilders.put("/artist/update/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateArtist)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}