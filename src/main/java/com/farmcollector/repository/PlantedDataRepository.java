package com.farmcollector.repository;

import com.farmcollector.model.PlantedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantedDataRepository extends JpaRepository<PlantedData, Long> {
    List<PlantedData> findBySeason(String season);
    List<PlantedData> findBySeasonAndFarmName(String season, String farmName);
    List<PlantedData> findBySeasonAndCropType(String season, String cropType);
}