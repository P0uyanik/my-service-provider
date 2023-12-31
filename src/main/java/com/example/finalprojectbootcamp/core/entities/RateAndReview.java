package com.example.finalprojectbootcamp.core.entities;
import com.example.finalprojectbootcamp.core.MyEnumsConverter.RaterToStringConverter;
import com.example.finalprojectbootcamp.core.base.Auditing;
import com.example.finalprojectbootcamp.core.enums.Rater;
import jakarta.persistence.*;

@Entity
public class RateAndReview extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    private int rating;
    private String review;
    @Convert (converter = RaterToStringConverter.class)
    Rater rater;

    public RateAndReview(int rating , Rater rater) {
        this.rating = rating;
        this.rater = rater ;
    }

    public RateAndReview(int rating, String review , Rater rater) {
        this.rating = rating;
        this.review = review;
        this.rater = rater ;
    }

    public RateAndReview() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
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
