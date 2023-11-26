package com.example.finalprojectbootcamp.core.entities;

import com.example.finalprojectbootcamp.core.base.Auditing;
import jakarta.persistence.*;

@Entity
public class SubService extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    private String price ;
    private String description ;

    public SubService(String price, String description) {
        this.price = price;
        this.description = description;
    }

    protected SubService() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne
    @JoinColumn(name = "service_fk" , referencedColumnName = "id")
    private Service service ;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }



    @ManyToOne
    @JoinColumn(name = "expert_fk" , referencedColumnName = "id")
    private Expert expert ;

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }
}
