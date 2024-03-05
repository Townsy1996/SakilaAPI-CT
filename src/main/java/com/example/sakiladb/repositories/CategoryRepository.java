package com.example.sakiladb.repositories;

import com.example.sakiladb.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT category FROM Category category WHERE category.name LIKE CONCAT('%', :name, '%')")
    Optional<Category> findByName(@Param("name") String name);
}
