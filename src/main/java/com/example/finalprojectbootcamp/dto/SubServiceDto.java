package com.example.finalprojectbootcamp.dto;

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
    private String title ;
    private BigDecimal price ;
    private String description ;
}
