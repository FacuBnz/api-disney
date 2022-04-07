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
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setImage(p.getImage());
        dto.setWeight(p.getWeight());
        dto.setAge(p.getAge());
        dto.setHistory(p.getHistory());

        List<MovieDTO> movies = new ArrayList<>();

        for(Movie m : p.getMovies()){
            MovieDTO mov = new MovieDTO();
            mov.setTitle(m.getTitle());
            mov.setImage(m.getImage());
            mov.setCreated_at(m.getCreatedAt());

            movies.add(mov);
        }
        dto.setMovies(movies);

        return dto;

    }

    public static List<MovieDTO> convertToMovieDTO(List<Movie> movies){
        List<MovieDTO> dtos = new ArrayList<>();

        for (Movie m : movies) {
            MovieDTO mdto = new MovieDTO();
            mdto.setTitle(m.getTitle());
            mdto.setImage(m.getImage());
            mdto.setCreated_at(m.getCreatedAt());

            dtos.add(mdto);
        }
        return dtos;
    }

    public static MovieDetailsDTO convertToMovieDTO(Movie m){
        MovieDetailsDTO mdto = new MovieDetailsDTO();
        mdto.setTitle(m.getTitle());
        mdto.setImage(m.getImage());
        mdto.setCreated_at(m.getCreatedAt());
        mdto.setCalification(m.getCalification());
        mdto.setCharacters(convertToCharacterDTO(m.getCharacters()));
        mdto.setGenre(m.getGenre().getName());
        return mdto;
    }

}

