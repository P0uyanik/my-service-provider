package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.repositories.ServiceRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Lazy;

import java.util.List;

@org.springframework.stereotype.Service

public class ServiceServiceImpl implements ServiceService {
    private Lazy<Page<Service>> currentPage;
    private final ServiceRepository serviceRepository;
    public static final int PAGE_SIZE = 1;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        currentPage = Lazy.of( () -> serviceRepository.findAll(Pageable.ofSize(PAGE_SIZE)));
        this.serviceRepository = serviceRepository;
    }


    @Override
    public void addANewService(Service service) {
        Service servicesByName = serviceRepository.findServicesByName(service.getName());
        MyExceptions.serviceAlreadyExists(servicesByName);
        serviceRepository.save(service);
    }

    @Override
    public List<Service> pp() {
        currentPage = currentPage.map(page -> serviceRepository.findAll(page.previousPageable()));
        return currentPage.get().getContent();
    }

    @Override
    public List<Service> np() {
        currentPage = currentPage.map(page -> serviceRepository.findAll(page.nextPageable()));
        return currentPage.get().getContent();
    }


    @Override
    public List<Service> findAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service findServicesByName(String name) {
        return serviceRepository.findServicesByName(name);
    }

    @Override
    public Page<Service> findServicesWithPageSizeAndElementSize(int pageSize, int elementSize) {
        Pageable pageable = PageRequest.of(pageSize, elementSize);
        return serviceRepository.findAll(pageable);
    }

}
