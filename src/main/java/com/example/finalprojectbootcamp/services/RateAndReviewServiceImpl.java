package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.core.enums.OfferStatus;
import com.example.finalprojectbootcamp.core.enums.Rater;
import com.example.finalprojectbootcamp.repositories.RateAndReviewRepository;
import com.example.finalprojectbootcamp.exceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Service
public class RateAndReviewServiceImpl implements RateAndReviewService {
    private final RateAndReviewRepository rateAndReviewRepository;
    private OfferService offerService;
    private OrderService orderService;

    public RateAndReviewServiceImpl(RateAndReviewRepository rateAndReviewRepository) {
        this.rateAndReviewRepository = rateAndReviewRepository;
    }


    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }



    @Autowired
    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
    @Override
    public void addANewRateAndReview(RateAndReview rateAndReview) {
        rateAndReviewRepository.save(rateAndReview) ;
    }

    @Override
    public void ratingAndReviewForExpert(RateAndReview rateAndReview, Customer customer, Order order, Offer offer) {

        Expert expertById = offer.getExpert();
        Order orderById = orderService.findOrderById(order.getId());
        MyExceptions.isOrderExists(orderById);
        Offer offerById = offerService.findOfferById(offer.getId());
        MyExceptions.checkOfferOrderExpertRelation(customer, expertById, offerById, orderById);
        rateAndReview.setExpert(expertById);
        customer.setRateAndReviews(rateAndReview);
        rateAndReviewRepository.save(rateAndReview);

    }

    @Override
    public void executionTimeOfTaskAndScheduledTime(List<Offer> offers ,  Order order ) {
        Offer selectedOffer = offers.stream().filter(offer -> offer.getOfferStatus() == OfferStatus.ACTIVE).findFirst().orElse(null);
        MyExceptions.isOfferExists(selectedOffer);
        Expert expert = selectedOffer.getExpert();
        LocalDate completionDateOfTask = order.getCompletionDateOfTask();
        LocalDate executionTime = order.getExecutionTime();
        int days = Period.between(executionTime, completionDateOfTask).getDays();
        if (days < 0 )
        {
            RateAndReview rateAndReview = new RateAndReview(days , Rater.SYSTEM);
            rateAndReview.setExpert(expert);
            rateAndReviewRepository.save(rateAndReview) ;
        }
    }

    @Override
    public double checkExpertRating(long expertId) {
        return rateAndReviewRepository.checkExpertRating(expertId);
    }
}
