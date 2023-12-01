package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Admin;
import com.example.finalprojectbootcamp.repositories.AdminRepository;

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository ;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findAdminByEmailAndAndPassword(String email, String password) {
        return adminRepository.findAdminByEmailAndAndPassword(email , password);
    }
}
