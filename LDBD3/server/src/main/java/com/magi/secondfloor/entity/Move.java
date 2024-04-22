package com.magi.secondfloor.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Move {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String type;
    
    @Getter
    @Setter
    private String category;
    
    @Getter
    @Setter
    private Integer power;
    
    @Getter
    @Setter
    private Integer accuracy;
    
    @Getter
    @Setter
    private Integer pp;
    
    @Getter
    @Setter
    private String description;

    Move() {}

    Move(
        String name,
        String type,
        String category,
        Integer power,
        Integer accuracy,
        Integer pp,
        String description
    ) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.description = description;
    }
}