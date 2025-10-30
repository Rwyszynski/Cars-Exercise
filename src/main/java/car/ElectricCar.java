package car;

import model.ElectricModel;
import model.Model;

public class ElectricCar extends Car {
    private int batteryLevel;

    public ElectricCar(String licensePlate, ElectricModel model, int initialDistance) {
        super(licensePlate, model, initialDistance);
        this.batteryLevel = model.getBatteryCapacity();
    }

    // Alternative constructor with default distance
    public ElectricCar(String licensePlate, ElectricModel model) {
        this(licensePlate, model, 0);
    }

    @Override
    public void drive(int distance) {
        ElectricModel electricModel = (ElectricModel) getModel();

        // Calculate energy consumption
        double energyPerKm = electricModel.getEnergyConsumption() / 100.0;
        double totalEnergyNeeded = energyPerKm * distance;

        // Check if we have enough battery
        if (totalEnergyNeeded > batteryLevel) {
            throw new IllegalArgumentException("Not enough battery to drive " + distance + " km. Needed: " +
                    totalEnergyNeeded + " kWh, available: " + batteryLevel + " kWh");
        }

        // Update battery level and distance
        batteryLevel -= (int) Math.round(totalEnergyNeeded);
        setDistanceDriven(getDistanceDriven() + distance);

        // Print driving information
        System.out.println("Driving for " + distance + " km");
        System.out.println("    Odometer: " + getDistanceDriven() + " km");
        System.out.println("    Consumption: " + (int) Math.round(totalEnergyNeeded) + " kWh");
        System.out.println("    Battery level: " + batteryLevel + " kWh");
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}