package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.core.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {
    Offer findOfferById(long id);

    void addANewOffer(Offer offer);
}
