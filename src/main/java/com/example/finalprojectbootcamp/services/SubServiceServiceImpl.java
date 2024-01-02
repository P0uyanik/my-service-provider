package com.example.finalprojectbootcamp.services;
import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;
import com.example.finalprojectbootcamp.repositories.SubServiceRepository;
import com.example.finalprojectbootcamp.exceptions.MyExceptions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Lazy;


import java.util.List;

@org.springframework.stereotype.Service
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private ServiceService serviceService;
    private static final int PAGE_SIZE =2  ;
    private Lazy<Page<SubService>> currentPage;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository) {
        currentPage = Lazy.of(() ->  subServiceRepository.findAll(Pageable.ofSize(PAGE_SIZE)) ) ;
        this.subServiceRepository = subServiceRepository;
    }

    @Autowired
    public void setServiceServiceImpl(ServiceServiceImpl service) {
        this.serviceService = service;
    }

    public List<SubService> pp() {
        currentPage = currentPage.map(page ->subServiceRepository.findAll(page.previousOrFirstPageable())) ;
        return currentPage.get().getContent() ;
    }
    public List<SubService> np() {
        currentPage = currentPage.map(page ->subServiceRepository.findAll(page.nextOrLastPageable())) ;
        return currentPage.get().getContent() ;
    }

    @Override
    @Transactional
    public void addANewSubService(SubService subService, Service service) {
        Service services = serviceService.findServicesByName(service.getName());
        MyExceptions.isServiceAvailable(services);
        SubService subServiceByTitle = subServiceRepository.findSubServiceByTitle(subService.getTitle());
        MyExceptions.subServiceAlreadyExists(subServiceByTitle) ;
        subService.setService(services);
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
    public int updateSubServiceByDescription(String name, String description) {
        return subServiceRepository.updateSubServiceByDescription(name, description);
    }

    @Override
    public Page<SubService> findSubServicesWithPageSizeAndElementSize(int pageSize, int elementSize) {
        Pageable pageable = PageRequest.of(pageSize, elementSize);
        return subServiceRepository.findAll(pageable);
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
