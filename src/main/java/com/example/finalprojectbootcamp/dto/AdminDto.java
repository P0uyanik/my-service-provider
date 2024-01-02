package com.example.finalprojectbootcamp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDto implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")
    private String password;
    @Email
    private String email;

}
