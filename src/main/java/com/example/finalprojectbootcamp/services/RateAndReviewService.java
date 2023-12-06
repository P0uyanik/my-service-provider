package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.repositories.RateAndReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;


interface RateAndReviewService {
    /*10*/void ratingAndReviewForExpert (RateAndReview rateAndReview , Customer customer ,  Order order , Offer offer) ; //customer
    /*15*/ void executionTimeOfTaskAndScheduledTime (String customerEmail , String customerPassword , long orderId )  ; // customer automat
    /*16*/  double checkExpertRating (long expertId) ; // expert

}
