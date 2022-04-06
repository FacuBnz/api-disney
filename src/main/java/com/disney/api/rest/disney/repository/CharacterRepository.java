package com.disney.api.rest.disney.repository;

import com.disney.api.rest.disney.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    List<Character> findCharacterByName(String name);
    List<Character> findCharacterByNameAndAge(String name, Integer age);
    List<Character> findCharacterByNameAndWeight(String name, Double weight);

    @Query("SELECT c FROM Character c JOIN c.movies m WHERE c.name=?1 and m.id=?2")
    List<Character> findCharacterByNameAndMovies(String name, Integer id);

    @Query("SELECT c FROM Character c JOIN c.movies m WHERE c.name=?1 AND c.age=?2 AND m.id=?3")
    List<Character> findCharacterByNameAndAgeAndMovies(String name, Integer age, Integer id);

    @Query("SELECT c FROM Character c JOIN FETCH c.movies m WHERE c.name=?1 and c.weight=?2 and m.id=?3")
    List<Character> findCharacterByNameAndWeightAndMovies(String name, Double weight, Integer id);

    @Query("SELECT c FROM Character c JOIN FETCH c.movies m WHERE " +
            "c.name=?1 and " +
            "c.age=?2 and " +
            "c.weight=?3 and " +
            "m.id=?4")
    List<Character> findCharacterByNameAndAgeAndWeightAndMovies(String name, Integer age, Double weight, Integer id);
}
