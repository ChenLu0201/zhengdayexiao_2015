package com.thoughtworks.model;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String name;
    @Enumerated(value = EnumType.STRING)
    public Gender gender;
    public String type;

    public Pet() {
    }

    public Pet(String name, Gender gender, String type) {
        this.name = name;
        this.gender = gender;
        this.type = type;
    }
}
