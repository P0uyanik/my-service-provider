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



}
