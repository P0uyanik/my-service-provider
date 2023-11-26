package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
