package com.mindata.superheroes.controller;

import com.mindata.superheroes.model.Superhero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {
    private Superhero s1;
    private Superhero s2;
    private Superhero s3;

    public SuperHeroController(){
        s1 = new Superhero();
        s1.setId(1L);
        s1.setSpeed(20);
        s1.setName("SpiderMan");
        s1.setStrength(23);
        s2 = new Superhero();
        s2.setId(2L);
        s2.setSpeed(10);
        s2.setName("Hulk");
        s2.setStrength(500);

        s3 = new Superhero();
        s3.setId(3L);
        s3.setSpeed(1000);
        s3.setName("Superman");
        s3.setStrength(200);
    }

    @GetMapping("/{id}")
    public Superhero get(@PathParam("id") Long id){
        return s1;
    }

    @GetMapping
    public List<Superhero> searchAll(){
        return Arrays.asList(s1, s2, s3);
    }


}
