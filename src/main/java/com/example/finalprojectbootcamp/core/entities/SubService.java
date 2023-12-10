package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Auditing;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class SubService extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    private String title ;
    private BigDecimal price ;
    private String description ;

    public SubService(String title, BigDecimal price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    protected SubService() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_fk" , referencedColumnName = "id")
    private Service service ;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }



    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "expert_fk" , referencedColumnName = "id")
    private Expert expert ;

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }




    @OneToMany
    @JoinColumn(name = "subservice_fk" , referencedColumnName = "id")
    List<Order> orderList = new ArrayList<>() ;

    public List<Order> getOrder() {
        return Collections.unmodifiableList(orderList);
    }

    public SubService setOrder(Order order) {
        this.orderList.add(order) ;
        return this ;
    }
}
