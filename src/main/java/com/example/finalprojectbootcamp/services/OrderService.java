package com.example.finalprojectbootcamp.services;


import com.example.finalprojectbootcamp.core.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void addANewOrder (Order order) ;
    Order findOrderById(long id) ;
    List<Order> showAllOrdersForExpert () ;

}
