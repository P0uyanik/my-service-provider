package com.example.finalprojectbootcamp.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RateAndReviewDto implements Serializable {
    private int rating;
    private String review;
}
