package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.core.entities.SubService;
import java.util.List;



public interface SubServiceService {
    /*3*/ void addANewSubService (SubService subService , Service service ) ; // admin
    /*5-1*/int updateSubServiceByPriceAndDescription(long id, String price, String description); // admin

    /*5-1*/ int updateSubServiceByPrice(long id, String price); // admin

    /*5-1*/ int updateSubServiceByDescription(String name, String description); // admin
    /*7-1*/  List<SubService> showSubServices (int pageSize) ; // admin
    /*7-2*/ List<SubService> pp() ; // admin
    /*7-3*/ List<SubService> np() ; // admin
    /*7-4*/    List<SubService>findAll() ; // admin

    SubService findSubServiceByTitle(String title);
}
