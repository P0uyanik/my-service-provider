package com.example.finalprojectbootcamp.services;
import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.repositories.ExpertRepository;
import com.example.finalprojectbootcamp.exceptions.MyExceptions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;
    private OrderService orderService;
    private RateAndReviewService rateAndReviewService;
    private SubServiceService subServiceService;
    private final PasswordEncoder passwordEncoder ;
    public ExpertServiceImpl(ExpertRepository expertRepository, PasswordEncoder passwordEncoder) {
        this.expertRepository = expertRepository;
        this.passwordEncoder = passwordEncoder;
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
        expert.setPassword(passwordEncoder.encode(expert.getPassword()));
        expertRepository.save(expert) ;
    }

    @Override
    public  int updateExpertByPassword( String email  , String oldPassword ,  String password) {
        return expertRepository.updateExpertByPassword(email , oldPassword , password) ;
    }

    @Override
    public void deleteExpert(long id) {
      Expert expert =   expertRepository.findExpertById(id) ;
        MyExceptions.isExpertExists(expert);
        expertRepository.delete(expert);
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
        Expert expert = expertRepository.findExpertByEmailAndPassword(expertEmail, expertPassword);
        MyExceptions.isExpertExists(expert);
        return expert ;
    }
    @Override
    public List<Order> showAllOrdersForExpert() {
        return orderService.showAllOrdersForExpert() ;
    }
    @Override
    @Transactional
    public void submittingOfferForOrder(String expertEmail , String expertPassword, long orderId, Offer offer) {
        Expert expert = findExpertByEmailAndPassword(expertEmail ,expertPassword);
        MyExceptions.expertAccess(expert.isAccessToTheSystem()) ;
        MyExceptions.checkExpertStatus (expert.getExpertStatus()) ;

        Order order = orderService.findOrderById(orderId);


        offer.setExpert(expert);
        order.setOffers(offer) ;
        orderService.addANewOrder(order);


    }

    @Override
    public double checkExpertRating(long expertId) {
        return rateAndReviewService.checkExpertRating(expertId);
    }

    @Override
    @Transactional
    public void selectingSubServiceForExpert(String email, String subServicesTitle) {
        Expert expert = expertRepository.findExpertByEmail(email);
        MyExceptions.isExpertExists(expert);
        MyExceptions.expertAccess(expert.isAccessToTheSystem());
        SubService subServiceByTitle = subServiceService.findSubServiceByTitle(subServicesTitle);
        MyExceptions.isSubServiceAvailable(subServiceByTitle);
        subServiceByTitle.setExpert(expert);
        expert.setSubServices(subServiceByTitle);
        expertRepository.save(expert) ;
    }


    @Override
    public Iterable<Expert> searchingSortedExperts() {
        QExpert expertForFilter = QExpert.expert;
         Iterable<Expert> all = expertRepository.findAll(
                expertForFilter.name.asc(),
                expertForFilter.lastname.asc(),
                expertForFilter.username.asc(),
                expertForFilter.email.asc()
        );
        return all ;

    }


}
