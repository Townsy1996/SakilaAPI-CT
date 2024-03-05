package com.example.sakiladb.controllers;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.input.FilmInput;
import com.example.sakiladb.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequestMapping("/film")
@RestController
public class FilmController {

    @Autowired
    FilmRepository filmRepository;

    @GetMapping("/getAll")
    public List <Film> listFilms(){
        return filmRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Film getFilmById (@PathVariable Short id){
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No such Film"));
    }

    @GetMapping("/getByActorId/{id}")
    public Iterable<Film> getFilmsByActorId(@PathVariable("id") Short actorId) {
        return filmRepository.findByActorId(actorId);
    }


    @GetMapping("/getByCatName/{catName}")
    public Iterable<Film> getFilmsByCatName(@PathVariable("catName") String catName){
        return filmRepository.findByCategoryName(catName);
    }


    @PostMapping("/create")
    public Film createFilm(@RequestBody FilmInput data){

        final var film = new Film();
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());
        film.setRunTime(data.getRunTime());
        film.setRating(data.getRating());
        return filmRepository.save(film);
    }

    @GetMapping("/getByRuntimeRange")
    public List<Film> getFilmsByRuntimeRange(@RequestParam(required = false) Integer minRuntime,
                                             @RequestParam(required = false) Integer maxRuntime) {
        List<Film> films = filmRepository.findAll();

        if (minRuntime != null && maxRuntime != null) {
            films = films.stream()
                    .filter(film -> film.getRunTime() >= minRuntime && film.getRunTime() <= maxRuntime)
                    .collect(Collectors.toList());
        }

        return films;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFilmById(@PathVariable("id") Short filmId){
        filmRepository.deleteById(filmId);
    }

}
