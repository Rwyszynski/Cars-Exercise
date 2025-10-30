package model;

public class ElectricModel extends Model {

    private int batteryCapacity;
    private int energyConsumption;

    public ElectricModel(String manufacturer, String modelName, int power, int batteryCapacity, int energyConsumption) {
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
                "batteryCapacity=" + batteryCapacity +
                ", energyConsumption=" + energyConsumption +
                ", manufacturer='" + manufacturer + '\'' +
                ", modelName='" + modelName + '\'' +
                ", power=" + power +
                '}';
    }
}
