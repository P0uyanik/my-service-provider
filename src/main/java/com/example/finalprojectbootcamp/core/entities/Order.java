package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Auditing;
import com.example.finalprojectbootcamp.core.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    private String suggestedPrice;
    private String jobDescription;
    private LocalDate executionTime;
    private String address;
    OrderStatus orderStatus = OrderStatus.WAITING_FOR_EXPERT_BIDS ;

    public Order(String suggestedPrice, String jobDescription, LocalDate executionTime, String address) {
        this.suggestedPrice = suggestedPrice;
        this.jobDescription = jobDescription;
        this.executionTime = executionTime;
        this.address = address;
    }

    protected Order() {
    }

    public String getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(String suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public LocalDate getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(LocalDate executionTime) {
        this.executionTime = executionTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @ManyToOne
    @JoinColumn(name = "customer_fk" , referencedColumnName = "id")
    private Customer customer ;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    @OneToMany
    @JoinColumn(name = "order_fk" , referencedColumnName = "id")
    List<Offer> offers = new ArrayList<>() ;

    public List<Offer> getOffers() {
        return Collections.unmodifiableList(offers);
    }

    public Order setOffers(Offer offer) {
        offers.add(offer) ;
        return this ;
    }




    @ManyToOne
    @JoinColumn(name = "subservice_fk" , referencedColumnName = "id")
    private SubService subService ;

    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }


}
