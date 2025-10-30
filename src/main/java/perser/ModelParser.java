package perser;
import model.ElectricModel;
import model.Model;
import model.RegularModel;
import java.util.Arrays;

public class ModelParser {

    public static Model parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Line is empty or null");
        }

        String[] parts = line.split(",");

        // Check if we have exactly 6 parts as specified in requirements
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid number of parameters: " + parts.length);
        }

        String type = parts[0].trim();

        if ("Regular".equals(type)) {
            try {
                String manufacturer = parts[1].trim();
                String modelName = parts[2].trim();
                int power = Integer.parseInt(parts[3].trim());
                int engineDisplacement = Integer.parseInt(parts[4].trim());
                double fuelConsumption = Double.parseDouble(parts[5].trim());
                return new RegularModel(manufacturer, modelName, power, engineDisplacement, fuelConsumption);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format", e);
            }

        } else if ("Electric".equals(type)) {
            try {
                String manufacturer = parts[1].trim();
                String modelName = parts[2].trim();
                int power = Integer.parseInt(parts[3].trim());
                int batteryCapacity = Integer.parseInt(parts[4].trim());
                int energyConsumption = Integer.parseInt(parts[5].trim());
                return new ElectricModel(manufacturer, modelName, power, batteryCapacity, energyConsumption);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format", e);
            }

        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}