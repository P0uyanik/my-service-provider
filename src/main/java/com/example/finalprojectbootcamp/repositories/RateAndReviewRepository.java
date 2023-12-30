package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.RateAndReview;
import com.example.finalprojectbootcamp.core.enums.Rater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface RateAndReviewRepository extends JpaRepository<RateAndReview, Long> {

    @Query
 ( value =" select AVG(rating) from rate_and_review " ,   nativeQuery = true )
    double checkExpertRating(long expertId);


}
