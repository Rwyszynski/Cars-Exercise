package com.assesment.cars.model;

public class ElectricModel extends Model {
    private int batteryCapacity;
    private int energyConsumption;

    public ElectricModel(String manufacturer, String modelName, int power,
                         int batteryCapacity, int energyConsumption) {
        super(manufacturer, modelName, power);
        this.batteryCapacity = batteryCapacity;
        this.energyConsumption = energyConsumption;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    @Override
    public String toString() {
        return "ElectricModel{" +
                "manufacturer='" + manufacturer + '\'' +
                ", modelName='" + modelName + '\'' +
                ", power=" + power +
                ", batteryCapacity=" + batteryCapacity +
                ", energyConsumption=" + energyConsumption +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        ElectricModel that = (ElectricModel) obj;
        return batteryCapacity == that.batteryCapacity &&
                energyConsumption == that.energyConsumption;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + batteryCapacity;
        result = 31 * result + energyConsumption;
        return result;
    }
}