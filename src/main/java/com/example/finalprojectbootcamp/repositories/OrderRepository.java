package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order , Long> {

    Order findOrderById(long id) ;
}
