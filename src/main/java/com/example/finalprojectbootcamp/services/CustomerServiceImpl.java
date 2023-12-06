package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.core.enums.OfferStatus;
import com.example.finalprojectbootcamp.core.enums.OrderStatus;
import com.example.finalprojectbootcamp.core.enums.Rater;
import com.example.finalprojectbootcamp.core.helperClasses.AccountStatus;
import com.example.finalprojectbootcamp.repositories.CustomerRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private ServiceService serviceService;
    private SubServiceService subServiceService;
    private OrderService orderService;
    private OfferService offerService;
    private ExpertService expertService;
    private CreditService creditService;
    private RateAndReviewService rateAndReviewService;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setExpertService(ExpertServiceImpl expertService) {
        this.expertService = expertService;
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
        customerRepository.save(customer);
    }

    @Override
    public int updateCustomerByPassword(long id, String password) {
        return customerRepository.updateCustomerByPassword(id, password);
    }

    @Override
    public List<Service> showAllServicesWithNavigation(int pageSize) {
        return serviceService.showAllServices(pageSize);
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
    public List<SubService> showSubServices(int pageSize) {
        return subServiceService.showSubServices(pageSize);
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
    public Customer findCustomerByEmailAndPassword(String email  , String password  ) {
        Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
        MyExceptions.isCustomerRegistered(customer);
        return customer ;
    }

    @Override
    public void registrationOfTheOrder(String customerEmail, String customerPassword, long serviceId, long subServiceId, Order order) {
        Service servicesById = serviceService.findServicesById(serviceId);
        MyExceptions.isServiceAvailable(servicesById);
        SubService mySubservice = servicesById.getSubServices().stream().filter(subService -> subService.getId() == subServiceId).findFirst().orElse(null) ;
        MyExceptions.isSubServiceAvailable(mySubservice);

        Customer customerById = findCustomerByEmailAndPassword(customerEmail, customerPassword);


        order.setSubService(mySubservice);
        order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_BIDS);
        customerById.setOrders(order);


        customerRepository.save(customerById);

    }

    @Override
    public List<Offer> customerOffers(String customerEmail , String customerPassword , long orderId) {
        Customer customerById = findCustomerByEmailAndPassword(customerEmail , customerPassword);



        Order orderById = orderService.findOrderById(orderId);
        ///////// Sort mit Comperator
        return MyExceptions.checkOrderForCustomer(customerById, orderById);
    }

    @Override
    public void selectingOffer(String customerEmail , String customerPassword , long orderId, long offerId) {
        List<Offer> offers = customerOffers(customerEmail,customerPassword ,  orderId);


        Offer offer = offerService.findOfferById(offerId);
        MyExceptions.isOfferExists(offer);

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


        MyExceptions.checkStartTimeException(checkStartTime) ;


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
        submitComment(customerEmail, customerPassword, orderId, rateAndReview);
    }


    private Offer findingSelectedOffer(List<Offer> offers) {
        return offers.stream().filter(offer -> offer.getOfferStatus().equals(OfferStatus.ACTIVE)).findFirst().orElse(null);
    }


    @Override
    public void submitComment(String customerEmail, String customerPassword, long orderId, RateAndReview rateAndReview) {
        Order order= orderService.findOrderById(orderId);
        MyExceptions.orderIfHasBeenCompleted(order) ;
        List<Offer> offers = customerOffers(customerEmail, customerPassword, orderId);
        Offer offer = findingSelectedOffer(offers);
        Expert expert = offer.getExpert();
        rateAndReview.setRater(Rater.CUSTOMER);
        expert.setRateAndReviews(rateAndReview);
        expertService.addANewExpert(expert);

    }

    @Override
    public List<Customer> searchingAndFilteringTheCustomers(Customer customer) {
        QCustomer myCustomer = QCustomer.customer;
        String email = customer.getEmail();
        String name = customer.getName();
        String lastname = customer.getLastname();
        String username = customer.getUsername();
        AccountStatus accountStatus = customer.getAccountStatus();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression isEmailSelected;
        BooleanExpression isNameSelected;
        BooleanExpression isLastnameSelected;
        BooleanExpression isUsernameSelected;
        BooleanExpression isAccessToAccount;

        if (!StringUtils.isNullOrEmpty(email))
            isEmailSelected = myCustomer.email.isNotNull();
        if (!StringUtils.isNullOrEmpty(name))
            isNameSelected = myCustomer.name.isNull();
        if (!StringUtils.isNullOrEmpty(lastname))
            isLastnameSelected = myCustomer.lastname.isNull();
        if (!StringUtils.isNullOrEmpty(username))
            isUsernameSelected = myCustomer.username.isNull();
        if (accountStatus.isAccessToAccount())
            isAccessToAccount = myCustomer.accountStatus.isNotNull().isTrue();


        customerRepository.findAll();
        return null;
    }

    @Override
    public void payingAmountWithCredit(String customerEmail, String customerPassword, long orderId) {
        creditService.payingAmountWithCredit(customerEmail, customerPassword, orderId);
    }

    @Override
    public void ratingAndReviewForExpert(RateAndReview rateAndReview , String customerEmail , String customerPassword , Order order, Offer offer) {
        Customer customerByEmailAndPassword = customerRepository.findCustomerByEmailAndPassword(customerEmail, customerPassword);
        rateAndReviewService.ratingAndReviewForExpert(rateAndReview ,  customerByEmailAndPassword , order , offer);
    }

    @Override
    public void executionTimeOfTaskAndScheduledTime(String customerEmail, String customerPassword, long orderId) {
        Customer customerByEmailAndPassword = customerRepository.findCustomerByEmailAndPassword(customerEmail, customerPassword);
        rateAndReviewService.executionTimeOfTaskAndScheduledTime(customerEmail , customerPassword , orderId);
    }


}
