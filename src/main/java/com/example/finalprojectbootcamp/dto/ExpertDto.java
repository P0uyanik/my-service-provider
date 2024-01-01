package com.example.finalprojectbootcamp.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpertDto implements Serializable {
    public String name;
    public String lastname;
    public String username;
    private String password ;
    public String email;
}
