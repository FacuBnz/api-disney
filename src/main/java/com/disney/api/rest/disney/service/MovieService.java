package com.disney.api.rest.disney.service;

import com.disney.api.rest.disney.DTO.MappperDTO;
import com.disney.api.rest.disney.DTO.MovieDTO;
import com.disney.api.rest.disney.DTO.MovieDetailsDTO;
import com.disney.api.rest.disney.entity.Movie;
import com.disney.api.rest.disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AmazonService amazonService;

    @Transactional
    public List<MovieDTO> getAll(){
        List<Movie> movies = movieRepository.findAll();
        return MappperDTO.convertToMovieDTO(movies);
    }

    @Transactional
    public MovieDetailsDTO findById(String id) throws Exception {
        Movie m = movieRepository.findById(Integer.valueOf(id)).orElseThrow(() ->
                new Exception(String.format("Movie not found", id)));

        return MappperDTO.convertToMovieDTO(m);
    }

    @Transactional
    public String create(Movie m, MultipartFile file){
        m.setImage(amazonService.uploadFile(file));
        movieRepository.save(m);
        return "Movie created successfully";
    }

    @Transactional
    public String update(Movie m, MultipartFile file) throws Exception {
        Movie movie = movieRepository.findById(m.getId()).orElseThrow(() -> new Exception(String.format("The movie not found", m.getId())));

        movie.setTitle(m.getTitle());
        movie.setCalification(m.getCalification());
        movie.setCreatedAt(m.getCreatedAt());
        movie.setGenre(m.getGenre());
        movie.setCharacters(m.getCharacters());

        if(file.isEmpty()){
            movie.setImage(m.getImage());
        }else{
            movie.setImage(amazonService.uploadFile(file));
        }

        movieRepository.save(movie);
        return "Movie edited successfully";
    }

    @Transactional
    public String delete(Integer id) throws Exception {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception(String.format("The movie not found", id)));
        String image = movie.getImage();
        movieRepository.deleteById(id);
        if(! image.isEmpty()) amazonService.deleteFileFromS3Bucket(image);
        return "Movie eliminated successfully";
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> getSearch(Map<String, String> params) throws Exception {

        String name = null;
        Integer idGenre = null;
        String order = null;
        List<Movie> ms;

        for (Map.Entry<String, String> param : params.entrySet()) {
            if(param.getKey().equalsIgnoreCase("name")) name = param.getValue();
            if(param.getKey().equalsIgnoreCase("order")) order = param.getValue();
            if(param.getKey().equalsIgnoreCase("genre")) idGenre = Integer.valueOf(param.getValue());
        }

        if(name != null){
            if(idGenre != null){
                if(order != null){
                    //title, idGenre, order
                    if(order.equalsIgnoreCase("asc")){
                        ms = movieRepository.findMoviesByTitleContainingAndGenreIdOrderByCreatedAtAsc(name,idGenre);
                        return MappperDTO.convertToMovieDTO(ms);
                    }

                    if(order.equalsIgnoreCase("desc")){
                        ms = movieRepository.findMoviesByTitleContainingAndGenreIdOrderByCreatedAtDesc(name, idGenre);
                        return MappperDTO.convertToMovieDTO(ms);
                    }
                }
                //title, idGenre
                ms = movieRepository.findMoviesByTitleContainingAndGenreId(name, idGenre);
                return MappperDTO.convertToMovieDTO(ms);
            }else if(order != null){
                //title, order
                if(order.equalsIgnoreCase("asc")){
                    ms = movieRepository.findMoviesByTitleContainingOrderByCreatedAtAsc(name);
                    return MappperDTO.convertToMovieDTO(ms);
                }
                if(order.equalsIgnoreCase("desc")){
                    ms = movieRepository.findMoviesByTitleContainingOrderByCreatedAtDesc(name);
                    return MappperDTO.convertToMovieDTO(ms);
                }
            }
            //title
            ms = movieRepository.findMoviesByTitleContaining(name);
            return MappperDTO.convertToMovieDTO(ms);
        }
        throw new Exception("Invalid parameters");
    }
}
