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
        ElectricModel em = (ElectricModel) model;

        double perKm = em.getEnergyConsumption() / 100.0;
        double consumption = perKm * distance;
        int consumed = (int) Math.round(consumption);

        if (batteryLevel - consumed < 0) {
            throw new IllegalArgumentException("Battery out of stock!");
        }

        batteryLevel -= consumed;
        distanceDriven += distance;

    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}

