package perser;

import model.ElectricModel;
import model.Model;
import model.RegularModel;

public class ModelParser {

    public static Model parse(String line) {
        String[] parts = line.split(",");
        if (parts.length < 2)
            throw new IllegalArgumentException("Invalid number of parameters" + parts.length);

        String type = parts[0].trim();

        switch (type) {
            case "Regular":
                if (parts.length != 6)
                    throw new IllegalArgumentException("Invalid number of parameters" + parts.length);
                return new RegularModel(parts[1], parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));

            case "Electric":
                if (parts.length != 6)
                    throw new IllegalArgumentException("Invalid number of parameters" + parts.length);
                return new ElectricModel(parts[1], parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));

            default:
                throw new IllegalArgumentException("Wrong type: " + type);
        }
    }
}
