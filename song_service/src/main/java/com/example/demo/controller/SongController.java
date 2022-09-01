package com.example.demo.controller;

import com.example.demo.entity.Song;
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
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {

        songService.deleteSong(id);
        return ResponseEntity.ok("song delete");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Song song)  {
        return new ResponseEntity<>(songService.updateSong(id, song), HttpStatus.NO_CONTENT);

    }

    @GetMapping("/byArtist/{id}")
    public ResponseEntity<List<Song>> findSongByIdArtist(@PathVariable Long id){
        return ResponseEntity.ok(songService.findSongByIdArtist(id));
    }

  @GetMapping("/datos")
  public ResponseEntity<?> getDatos(){
      return   ResponseEntity.ok(songService.get());
  }
}
