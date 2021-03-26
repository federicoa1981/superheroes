package com.sistemas.superheroes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Superhero {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer speed;
    private Integer strength;

}
