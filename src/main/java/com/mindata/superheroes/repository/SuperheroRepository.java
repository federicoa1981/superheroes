package com.mindata.superheroes.repository;

import com.mindata.superheroes.model.Superhero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroRepository extends CrudRepository<Superhero, Long> {
    List<Superhero> findByNameContains(String name);
}
