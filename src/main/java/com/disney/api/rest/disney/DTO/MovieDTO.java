package com.disney.api.rest.disney.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class MovieDTO {
    private String title;
    private String image;
    private LocalDate created_at;
}
