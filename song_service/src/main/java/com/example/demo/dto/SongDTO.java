package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SongDTO {
    private Long id;

    @NotEmpty(message = "The name of the song is required, please enter one")
    @Size(min = 4, max = 30)
    private String name;

    @NotEmpty(message = "The album of the song is required, please enter one")
    @Size(min = 4, max = 30)
    private String album;

    @NotNull(message = "The artist id is mandatory to identify who owns the song, please enter one")
    private Long idArtist;

}
