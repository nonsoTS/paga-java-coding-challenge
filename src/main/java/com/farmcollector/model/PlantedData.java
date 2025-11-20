package com.farmcollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "planted_data")
public class PlantedData {

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
    private Double plantingArea; // in acres

    @Column(nullable = false)
    private Double expectedAmount; // in tons

    // Constructors
    public PlantedData() {
    }

    public PlantedData(Long id, String farmName, String season, String cropType,
                       Double plantingArea, Double expectedAmount) {
        this.id = id;
        this.farmName = farmName;
        this.season = season;
        this.cropType = cropType;
        this.plantingArea = plantingArea;
        this.expectedAmount = expectedAmount;
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

    public Double getPlantingArea() {
        return plantingArea;
    }

    public void setPlantingArea(Double plantingArea) {
        this.plantingArea = plantingArea;
    }

    public Double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }
}