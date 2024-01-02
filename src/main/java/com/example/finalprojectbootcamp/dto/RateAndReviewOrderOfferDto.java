package com.example.finalprojectbootcamp.dto;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

public class RateAndReviewOrderOfferDto {

    @Valid private RateAndReviewDto rateAndReviewDto ;
    @Valid private  OrderDto orderDto ;
    @Valid private OfferDto offerDto ;



    public RateAndReviewDto getRateAndReviewDto() {
        return rateAndReviewDto;
    }

    public void setRateAndReviewDto(RateAndReviewDto rateAndReviewDto) {
        this.rateAndReviewDto = rateAndReviewDto;
    }

    public OfferDto getOfferDto() {
        return offerDto;
    }

    public void setOfferDto(OfferDto offerDto) {
        this.offerDto = offerDto;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
