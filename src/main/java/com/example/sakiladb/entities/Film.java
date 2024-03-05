package com.example.sakiladb.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Year releaseYear;

    @Column(name = "rating")
    private String rating;

    @Column(name = "length")
    private int runTime;

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categorySet;

    @ManyToMany(mappedBy = "filmsActedIn")
    private Set<Actor> actors;

    public Film() {}

    public Film(int filmId, String title, String description, Year releaseYear, String rating, Set<Category> categorySet, Set<Actor> actors, int runTime) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categorySet = categorySet;
        this.actors = actors;
        this.runTime = runTime;
    }

}
