package com.example.demo.controller;

import com.example.demo.model.Artist;
import com.example.demo.service.ArtistService;
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

    @PostMapping(value = "/create")
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist artist1 = artistService.createArtist(artist);
        return new ResponseEntity<Artist>(artist1, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Artist>> findAll() {
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id") Long id) {
        Optional<Artist> artist = artistService.findById(id);
        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {

        artistService.deleteArtist(id);
        return ResponseEntity.ok("artist delete");
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Artist artist)  {

        artistService.update(id, artist);

    }


}
