package com.mindata.superheroes.controller;

import com.mindata.superheroes.exception.EntityNotFoundException;
import com.mindata.superheroes.model.Superhero;
import com.mindata.superheroes.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    @Autowired
    private SuperheroService superheroService;


    @GetMapping("/{id}")
    public Superhero get(@PathParam("id") Long id){
        return superheroService.getById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @GetMapping
    public Iterable<Superhero> searchAll(){
        return superheroService.getAll();
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody Superhero superhero){
        return new ResponseEntity(superheroService.create(superhero), HttpStatus.CREATED); }

    @PutMapping
    public void update(@RequestBody Superhero superhero){
         superheroService.update(superhero);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathParam("id") Long id){
        superheroService.delete(id);
    }



}
