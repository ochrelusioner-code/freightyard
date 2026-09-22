package com.spring.freightyard.services;

import com.spring.freightyard.models.FreightCar;
import com.spring.freightyard.models.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainCapacityService {

    public double calculateTotalWeight(Train train) {
        if (train.getFreightCars() == null || train.getFreightCars().isEmpty()) {
            return 0.0;
        }


        double totalWeight = 0.0;
        for (FreightCar car : train.getFreightCars()) {
            totalWeight += car.getWeightInTons();
        }
        return totalWeight;
    }


    public boolean isOverweight(Train train) {
        double currentWeight = calculateTotalWeight(train);
        return currentWeight > train.getMaxWeightCapacity();
    }
}
