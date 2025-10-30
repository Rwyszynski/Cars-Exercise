package car;

import model.ElectricModel;
import model.Model;

public class ElectricCar extends Car {

    private int batteryLevel;

    public ElectricCar(String licensePlate, Model model, int distanceDriven, int batteryLevel) {
        super(licensePlate, model, distanceDriven);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void drive(int distance) {
        if (!(model instanceof ElectricModel)) {
            throw new IllegalArgumentException("Model is not electric!");
        }

        ElectricModel em = (ElectricModel) model;
        double perKm = em.getEnergyConsumption() / 100.0;
        double consumption = perKm * distance;
        int consumed = (int) Math.round(consumption);

        if (batteryLevel - consumed < 0) {
            throw new IllegalStateException("Not enough battery to drive " + distance + " km");
        }

        batteryLevel -= consumed;
        distanceDriven += distance;

        System.out.println("Driving for " + distance + " km");
        System.out.println("    Odometer: " + distanceDriven + " km");
        System.out.println("    Consumption: " + consumed + " kWh");
        System.out.println("    Battery level: " + batteryLevel + " kWh");
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}