package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Expert;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>  , QuerydslPredicateExecutor<Customer> {
    @Modifying
    @Query(
            """
                    update Customer c set c.password=:password where c.email=:email and c.password=:oldPassword
                    """
    )
    int updateCustomerByPassword(String email, String oldPassword , String password);
    Customer findCustomerByEmailAndPassword(String email , String password) ;
}
