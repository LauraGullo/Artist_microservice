package com.example.demo.controller;

import com.example.demo.entity.Song;
import com.example.demo.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
@Tag(name = "Songs")
public class SongController {
    @Autowired
    private SongService songService;

    @Operation(summary = "Create a song", description = "Save a new song to the database")
    @ApiResponse(responseCode = "201", description = "The song created",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Song.class)) })
    @PostMapping(value = "/create")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Song song1 = songService.createSong(song);
        return new ResponseEntity<Song>(song1, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of songs", description = "Get a list of all songs from the database")
    @ApiResponse(responseCode = "200", description = "List of songs",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Song.class)) })
    @GetMapping("/getAll")
    public ResponseEntity<List<Song>> findAll() {
        return ResponseEntity.ok(songService.findAll());
    }

    @Operation(summary = "Get a song by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the song",
                                                  content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Song.class)) }),
    @ApiResponse(responseCode = "404", description = "Song not found",
            content = @Content) })
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(songService.findById(id));
    }

    @Operation(summary = "Delete a song by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed the song",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class)) }),
            @ApiResponse(responseCode = "404", description = "Song not found",
                    content = @Content) })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok("song delete");
    }

    @Operation(summary = "Update a song", description = "Update and save a song by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated song",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class)) }),
            @ApiResponse(responseCode = "404", description = "Song not found",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Song song)  {
        return new ResponseEntity<>(songService.updateSong(id, song), HttpStatus.NO_CONTENT);

    }

    @Operation(summary = "Get a list of songs by artist", description = "Get a list of all songs by artist id")
    @ApiResponse(responseCode = "200", description = "List of songs",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Song.class)) })
    @GetMapping("/byArtist/{id}")
    public ResponseEntity<List<Song>> findSongByIdArtist(@PathVariable Long id){
        return ResponseEntity.ok(songService.findSongByIdArtist(id));
    }

}
