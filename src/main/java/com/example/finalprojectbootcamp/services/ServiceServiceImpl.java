package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.repositories.ServiceRepository;

import java.util.Optional;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository ;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    @Override
    public void addANewService(Service service) {
        serviceRepository.save(service) ;
    }

    @Override
    public Service findServicesById (long id) {
        return serviceRepository.findServicesById(id);
    }
}
