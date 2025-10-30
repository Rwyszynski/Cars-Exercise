package model;

import java.util.Objects;

public abstract class Model implements Comparable<Model> {
    protected String manufacturer;
    protected String modelName;
    protected int power;

    public Model(String manufacturer, String modelName, int power) {
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.power = power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public int getPower() {
        return power;
    }

    @Override
    public int compareTo(Model other) {
        int manufacturerCompare = this.manufacturer.compareTo(other.manufacturer);
        if (manufacturerCompare != 0) {
            return manufacturerCompare;
        }
        return this.modelName.compareTo(other.modelName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "manufacturer='" + manufacturer + '\'' +
                ", modelName='" + modelName + '\'' +
                ", power=" + power +
                '}';
    }
}