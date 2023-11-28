package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.repositories.ExpertRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository ;
    private OrderService orderService ;

    public ExpertServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }


    @Autowired
    void setOrderService (OrderServiceImpl orderService)
    {
        this.orderService = orderService ;
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
        Expert expertByIdy= expertRepository.findExpertById(expert.getId());
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
    public int updateExpertById(long id) {
        return expertRepository.updateExpertById(id);
    }

    @Override
    public Expert findExpertById(long id) {
        return expertRepository.findExpertById(id) ;
    }

    @Override
    public void submittingOfferForOrder(long expertId, long orderId, Offer offer) {
        Expert expert = findExpertById(expertId);
        MyExceptions.isExpertExists(expert);
        MyExceptions.expertAccess(expert.isAccessToTheSystem()) ;


        Order order = orderService.findOrderById(orderId);
        MyExceptions.isOrderExists(order) ;

        offer.setExpert(expert);
        order.setOffers(offer) ;
        orderService.addANewOrder(order);


    }
}
