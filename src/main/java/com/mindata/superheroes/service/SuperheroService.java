package com.mindata.superheroes.service;

import com.mindata.superheroes.exception.EntityNotFoundException;
import com.mindata.superheroes.model.Superhero;
import com.mindata.superheroes.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperheroService {
    @Autowired
    private SuperheroRepository repository;

    public Optional<Superhero> getById(Long id) {
        return repository.findById(id);
    }

    public Iterable<Superhero> getAll() {
        return repository.findAll();
    }

    public Long create(Superhero superhero) {
        return repository.save(superhero).getId();
    }

    public void update(Long id, Superhero superheroUpdate) {
        Superhero founded = repository.findById(id).orElseThrow(()-> new EntityNotFoundException(superheroUpdate.getId()));
        repository.save(set(founded, superheroUpdate));
    }

    private Superhero set(Superhero founded, Superhero superhero) {
        founded.setName(superhero.getName());
        founded.setStrength(superhero.getStrength());
        founded.setSpeed(superhero.getSpeed());
        return  founded;
    }

    public void delete(Long id) {
        Superhero founded = repository.findById(id).orElseThrow(()-> new EntityNotFoundException(id));
        repository.delete(founded);
    }
}
