package com.example.sakiladb.controllers;
import com.example.sakiladb.entities.Category;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("/cat")
@RestController
public class CategoryController {
    private final CategoryRepository catRepository;
    private static final String notFoundResponse = "Category not found";

    @Autowired
    public CategoryController(CategoryRepository catRepository){
        this.catRepository = catRepository;
    }

    @GetMapping("/get/{id}")
    public Category getCatById(@PathVariable("id") int catId){
        return catRepository.findById(catId).orElseThrow(() -> new ResourceAccessException(notFoundResponse));
    }

    @GetMapping("/getByName/{name}")
    public Category getCatByName(@PathVariable("name") String name){
        return catRepository.findByName(name).orElseThrow(() -> new ResourceAccessException(notFoundResponse));
    }

    @GetMapping("/getAll")
    public Iterable<Category> getAllCats(){
        return catRepository.findAll();
    }

    @GetMapping("/getFilmsByCat/{id}")
    public List<Film> getFilmsByCat(@PathVariable("id") int catId){
        Category cat = catRepository.findById(catId).orElseThrow(() -> new ResourceAccessException(notFoundResponse));
        return new ArrayList<>(cat.getFilmSet());
    }

    @PutMapping("/update/{id}")
    public Category updateCatById(
            @PathVariable("id") int catId,
            @RequestBody Category updatedCategory){
        Category cat = catRepository.findById(catId).orElseThrow(() -> new ResourceAccessException(notFoundResponse));
        cat.setName(updatedCategory.getName());
        return catRepository.save(cat);
    }

    @PostMapping("/create")
    public Category createCat(@RequestBody Category newCat)
    {
        if(catRepository.findByName(newCat.getName()).isEmpty()){
            return catRepository.save(newCat);
        }
        else {
            throw new IllegalArgumentException("Film already exists");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCatById(@PathVariable("id") int catId){
        catRepository.deleteById(catId);
    }
}
