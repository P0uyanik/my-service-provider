package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.repositories.RateAndReviewRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;

public class RateAndReviewServiceImpl implements RateAndReviewService {
    private final RateAndReviewRepository rateAndReviewRepository;
    private CustomerService customerService;
    private ExpertService expertService;
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
    public void setCustomerService (CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    @Autowired
    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }


    @Autowired
    public void setExpertService(ExpertServiceImpl expertService) {
        this.expertService = expertService;
    }


    @Override
    public void ratingAndReviewForExpert(RateAndReview rateAndReview, Customer customer, Expert expert, Order order, Offer offer) {
        Expert expertById = expertService.findExpertById(expert.getId());
        MyExceptions.isExpertExists(expertById);

        Customer customerById = customerService.findCustomerById(customer.getId());
        MyExceptions.isCustomerRegistered(customerById);


        Order orderById = orderService.findOrderById(order.getId());
        MyExceptions.isOrderExists(orderById);

        Offer offerById = offerService.findOfferById(offer.getId());
        MyExceptions.isOfferExists(offerById);

        MyExceptions.checkOfferOrderExpertRelation(customerById, expertById, offerById, orderById);


        customerById.setRateAndReviews(rateAndReview);
        rateAndReview.setExpert(expertById);
        rateAndReviewRepository.save(rateAndReview);

    }
}
