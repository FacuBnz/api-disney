package com.disney.api.rest.disney.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "characters")
@Setter
@Getter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40)
    @NotBlank(message = "The name cannot be null and void")
    private String name;

    private String image;

    @Min(value = 0,message = "The minimum age is 0")
    private Integer age;

    @Min(value = 0,message = "The minimum weight es 0.0")
    private Double weight;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "The history cannot be null and void")
    private String history;

    @ManyToMany(mappedBy = "characters")
    @JsonIgnoreProperties(value="characters")
    private List<Movie> movies;

}
