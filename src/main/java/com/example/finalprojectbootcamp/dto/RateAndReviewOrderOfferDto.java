package com.example.finalprojectbootcamp.dto;

public class RateAndReviewOrderOfferDto {
    private RateAndReviewDto rateAndReviewDto ;
    private  OrderDto orderDto ;
    private OfferDto offerDto ;



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
