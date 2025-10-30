package car;

import model.Model;

public abstract class Car {

    protected String licensePlate;
    protected Model model;
    protected int distanceDriven;

    public Car(String licensePlate, Model model, int distanceDriven) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.distanceDriven = distanceDriven;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Model getModel() {
        return model;
    }

    public int getDistanceDriven() {
        return distanceDriven;
    }

    public abstract void drive(int distance);
}
