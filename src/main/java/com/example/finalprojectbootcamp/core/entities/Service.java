package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Auditing;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Service extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    private String name ;

    public Service(String name) {
        this.name = name;
    }

    protected Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany
    @JoinColumn(name = "service_fk" , referencedColumnName = "id")
    List<SubService> subServices = new ArrayList<>() ;


    public List<SubService> getSubServices() {
        return Collections.unmodifiableList(subServices);
    }

    public Service setSubServices(SubService subService) {
        subServices.add(subService) ;
        return this ;
    }
}
