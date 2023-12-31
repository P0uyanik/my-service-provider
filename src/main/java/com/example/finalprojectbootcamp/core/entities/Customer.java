package com.example.finalprojectbootcamp.core.entities;
import com.example.finalprojectbootcamp.core.base.User;
import com.example.finalprojectbootcamp.core.helperClasses.AccountStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@DiscriminatorValue(value = "CUSTOMER")
public class Customer extends User {
    public Customer(String name, String lastname, String username, String email, String password) {
        super(name, lastname, username, email, password);
    }

    protected Customer() {
    }
    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_fk" , referencedColumnName = "id")
    List<Order> orders = new ArrayList<>();


    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
    public Customer setOrders(Order order) {
        orders.add(order) ;
        return  this ;
    }


    @OneToOne(fetch = FetchType.EAGER)
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


}
