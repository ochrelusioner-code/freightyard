package com.spring.freightyard.controllers;

import com.spring.freightyard.models.FreightCar;
import com.spring.freightyard.models.Train;
import com.spring.freightyard.repositories.TrainRepository;
import com.spring.freightyard.services.TrainCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "http://localhost:5173")
public class TrainController {

    private final TrainRepository trainRepository;
    private final TrainCapacityService capacityService;

    @Autowired
    public TrainController(TrainRepository trainRepository, TrainCapacityService capacityService) {
        this.trainRepository = trainRepository;
        this.capacityService = capacityService;
    }

    @GetMapping
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        Optional<Train> train = trainRepository.findById(id);

        return train.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train newTrain) {

        if (newTrain.getFreightCars() != null) {
            for (FreightCar car : newTrain.getFreightCars()) {
                car.setTrain(newTrain);
            }
        }

        Train savedTrain = trainRepository.save(newTrain);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrain);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> getTrainStatus(@PathVariable Long id) {
        Optional<Train> trainOptional = trainRepository.findById(id);

        if (trainOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Train train = trainOptional.get();

        double currentWeight = capacityService.calculateTotalWeight(train);
        boolean overweight = capacityService.isOverweight(train);

        Map<String, Object> statusResponse = new HashMap<>();
        statusResponse.put("manifestNumber", train.getManifestNumber());
        statusResponse.put("maxCapacity", train.getMaxWeightCapacity());
        statusResponse.put("currentWeight", currentWeight);
        statusResponse.put("isOverweight", overweight);

        return ResponseEntity.ok(statusResponse);
    }
}