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
public class OfferDto implements Serializable {
    @NotBlank
    private BigDecimal suggestedPrice;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;
    @NotBlank
    private int durationOfTheJobInDays;
}
