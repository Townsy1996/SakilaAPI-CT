package com.example.sakiladb.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "actor")


public class Actor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "actor_id")
private Short id;
@Column(name = "first_name")
private String firstName;
@Column(name = "last_name")
private String lastName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<Film> filmsActedIn;

}
