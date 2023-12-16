package com.example.finalprojectbootcamp.core.entities;


import com.example.finalprojectbootcamp.core.base.User;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue(value = "ADMIN")
public class Admin  extends User {
    public Admin(String name, String lastname, String username, String email, String password) {
        super(name, lastname, username, email, password);
    }


    protected Admin() {
    }
}
