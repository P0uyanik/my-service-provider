package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;

import java.util.List;


public interface AdminService {
    Admin findAdminByEmailAndAndPassword(String email , String password) ;
    /*3*/ void addANewSubService (SubService subService , Service service ) ;
    /*3*/ void addANewService ( Service service) ;

    /*4*/   void deleteExpert(long id)  ;




    void selectingSubServiceForExpert (String email , SubService subServices) ;// admin


    /*5-1*/ List<Service> np() ;
    /*5-2*/ List<Service> pp() ;

    /*7-1*/  List<SubService> showAllSubServices (int pageSize) ;
    /*7-2*/ List<SubService> ppSubServices() ;
    /*7-3*/ List<SubService> npSubServices() ;
    List<SubService>findAllSubServices() ;
    /*5-1*/ int updateSubServiceByDescription(String name, String description);
    /*6*/ List<Expert> findExpertByExpertStatus();
    /*6-1*/ int updateExpertStatusByEmail(String email)  ;
    List <Expert> searchingAndFilteringTheExperts (Expert expert) ;
    List <Customer> searchingAndFilteringTheCustomers (Customer customer) ;
}
