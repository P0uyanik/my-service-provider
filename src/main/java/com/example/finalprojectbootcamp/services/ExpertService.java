package com.example.finalprojectbootcamp.services;


import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.core.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExpertService {
    /*1*/ void addANewExpert (Expert expert) ;
    /*2*/ int updateExpertByPassword(long id , String password);
    /*4*/  void deleteExpert (Expert expert) ;
    /*6*/ List<Expert> findExpertByExpertStatus();
    /*6-1*/ int updateExpertById (long id) ;
    List<Order> showAllOrdersForExpert() ;
    Expert findExpertByEmailAndPassword (String email , String password) ; ;

    /*8*/ void submittingOfferForOrder(String expertEmail , String expertPassword, long orderId, Offer offer) ;
}
