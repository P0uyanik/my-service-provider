package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import org.springframework.data.domain.Page;

import java.util.List;


public interface AdminService {

    void addANewAdmin ( Admin admin ) ;
    Admin findAdminByEmailAndAndPassword(String email, String password);

    void addANewSubService(SubService subService, Service service);

    void addANewService(Service service);

    void deleteExpert(long id);

    void selectingSubServiceForExpert(String email, String subServicesTitle);// admin

    List<Service> np();

    List<Service> pp();

    Page<SubService> findSubServicesWithPageSizeAndElementSize(int pageSize, int elementSize);

    List<SubService> ppSubServices();

    List<SubService> npSubServices();

    List<SubService> findAllSubServices();

    int updateSubServiceByDescription(String name, String description);

    List<Expert> findExpertByExpertStatus();

    int updateExpertStatusByEmail(String email);

    List<Expert> searchingAndFilteringTheExperts(Expert expert);

    List<Customer> searchingAndFilteringTheCustomers(Customer customer);

    Page<Service> findServicesWithPageSizeAndElementSize(int pageSize, int elementSize);
}
