package com.example.techiteasy.dto;

import jakarta.validation.constraints.*;

public class TelevisionInputDto {
    @NotNull
    private Long id;
    @Size(max = 20, message = "Name must be between 0-20 characters.")
    private String name;
    @NotNull(message = "Type is required.")
    private String type;
    @NotNull(message = "Brand is required.")
    private String brand;
    @Positive(message = "Price must be higher than zero.")
    private double price;
    private int screenSize;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
