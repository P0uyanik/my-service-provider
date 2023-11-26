package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , Long > {
}
