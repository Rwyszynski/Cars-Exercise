package perser;
import model.ElectricModel;
import model.Model;
import model.RegularModel;
import java.util.Arrays;

public class ModelParser {

    public static Model parse(String line) {
        String[] parts = line.split(",");

        if (parts.length == 5) {
            String manufacturer = parts[0];
            String modelName = parts[1];
            int power = Integer.parseInt(parts[2]);
            int engineDisplacement = Integer.parseInt(parts[3]);
            double fuelConsumption = Double.parseDouble(parts[4]);
            return new RegularModel(manufacturer, modelName, power, engineDisplacement, fuelConsumption);
        } else if (parts.length == 5) {
            String manufacturer = parts[0];
            String modelName = parts[1];
            int power = Integer.parseInt(parts[2]);
            int batteryCapacity = Integer.parseInt(parts[3]);
            int energyConsumption = Integer.parseInt(parts[4]);
            return new ElectricModel(manufacturer, modelName, power, batteryCapacity, energyConsumption);
        } else {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
    }
}