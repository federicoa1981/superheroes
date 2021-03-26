package com.sistemas.superheroes.controller;

import com.sistemas.superheroes.exception.EntityNotFoundException;
import com.sistemas.superheroes.model.Superhero;
import com.sistemas.superheroes.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/superheroes")
public class SuperheroController {

    @Autowired
    private SuperheroService superheroService;


    @GetMapping("/{id}")
    public Superhero get(@PathVariable Long id){
        return superheroService.getById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @GetMapping
    public Iterable<Superhero> searchAll(){
        return superheroService.getAll();
    }

    @GetMapping("/filter")
    public Iterable<Superhero> searchByName(@RequestParam("name") String name){
        return superheroService.findByName(name);
    }


    @PostMapping
    public ResponseEntity<Long> create(@RequestBody Superhero superhero){
        return new ResponseEntity(superheroService.create(superhero), HttpStatus.CREATED); }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Superhero superhero){
         superheroService.update(id, superhero);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        superheroService.delete(id);
    }



}
