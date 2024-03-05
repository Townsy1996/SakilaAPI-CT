package com.example.sakiladb.repositories;

import com.example.sakiladb.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Query("SELECT actor FROM Actor actor INNER JOIN actor.filmsActedIn films WHERE films.filmId = :filmId")
    Iterable<Actor> findByFilmId(@Param("filmId") int filmId);

    @Query("SELECT actor FROM Actor actor INNER JOIN actor.filmsActedIn films WHERE films.title LIKE CONCAT('%', :filmTitle, '%')")
    Iterable<Actor> findByFilmTitle(@Param("filmTitle") String filmTitle);
}
