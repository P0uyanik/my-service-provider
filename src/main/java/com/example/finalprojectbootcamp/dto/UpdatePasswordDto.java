package com.example.finalprojectbootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {
    private String email ;
    private  String oldPassword ;
    private String password ;
}
