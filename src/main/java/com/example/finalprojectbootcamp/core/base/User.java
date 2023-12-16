package com.example.finalprojectbootcamp.core.base;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (
        name = "Roles" ,
        discriminatorType = DiscriminatorType.STRING
)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users")
public  class User extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String lastname;
    String username;
    String email;
    String password;
    public User(String name, String lastname, String username, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    protected User() {
    }

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
