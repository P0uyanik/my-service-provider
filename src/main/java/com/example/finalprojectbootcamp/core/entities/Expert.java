package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Person;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
public class Expert extends Person {

    private ExpertStatus expertStatus = ExpertStatus.NEW;

    public Expert(String name, String lastname, String username, String email, String password, ExpertStatus expertStatus) {
        super(name, lastname, username, email, password);
        this.expertStatus = expertStatus;
    }

    protected Expert() {
        super();
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


    @OneToOne
    @JoinColumn(name = "credit_fk" , referencedColumnName = "id")
    private Credit credit ;

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}
