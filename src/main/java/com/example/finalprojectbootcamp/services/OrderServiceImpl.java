package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.repositories.OrderRepository;
import com.example.finalprojectbootcamp.exceptions.MyExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
        Order orderById = orderRepository.findOrderById(id);
        MyExceptions.isOrderExists(orderById);
        return orderById ;
    }

    @Override
    public List<Order> showAllOrdersForExpert() {
        return orderRepository.findOrderByOrderStatus() ;
    }


}
