package com.epam.assessment.cars.Application.model;

public class RegularModel extends Model {
    private int engineDisplacement;
    private double fuelConsumption;

    public RegularModel(String manufacturer, String modelName, int power,
                        int engineDisplacement, double fuelConsumption) {
        super(manufacturer, modelName, power);
        this.engineDisplacement = engineDisplacement;
        this.fuelConsumption = fuelConsumption;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String toString() {
        return "RegularModel{" +
                "manufacturer='" + manufacturer + '\'' +
                ", modelName='" + modelName + '\'' +
                ", power=" + power +
                ", engineDisplacement=" + engineDisplacement +
                ", fuelConsumption=" + fuelConsumption +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        RegularModel that = (RegularModel) obj;
        return engineDisplacement == that.engineDisplacement &&
                Double.compare(that.fuelConsumption, fuelConsumption) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + engineDisplacement;
        result = 31 * result + Double.hashCode(fuelConsumption);
        return result;
    }
}