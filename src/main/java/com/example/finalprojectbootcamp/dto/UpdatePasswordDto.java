package com.example.finalprojectbootcamp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {
    @NotBlank private String email ;
    @NotBlank  private  String oldPassword ;
    @NotBlank private String password ;
}
