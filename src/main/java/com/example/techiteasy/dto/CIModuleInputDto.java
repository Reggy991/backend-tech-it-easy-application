package com.example.techiteasy.dto;

import com.example.techiteasy.model.Television;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CIModuleInputDto {
    @NotNull
    private Long id;
    @Size(max = 20, message = "Name must be between 0-20 characters.")
    private String name;
    @NotNull(message = "Type is required.")
    private String type;
    @Positive(message = "Price must be higher than zero.")
    private Double price;
    private Television television;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Television getTelevision() {
        return television;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }
}
