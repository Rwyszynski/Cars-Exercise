package perser;
import model.ElectricModel;
import model.Model;
import model.RegularModel;

public class ModelParser {

    public static Model parse(String line) {

        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Line is empty or null");
        }

        String[] parts = line.split(",");
        System.out.println("PARTS COUNT: " + parts.length);
        for (int i = 0; i < parts.length; i++) {
            System.out.println("  Part[" + i + "]: '" + parts[i] + "'");
        }

        if (parts.length == 6) {
            Model model = parseWithType(parts);
            System.out.println("CREATED: " + model.getClass().getSimpleName());
            return model;
        } else if (parts.length == 5) {
            Model model = parseWithoutType(parts);
            System.out.println("CREATED: " + model.getClass().getSimpleName());
            return model;
        } else {
            throw new IllegalArgumentException("Invalid number of parameters" + parts.length);
        }
    }

    private static Model parseWithType(String[] parts) {
        String type = parts[0].trim();
        System.out.println("TYPE: " + type);

        if ("Regular".equals(type)) {
            String manufacturer = parts[1].trim();
            String modelName = parts[2].trim();
            int power = Integer.parseInt(parts[3].trim());
            int engineDisplacement = Integer.parseInt(parts[4].trim());
            double fuelConsumption = Double.parseDouble(parts[5].trim());
            return new RegularModel(manufacturer, modelName, power, engineDisplacement, fuelConsumption);

        } else if ("Electric".equals(type)) {
            String manufacturer = parts[1].trim();
            String modelName = parts[2].trim();
            int power = Integer.parseInt(parts[3].trim());
            int batteryCapacity = Integer.parseInt(parts[4].trim());
            int energyConsumption = Integer.parseInt(parts[5].trim());
            return new ElectricModel(manufacturer, modelName, power, batteryCapacity, energyConsumption);

        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    private static Model parseWithoutType(String[] parts) {
        System.out.println("PARSING WITHOUT TYPE");
        String manufacturer = parts[0].trim();
        String modelName = parts[1].trim();
        int power = Integer.parseInt(parts[2].trim());
        int fourthParam = Integer.parseInt(parts[3].trim());
        double fifthParam = Double.parseDouble(parts[4].trim());

        System.out.println("Params: " + manufacturer + ", " + modelName + ", " + power + ", " + fourthParam + ", " + fifthParam);


        if (fourthParam >= 1000 && fourthParam <= 5000) {

            System.out.println("DETECTED: RegularModel (engine displacement: " + fourthParam + ")");
            return new RegularModel(manufacturer, modelName, power, fourthParam, fifthParam);
        } else if (fourthParam >= 40 && fourthParam <= 100) {

            System.out.println("DETECTED: ElectricModel (battery capacity: " + fourthParam + ")");
            return new ElectricModel(manufacturer, modelName, power, fourthParam, (int)fifthParam);
        } else {

            System.out.println("DEFAULTING TO: RegularModel");
            return new RegularModel(manufacturer, modelName, power, fourthParam, fifthParam);
        }
    }
}