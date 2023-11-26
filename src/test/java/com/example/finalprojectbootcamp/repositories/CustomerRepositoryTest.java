package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.services.CustomerService;
import com.example.finalprojectbootcamp.services.CustomerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(
        properties = {
                "spring.datasource.url=jdbc:h2:tcp://localhost:9092/C:/Users/puja/source/final/TestDataBaseH2/testForRepositories",
                "spring.datasource.username=username",
                "spring.datasource.password=password",
                "spring.jpa.hibernate.ddl-auto=create"
        }
)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void addANewCustomer() {
        Customer customer = new Customer("String", "String", "String", "String", "String");
        customerRepository.save(customer);
        System.out.println(customerRepository.count());
        Assertions.assertThat(customerRepository.count()).isNotZero();
    }

}