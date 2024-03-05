package com.example.sakiladb.repositories;

import com.example.sakiladb.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Short> {
    @Query("SELECT film FROM Film film JOIN film.actors actor WHERE actor.id = :id")
    Iterable<Film> findByActorId(@Param("id") Short id);

    @Query("SELECT film FROM Film film JOIN film.categorySet cat WHERE cat.name = :catName")
    Iterable<Film> findByCategoryName(@Param("catName") String catName);
}
