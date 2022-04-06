package com.disney.api.rest.disney.DTO;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class MovieDetailsDTO {
    private String title;
    private String image;
    private LocalDate created_at;
    private Integer calification;
    private List<CharacterDTO> characters;
    private String genre;
}
