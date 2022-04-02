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
}
