package com.example.finalprojectbootcamp.mapper;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class) ;
    CustomerDto toCustomerDto (Customer customer) ;
    Customer toCustomer (CustomerDto customerDto) ;
    List<CustomerDto> toCustomerDtoAsList (List<Customer> customerList) ;
    List<Customer> toCustomerAsList (List<CustomerDto> customerDosS) ;
}
