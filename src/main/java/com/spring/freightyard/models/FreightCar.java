package com.spring.freightyard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "freight_cars")
public class FreightCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carNumber;
    private String cargoType;
    private double weightInTons;

    @ManyToOne
    @JoinColumn(name = "train_id")
    @JsonIgnore
    private Train train;

    public FreightCar(){}

    public FreightCar(String carNumber, String cargoType, double weightInTons) {
        this.carNumber = carNumber;
        this.cargoType = cargoType;
        this.weightInTons = weightInTons;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCarNumber() { return carNumber;}
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCargoType() {return cargoType;}
    public void setCargoType(String  cargoType) {this.cargoType = cargoType;}

    public double getWeightInTons() {return weightInTons;}
    public void setWeightInTons(double weightInTons) {this.weightInTons = weightInTons;}

    public Train getTrain() {return train;}
    public void setTrain(Train train) {this.train = train;}
}
