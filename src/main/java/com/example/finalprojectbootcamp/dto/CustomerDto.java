package com.example.finalprojectbootcamp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable {
    private String name;
    private String lastname;
    private String username;
    private String password ;
    @Column(unique = true)
    private String email;
}
