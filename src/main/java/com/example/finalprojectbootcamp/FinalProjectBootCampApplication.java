package com.example.finalprojectbootcamp;
import com.example.finalprojectbootcamp.core.entities.Expert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinalProjectBootCampApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectBootCampApplication.class, args);

    }

}
