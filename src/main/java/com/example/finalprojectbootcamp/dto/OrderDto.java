package com.example.finalprojectbootcamp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto implements Serializable {
    @NotBlank
    private BigDecimal suggestedPrice;
    @NotBlank
    private String jobDescription;
    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate executionTime;
    @NotBlank
    private String address;
    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate completionDateOfTask;
}
