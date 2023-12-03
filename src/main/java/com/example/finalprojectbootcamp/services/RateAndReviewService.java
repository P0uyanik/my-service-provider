package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import org.springframework.stereotype.Service;

@Service
interface RateAndReviewService {
    /*10*/void ratingAndReviewForExpert (RateAndReview rateAndReview , Customer customer , Expert expert , Order order , Offer offer) ;
    /*15*/ void executionTimeOfTaskAndScheduledTime (String customerEmail , String customerPassword , long orderId)  ;
}
