package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Admin;
import com.example.finalprojectbootcamp.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin findAdminByEmailAndAndPassword(String email , String password) ;

}
