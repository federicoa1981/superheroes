package com.sistemas.superheroes.service;

import com.sistemas.superheroes.exception.EntityNotFoundException;
import com.sistemas.superheroes.model.Superhero;
import com.sistemas.superheroes.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperheroService {
    @Autowired
    private SuperheroRepository repository;

    public Superhero getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Iterable<Superhero> getAll() {
        return repository.findAll();
    }

    public Iterable<Superhero> findByName(String name) {
        return repository.findByNameContains(name);
    }

    public Long create(Superhero superhero) {
        return repository.save(superhero).getId();
    }

    public void update(Long id, Superhero superheroUpdate) {
        Superhero founded = getById(id);
        repository.save(set(founded, superheroUpdate));
    }

    private Superhero set(Superhero founded, Superhero superhero) {
        founded.setName(superhero.getName());
        founded.setStrength(superhero.getStrength());
        founded.setSpeed(superhero.getSpeed());
        return  founded;
    }

    public void delete(Long id) {
        Superhero founded = getById(id);
        repository.delete(founded);
    }
}
