package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.core.enums.OrderStatus;


import java.util.List;

@org.springframework.stereotype.Service
public interface CustomerService {








   /*1*/ void addANewCustomer (Customer customer) ;
   /*2*/ int updateCustomerByPassword( long id , String password) ;


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


   /*13*/    void cancellingAnOffer(String customerEmail , String customerPassword , long orderId, long offerId) ;

   // /*11-1*/ List <Offer> customerOffers (long customerId , long orderId ) ;
   /*14*/    void changingTheOrderStatusToStarted(String customerEmail , String customerPassword , long orderId);


   void changingTheOrderStatusToCompleted(String customerEmail , String customerPassword , long orderId , RateAndReview rateAndReview ) ;

   /*16*/  void submitComment(String customerEmail , String customerPassword , long orderId , RateAndReview rateAndReview )   ;

  List <Customer> searchingAndFilteringTheCustomers (Customer customer) ;
}

