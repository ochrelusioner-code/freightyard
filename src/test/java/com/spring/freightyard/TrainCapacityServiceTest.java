package com.spring.freightyard;

import com.spring.freightyard.models.FreightCar;
import com.spring.freightyard.models.Train;
import com.spring.freightyard.services.TrainCapacityService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainCapacityServiceTest {
    private final TrainCapacityService capacityService = new TrainCapacityService();

    @Test
    public void testTrainUnderCapacity_ShouldReturnFalse() {
        Train train = new Train("BNSF-101", "Chicago", 500.0);
        FreightCar car1 = new FreightCar("FC-01", "Coal", 200.0);
        FreightCar car2 = new FreightCar("FC-02", "Steel", 150.0);

        train.addFreightCar(car1);
        train.addFreightCar(car2);

        boolean isOverweight = capacityService.isOverweight(train);

        assertFalse(isOverweight, "Train should not be overweight");
    }

    @Test

    public void testTrainUnderCapacity_ShouldReturnTrue() {

        Train train = new Train("BSNF-202", "Dallas", 500.0);
        FreightCar car1 = new FreightCar("FC-01", "Lumber", 200.0);
        FreightCar car2 = new FreightCar("FC-02", "Steel", 150.0);

        train.addFreightCar(car1);
        train.addFreightCar(car2);
    }
}
