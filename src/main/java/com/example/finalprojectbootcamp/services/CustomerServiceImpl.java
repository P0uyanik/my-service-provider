package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.core.enums.OfferStatus;
import com.example.finalprojectbootcamp.core.enums.OrderStatus;
import com.example.finalprojectbootcamp.core.enums.Rater;
import com.example.finalprojectbootcamp.repositories.CustomerRepository;
import com.example.finalprojectbootcamp.exceptions.MyExceptions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private ServiceService serviceService;
    private SubServiceService subServiceService;
    private OrderService orderService;
    private OfferService offerService;
    private CreditService creditService;
    private RateAndReviewService rateAndReviewService;

    private final PasswordEncoder passwordEncoder ;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setServiceService(ServiceServiceImpl serviceService) {
        this.serviceService = serviceService;
    }

    @Autowired
    public void setCreditService(CreditServiceImpl creditService) {
        this.creditService = creditService;
    }

    @Autowired
    public void setSubServiceService(SubServiceServiceImpl subServiceService) {
        this.subServiceService = subServiceService;
    }
    @Autowired
    public void setRateAndReviewService(RateAndReviewServiceImpl rateAndReviewService) {
        this.rateAndReviewService = rateAndReviewService;
    }

    @Autowired
    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @Override
    public void addANewCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getUsername()));
        customerRepository.save(customer);
    }

    @Override
    public int updateCustomerByPassword(String email, String oldPassword , String password) {
        return customerRepository.updateCustomerByPassword(email, oldPassword,  password);
    }


    @Override
    public List<Service> npService() {
        return serviceService.np();
    }

    @Override
    public List<Service> ppService() {
        return serviceService.pp();
    }

    @Override
    public Page<SubService> findSubServicesWithPageSizeAndElementSize(int pageSize, int elementSize) {
        return subServiceService.findSubServicesWithPageSizeAndElementSize(pageSize, elementSize);
    }

    @Override
    public List<SubService> ppSubService() {
        return subServiceService.pp();
    }

    @Override
    public List<SubService> npSubService() {
        return subServiceService.np();
    }

    @Override
    public List<Service> findAllServices() {
        return serviceService.findAllServices();
    }

    @Override
    public Customer findCustomerByEmailAndPassword(String email, String password) {
        Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
        MyExceptions.isCustomerRegistered(customer);
        return customer;
    }

    @Override
    @Transactional
    public void registrationOfTheOrder(String customerEmail, String customerPassword, String serviceName, String subServiceName, Order order) {
        Service servicesById = serviceService.findServicesByName(serviceName);
        SubService mySubservice = servicesById.getSubServices().stream().filter(subService -> subService.getTitle().equals(subServiceName)).findFirst().orElse(null);
        MyExceptions.isSubServiceAvailable(mySubservice);
        Customer customerByEmail = findCustomerByEmailAndPassword(customerEmail, customerPassword);
        order.setSubService(mySubservice);
        order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_BIDS);
        customerByEmail.setOrders(order);
        customerRepository.save(customerByEmail);

    }


    @Override
    public List<Offer> customerOffers(String customerEmail, String customerPassword, long orderId) {

        Customer customerById = findCustomerByEmailAndPassword(customerEmail, customerPassword);
        Order orderById = orderService.findOrderById(orderId);
        ///////// Sort mit Comperator
        return MyExceptions.checkOrderForCustomer(customerById, orderById);
    }

    @Override
    public void selectingOffer(String customerEmail, String customerPassword, long orderId, long offerId) {
        List<Offer> offers = customerOffers(customerEmail, customerPassword, orderId);
        Offer offer = offerService.findOfferById(offerId);
        Order order = orderService.findOrderById(orderId);
        MyExceptions.checkOffers(offers);
        Offer selectedOffer = MyExceptions.checkOfferForOrder(offers, offer);
        selectedOffer.setOfferStatus(OfferStatus.ACTIVE);
        order.setOffers(selectedOffer);
        order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_TO_ARRIVE_AT_YOUR_LOCATION);
        orderService.addANewOrder(order);


    }


    @Override
    public void cancellingAnOffer(String customerEmail, String customerPassword, long orderId, long offerId) {
        List<Offer> offers = customerOffers(customerEmail, customerPassword, orderId);
        Order order = orderService.findOrderById(orderId);

        Offer offer = findingSelectedOffer(offers);
        offer.setOfferStatus(OfferStatus.DEACTIVE);
        order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_BIDS);
        order.setOffers(offer);
        orderService.addANewOrder(order);

    }


    @Override
    public void changingTheOrderStatusToStarted(String customerEmail, String customerPassword, long orderId) {
        List<Offer> offers = customerOffers(customerEmail, customerPassword, orderId);
        MyExceptions.checkOffersToFindActiveOffer(offers);
        Order order = orderService.findOrderById(orderId);
        Offer offer = findingSelectedOffer(offers);
        LocalDate startTime = offer.getStartTime();
        int checkStartTime = LocalDate.now().compareTo(startTime);
        MyExceptions.checkStartTimeException(checkStartTime);
        order.setOrderStatus(OrderStatus.STARTED);
        orderService.addANewOrder(order);
    }

    @Override
    public void changingTheOrderStatusToCompleted(String customerEmail, String customerPassword, long orderId, RateAndReview rateAndReview) {
        List<Offer> offers = customerOffers(customerEmail, customerPassword, orderId);
        MyExceptions.checkOffersToFindActiveOffer(offers);
        Order order = orderService.findOrderById(orderId);
        order.setCompletionDateOfTask(LocalDate.now());
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderService.addANewOrder(order);
        executionTimeOfTaskAndScheduledTime(offers, orderId);
        submitComment(offers, order, rateAndReview);
    }


    private Offer findingSelectedOffer(List<Offer> offers) {
        return offers.stream().filter(offer -> offer.getOfferStatus().equals(OfferStatus.ACTIVE)).findFirst().orElse(null);
    }


    @Override
    public void submitComment(List<Offer> offers, Order order, RateAndReview rateAndReview) {
        MyExceptions.orderIfHasBeenCompleted(order);
        Offer offer = findingSelectedOffer(offers);
        Expert expert = offer.getExpert();
        rateAndReview.setRater(Rater.CUSTOMER);
        rateAndReview.setExpert(expert);
        rateAndReviewService.addANewRateAndReview(rateAndReview);
        /*
        expert.setRateAndReviews(rateAndReview);
        expertService.addANewExpert(expert);
         */


    }

    @Override
    public List<Customer> searchingAndFilteringTheCustomers(Customer customer) {

        return null;
    }

    @Override
    public void payingAmountWithCredit(String customerEmail, String customerPassword, long orderId) {
        orderService.findOrderById(orderId);
        List<Offer> offers = customerOffers(customerEmail, customerPassword, orderId);
        Customer customer = findCustomerByEmailAndPassword(customerEmail, customerPassword);
        creditService.payingAmountWithCredit(offers, customer);
    }

    @Override
    public void ratingAndReviewForExpert(RateAndReview rateAndReview, String customerEmail, String customerPassword, Order order, Offer offer) {
        Customer customer = customerRepository.findCustomerByEmailAndPassword(customerEmail, customerPassword);

        rateAndReviewService.ratingAndReviewForExpert(rateAndReview, customer, order, offer);
    }

    @Override
    public void executionTimeOfTaskAndScheduledTime(List<Offer> offers, long orderId) {
        Order order = orderService.findOrderById(orderId);
        rateAndReviewService.executionTimeOfTaskAndScheduledTime(offers, order);
    }


}
