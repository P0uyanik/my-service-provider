package com.example.finalprojectbootcamp.services;


import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.core.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;



public interface ExpertService {
    /*1*/ void addANewExpert (Expert expert) ; // expert
    /*2*/ int updateExpertByPassword(long id , String password); // expert
    /*4*/  void deleteExpert (Expert expert) ; //admin
    /*6*/ List<Expert> findExpertByExpertStatus(); // admin
    /*6-1*/ int updateExpertStatusByEmail(String email) ; // admin
    /*10*/ List<Order> showAllOrdersForExpert() ; //expert
    Expert findExpertByEmailAndPassword (String email , String password) ; //expert

    /*8*/ void submittingOfferForOrder(String expertEmail , String expertPassword, long orderId, Offer offer) ; //expert

    /*17*/ double checkExpertRating (long expertId) ; // expert
    /*18*/  List <Expert> searchingAndFilteringTheExperts (Expert expert) ; // admin
    void selectingSubServiceForExpert (String email , SubService subServices) ;// admin

}
