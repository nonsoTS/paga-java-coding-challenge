package com.farmcollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "harvested_data")
public class HarvestedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String farmName;

    @Column(nullable = false)
    private String season;

    @Column(nullable = false)
    private String cropType;

    @Column(nullable = false)
    private Double actualAmount; // in tons

    // Constructors
    public HarvestedData() {
    }

    public HarvestedData(Long id, String farmName, String season, String cropType, Double actualAmount) {
        this.id = id;
        this.farmName = farmName;
        this.season = season;
        this.cropType = cropType;
        this.actualAmount = actualAmount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }
}