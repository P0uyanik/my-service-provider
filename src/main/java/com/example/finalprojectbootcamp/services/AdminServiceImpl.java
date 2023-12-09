
package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.repositories.AdminRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    private ServiceService service;
    private SubServiceService subServiceService;
    private ExpertService expertService;
    private CustomerService customerService;


    @Autowired
    public void setService(ServiceServiceImpl service)
    {
        this.service = service ;
    }
    @Autowired
    public void setSubServiceService (SubServiceServiceImpl subServiceService)
    {
        this.subServiceService = subServiceService ;
    }
    @Autowired
    public  void setExpertService (ExpertServiceImpl expertService)
    {
        this.expertService = expertService ;
    }

    @Autowired
    public  void setCustomerService (CustomerServiceImpl customerService)
    {
        this.customerService = customerService ;
    }



    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findAdminByEmailAndAndPassword(String email, String password) {
        Admin admin = adminRepository.findAdminByEmailAndAndPassword(email, password);
        MyExceptions.isAdminExists(admin) ;
        return admin ;
    }

    @Override
    public void addANewSubService(SubService subService, Service service) {
        subServiceService.addANewSubService(subService, service);
    }

    @Override
    public void addANewService(Service service) {
        this.service.addANewService(service);
    }



    @Override
    public void deleteExpert(Expert expert) {
        expertService.deleteExpert(expert);
    }

    @Override
    public void selectingSubServiceForExpert(String email, SubService subServices) {
        expertService.selectingSubServiceForExpert(email , subServices);
    }

    @Override
    public List<Service> showAllServices(int pageSize) {
        return service.showAllServices(pageSize);
    }

    @Override
    public List<Service> np() {
        return service.np();
    }

    @Override
    public List<Service> pp() {
        return service.pp();
    }

    @Override
    public List<SubService> showAllSubServices(int pageSize) {
        return subServiceService.showSubServices(pageSize);
    }

    @Override
    public List<SubService> ppSubServices() {
        return subServiceService.pp();
    }

    @Override
    public List<SubService> npSubServices() {
        return subServiceService.np();
    }

    @Override
    public List<SubService> findAllSubServices() {
        return subServiceService.findAll();
    }

    @Override
    public int updateSubServiceByDescription(String name, String description) {
        return subServiceService.updateSubServiceByDescription(name , description);
    }

    @Override
    public List<Expert> findExpertByExpertStatus() {
        return expertService.findExpertByExpertStatus();
    }

    @Override
    public int updateExpertStatusByEmail(String email) {
        return expertService.updateExpertStatusByEmail(email);
    }

    @Override
    public List<Expert> searchingAndFilteringTheExperts(Expert expert) {
        return expertService.searchingAndFilteringTheExperts(expert);
    }

    @Override
    public List<Customer> searchingAndFilteringTheCustomers(Customer customer) {
        return customerService.searchingAndFilteringTheCustomers(customer) ;
    }
}


