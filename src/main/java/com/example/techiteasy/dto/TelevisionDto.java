package com.example.techiteasy.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TelevisionDto {
    @NotNull
    public Long id;
    @NotBlank
    public String name;
    @NotBlank
    public String type;
    @NotBlank
    public String brand;
    @Min(1)
    public double price;
    public int screenSize;
}
