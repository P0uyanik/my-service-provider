package com.example.finalprojectbootcamp.core.entities;


import com.example.finalprojectbootcamp.core.base.Person;
import jakarta.persistence.Entity;


@Entity
public class Admin  extends Person {
    public Admin(String name, String lastname, String username, String email, String password) {
        super(name, lastname, username, email, password);
    }
    protected Admin() {
    }
}
