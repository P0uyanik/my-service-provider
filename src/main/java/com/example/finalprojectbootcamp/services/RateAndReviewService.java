package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import org.springframework.stereotype.Service;

@Service
interface RateAndReviewService {
    /*01*/void ratingAndReviewForExpert (RateAndReview rateAndReview , Customer customer , Expert expert , Order order , Offer offer) ;
}
