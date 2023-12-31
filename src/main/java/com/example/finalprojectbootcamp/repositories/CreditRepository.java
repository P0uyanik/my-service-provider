package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface CreditRepository extends JpaRepository<Credit, Long> {

@Modifying
@Query
        (
                """
                        update Credit c set c.creditAmount =:newBalanceOfCustomer where c.id = ?#{#myCustomerCredit.id}
                                                """
        )

    void deductingCreditFromCustomer(Credit myCustomerCredit, BigDecimal newBalanceOfCustomer);
    @Modifying
    @Query
            (
                    """
                            update Credit c set c.creditAmount =:newBalanceOfExpert where c.id = ?#{#myExpertcredit.id}
                                                    """
            )
    void addingCreditToExpert(Credit myExpertcredit, BigDecimal newBalanceOfExpert);
}
