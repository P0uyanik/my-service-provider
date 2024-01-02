package com.example.finalprojectbootcamp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubServiceDto implements Serializable {
    @NotBlank private String title ;
    @NotBlank private BigDecimal price ;
    @NotBlank private String description ;
}
