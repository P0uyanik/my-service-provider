package com.example.finalprojectbootcamp.controllers;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.dto.*;
import com.example.finalprojectbootcamp.exceptions.*;
import com.example.finalprojectbootcamp.mapper.*;
import com.example.finalprojectbootcamp.services.AdminService;
import com.example.finalprojectbootcamp.services.AdminServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(("/admin"))
@Slf4j
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/find-admin")
    ResponseEntity<?> findAdminByEmailAndAndPassword(@RequestBody @Valid SignInDto signInDto) {
        String email = signInDto.getEmail();
        String password = signInDto.getPassword();
        Admin admin = adminService.findAdminByEmailAndAndPassword(email, password);
        AdminDto adminDto = AdminMapper.INSTANCE.toAdminDto(admin);
        return ResponseEntity.status(HttpStatus.OK).body(adminDto);
    }

    @PostMapping("add-new-admin")
    ResponseEntity<?> addANewAdmin(AdminDto adminDto) {
        Admin admin = AdminMapper.INSTANCE.toAdmin(adminDto);
        adminService.addANewAdmin(admin);
        return ResponseEntity.status(HttpStatus.OK).body(adminDto);
    }

    @PostMapping("add-new-service")
    ResponseEntity<?> addANewService(@RequestBody @Valid ServiceDto serviceDto) {
        Service service = ServiceMapper.INSTANCE.toService(serviceDto);
        adminService.addANewService(service);
        return ResponseEntity.status(HttpStatus.OK).body(serviceDto);
    }

    @PostMapping("add-new-sub-service")
    ResponseEntity<?> addANewSubService(@RequestBody @Valid SubServiceAndServiceDtoS request) {
        ServiceDto serviceDto = request.getServiceDto();
        SubServiceDto subServiceDto = request.getSubServiceDto();
        SubService subService = SubServiceMapper.INSTANCE.toSubService(subServiceDto);
        Service service = ServiceMapper.INSTANCE.toService(serviceDto);
        adminService.addANewSubService(subService, service);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("delete-expert/{id}")
    void deleteExpert(@PathVariable("id") long id) {
        adminService.deleteExpert(id);
    }


    @GetMapping("experts-by-status-code")
    ResponseEntity<?> findExpertsAwaitingApproval() {
        List<Expert> expertByExpertStatus = adminService.findExpertByExpertStatus();
        ExpertMapper instance = ExpertMapper.INSTANCE;
        List<ExpertDto> expertDtoList = expertByExpertStatus.stream().map(instance::toExpertDto).toList();
        return ResponseEntity.ok(expertDtoList);
    }


    @PutMapping("update-ExpertStatus/{email}")
    void updateExpertStatusByEmail(@PathVariable("email") String email) {
        int i = adminService.updateExpertStatusByEmail(email);
    }

    @PostMapping("select-sub-service-for-expert/{email}")
    void selectingSubServiceForExpert(@PathVariable("email") String email, @RequestParam("subServicesTitle") String subServicesTitle) {
        adminService.selectingSubServiceForExpert(email, subServicesTitle);
    }


    @PutMapping("update-sub-service-description/{name}")
    int updateSubServiceByDescription(@PathVariable("name") String name, @RequestParam("description") String description) {
        return adminService.updateSubServiceByDescription(name, description);
    }

    @GetMapping("all-sub-services")
    ResponseEntity<?> findAllSubServices() {
        List<SubService> allSubServices = adminService.findAllSubServices();
        List<SubServiceDto> subServiceDtoAsList = SubServiceMapper.INSTANCE.toSubServiceDtoAsList(allSubServices);
        if (subServiceDtoAsList == null)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(subServiceDtoAsList);
    }


    @GetMapping("show-services-with-page-size-element-size")
    ResponseEntity<?> showServicesWithPageSizeElementSIze(@RequestParam("page-size") Optional<Integer> maybePageSize, @RequestParam("element-size") Optional<Integer> maybeElementSize) {
        int pageSize = maybePageSize.orElse(0);
        int elementSize = maybeElementSize.orElse(5);
        Page<Service> servicesWithPageSizeAndElementSize = adminService.findServicesWithPageSizeAndElementSize(pageSize, elementSize);
        List<Service> serviceList = servicesWithPageSizeAndElementSize.getContent();
        List<ServiceDto> serviceDtoAsList = ServiceMapper.INSTANCE.toServiceDtoAsList(serviceList);
        return ResponseEntity.ok(serviceDtoAsList);
    }

    @GetMapping("next-service-page")
    ResponseEntity<?> npService() {
        List<Service> np = adminService.np();
        List<ServiceDto> serviceDtoAsList = ServiceMapper.INSTANCE.toServiceDtoAsList(np);
        return ResponseEntity.ok(serviceDtoAsList);
    }

    @GetMapping("previous-service-page")
    ResponseEntity<?> ppService() {
        List<Service> pp = adminService.pp();
        List<ServiceDto> serviceDtoAsList = ServiceMapper.INSTANCE.toServiceDtoAsList(pp);
        return ResponseEntity.ok(serviceDtoAsList);
    }

    @GetMapping("show-sub-services-with-page-size-element-size")
    ResponseEntity<?> showAllSubServices(@RequestParam("page-size") Optional<Integer> maybePageSize, @RequestParam("element-size") Optional<Integer> maybeElementSize) {
        int pageSize = maybePageSize.orElse(0);
        int elementSize = maybeElementSize.orElse(5);
        Page<SubService> subServicesWithPageSizeAndElementSize = adminService.findSubServicesWithPageSizeAndElementSize(pageSize, elementSize);
        List<SubService> mySubServices = subServicesWithPageSizeAndElementSize.getContent();
        List<SubServiceDto> subServiceDtoAsList = SubServiceMapper.INSTANCE.toSubServiceDtoAsList(mySubServices);
        return ResponseEntity.ok(subServiceDtoAsList);
    }

    @GetMapping("next-sub-service-page")
    ResponseEntity<?> npSubServices() {
        List<SubService> subServices = adminService.npSubServices();
        List<SubServiceDto> subServiceDtoAsList = SubServiceMapper.INSTANCE.toSubServiceDtoAsList(subServices);
        return ResponseEntity.ok(subServiceDtoAsList);
    }


    @GetMapping("previous_sub_service_page")
    ResponseEntity<?> ppSubServices() {
        List<SubService> subServices = adminService.ppSubServices();
        List<SubServiceDto> subServiceDtoAsList = SubServiceMapper.INSTANCE.toSubServiceDtoAsList(subServices);
        return ResponseEntity.ok(subServiceDtoAsList);
    }


    @GetMapping("search_experts")
    ResponseEntity<?> searchingSortedExperts() {
        Iterable<Expert> experts = adminService.searchingSortedExperts();
        List<ExpertDto> expertDtoAsList = ExpertMapper.INSTANCE.toExpertDtoAsList((List<Expert>) experts);
        return ResponseEntity.ok(expertDtoAsList);
    }

    @GetMapping("search_customers")
    ResponseEntity<?> searchingSortedCustomers() {
        Iterable<Customer> customers = adminService.searchingSortedCustomers();
        List<CustomerDto> customerDtoAsList = CustomerMapper.INSTANCE.toCustomerDtoAsList((List<Customer>) customers);
        return ResponseEntity.ok(customerDtoAsList);
    }

    @ExceptionHandler
    public ResponseEntity<?> adminNotFoundException(AdminNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("admin not Found");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> expertAccessException(ExpertAccessException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The expert does not have access.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> subServiceNotFoundException(SubServiceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("The SubService does not have access.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> pageSizeException(PageSizeException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The page size has not been entered correctly and is out of bounds");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> subServiceExistsException(ExistsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The sub-service or service selected for addition to the service is currently available, and it cannot be added again.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }


}
