package com.example.demo.exception;

public class SongNotFound extends RuntimeException{

    public SongNotFound(String message) {
        super(message);
    }
}
