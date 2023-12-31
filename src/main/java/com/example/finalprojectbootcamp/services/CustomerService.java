package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import org.springframework.data.domain.Page;


import java.util.List;


public interface CustomerService {

    void addANewCustomer(Customer customer);

    int updateCustomerByPassword(String email, String oldPassword, String password);


    List<Service> npService();

    List<Service> ppService();

    Page<SubService> findSubServicesWithPageSizeAndElementSize(int pageSize, int elementSize);

    List<SubService> ppSubService();

    List<SubService> npSubService();

    List<Service> findAllServices();

    Customer findCustomerByEmailAndPassword(String email, String password);

    void registrationOfTheOrder(String customerEmail, String customerPassword, String serviceName, String subServiceName, Order order);

    List<Offer> customerOffers(String customerEmail, String customerPassword, long orderId);

    void selectingOffer(String customerEmail, String customerPassword, long orderId, long offerId);

    void cancellingAnOffer(String customerEmail, String customerPassword, long orderId, long offerId);

    void changingTheOrderStatusToStarted(String customerEmail, String customerPassword, long orderId);


    void changingTheOrderStatusToCompleted(String customerEmail, String customerPassword, long orderId, RateAndReview rateAndReview);

    void submitComment(List<Offer> offers, Order order, RateAndReview rateAndReview);

    List<Customer> searchingAndFilteringTheCustomers(Customer customer);


    void payingAmountWithCredit(String customerEmail, String customerPassword, long orderId);

    void ratingAndReviewForExpert(RateAndReview rateAndReview, String customerEmail, String customerPassword, Order order, Offer offer);

    void executionTimeOfTaskAndScheduledTime(List<Offer> offers, long orderId);
}

