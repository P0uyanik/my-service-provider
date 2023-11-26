package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert , Long> {
}
