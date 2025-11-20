package com.farmcollector.controller;

import com.farmcollector.service.FarmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    private final FarmService farmService;

    public ReportController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<String> seasons = farmService.getAllSeasons();
        model.addAttribute("seasons", seasons);
        return "index";
    }

    @GetMapping("/reports")
    public String showReports(@RequestParam String season, Model model) {
        Map<String, Map<String, Object>> farmReports = farmService.getReportByFarm(season);
        Map<String, Map<String, Object>> cropReports = farmService.getReportByCropType(season);

        model.addAttribute("season", season);
        model.addAttribute("farmReports", farmReports);
        model.addAttribute("cropReports", cropReports);

        return "reports";
    }
}