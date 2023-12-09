package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;


import java.util.List;


public interface CustomerService {

   /*1*/ void addANewCustomer (Customer customer) ; // Customer
   /*2*/ int updateCustomerByPassword( String email  , String password) ; // customer


   /*7*/ List<Service> showAllServicesWithNavigation(int pageSize) ; // customer
   /*7-1*/public List<Service> npService() ; // customer
   /*7-2*/public List<Service> ppService() ; // customer
   /*7-1*/  List<SubService> showSubServices (int pageSize) ; // customer
   /*7-2*/ List<SubService> ppSubService() ; // customer
   /*7-3*/ List<SubService> npSubService() ; // customer
   /*7.1-1*/ List<Service> findAllServices() ; // customer
   Customer findCustomerByEmailAndPassword(String email ,String  password) ; // customer
   /*7.1-2*/void registrationOfTheOrder (String customerEmail , String customerPassword , long serviceId , long subServiceId  , Order order ) ; //customer

   /*11*/    public List<Offer> customerOffers(String customerEmail , String customerPassword , long orderId) ; // // customer
   /*13*/ void selectingOffer(String customerEmail , String customerPassword , long orderId, long offerId) ; // // customer


   /*13*/    void cancellingAnOffer(String customerEmail , String customerPassword , long orderId, long offerId) ; // customer


   /*14*/    void changingTheOrderStatusToStarted(String customerEmail , String customerPassword , long orderId);  // customer

   /*7*/ List<Service> showAllServicesWithNavigation(int pageSize) ;
   /*7-1*/public List<Service> npService() ;
   /*7-2*/public List<Service> ppService() ;
   /*7-1*/  List<SubService> showSubServices (int pageSize) ;
   /*7-2*/ List<SubService> ppSubService() ;
   /*7-3*/ List<SubService> npSubService() ;
   /*7.1-1*/ List<Service> findAllServices() ;
   Customer findCustomerByEmailAndPassword(String email ,String  password) ;
   /*7.1-2*/void registrationOfTheOrder (String customerEmail , String customerPassword , long serviceId , long subServiceId  , Order order ) ;

   /*11*/    public List<Offer> customerOffers(String customerEmail , String customerPassword , long orderId) ;
   /*13*/ void selectingOffer(String customerEmail , String customerPassword , long orderId, long offerId) ;

   /*16*/  void submitComment(String customerEmail , String customerPassword , long orderId , RateAndReview rateAndReview )   ; // customer

   /*13*/    void cancellingAnOffer(String customerEmail , String customerPassword , long orderId, long offerId) ;


   void payingAmountWithCredit (String customerEmail ,  String customerPassword , long orderId ) ; // customer


   /*10*/void ratingAndReviewForExpert(RateAndReview rateAndReview , String customerEmail , String customerPassword , Order order, Offer offer) ; //customer
   /*15*/ void executionTimeOfTaskAndScheduledTime (String customerEmail , String customerPassword , long orderId )  ; // customer automat
}

