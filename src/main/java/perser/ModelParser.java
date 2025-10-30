package perser;
import model.ElectricModel;
import model.Model;
import model.RegularModel;

public class ModelParser {

    public static Model parse(String line) {
        String[] parts = line.split(",");
        if (parts.length == 0) {
            throw new IllegalArgumentException("Invalid number of parameters");
        }

        String type = parts[0].trim();

        switch (type) {
            case "Regular":
                if (parts.length != 6)
                    throw new IllegalArgumentException(
                            "Invalid number of parameters" + parts.length);

                String manufacturerR = parts[1].trim();
                String modelNameR = parts[2].trim();
                int powerR = Integer.parseInt(parts[3].trim());
                int engineDisplacement = Integer.parseInt(parts[4].trim());
                double fuelConsumption = Double.parseDouble(parts[5].trim());

                return new RegularModel(manufacturerR, modelNameR, powerR, engineDisplacement, fuelConsumption);

            case "Electric":
                if (parts.length != 6)
                    throw new IllegalArgumentException(
                            "Invalid number of parameters" + parts.length);

                String manufacturerE = parts[1].trim();
                String modelNameE = parts[2].trim();
                int powerE = Integer.parseInt(parts[3].trim());
                int batteryCapacity = Integer.parseInt(parts[4].trim());
                int energyConsumption = Integer.parseInt(parts[5].trim());

                return new ElectricModel(manufacturerE, modelNameE, powerE, batteryCapacity, energyConsumption);

            default:
                throw new IllegalArgumentException("Unknown" + type);
        }
    }
}