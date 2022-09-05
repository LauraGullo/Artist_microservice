package com.example.demo.service;

import com.example.demo.entity.Artist;
import com.example.demo.exception.ArtistNotFound;
import com.example.demo.feignclients.SongFeignClient;
import com.example.demo.model.Song;
import com.example.demo.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final SongFeignClient songFeignClient;

    @Transactional
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Transactional
    public Artist findById(Long id)  {
        return artistRepository.findById(id)
                .orElseThrow(()->new ArtistNotFound("Song not found by id: " + id));
    }

    @Transactional
    public void deleteArtist(Long id) {
        //Artist artist = this.findById(id);
        artistRepository.deleteById(this.findById(id).getId());
    }

    @Transactional
    public Artist updateArtist(Long id, Artist artist) {
        Artist artistOld = artistRepository.findById(id)
                .orElseThrow(()-> new ArtistNotFound("Song not found by id: " + id));;
        artistOld.setName(artist.getName());
        artistOld.setBirthday(artist.getBirthday());
        return artistRepository.save(artistOld);
    }

    public Song saveSong(Song song){
        return songFeignClient.createSong(song);
    }

    public List<Song> findSongsByArtist(Long id){
        return songFeignClient.findSongByIdArtist(id);
    }
}
