package com.example.demo.controller;

import com.example.demo.entity.Artist;
import com.example.demo.model.Song;
import com.example.demo.service.ArtistService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PostMapping("/create")
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist artist1 = artistService.createArtist(artist);
        return new ResponseEntity<>(artist1, HttpStatus.CREATED);
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<Artist>> findAll() {

        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(artistService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.ok("artist delete");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Artist artist)  {
        return new ResponseEntity<>(artistService.updateArtist(id, artist), HttpStatus.NO_CONTENT);
    }
    @PostMapping(value = "/createSong")
    public ResponseEntity<Song> saveSong(@RequestBody Song song){
        return new ResponseEntity<>(artistService.saveSong(song), HttpStatus.CREATED);
    }

    @CircuitBreaker(name="songCB", fallbackMethod = "fallBackFindSong")
    @GetMapping("/getAllSongs/{id}")
    public ResponseEntity<List<Song>> findSongs(@PathVariable Long id){
        return ResponseEntity.ok(artistService.findSongsByArtist(id));
    }


    private ResponseEntity<List<Song>> fallBackFindSong(@PathVariable("id") Long id, RuntimeException e) {
        return new ResponseEntity("No se puede encontrar la canción", HttpStatus.OK);
    }



}
