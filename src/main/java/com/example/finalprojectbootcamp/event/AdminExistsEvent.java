package com.example.finalprojectbootcamp.event;

import com.example.finalprojectbootcamp.core.entities.Admin;
import com.example.finalprojectbootcamp.exceptions.AdminNotFoundException;
import com.example.finalprojectbootcamp.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class AdminExistsEvent {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Value(value = "${AdminExistsEvent.name}")
    private String name;
    @Value(value = "${AdminExistsEvent.lastname}")
    private String lastname;
    @Value(value = "${AdminExistsEvent.username}")
    private String username;
    @Value(value = "${AdminExistsEvent.password}")
    private String password;
    private String email;

    public AdminExistsEvent(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @EventListener
    public void onContextStartedEvent(ContextRefreshedEvent event) throws AdminNotFoundException {
        List<Admin> all = adminRepository.findAll();
        if (all.size() == 0) {
            Admin admin = new Admin(getName(), getLastname(), getUsername(), getEmail(), passwordEncoder.encode(getPassword()));
            adminRepository.save(admin);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
