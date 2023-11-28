package com.example.finalprojectbootcamp.services;


import com.example.finalprojectbootcamp.core.entities.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void addANewOrder (Order order) ;
}
