package com.disney.api.rest.disney.DTO;

import com.disney.api.rest.disney.entity.Character;
import com.disney.api.rest.disney.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MappperDTO {

    public static List<CharacterDTO> convertToCharacterDTO(List<Character> pers){
        List<CharacterDTO> dtos = new ArrayList<>();

        for(Character charactr: pers){
            CharacterDTO dto = new CharacterDTO();
            dto.setName(charactr.getName());
            dto.setImage(charactr.getImage());
            dtos.add(dto);
        }
        return dtos;
    }

    public static CharacterDetailsDTO convertToCharacterDTO(Character p){
        CharacterDetailsDTO dto = new CharacterDetailsDTO();
        dto.setName(p.getName());
        dto.setImage(p.getImage());
        dto.setWeight(p.getWeight());
        dto.setAge(p.getAge());
        dto.setHistory(p.getHistory());

        List<MovieDTO> movies = new ArrayList<>();

        for(Movie m : p.getMovies()){
            MovieDTO mov = new MovieDTO();
            System.out.println(m.getTitle());
            mov.setTitle(m.getTitle());
            mov.setImage(m.getImage());
            mov.setCreated_at(m.getCreated_at());

            movies.add(mov);
        }
        dto.setMovies(movies);

        return dto;

    }
}

