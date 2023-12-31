package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;
import org.springframework.data.domain.Page;

import java.util.List;


public interface SubServiceService {
    void addANewSubService(SubService subService, Service service);

    int updateSubServiceByPriceAndDescription(long id, String price, String description);

    int updateSubServiceByPrice(long id, String price);

    int updateSubServiceByDescription(String name, String description);

    Page<SubService> findSubServicesWithPageSizeAndElementSize(int pageSize, int elementSize);

    List<SubService> pp();

    List<SubService> np();

    List<SubService> findAll();

    SubService findSubServiceByTitle(String title);
}
