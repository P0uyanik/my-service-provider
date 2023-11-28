package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Offer;
import org.springframework.stereotype.Service;

@Service
public interface OfferService {
    Offer findOfferById(long id) ;
}
