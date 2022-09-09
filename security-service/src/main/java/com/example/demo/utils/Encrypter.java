package com.example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encrypter {
    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }
}
