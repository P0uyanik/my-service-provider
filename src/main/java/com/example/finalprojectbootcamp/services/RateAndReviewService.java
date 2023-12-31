package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;


import java.util.List;


interface RateAndReviewService {
    void ratingAndReviewForExpert (RateAndReview rateAndReview , Customer customer ,  Order order , Offer offer) ;
   void executionTimeOfTaskAndScheduledTime (List<Offer> offers , Order order  ) ;
    double checkExpertRating (long expertId) ;
    void addANewRateAndReview (RateAndReview rateAndReview)  ;

}
