package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.core.enums.OfferStatus;
import com.example.finalprojectbootcamp.exceptions.MyExceptions;
import com.example.finalprojectbootcamp.repositories.CreditRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;

    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    @Transactional
    public void payingAmountWithCredit(List<Offer> offers, Customer myCustomer) {
        Offer selectedOffer = offers.stream().filter(offer -> offer.getOfferStatus().equals(OfferStatus.ACTIVE)).findFirst().orElse(null);
        MyExceptions.isOfferExists(selectedOffer);
        Expert myExpert = selectedOffer.getExpert();
        Credit myCustomerCredit = myCustomer.getCredit();
        BigDecimal creditAmountOfCustomer = myCustomerCredit.getCreditAmount();
        Credit myExpertcredit = myExpert.getCredit();
        BigDecimal creditAmountOfExpert = myExpertcredit.getCreditAmount() ;
        BigDecimal suggestedPrice = selectedOffer.getSuggestedPrice();
        MyExceptions.checkingAccountBalance(creditAmountOfCustomer, suggestedPrice);
        BigDecimal newBalanceOfCustomer = creditAmountOfCustomer.subtract(suggestedPrice)  ;
        creditRepository.deductingCreditFromCustomer(myCustomerCredit, newBalanceOfCustomer) ;
        BigDecimal newBalanceOfExpert = creditAmountOfExpert.add(suggestedPrice);
        creditRepository.addingCreditToExpert (myExpertcredit , newBalanceOfExpert);

    }
}
