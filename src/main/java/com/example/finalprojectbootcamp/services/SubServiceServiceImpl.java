package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;
import com.example.finalprojectbootcamp.repositories.SubServiceRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private ServiceService serviceService;
    private Page<SubService> currentPage;

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
        return subServiceRepository.updateSubServiceByPrice(id, price);
    }

    @Override
    public int updateSubServiceByDescription(long id, String description) {
        return subServiceRepository.updateSubServiceByDescription(id, description);
    }

    @Override
    public List<SubService> showSubServices(int pageSize) {
        MyExceptions.pageSizeIsNotCorrect(pageSize);
        if (subServiceRepository.count()  < pageSize)
            pageSize = (int) subServiceRepository.count() ;

        return subServiceRepository.findAll(Pageable.ofSize(pageSize)).getContent();
    }

    public List<SubService> pp() {
        return subServiceRepository.findAll(currentPage.previousPageable()).getContent() ;
    }
    public List<SubService> np() {
        return subServiceRepository.findAll(currentPage.nextPageable()).getContent() ;
    }

    @Override
    public List<SubService> findAll() {
        return subServiceRepository.findAll() ;
    }

    @Override
    public SubService findSubServiceByTitle(String title) {
        SubService subServiceByTitle = subServiceRepository.findSubServiceByTitle(title);
        MyExceptions.isSubServiceAvailable(subServiceByTitle);
        return subServiceByTitle ;
    }

}
