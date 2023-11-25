package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Person;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;
import jakarta.persistence.Entity;

@Entity
public class Expert extends Person {

private ExpertStatus expertStatus = ExpertStatus.NEW ;

    public Expert(String name, String lastname, String username, String email, String password , ExpertStatus expertStatus) {
        super( name, lastname ,  username,  email,  password);
        this.expertStatus = expertStatus;
    }

    protected Expert() {
        super();

    }
}
