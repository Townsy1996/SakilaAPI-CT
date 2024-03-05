package com.example.sakiladb.input;


import lombok.Data;

@Data
public class FilmInput {

    private String title;
    private String description;
    private Integer releaseYear;
    private Short runTime;
    private String rating;

}
