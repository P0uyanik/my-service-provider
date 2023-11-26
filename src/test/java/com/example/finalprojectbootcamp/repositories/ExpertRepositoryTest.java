package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource (
        properties = {
                "spring.datasource.url=jdbc:h2:tcp://localhost:9092/C:/Users/puja/source/final/TestDataBaseH2/testForRepositories",
                "spring.datasource.username=username",
                "spring.datasource.password=password",
                "spring.jpa.hibernate.ddl-auto=create"
        }

)
class ExpertRepositoryTest {
    @Autowired
    private ExpertRepository expertRepository ;
    @Test
    void addANewCustomer() {
        Expert expert = new Expert("String", "String", "String", "String", "String" , ExpertStatus.NEW);
        expertRepository.save(expert);
        System.out.println(expertRepository.count());
        Assertions.assertThat(expertRepository.count()).isNotZero();
    }


}