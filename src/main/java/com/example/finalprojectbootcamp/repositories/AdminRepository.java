package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository <Admin , Long > {
    Admin findAdminByEmailAndAndPassword(String email , String password) ;
}
