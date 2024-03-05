package com.example.sakiladb.repositories;

import com.example.sakiladb.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Short> {
}
