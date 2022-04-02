package com.disney.api.rest.disney.DTO;

import com.disney.api.rest.disney.entity.Character;

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
}

