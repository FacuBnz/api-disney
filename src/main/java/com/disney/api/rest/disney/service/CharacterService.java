package com.disney.api.rest.disney.service;

import com.disney.api.rest.disney.DTO.CharacterDTO;
import com.disney.api.rest.disney.DTO.CharacterDetailsDTO;
import com.disney.api.rest.disney.DTO.MappperDTO;
import com.disney.api.rest.disney.entity.Character;
import com.disney.api.rest.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Transactional(readOnly = true)
    public List<CharacterDTO> getAll(){
        List<Character> characters = characterRepository.findAll();
        return MappperDTO.convertToCharacterDTO(characters);
    }

    @Transactional(readOnly = true)
    public CharacterDetailsDTO findById(String id) throws Exception {
        Character p = characterRepository.findById(Integer.valueOf(id)).orElseThrow(() ->
                new Exception(String.format("Character not found", id)));
        return MappperDTO.convertToCharacterDTO(p);
    }

    @Transactional(readOnly = true)
    public List<CharacterDTO> getSearch(Map<String, String> params) throws Exception {
        String name = null;
        Integer age = null;
        Integer idMovie = null;
        Double weight = null;
        List<Character> pers;

        for (Map.Entry<String, String> param : params.entrySet()) {
            if(param.getKey().equalsIgnoreCase("name")) name = param.getValue();
            if(param.getKey().equalsIgnoreCase("age")) age = Integer.parseInt(param.getValue());
            if(param.getKey().equalsIgnoreCase("idMovie")) idMovie = Integer.parseInt(param.getValue());
            if(param.getKey().equalsIgnoreCase("weight")) weight = Double.valueOf(param.getValue());
        }

        if(name != null){

            if(age != null){

                if(idMovie != null){

                    if(weight != null){
                        //name, age, idmovie, weight
                        pers = characterRepository.findCharacterByNameAndAgeAndWeightAndMovies(name, age, weight, idMovie);
                        MappperDTO.convertToCharacterDTO(pers);
                    }
                    //name, age, idmovie
                    pers = characterRepository.findCharacterByNameAndAgeAndMovies(name, age, idMovie);
                    return MappperDTO.convertToCharacterDTO(pers);
                }

                pers = characterRepository.findCharacterByNameAndAge(name, age);
                return MappperDTO.convertToCharacterDTO(pers);

            }else if(idMovie != null){

                if(weight != null){
                    //name, idMovie, weight
                    pers = characterRepository.findCharacterByNameAndWeightAndMovies(name, weight, idMovie);
                    MappperDTO.convertToCharacterDTO(pers);
                }
                pers = characterRepository.findCharacterByNameAndMovies(name, idMovie);
                return MappperDTO.convertToCharacterDTO(pers);
            }else if(weight != null){
                pers = characterRepository.findCharacterByNameAndWeight(name, weight);
                return MappperDTO.convertToCharacterDTO(pers);
            }
            pers = characterRepository.findCharacterByName(name);
            return MappperDTO.convertToCharacterDTO(pers);
        }

        throw new Exception("Invalid parameters");
    }
}
