package com.farmcollector.controller;

import com.farmcollector.dto.HarvestedRequest;
import com.farmcollector.dto.PlantedRequest;
import com.farmcollector.model.HarvestedData;
import com.farmcollector.model.PlantedData;
import com.farmcollector.service.FarmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping("/planted")
    public ResponseEntity<PlantedData> submitPlantedData(@Valid @RequestBody PlantedRequest request) {
        PlantedData saved = farmService.savePlantedData(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/harvested")
    public ResponseEntity<HarvestedData> submitHarvestedData(@Valid @RequestBody HarvestedRequest request) {
        HarvestedData saved = farmService.saveHarvestedData(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}