package com.example.demo.controller;

import com.example.demo.dto.ArtistDTO;
import com.example.demo.entity.Artist;
import com.example.demo.model.Song;
import com.example.demo.service.ArtistService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/artist")
@Tag(name = "Artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Operation(summary = "Create an artist", description = "Save a new artist to the database")
    @ApiResponse(responseCode = "201", description = "Artist created",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ArtistDTO.class)) })
    @PostMapping("/create")
    public ResponseEntity<ArtistDTO> createArtist(@Valid @RequestBody ArtistDTO artistDTO) {
        ArtistDTO artist = artistService.createArtist(artistDTO);
        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of artists", description = "Get a list of all artists from the database")
    @ApiResponse(responseCode = "200", description = "List of artists",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ArtistDTO.class)) })
    @GetMapping("/getAll")
    public ResponseEntity<List<ArtistDTO>> findAll() {
        return ResponseEntity.ok(artistService.findAll());
    }

    @Operation(summary = "Get an artist by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the artist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content) })
    @GetMapping("/getOne/{id}")
    public ResponseEntity<ArtistDTO> getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(artistService.findById(id));
    }

    @Operation(summary = "Delete an artist by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed the artist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content) })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.ok("Artist successfully deleted");
    }

    @Operation(summary = "Update an artist", description = "Update and save an artist by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated artist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ArtistDTO artistDTO)  {
        return new ResponseEntity<>(artistService.updateArtist(id, artistDTO), HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create a song", description = "Save a new song to the database")
    @ApiResponse(responseCode = "201", description = "The song created",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Song.class)) })
    @PostMapping(value = "/createSong")
    public ResponseEntity<Song> saveSong(@Valid @RequestBody Song song){
        return new ResponseEntity<>(artistService.saveSong(song), HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of songs by its artist id", description = "Get a list of all songs from the database")
    @ApiResponse(responseCode = "200", description = "List of songs",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Song.class)) })
    @GetMapping("/getAllSongs/{id}")
    public ResponseEntity<List<Song>> findSongs(@PathVariable Long id){
        return ResponseEntity.ok(artistService.findSongsByArtist(id));
    }

    @Operation(summary = "Get a discography by artist", description = "Get a list of all artist and their songs")
    @ApiResponse(responseCode = "200", description = "List of songs",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Song.class)) })
    @GetMapping("/discography")
    public ResponseEntity<Map<String, Object>> countOfSongsByArtist(){
        return ResponseEntity.ok(artistService.countOfSongByArtist());
    }
}
