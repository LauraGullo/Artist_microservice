package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SongDTO {
    private Long id;
    private String name;
    private String album;
    private Long idArtist;

}
