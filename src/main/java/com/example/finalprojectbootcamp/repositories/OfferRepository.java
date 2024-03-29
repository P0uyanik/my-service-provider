package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository <Offer, Long > {
    Offer findOfferById(long id) ;
}
