package com.example.finalprojectbootcamp.mapper;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.dto.ExpertDto;
import com.example.finalprojectbootcamp.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class) ;
    OrderDto toOrderDto (Order order) ;
    Order toOrder (OrderDto orderDto) ;
    List<OrderDto> toOrderDtoAsList (List<Order> orders) ;
    List<Order> toOrderAsList (List<OrderDto> orderDtoS) ;
}
