package com.example.sakiladb.controllers;


import com.example.sakiladb.entities.Category;
import com.example.sakiladb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@CrossOrigin
@RequestMapping("/categories")
@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/getAll")
    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Category getCatById(@PathVariable Short id){

        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No such category"));

    }

}
