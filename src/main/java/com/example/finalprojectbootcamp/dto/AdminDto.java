package com.example.finalprojectbootcamp.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDto implements Serializable {
    private String name;
    private String lastname;
    private String username;
    private String password ;
    private String email;

}
