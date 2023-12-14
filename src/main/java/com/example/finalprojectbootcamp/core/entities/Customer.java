package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Person;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;
import com.example.finalprojectbootcamp.core.helperClasses.AccountStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Customer extends User {
    private Role role = Role.CUSTOMER ;
    public Customer(String name, String lastname, String username, String email, String password) {
        super(name, lastname, username, email, password);
    }

    protected Customer() {
    }
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_fk" , referencedColumnName = "id")
    List<Order> orders = new ArrayList<>();


    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
    public Customer setOrders(Order order) {
        orders.add(order) ;
        return  this ;
    }


    @OneToOne
    @JoinColumn(name = "credit_fk" , referencedColumnName = "id")
    private Credit credit ;

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }


    @OneToMany
    @JoinColumn(name = "costumer_fk" ,referencedColumnName = "id")
    List<RateAndReview> rateAndReviews = new ArrayList<>() ;

    public List<RateAndReview> getRateAndReviews() {
        return Collections.unmodifiableList(rateAndReviews);
    }

    public Customer setRateAndReviews(RateAndReview rateAndReview) {
        rateAndReviews.add(rateAndReview) ;
        return this ;
    }

    @Embedded
    private AccountStatus accountStatus;
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
