package com.mindata.superheroes.service;

import com.mindata.superheroes.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperheroService {
    @Autowired
    private SuperheroRepository repository;
    

}
