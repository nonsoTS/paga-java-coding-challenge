package com.farmcollector.service;

import com.farmcollector.dto.HarvestedRequest;
import com.farmcollector.dto.PlantedRequest;
import com.farmcollector.model.HarvestedData;
import com.farmcollector.model.PlantedData;
import com.farmcollector.repository.HarvestedDataRepository;
import com.farmcollector.repository.PlantedDataRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FarmService {

    private final PlantedDataRepository plantedDataRepository;
    private final HarvestedDataRepository harvestedDataRepository;

    public FarmService(PlantedDataRepository plantedDataRepository,
                       HarvestedDataRepository harvestedDataRepository) {
        this.plantedDataRepository = plantedDataRepository;
        this.harvestedDataRepository = harvestedDataRepository;
    }

    public PlantedData savePlantedData(PlantedRequest request) {
        PlantedData data = new PlantedData();
        data.setFarmName(request.getFarmName());
        data.setSeason(request.getSeason());
        data.setCropType(request.getCropType());
        data.setPlantingArea(request.getPlantingArea());
        data.setExpectedAmount(request.getExpectedAmount());
        return plantedDataRepository.save(data);
    }

    public HarvestedData saveHarvestedData(HarvestedRequest request) {
        HarvestedData data = new HarvestedData();
        data.setFarmName(request.getFarmName());
        data.setSeason(request.getSeason());
        data.setCropType(request.getCropType());
        data.setActualAmount(request.getActualAmount());
        return harvestedDataRepository.save(data);
    }

    public List<String> getAllSeasons() {
        Set<String> seasons = new HashSet<>();
        seasons.addAll(plantedDataRepository.findAll().stream()
                .map(PlantedData::getSeason).collect(Collectors.toSet()));
        seasons.addAll(harvestedDataRepository.findAll().stream()
                .map(HarvestedData::getSeason).collect(Collectors.toSet()));
        return new ArrayList<>(seasons);
    }

    public Map<String, Map<String, Object>> getReportByFarm(String season) {
        List<PlantedData> plantedList = plantedDataRepository.findBySeason(season);
        List<HarvestedData> harvestedList = harvestedDataRepository.findBySeason(season);

        Map<String, Map<String, Object>> farmReports = new HashMap<>();

        // Group planted data by farm
        Map<String, List<PlantedData>> plantedByFarm = plantedList.stream()
                .collect(Collectors.groupingBy(PlantedData::getFarmName));

        // Group harvested data by farm
        Map<String, List<HarvestedData>> harvestedByFarm = harvestedList.stream()
                .collect(Collectors.groupingBy(HarvestedData::getFarmName));

        // Get all farm names
        Set<String> allFarms = new HashSet<>();
        allFarms.addAll(plantedByFarm.keySet());
        allFarms.addAll(harvestedByFarm.keySet());

        for (String farm : allFarms) {
            Map<String, Object> report = new HashMap<>();

            double totalExpected = plantedByFarm.getOrDefault(farm, Collections.emptyList())
                    .stream().mapToDouble(PlantedData::getExpectedAmount).sum();

            double totalActual = harvestedByFarm.getOrDefault(farm, Collections.emptyList())
                    .stream().mapToDouble(HarvestedData::getActualAmount).sum();

            report.put("expectedAmount", totalExpected);
            report.put("actualAmount", totalActual);
            report.put("difference", totalActual - totalExpected);

            farmReports.put(farm, report);
        }

        return farmReports;
    }

    public Map<String, Map<String, Object>> getReportByCropType(String season) {
        List<PlantedData> plantedList = plantedDataRepository.findBySeason(season);
        List<HarvestedData> harvestedList = harvestedDataRepository.findBySeason(season);

        Map<String, Map<String, Object>> cropReports = new HashMap<>();

        // Group planted data by crop type
        Map<String, List<PlantedData>> plantedByCrop = plantedList.stream()
                .collect(Collectors.groupingBy(PlantedData::getCropType));

        // Group harvested data by crop type
        Map<String, List<HarvestedData>> harvestedByCrop = harvestedList.stream()
                .collect(Collectors.groupingBy(HarvestedData::getCropType));

        // Get all crop types
        Set<String> allCrops = new HashSet<>();
        allCrops.addAll(plantedByCrop.keySet());
        allCrops.addAll(harvestedByCrop.keySet());

        for (String crop : allCrops) {
            Map<String, Object> report = new HashMap<>();

            double totalExpected = plantedByCrop.getOrDefault(crop, Collections.emptyList())
                    .stream().mapToDouble(PlantedData::getExpectedAmount).sum();

            double totalActual = harvestedByCrop.getOrDefault(crop, Collections.emptyList())
                    .stream().mapToDouble(HarvestedData::getActualAmount).sum();

            double totalArea = plantedByCrop.getOrDefault(crop, Collections.emptyList())
                    .stream().mapToDouble(PlantedData::getPlantingArea).sum();

            report.put("totalArea", totalArea);
            report.put("expectedAmount", totalExpected);
            report.put("actualAmount", totalActual);
            report.put("difference", totalActual - totalExpected);

            cropReports.put(crop, report);
        }

        return cropReports;
    }
}