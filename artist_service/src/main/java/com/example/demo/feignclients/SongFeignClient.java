package com.example.demo.feignclients;

import com.example.demo.model.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "song-service", path = "/song")
public interface SongFeignClient {

    @PostMapping(value = "/create")
    Song createSong(@RequestBody Song song);

    @GetMapping("/byArtist/{id}")
    List<Song> findSongByIdArtist(@PathVariable Long id);
}
