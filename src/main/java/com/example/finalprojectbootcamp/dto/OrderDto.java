package com.example.finalprojectbootcamp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private BigDecimal suggestedPrice;
    private String jobDescription;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate executionTime;
    private String address;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate completionDateOfTask;
}
