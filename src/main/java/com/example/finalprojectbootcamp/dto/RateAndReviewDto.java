package com.example.finalprojectbootcamp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RateAndReviewDto implements Serializable {
    @NotBlank
    private int rating;
    private String review;
}
