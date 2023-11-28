package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.repositories.OfferRepository;

public class OfferServiceImpl implements OfferService {
private  final OfferRepository offerRepository ;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer findOfferById(long id) {
        return offerRepository.findOfferById(id);
    }
}
