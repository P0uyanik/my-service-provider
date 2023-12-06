package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Offer;



public interface OfferService {
    Offer findOfferById(long id);

    void addANewOffer(Offer offer);
}
