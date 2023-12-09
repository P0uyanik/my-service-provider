package com.example.finalprojectbootcamp.core.entities;
import com.example.finalprojectbootcamp.core.MyEnumsConverter.OfferStatusToStringConverter;
import com.example.finalprojectbootcamp.core.base.Auditing;
import com.example.finalprojectbootcamp.core.enums.OfferStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Offer extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert (converter = OfferStatusToStringConverter.class)
    OfferStatus offerStatus = OfferStatus.DEACTIVE;

    public Long getId() {
        return id;
    }

    private BigDecimal suggestedPrice;
    private LocalDate startTime;
    private int durationOfTheJobInDays;

    protected Offer() {
    }

    public BigDecimal getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(BigDecimal suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public int getDurationOfTheJobInDays() {
        return durationOfTheJobInDays;
    }

    public void setDurationOfTheJobInDays(int durationOfTheJobInDays) {
        this.durationOfTheJobInDays = durationOfTheJobInDays;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    @ManyToOne
    @JoinColumn(name = "expert_fk", referencedColumnName = "id")
    private Expert expert;

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    @ManyToOne
    @JoinColumn(name = "order_fk", referencedColumnName = "id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
