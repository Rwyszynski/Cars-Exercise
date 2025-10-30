package com.epam.assessment.cars.Application.car;

import com.epam.assessment.cars.Application.model.Model;

public abstract class Car {
    private String licensePlate;
    private Model model;
    private int distanceDriven;

    public Car(String licensePlate, Model model, int distanceDriven) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.distanceDriven = distanceDriven;
    }

    // ... gettery i settery
    public Model getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getDistanceDriven() {
        return distanceDriven;
    }

    public void setDistanceDriven(int distanceDriven) {
        this.distanceDriven = distanceDriven;
    }

    public abstract void drive(int distance);
}