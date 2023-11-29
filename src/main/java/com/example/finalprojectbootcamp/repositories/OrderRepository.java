package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(long id);

    @Query(
            """
                    select Order from Order o where
                     o.orderStatus =?#{T(com.example.finalprojectbootcamp.core.enums.OrderStatus).WAITING_FOR_EXPERT_BIDS}
                     OR  o.orderStatus =?#{T(com.example.finalprojectbootcamp.core.enums.OrderStatus).WAITING_FOR_EXPERT_SELECTION}
                    """
    )
    List<Order> findOrderByOrderStatus();



}
