package com.disney.api.rest.disney.controller;

import com.disney.api.rest.disney.config.Response;
import com.disney.api.rest.disney.entity.Movie;
import com.disney.api.rest.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getMovies(@RequestParam Map<String, String> params){
        try{
            if(params.isEmpty()) return ResponseEntity.ok(movieService.getAll());
            return ResponseEntity.ok(movieService.getSearch(params));
        }catch (Exception e){
            Response res = new Response();
            res.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieDetails(@PathVariable String id){
        try{
            return ResponseEntity.ok(movieService.findById(id));
        }catch (Exception e){
            Response res = new Response();
            res.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @ModelAttribute Movie m, @RequestPart(value = "file") MultipartFile file){
        try{
            String message = movieService.create(m, file);
            Response res = new Response();
            res.setMessage(message);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(res);
        }catch (Exception e){
            Response res = new Response();
            res.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Movie movie, @RequestPart(value = "file") MultipartFile file){
        try{
            String message = movieService.update(movie, file);
            Response res = new Response();
            res.setMessage(message);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(res);
        }catch (Exception e){
            Response res = new Response();
            res.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            String message = movieService.delete(id);
            Response res = new Response();
            res.setMessage(message);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            Response res = new Response();
            res.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }
}
