package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.repositories.ServiceRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
@org.springframework.stereotype.Service

public class ServiceServiceImpl implements ServiceService {
    private Page<Service> currentPage;
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    @Override
    public void addANewService(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public Service findServicesById(long id) {
        Service servicesById = serviceRepository.findServicesById(id);
        MyExceptions.isServiceAvailable(servicesById);
        return servicesById ;
    }


    private Page<Service> findServicesWithPageSize(int pageSize) {
        return serviceRepository.findAll(Pageable.ofSize(pageSize));
    }
    @Override
    public List<Service> pp() {
        return serviceRepository.findAll(currentPage.previousPageable()).getContent();
    }

    @Override
    public List<Service> findAllServices() {
        return serviceRepository.findAll() ;
    }

    @Override
    public Service findServicesByName(String name) {
        return serviceRepository.findServicesByName(name);
    }

    @Override
    public List<Service> np() {
        return serviceRepository.findAll(currentPage.nextPageable()).getContent();
    }

    @Override
    public List<Service> showAllServices(int pageSize) {
        MyExceptions.pageSizeIsNotCorrect(pageSize);

        if (serviceRepository.count()  < pageSize)
            pageSize = (int) serviceRepository.count() ;

        this.currentPage = findServicesWithPageSize(pageSize);

        return currentPage.getContent();
    }
}
