package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Person;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Customer extends Person {
    public Customer(String name, String lastname, String username, String email, String password, ExpertStatus expertStatus) {
        super(name, lastname, username, email, password);
    }

    protected Customer() {
    }


}
