package com.spring.freightyard.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;


@Entity // Tells Spring this class is a database table
@Table(name = "trains")


public class Train {

    @Id // Marks this as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID (1, 2, 3...)

    private Long id;

    private String manifestNumber;
    private String destination;
    private double maxWeightCapacity;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreightCar> freightCars = new ArrayList<>();

    public Train() {}

    public Train(String manifestNumber, String destination, double maxWeightCapacity) {
        this.manifestNumber = manifestNumber;
        this.destination = destination;
        this.maxWeightCapacity = maxWeightCapacity;
    }

    public Long getId() {return id;}
    public void setId(Long id) { this.id = id;}

    public String getManifestNumber() {return manifestNumber;}
    public void setManifestNumber(String manifestNumber) {this.manifestNumber = manifestNumber;}

    public String getDestination() {return destination;}
    public void setDestination(String destination) {this.destination = destination;}

    public double getMaxWeightCapacity() {return maxWeightCapacity;}
    public void setMaxWeightCapacity(double maxWeightCapacity) {this.maxWeightCapacity = maxWeightCapacity;}

    public List<FreightCar> getFreightCars() {
        return freightCars;
    }

    public void addFreightCar(FreightCar car) {
        freightCars.add(car);
        car.setTrain(this);
    }

}


