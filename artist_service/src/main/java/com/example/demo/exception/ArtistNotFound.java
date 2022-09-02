package com.example.demo.exception;

public class ArtistNotFound extends RuntimeException {

    public ArtistNotFound(String message) {
        super(message);
    }
}
