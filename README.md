# FarmCollector - Farm Data Collection and Reporting System

## Project Overview

This is a Spring Boot application built for the FarmCollector coding challenge. It collects planting and harvesting data from farmers and produces simple text-based reports for each season. Reports cover totals by farm and by crop type. The app exposes two APIs (planted and harvested) as required in the challenge PDF.

## Tech Stack

* Java 21
* Spring Boot 3.2.x
* Spring Data JPA
* H2 (in-memory)
* Spring Validation
* Spring MVC / Thymeleaf
* Maven
* JUnit 5 + MockMvc

## What I Did

* Implemented the two required APIs: `/api/planted` and `/api/harvested`.
* Validated all input fields with standard bean validation.
* Set up JPA entities for planted and harvested data.
* Added repositories and a service class to handle saving and simple aggregation.
* Created a reports page that shows expected vs actual amounts grouped by farm or by crop type for each season.
* Added tests for both API calls (success and invalid input cases).
* Fixed test failures caused by validation by making sure test data includes all required fields.
* Updated dependencies and checked CVE warnings. Nothing breaking from Spring Boot itself, so proceeded.
* Confirmed the challenge requirements: simple UI, text only, no charts, at least one test per API.

## Running the App

Prerequisites: Java 21+, Maven.

```bash
mvn clean install
mvn spring-boot:run
```

Runs on: `http://localhost:8080`

## API Endpoints

### POST /api/planted

Submits planted data. Required fields: farmName, season, cropType, plantingArea, expectedAmount.

Example:

```json
{
  "farmName": "MyFarm",
  "season": "Spring 2024",
  "cropType": "Corn",
  "plantingArea": 10,
  "expectedAmount": 50
}
```

### POST /api/harvested

Submits harvested data. Required fields: farmName, season, cropType, actualAmount.

Example:

```json
{
  "farmName": "MyFarm",
  "season": "Spring 2024",
  "cropType": "Corn",
  "actualAmount": 45
}
```

Both endpoints return 201 on success and 400 for invalid data.

## Reports

Accessible at:

```
/reports?season=<season>
```

Shows two text-based tables:

* Expected vs actual by farm
* Expected vs actual by crop type

Uses totals aggregated from both planted and harvested inputs.

## Tests

Tests are under `src/test/java`.
Used MockMvc.
Covers:

* planted success
* planted invalid input
* harvested success
* harvested invalid input

Also fixed an issue where the success test was failing because not all required fields were being populated, which triggered validation errors. Adding season, plantingArea, and expectedAmount resolved it.

## Project Structure (Short)

```
controller/
  FarmController.java
  ReportController.java

dto/
  PlantedRequest.java
  HarvestedRequest.java

model/
  PlantedData.java
  HarvestedData.java

repository/
  PlantedDataRepository.java
  HarvestedDataRepository.java

service/
  FarmService.java

resources/
  templates/index.html
  templates/reports.html
```

## Notes

* H2 is in-memory so data is cleared on restart.
* Code aligns with the instructions in the coding challenge PDF.
* Reports are plain text as requested.
* Did not add anything beyond challenge scope except basic validation, which is standard.