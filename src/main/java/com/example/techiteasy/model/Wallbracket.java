package com.example.techiteasy.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wallbrackets")
public class Wallbracket {
    @Id
    @GeneratedValue
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;
    @ManyToMany(mappedBy = "wallbrackets")
    private List<Television> televisions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public void setAdjustable(Boolean ajustable) {
        this.adjustable = ajustable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
