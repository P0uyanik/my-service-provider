package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@org.springframework.stereotype.Service
public interface ServiceService {
    /*3*/ void addANewService (Service service) ;
    /*3-1*/ Service findServicesById(long id);
}
