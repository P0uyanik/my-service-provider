package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.repositories.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements  OrderService {
    private final OrderRepository orderRepository ;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addANewOrder(Order order) {
        orderRepository.save(order) ;
    }

    @Override
    public Order findOrderById(long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> showAllOrdersForExpert() {
        return orderRepository.findOrderByOrderStatus() ;
    }


}
