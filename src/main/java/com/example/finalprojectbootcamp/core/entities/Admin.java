package com.example.finalprojectbootcamp.core.entities;


import com.example.finalprojectbootcamp.core.base.User;
import com.example.finalprojectbootcamp.core.enums.Role;
import jakarta.persistence.Entity;


@Entity
public class Admin  extends User {
    private Role role = Role.ADMIN ;
    public Admin(String name, String lastname, String username, String email, String password) {
        super(name, lastname, username, email, password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    protected Admin() {
    }
}
