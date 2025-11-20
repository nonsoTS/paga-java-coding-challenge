package com.farmcollector.repository;

import com.farmcollector.model.HarvestedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestedDataRepository extends JpaRepository<HarvestedData, Long> {
    List<HarvestedData> findBySeason(String season);
    List<HarvestedData> findBySeasonAndFarmName(String season, String farmName);
    List<HarvestedData> findBySeasonAndCropType(String season, String cropType);
}