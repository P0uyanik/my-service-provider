package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Service;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;



public interface ServiceService {
    void addANewService (Service service) ;
    List<Service> np() ;
     List<Service> pp() ;

   List <Service> findAllServices () ;
    Service findServicesByName(String name);

    Page<Service> findServicesWithPageSizeAndElementSize(int pageSize, int elementSize);
}
