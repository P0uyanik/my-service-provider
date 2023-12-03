package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;
import com.example.finalprojectbootcamp.repositories.CustomerRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private ServiceService serviceService;
    private SubServiceService subServiceService;
    private OrderService orderService;
    private OfferService offerService;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setServiceService(ServiceServiceImpl serviceService) {
        this.serviceService = serviceService;
    }

    @Autowired
    public void setSubServiceService(SubServiceServiceImpl subServiceService) {
        this.subServiceService = subServiceService;
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
    public Customer findCustomerById(long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public void registrationOfTheOrder(long serviceId, long subServiceId, long customerId, Order order) {
        Service servicesById = serviceService.findServicesById(serviceId);
        MyExceptions.isServiceAvailable(servicesById);
        SubService mySubservice = null;
        for (SubService subService : servicesById.getSubServices()) {
            if (subService.getId() == subServiceId)
                mySubservice = subService;
        }
        MyExceptions.isSubServiceAvailable(mySubservice);
        Customer customerById = findCustomerById(customerId);
        MyExceptions.isCustomerRegistered(customerById) ;
        order.setSubService(mySubservice);
        customerById.setOrders(order) ;

    }

    @Override
    public List<Offer> customerOffers(long customerId, long orderId) {
        Customer customerById = findCustomerById(customerId);
        MyExceptions.isCustomerRegistered(customerById);



        Order orderById = orderService.findOrderById(orderId);
        MyExceptions.isOrderExists(orderById);
        ///////// Sort mit Comperator
        return MyExceptions.checkOrderForCustomer(customerById, orderById);
    }

    @Override
    public void selectingOffer(long customerId, long orderId, long offerId) {
        List<Offer> offers = customerOffers(customerId, orderId);


        Offer offer = offerService.findOfferById(offerId);
        MyExceptions.isOfferExists(offer);

        Order order = orderService.findOrderById(orderId);


        MyExceptions.checkOffers(offers);

        // be yek orderkhassi offer hayi taalogh gerefte
        MyExceptions.checkOfferForOrder(offers, offer);

        for (Offer offer1 : offers)
            if (offer1.hashCode() == offer.hashCode()) {
                offer1.setOfferStatus(OfferStatus.ACTIVE);
                order.setOffers(offer1);
                order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_TO_ARRIVE_AT_YOUR_LOCATION);
                orderService.addANewOrder(order);
            }


    }


    @Override
    public void cancellingAnOffer(long customerId, long orderId, long offerId) {
        List<Offer> offers = customerOffers(customerId, orderId);
        Order order = orderService.findOrderById(orderId);
        for (Offer offer : offers) {
            if (offer.getOfferStatus().equals(OfferStatus.ACTIVE)) {
                offer.setOfferStatus(OfferStatus.DEACTIVE);
                order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_BIDS);
                order.setOffers(offer) ;
                orderService.addANewOrder(order);
            }
        }
    }



    @Override
    public void changingTheOrderStatusToStarted(String customerEmail , String customerPassword , long orderId) {
        List<Offer> offers = customerOffers(customerEmail , customerPassword , orderId);
        MyExceptions.checkOffersToFindActiveOffer(offers) ;
        Order order = orderService.findOrderById(orderId);
        order.setOrderStatus(OrderStatus.STARTED);
        orderService.addANewOrder(order);
    }

    @Override
    public void changingTheOrderStatusToCompleted(String customerEmail , String customerPassword , long orderId , RateAndReview rateAndReview ) {
        List<Offer> offers = customerOffers(customerEmail , customerPassword , orderId);
        MyExceptions.checkOffersToFindActiveOffer(offers) ;
        Order order = orderService.findOrderById(orderId);
        order.setCompletionDateOfTask(LocalDate.now());
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderService.addANewOrder(order);
        submitComment(customerEmail , customerPassword , orderId , rateAndReview) ;
    }

    @Override
    public void submitComment(String customerEmail , String customerPassword , long orderId , RateAndReview rateAndReview ) {
        List<Offer> offers = customerOffers(customerEmail , customerPassword , orderId);
        for (Offer offer : offers) {
            if (offer.getOfferStatus().equals(OfferStatus.ACTIVE)) {
                Expert expert = offer.getExpert();
                rateAndReview.setRater(Rater.CUSTOMER);
                expert.setRateAndReviews(rateAndReview) ;
                expertService.addANewExpert(expert);
            }
        }

    }

    @Override
    public List<Customer> searchingAndFilteringTheCustomers(Customer customer) {
        QCustomer myCustomer = QCustomer.customer;
        String email = customer.getEmail();
        String name = customer.getName();
        String lastname = customer.getLastname();
        String username = customer.getUsername();
        AccountStatus accountStatus = customer.getAccountStatus();

        BooleanBuilder booleanBuilder = new BooleanBuilder() ;

        BooleanExpression isEmailSelected ;
        BooleanExpression isNameSelected ;
        BooleanExpression isLastnameSelected ;
        BooleanExpression isUsernameSelected ;
        BooleanExpression isAccessToAccount ;

        if (!StringUtils.isNullOrEmpty(email))
            isEmailSelected=   myCustomer.email.isNotNull();
        if (!StringUtils.isNullOrEmpty(name))
            isNameSelected=   myCustomer.name.isNull() ;
        if (!StringUtils.isNullOrEmpty(lastname))
            isLastnameSelected=   myCustomer.lastname.isNull() ;
        if (!StringUtils.isNullOrEmpty(username))
            isUsernameSelected=   myCustomer.username.isNull() ;
        if (accountStatus.isAccessToAccount())
            isAccessToAccount=   myCustomer.accountStatus.isNotNull().isTrue(); ;

        customerRepository.findAll() ;
        return null;
    }


}
