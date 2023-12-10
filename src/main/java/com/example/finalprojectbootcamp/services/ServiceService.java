package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Service;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;



public interface ServiceService {
    /*3*/ void addANewService (Service service) ;
    /*5-1*/ List<Service> np() ; // admin
    /*5-2*/ List<Service> pp() ; // admin

    /*7.1 - 1*/ List <Service> findAllServices () ;
    Service findServicesByName(String name);

    Page<Service> findServicesWithPageSizeAndElementSize(int pageSize, int elementSize);
}
