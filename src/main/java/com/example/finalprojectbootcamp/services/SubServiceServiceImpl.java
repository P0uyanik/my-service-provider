package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;
import com.example.finalprojectbootcamp.repositories.SubServiceRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;


public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private ServiceService serviceService;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository) {
        this.subServiceRepository = subServiceRepository;
    }

    @Autowired
    public void setServiceServiceImpl(ServiceServiceImpl service) {
        this.serviceService = service;
    }

    @Override
    public void addANewSubService(SubService subService, Service service) {
        Service services = serviceService.findServicesById(service.getId());
        MyExceptions.isServiceAvailable(services);
        subService.setService(service);
        subServiceRepository.save(subService);
    }

    @Override
    public int updateSubServiceByPriceAndDescription(long id, String price, String description) {
        return subServiceRepository.updateSubServiceByPriceAndDescription(id, price, description);
    }

    @Override
    public int updateSubServiceByPrice(long id, String price) {
        return subServiceRepository.updateSubServiceByPrice(id , price);
    }

    @Override
    public int updateSubServiceByDescription(long id, String description) {
        return subServiceRepository.updateSubServiceByDescription(id , description);
    }

}
