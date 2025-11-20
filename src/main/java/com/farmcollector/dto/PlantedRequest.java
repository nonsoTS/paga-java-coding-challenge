package com.farmcollector.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PlantedRequest {

    @NotBlank(message = "Farm name is required")
    private String farmName;

    @NotBlank(message = "Season is required")
    private String season;

    @NotBlank(message = "Crop type is required")
    private String cropType;

    @NotNull(message = "Planting area is required")
    @Positive(message = "Planting area must be positive")
    private Double plantingArea; // in acres

    @NotNull(message = "Expected amount is required")
    @Positive(message = "Expected amount must be positive")
    private Double expectedAmount; // in tons

    // Constructors
    public PlantedRequest() {
    }

    public PlantedRequest(String farmName, String season, String cropType,
                          Double plantingArea, Double expectedAmount) {
        this.farmName = farmName;
        this.season = season;
        this.cropType = cropType;
        this.plantingArea = plantingArea;
        this.expectedAmount = expectedAmount;
    }

    // Getters and Setters
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