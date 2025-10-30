package perser;
import model.ElectricModel;
import model.Model;
import model.RegularModel;

public class ModelParser {

    public static Model parse(String line) {
        String[] tokens = line.split(",");

        if (tokens.length != 6) {
            throw new IllegalArgumentException(
                    "Invalid number of parameters. Expected 6, received: " + tokens.length
            );
        }

        String type = tokens[0];

        switch (type) {
            case "Regular":
                return new RegularModel(
                        tokens[1],
                        tokens[2],
                        Integer.parseInt(tokens[3]),
                        Integer.parseInt(tokens[4]),
                        Double.parseDouble(tokens[5])
                );
            case "Electric":
                return new ElectricModel(
                        tokens[1],
                        tokens[2],
                        Integer.parseInt(tokens[3]),
                        Integer.parseInt(tokens[4]),
                        Integer.parseInt(tokens[5])
                );
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}