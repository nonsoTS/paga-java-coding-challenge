package com.farmcollector.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.farmcollector.dto.PlantedRequest;
import com.farmcollector.model.PlantedData;
import com.farmcollector.service.FarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FarmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FarmService farmService;

    @Test
    void testSubmitPlantedData_Success() throws Exception {
        // MUST provide ALL required @Valid fields
        PlantedRequest request = new PlantedRequest();
        request.setFarmName("TestFarm");
        request.setCropType("Corn");
        request.setSeason("Wet");
        request.setPlantingArea(10.5);
        request.setExpectedAmount(200.0);

        // Mock service response
        PlantedData savedData = new PlantedData();
        savedData.setId(1L);
        savedData.setFarmName("TestFarm");
        savedData.setCropType("Corn");

        when(farmService.savePlantedData(any(PlantedRequest.class))).thenReturn(savedData);

        mockMvc.perform(post("/api/planted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.farmName").value("TestFarm"));
    }

    @Test
    void testSubmitPlantedData_InvalidInput() throws Exception {
        PlantedRequest request = new PlantedRequest();
        request.setFarmName(""); // invalid

        mockMvc.perform(post("/api/planted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}