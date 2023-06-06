package com.example.techiteasy.dto;

import com.example.techiteasy.model.CIModule;
import com.example.techiteasy.model.RemoteController;
import com.example.techiteasy.model.Wallbracket;
import jakarta.validation.constraints.*;

import java.util.List;

public class TelevisionDto {
    private Long id;
    private String name;
    private String type;
    private String brand;
    private double price;
    private int screenSize;
    private RemoteController remoteController;
    private List<CIModule> ciModule;
    private List<Wallbracket> wallbrackets;

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

    public RemoteController getRemoteController() {
        return remoteController;
    }

    public void setRemoteController(RemoteController remoteController) {
        this.remoteController = remoteController;
    }

    public List<CIModule> getCiModule() {
        return ciModule;
    }

    public void setCiModule(List<CIModule> ciModule) {
        this.ciModule = ciModule;
    }

    public List<Wallbracket> getWallbrackets() {
        return wallbrackets;
    }

    public void setWallbrackets(List<Wallbracket> wallbrackets) {
        this.wallbrackets = wallbrackets;
    }
}
