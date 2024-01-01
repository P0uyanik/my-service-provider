package com.example.finalprojectbootcamp.controllers;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.core.entities.Order;
import com.example.finalprojectbootcamp.dto.ExpertDto;
import com.example.finalprojectbootcamp.dto.OfferDto;
import com.example.finalprojectbootcamp.dto.OrderDto;
import com.example.finalprojectbootcamp.dto.UpdatePasswordDto;
import com.example.finalprojectbootcamp.mapper.ExpertMapper;
import com.example.finalprojectbootcamp.mapper.OfferMapper;
import com.example.finalprojectbootcamp.mapper.OrderMapper;
import com.example.finalprojectbootcamp.services.ExpertService;
import com.example.finalprojectbootcamp.exceptions.ExpertAccessException;
import com.example.finalprojectbootcamp.exceptions.NegativeRatingException;
import com.example.finalprojectbootcamp.exceptions.OrderNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expert")
public class ExpertController {
    private final ExpertService expertService;

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }


    @PostMapping("add-new-expert")
    void addANewExpert(@RequestBody @Valid ExpertDto expertDto) {
        Expert expert = ExpertMapper.INSTANCE.toExpert(expertDto);
        expertService.addANewExpert(expert);
    }


    //ok
    @PostMapping("submitting-offer-for-order")
    void submittingOfferForOrder(@RequestParam("email") String expertEmail, @RequestParam("password") String expertPassword, @RequestParam("order-id") long orderId, @RequestBody @Valid OfferDto offerDto) {
        Offer offer = OfferMapper.INSTANCE.toOffer(offerDto);
        expertService.submittingOfferForOrder(expertEmail, expertPassword, orderId, offer);
    }


    //ok
    @GetMapping("show-orders-for-expert")
    ResponseEntity<?> showAllOrdersForExpert() {
        List<Order> orders = expertService.showAllOrdersForExpert();
          List<OrderDto> orderDtoAsList = OrderMapper.INSTANCE.toOrderDtoAsList(orders);
        return ResponseEntity.ok(orderDtoAsList);
    }




    //ok
    @GetMapping("find-expert-by-email-password")
    ResponseEntity<?> findExpertByEmailAndPassword(@RequestParam("email") String expertEmail, @RequestParam("password") String expertPassword) {
        Expert expert = expertService.findExpertByEmailAndPassword(expertEmail, expertPassword);
         ExpertDto expertDto = ExpertMapper.INSTANCE.toExpertDto(expert);
        return ResponseEntity.ok(expertDto);
    }

    @PutMapping("update-expert-by-password")
    int updateExpertByPassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        return expertService.updateExpertByPassword(updatePasswordDto.getEmail(), updatePasswordDto.getOldPassword() , updatePasswordDto.getPassword());
    }

    @GetMapping("check-expert-rating/{id}")
    double checkExpertRating(@PathVariable("id") long expertId) {
        return expertService.checkExpertRating(expertId);
    }


    @ExceptionHandler
    public ResponseEntity<?> orderNotFoundException(OrderNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("No order with these specifications exists");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> expertAccessException(ExpertAccessException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The expert in question does not have access to the system due to either having a negative rating or the account being inactive.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> negativeRatingException(NegativeRatingException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("The desired expert has a negative rating, and for this reason, their access has been blocked.");
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }


}
