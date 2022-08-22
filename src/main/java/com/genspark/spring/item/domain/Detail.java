package com.genspark.spring.item.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "details", uniqueConstraints = {@UniqueConstraint(columnNames = "detail_id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    Long id;

    @Column(name = "description")
    String description;

    @Column(name = "img_url")
    String imgUrl;

    @OneToMany(mappedBy = "detail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Review> review;

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detail detail = (Detail) o;

        return id != null ? id.equals(detail.id) : detail.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", review=" + review +
                '}';
    }
}
