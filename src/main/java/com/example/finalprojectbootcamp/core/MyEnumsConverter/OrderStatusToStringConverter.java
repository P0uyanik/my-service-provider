package com.example.finalprojectbootcamp.core.MyEnumsConverter;

import com.example.finalprojectbootcamp.core.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class OrderStatusToStringConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus attribute) {
        return attribute.name();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbData) {
        return Enum.valueOf(OrderStatus.class, dbData);
    }
}
