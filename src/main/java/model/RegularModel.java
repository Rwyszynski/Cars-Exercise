package model;

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
}