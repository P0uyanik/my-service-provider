package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Modifying
    @Query(
            """
                    update Customer c set c.password=:password where c.email=:email
                    """
    )
    int updateCustomerByPassword(String email, String password);
    Customer findCustomerByEmailAndPassword(String email , String password) ;
}
