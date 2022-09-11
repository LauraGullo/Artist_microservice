package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtistDTO {
    private Long id;

    @NotEmpty(message = "The name of the artist is required, please enter one")
    @Size(min = 4, max = 30)
    private String name;
    private LocalDate birthday;
}
