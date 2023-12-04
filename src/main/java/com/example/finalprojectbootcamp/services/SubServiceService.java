package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@org.springframework.stereotype.Service
public interface SubServiceService {
    /*3*/ void addANewSubService (SubService subService , Service service ) ;
    /*5-1*/int updateSubServiceByPriceAndDescription(long id, String price, String description);

    /*5-1*/ int updateSubServiceByPrice(long id, String price);

    /*5-1*/ int updateSubServiceByDescription(long id, String description);
    /*7-1*/  List<SubService> showSubServices (int pageSize) ; // admin
    /*7-2*/ List<SubService> pp() ; // admin
    /*7-3*/ List<SubService> np() ; // admin
    /*7-4*/    List<SubService>findAll() ; // admin

    SubService findSubServiceByTitle(String title);
}
