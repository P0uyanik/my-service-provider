package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerServiceImpl implements  CustomerService {

    private final CustomerRepository customerRepository ;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addANewCustomer(Customer customer) {
        customerRepository.save(customer) ;
    }

    @Override
    public int updateCustomerByPassword(long id ,String password) {
        return customerRepository.updateCustomerByPassword(id , password) ;
    }

}
