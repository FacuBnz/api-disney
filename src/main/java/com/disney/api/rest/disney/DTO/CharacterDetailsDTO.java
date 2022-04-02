package com.disney.api.rest.disney.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CharacterDetailsDTO {
    private String name;
    private String image;
    private Integer age;
    private Double weight;
    private String history;
    private List<MovieDTO> movies;
}
