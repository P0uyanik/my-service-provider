package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;


import java.util.List;

@org.springframework.stereotype.Service
public interface CustomerService {
   /*1*/ void addANewCustomer (Customer customer) ;
   /*2*/ int updateCustomerByPassword( long id , String password) ;


   /*7*/ List<Service> showAllServicesWithNavigation(int pageSize) ;
   /*7-1*/public List<Service> npService() ;
   /*7-2*/public List<Service> ppService() ;
   /*7-1*/  List<SubService> showSubServices (int pageSize) ;
   /*7-2*/ List<SubService> ppSubService() ;
   /*7-3*/ List<SubService> npSubService() ;
   /*7.1-1*/ List<Service> findAllServices() ;
   Customer findCustomerById(long id) ;
   /*7.1-2*/void registrationOfTheOrder (long serviceId , long subServiceId ,  long customerId , Order order ) ;

}
