package com.disney.api.rest.disney.controller;

import com.disney.api.rest.disney.config.Response;
import com.disney.api.rest.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<?> getCharacters(@RequestParam() Map<String, String> params){
        try{
            if(params.isEmpty()) return ResponseEntity.ok(characterService.getAll());
            return ResponseEntity.ok(characterService.getSearch(params));
        }catch (Exception e){
            Response res = new Response();
            res.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCharacterDetails(@PathVariable String id){
        try{
            return ResponseEntity.ok(characterService.findById(id));
        }catch (Exception e){
            Response res = new Response();
            res.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }


}
