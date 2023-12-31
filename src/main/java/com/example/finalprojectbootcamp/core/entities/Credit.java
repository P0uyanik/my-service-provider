package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Auditing;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Credit extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {
        return id;
    }
    private BigDecimal creditAmount ;

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }
}
