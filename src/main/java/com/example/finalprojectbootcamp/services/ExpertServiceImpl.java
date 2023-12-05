package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.core.entities.SubService;
import com.example.finalprojectbootcamp.repositories.ExpertRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;
    private OrderService orderService;
    private RateAndReviewService rateAndReviewService;
    private SubServiceService subServiceService;

    public ExpertServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }


    @Autowired
    void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Autowired
    void setRateAndReviewService(RateAndReviewServiceImpl rateAndReviewService) {
        this.rateAndReviewService = rateAndReviewService;
    }

    @Autowired
    void setSubService(SubServiceServiceImpl subService) {
        this.subServiceService = subService;
    }

    @Override
    public void addANewExpert(Expert expert) {
        expertRepository.save(expert) ;
    }

    @Override
    public int updateExpertByPassword(long id ,String password) {
        return expertRepository.updateExpertByPassword(id , password) ;
    }

    @Override
    public void deleteExpert(Expert expert) {
        Expert expertByIdy= expertRepository.findExpertByEmailAndPassword(expert.getEmail() , expert.getPassword());
        MyExceptions.isExpertExists(expertByIdy) ;
        expertRepository.delete(expertByIdy);
    }

    @Override
    public List<Expert> findExpertByExpertStatus() {
       List<Expert>  expertByExpertStatus = expertRepository.findExpertByExpertStatus();
        MyExceptions.couldNotBeFound (expertByExpertStatus) ;
        return expertByExpertStatus ;
    }

    @Override
    public int updateExpertStatusByEmail(String email) {
        return expertRepository.updateExpertStatusByEmail(email);
    }

    @Override
    public  Expert findExpertByEmailAndPassword (String expertEmail , String expertPassword)  {
        return expertRepository.findExpertByEmailAndPassword(expertEmail , expertPassword) ;
    }
    @Override
    public List<Order> showAllOrdersForExpert() {
        return orderService.showAllOrdersForExpert() ;
    }
    @Override
    public void submittingOfferForOrder(String expertEmail , String expertPassword, long orderId, Offer offer) {
        Expert expert = findExpertByEmailAndPassword(expertEmail ,expertPassword );
        MyExceptions.isExpertExists(expert);
        MyExceptions.expertAccess(expert.isAccessToTheSystem()) ;

        double avgRating = rateAndReviewService.checkExpertRating(expert.getId());
        MyExceptions.negativeRating(avgRating);


        Order order = orderService.findOrderById(orderId);
        MyExceptions.isOrderExists(order) ;

        offer.setExpert(expert);
        order.setOffers(offer) ;
        orderService.addANewOrder(order);


    }

    @Override
    public double checkExpertRating(long expertId) {
        return rateAndReviewService.checkExpertRating(expertId);
    }

    @Override
    public void selectingSubServiceForExpert(String email, SubService subServices) {
        Expert expert = expertRepository.findExpertByEmail(email);
        MyExceptions.expertAccess(expert.isAccessToTheSystem());
        SubService subServiceByTitle = subServiceService.findSubServiceByTitle(subServices.getTitle());
        MyExceptions.isSubServiceAvailable(subServiceByTitle);
        expert.setSubServices(subServices);
        expertRepository.save(expert) ;
    }


}
