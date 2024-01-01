package com.example.finalprojectbootcamp.controllers;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.dto.*;
import com.example.finalprojectbootcamp.exceptions.*;
import com.example.finalprojectbootcamp.mapper.*;
import com.example.finalprojectbootcamp.services.CustomerService;
import com.example.finalprojectbootcamp.services.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("add_new_customer")
    void addANewCustomer(@RequestBody @Valid CustomerDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.toCustomer(customerDto);
        customerService.addANewCustomer(customer);
    }

    @GetMapping("find-customer")
    ResponseEntity<?> findCustomerByEmailAndPassword(@RequestBody SignInDto signInDto) {
        Customer customerByEmailAndPassword = customerService.findCustomerByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword());
        CustomerDto customerDto = CustomerMapper.INSTANCE.toCustomerDto(customerByEmailAndPassword);
        return ResponseEntity.ok(customerDto);
    }

    @PutMapping("update-customer-by-password")
    ResponseEntity<?> updateCustomerByPassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        int i = customerService.updateCustomerByPassword(updatePasswordDto.getEmail(), updatePasswordDto.getOldPassword(), updatePasswordDto.getPassword());
        return ResponseEntity.ok(updatePasswordDto);
    }

    //ok
    @GetMapping("find-all-services")
    ResponseEntity<?> findAllServices() {
        List<Service> allServices = customerService.findAllServices();
        List<ServiceDto> serviceDtoAsList = ServiceMapper.INSTANCE.toServiceDtoAsList(allServices);
        return ResponseEntity.ok(serviceDtoAsList);
    }

    @PostMapping("order-registeration")
    void registrationOfTheOrder(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("service-name") String serviceName, @RequestParam("sub-service-name") String subServiceName, @Valid @RequestBody OrderDto orderDto) {
        Order order = OrderMapper.INSTANCE.toOrder(orderDto);
        customerService.registrationOfTheOrder(customerEmail, customerPassword, serviceName, subServiceName, order);
    }


    //0k
    @PostMapping("select-offer")
    void selectingOffer(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("order-id") long orderId, @RequestParam("offer-id") long offerId) {
        customerService.selectingOffer(customerEmail, customerPassword, orderId, offerId);
    }

    @GetMapping("customer-offers")
    public ResponseEntity<?> customerOffers(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("order-id") long orderId) {
        List<Offer> offers = customerService.customerOffers(customerEmail, customerPassword, orderId);
        List<OfferDto> offerDtoAsList = OfferMapper.INSTANCE.toOfferDtoAsList(offers);
        return ResponseEntity.ok(offerDtoAsList);
    }

    @PutMapping("cancel-offer")
    void cancellingAnOffer(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("order-id") long orderId, @RequestParam("offer-id") long offerId) {
        customerService.cancellingAnOffer(customerEmail, customerPassword, orderId, offerId);
    }


    //ok
    @PutMapping("change-order-status-to-started")
    void changingTheOrderStatusToStarted(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("order-id") long orderId) {
        customerService.changingTheOrderStatusToStarted(customerEmail, customerPassword, orderId);
    }

    //ok
    @PutMapping("change-order-status-to-completed")
    void changingTheOrderStatusToCompleted(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("order-id") long orderId, @Valid @RequestBody RateAndReviewDto rateAndReviewDto) {
        RateAndReview rateAndReview = RateAndReviewMapper.INSTANCE.toRateAndReview(rateAndReviewDto);
        customerService.changingTheOrderStatusToCompleted(customerEmail, customerPassword, orderId, rateAndReview);
    }


    @PostMapping("rating_and_review_for_expert")
    void ratingAndReviewForExpert(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword ,
                                  @Valid @RequestBody RateAndReviewOrderOfferDto rateAndReviewOrderOfferDto) {
        RateAndReview rateAndReview = RateAndReviewMapper.INSTANCE.toRateAndReview(rateAndReviewOrderOfferDto.getRateAndReviewDto());
        Order order = OrderMapper.INSTANCE.toOrder(rateAndReviewOrderOfferDto.getOrderDto());
        Offer offer = OfferMapper.INSTANCE.toOffer(rateAndReviewOrderOfferDto.getOfferDto());
        customerService.ratingAndReviewForExpert(rateAndReview , customerEmail, customerPassword, order, offer);
    }


    @GetMapping("next-service-page")
    public ResponseEntity<?> npService() {
        List<Service> services = customerService.npService();
        List<ServiceDto> serviceDtoAsList = ServiceMapper.INSTANCE.toServiceDtoAsList(services);
        return ResponseEntity.ok(serviceDtoAsList);
    }

    @GetMapping("previous-service-page")
    public ResponseEntity<?> ppService() {
        List<Service> services = customerService.ppService();
           List<ServiceDto> serviceDtoAsList = ServiceMapper.INSTANCE.toServiceDtoAsList(services);
        return ResponseEntity.ok(serviceDtoAsList);
    }

    @GetMapping("show-all-sub-services")
    ResponseEntity<?> showAllSubServices(@RequestParam("page-size") Optional <Integer> maybePageSize , @RequestParam ("element-size") Optional <Integer> maybeElementSize) {        int pageSize = maybePageSize.orElse(0);
        int elementSize = maybeElementSize.orElse(5);
        Page<SubService> subServicesWithPageSizeAndElementSize = customerService.findSubServicesWithPageSizeAndElementSize(pageSize, elementSize);
        List<SubService> mySubServices = subServicesWithPageSizeAndElementSize.getContent();
        List<SubServiceDto> subServiceDtoAsList = SubServiceMapper.INSTANCE.toSubServiceDtoAsList(mySubServices);
        return ResponseEntity.ok(subServiceDtoAsList);
    }


    @GetMapping("previous-sub-service-page")
    ResponseEntity<?> ppSubService() {
        List<SubService> subServices = customerService.ppSubService();
        List<SubServiceDto> subServiceDtoAsList = SubServiceMapper.INSTANCE.toSubServiceDtoAsList(subServices);
        return ResponseEntity.ok(subServiceDtoAsList);

    }

    @GetMapping("next-sub-service-page")
    ResponseEntity<?> npSubService() {
        List<SubService> subServices = customerService.npSubService();
            List<SubServiceDto> subServiceDtoAsList = SubServiceMapper.INSTANCE.toSubServiceDtoAsList(subServices);
        return ResponseEntity.ok(subServiceDtoAsList);
    }

    @PutMapping ("paying-amount-with-credit")
    void payingAmountWithCredit(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("order-id") long orderId) {
        customerService.payingAmountWithCredit(customerEmail, customerPassword, orderId);
    }


