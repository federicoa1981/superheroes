package com.mindata.superheroes.controller;

import com.mindata.superheroes.model.Superhero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    @GetMapping("/{id}")
    public Superhero get(@PathParam("id") Long id){
        Superhero s =new Superhero();
        s.setId(1L);
        s.setSpeed(20);
        s.setName("SpiderMan");
        s.setStrength(23);
        return s;
    }


}
