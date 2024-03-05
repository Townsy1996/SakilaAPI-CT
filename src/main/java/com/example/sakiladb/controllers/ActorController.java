package com.example.sakiladb.controllers;

import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.input.ActorInput;
import com.example.sakiladb.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@CrossOrigin
@RequestMapping("/actor")
@RestController
public class ActorController {

    @Autowired
    ActorRepository actorRepository;

    @GetMapping("/getAll")
    public List<Actor> listActors(){

        return actorRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Actor getActorById (@PathVariable Short id){
        return actorRepository.findById(id)
                .orElseThrow( () -> new ResourceAccessException("No such actor"));
    }

    @PostMapping("/create")
    public Actor createActor(@RequestBody ActorInput data){

        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        return actorRepository.save(actor);
    }

    @PutMapping("/update/{id}")
    public Actor updateActorById(
            @PathVariable("id") Short actorId,
            @RequestBody Actor updatedActor
    ){
        Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new ResourceAccessException("Actor not found"));

        actor.setFirstName(updatedActor.getFirstName());
        actor.setLastName(updatedActor.getLastName());
        actor.setFilmsActedIn(updatedActor.getFilmsActedIn());

        return actorRepository.save(actor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActorById(@PathVariable("id") Short actorId){
        actorRepository.deleteById(actorId);
    }

}