/*

    @PutMapping ("charging-credit")
    void chargingCredit(@RequestParam("email") String customerEmail, @RequestParam("password") String customerPassword, @RequestParam("order-id") long orderId) {
        customerService.payingAmountWithCredit(customerEmail, customerPassword, orderId);
    }


 */



















// chera list i az offer ha ra dashte bashim

    @PostMapping("submit-comment")
    void submitComment(@Valid @RequestBody List<Offer> offerDtoS, @Valid @RequestBody Order orderDto, @Valid @RequestBody RateAndReview rateAndReviewDto) {
        //  RateAndReview rateAndReview = RateAndReviewMapper.INSTANCE.toRateAndReview(rateAndReviewDto);
        // Order order = OrderMapper.INSTANCE.toOrder(orderDto);
        //   List<Offer> offerAsList = OfferMapper.INSTANCE.toOfferAsList(offerDtoS);
        customerService.submitComment(offerDtoS, orderDto, rateAndReviewDto);
    }





    @ExceptionHandler
    public ResponseEntity<?> customerNotFoundException(CustomerNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("No customer with these specifications exists");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> orderNotFoundException(OrderNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("No order with these specifications exists");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> rateAndReviewException(RateAndReviewException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("It is not possible to leave a review or provide a rating at the moment.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> offerNotFoundException(OfferNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("No offer with these specifications exists");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }


    @ExceptionHandler
    public ResponseEntity<?> orderCommentException(OrderCommentException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The task must be completed first before a review can be submitted for it.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> activeOfferException(ActiveOfferException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("There is no active offer for this order.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> startTimeException(StartTimeException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The entered StartTime is not correct. The start time of the work should be after the declared time.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> selectedOfferException(SelectedOfferException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("The selected offer is not correct.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> offerNotMatch(OfferNotMatch e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The offer and order do not match each other. Please review them again");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> orderNotFoundForCustomer(OrderNotFoundForCustomer e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("No order with these specifications exists");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> serviceNotFoundException(ServiceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("No service with these specifications exists");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> subServiceNotFoundException(SubServiceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("No sub-service with these specifications exists.");
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


}
