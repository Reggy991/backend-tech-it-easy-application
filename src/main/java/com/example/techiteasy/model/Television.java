package com.example.techiteasy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "televisions")
public class Television {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String brand;
    private double price;
    private int screenSize;
    @OneToOne
    @JsonIgnore
    private RemoteController remoteController;
    @OneToMany(mappedBy = "television")
    private List<CIModule> ciModule;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "televisions_wallbrackets",
            joinColumns = @JoinColumn(name = "television_id"),
            inverseJoinColumns = @JoinColumn(name = "wallbracket_id")
    )
    private List<Wallbracket> wallbrackets;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

