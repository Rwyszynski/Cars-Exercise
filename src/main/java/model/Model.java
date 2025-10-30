package model;

import java.util.Objects;

public class Model {
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

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return power == model.power && Objects.equals(manufacturer, model.manufacturer) && Objects.equals(modelName, model.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, modelName, power);
    }

    @Override
    public String toString() {
        return "Model{" +
                "manufacturer='" + manufacturer + '\'' +
                ", modelName='" + modelName + '\'' +
                ", power=" + power +
                '}';
    }
}
