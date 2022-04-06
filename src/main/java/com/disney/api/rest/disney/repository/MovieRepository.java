package com.disney.api.rest.disney.repository;

import com.disney.api.rest.disney.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findMoviesByTitleContaining(String title);
    List<Movie> findMoviesByTitleContainingOrderByCreatedAtAsc(String title);
    List<Movie> findMoviesByTitleContainingOrderByCreatedAtDesc(String title);
    List<Movie> findMoviesByTitleContainingAndGenreId(String title, Integer genre_id);
    List<Movie> findMoviesByTitleContainingAndGenreIdOrderByCreatedAtAsc(String title, Integer genre_id);
    List<Movie> findMoviesByTitleContainingAndGenreIdOrderByCreatedAtDesc(String title, Integer idGenre);

}
