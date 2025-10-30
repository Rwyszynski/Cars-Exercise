package com.epam.assessment.cars.Application.car;

import com.epam.assessment.cars.Application.model.ElectricModel;
import com.epam.assessment.cars.Application.model.Model;

public class ElectricCar extends Car {
    private int batteryLevel;

    public ElectricCar(String licensePlate, ElectricModel model, int initialDistance, int batteryLevel) {
        super(licensePlate, model, initialDistance);
        this.batteryLevel = batteryLevel;
    }

    public ElectricCar(String licensePlate, Model model, int initialDistance) {
        super(licensePlate, model, initialDistance);
        if (!(model instanceof ElectricModel)) {
            throw new IllegalArgumentException("Model must be an ElectricModel");
        }
        ElectricModel electricModel = (ElectricModel) model;
        this.batteryLevel = electricModel.getBatteryCapacity();
    }

    @Override
    public void drive(int distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }

        ElectricModel electricModel = (ElectricModel) getModel();

        double energyPerKm = electricModel.getEnergyConsumption() / 100.0;
        double totalEnergyNeeded = energyPerKm * distance;

        if (totalEnergyNeeded > batteryLevel) {
            throw new IllegalStateException("Not enough battery to drive " + distance + " km. Needed: " +
                    Math.round(totalEnergyNeeded) + " kWh, available: " + batteryLevel + " kWh");
        }

        batteryLevel -= (int) Math.round(totalEnergyNeeded);
        setDistanceDriven(getDistanceDriven() + distance);

        System.out.println("Driving for " + distance + " km");
        System.out.println("    Odometer: " + getDistanceDriven() + " km");
        System.out.println("    Consumption: " + (int) Math.round(totalEnergyNeeded) + " kWh");
        System.out.println("    Battery level: " + batteryLevel + " kWh");
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}