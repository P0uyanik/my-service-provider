package com.example.finalprojectbootcamp.core.entities;
import com.example.finalprojectbootcamp.core.MyEnumsConverter.ExpertStatusToStringConverter;
import com.example.finalprojectbootcamp.core.base.User;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;
import com.example.finalprojectbootcamp.core.helperClasses.AccountStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@DiscriminatorValue(value = "EXPERT")
public class Expert extends User {
    private Role role = Role.EXPERT ;
    @Convert (converter = ExpertStatusToStringConverter.class)
    private ExpertStatus expertStatus = ExpertStatus.NEW;
    private boolean accessToTheSystem = false ;


    @Embedded
    private AccountStatus accountStatus;
    public Expert(String name, String lastname, String username, String email, String password) {
        super(name, lastname, username, email, password);
    }

    protected Expert() {
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "expert_fk", referencedColumnName = "id")
    List<Offer> offers;

    public List<Offer> getOffers() {
        return Collections.unmodifiableList(offers);
    }

    public Expert setOffers(Offer offer) {
        offers.add(offer) ;
        return this ;
    }
    public ExpertStatus getExpertStatus() {
        return expertStatus;
    }

    public void setExpertStatus(ExpertStatus expertStatus) {
        this.expertStatus = expertStatus;
    }


    @OneToMany (cascade = {CascadeType.MERGE , CascadeType.PERSIST } )
    @JoinColumn(name = "expert_fk" , referencedColumnName = "id")
    List<SubService> subServices = new ArrayList<>() ;

    public List<SubService> getSubServices() {
        return Collections.unmodifiableList(subServices);
    }

    public Expert setSubServices(SubService subService) {
        subServices.add(subService)  ;
        return this ;
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
    @JoinColumn(name = "expert_fk" ,referencedColumnName = "id")
    List<RateAndReview> rateAndReviews = new ArrayList<>() ;

    public List<RateAndReview> getRateAndReviews() {
        return Collections.unmodifiableList(rateAndReviews);
    }

    public Expert setRateAndReviews(RateAndReview rateAndReview) {
        rateAndReviews.add(rateAndReview) ;
        return this ;
    }



    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }







    public boolean isAccessToTheSystem() {
        return accessToTheSystem;
    }

    public void setAccessToTheSystem(boolean accessToTheSystem) {
        this.accessToTheSystem = accessToTheSystem;
    }


}
