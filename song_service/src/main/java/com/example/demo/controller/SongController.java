package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping(value = "/create")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Song song1 = songService.createSong(song);
        return new ResponseEntity<Song>(song1, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Song>> findAll() {
        return ResponseEntity.ok(songService.findAll());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id") Long id) {
        Optional<Song> song = songService.findById(id);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {

        songService.deleteSong(id);
        return ResponseEntity.ok("song delete");
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Song song)  {

        songService.update(id, song);

    }

}
