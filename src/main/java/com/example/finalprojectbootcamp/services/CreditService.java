package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Offer;
import java.util.List;


public interface CreditService {
    void payingAmountWithCredit (List<Offer> offers , Customer myCustomer ) ;
}
