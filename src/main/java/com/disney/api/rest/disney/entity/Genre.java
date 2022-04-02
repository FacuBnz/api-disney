package com.disney.api.rest.disney.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
@Setter
@Getter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;
    private String image;

    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "genre")
    private List<Movie> movies;
}
