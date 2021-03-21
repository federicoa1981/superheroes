package com.mindata.superheroes.repository;

import com.mindata.superheroes.model.Superhero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends CrudRepository<Superhero, Long> {

}
