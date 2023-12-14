package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.repositories.OfferRepository;
import com.example.finalprojectbootcamp.exceptions.MyExceptions;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
private  final OfferRepository offerRepository ;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
    @Override
    public Offer findOfferById(long id) {
        Offer offerById = offerRepository.findOfferById(id);
        MyExceptions.isOfferExists(offerById);
        return offerById ;
    }

    @Override
    public void addANewOffer(Offer offer) {
        offerRepository.save(offer) ;
    }


}
