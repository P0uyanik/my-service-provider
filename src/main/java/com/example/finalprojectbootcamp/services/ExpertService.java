package com.example.finalprojectbootcamp.services;


import com.example.finalprojectbootcamp.core.entities.*;


import java.util.List;


public interface ExpertService {
    void addANewExpert(Expert expert); // expert

    int updateExpertByPassword(String email, String oldPassword, String password);

    void deleteExpert(long id);

    List<Expert> findExpertByExpertStatus();

    int updateExpertStatusByEmail(String email);

    List<Order> showAllOrdersForExpert();

    Expert findExpertByEmailAndPassword(String email, String password);

    void submittingOfferForOrder(String expertEmail, String expertPassword, long orderId, Offer offer);

    double checkExpertRating(long expertId);

    Iterable<Expert> searchingSortedExperts();

    void selectingSubServiceForExpert(String email, String subServicesTitle);

}
