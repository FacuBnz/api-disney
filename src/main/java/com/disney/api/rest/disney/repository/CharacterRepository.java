package com.disney.api.rest.disney.repository;

import com.disney.api.rest.disney.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
