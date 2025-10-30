package com.assesment.cars.model;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Model model = (Model) obj;
        return power == model.power &&
                manufacturer.equals(model.manufacturer) &&
                modelName.equals(model.modelName);
    }

    @Override
    public int hashCode() {
        int result = manufacturer.hashCode();
        result = 31 * result + modelName.hashCode();
        result = 31 * result + power;
        return result;
    }
}