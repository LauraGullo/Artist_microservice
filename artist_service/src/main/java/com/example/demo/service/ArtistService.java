package com.example.demo.service;

import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }
    @Transactional
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
    @Transactional
    public Optional<Artist> findById(Long id)  {
        return artistRepository.findById(id);
    }

    @Transactional
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    @Transactional
    public Artist update(Long id, Artist artist) {
        Optional<Artist> artistOld = artistRepository.findById(id);
        Artist artistNew= artistOld.get();
        return artistNew = artistRepository.save(artist);
    }
}
