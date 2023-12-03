package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomerRepository extends JpaRepository<Customer, Long> , QuerydslPredicateExecutor<Customer> {
    @Modifying
    @Query(
            """
                    update Customer c set c.password=:password where c.id=:id
                    """
    )
    int updateCustomerByPassword(long id, String password);
    Customer findCustomerByEmailAndPassword(String email , String password) ;
}
