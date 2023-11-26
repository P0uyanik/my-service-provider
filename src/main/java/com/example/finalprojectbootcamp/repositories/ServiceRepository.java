package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository <Service , Long> {
}
