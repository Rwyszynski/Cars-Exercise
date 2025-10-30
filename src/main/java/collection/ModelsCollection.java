package collection;

import model.ElectricModel;
import model.Model;
import parser.ModelParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelsCollection {
    private List<Model> models = new ArrayList<>();

    public void readFromFile(String filename) {
        try {
            Path filePath = Path.of(filename);
            if (!Files.exists(filePath)) {
                throw new RuntimeException("Error occurred while reading file: " + filename + " (File not found)");
            }

            List<String> lines = Files.readAllLines(filePath);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (!line.trim().isEmpty()) {
                    try {
                        Model model = ModelParser.parse(line);
                        models.add(model);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error parsing line " + (i + 1) + ": " + e.getMessage());
                        throw e;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading file: " + e.getMessage());
        }
    }

    public Optional<Model> getModel(String manufacturer, String modelName) {
        return models.stream()
                .filter(model -> manufacturer.equals(model.getManufacturer()) &&
                        modelName.equals(model.getModelName()))
                .findFirst();
    }

    public List<ElectricModel> getElectricModelsByBatteryCapacity(int batteryCapacityLimit) {
        return models.stream()
                .filter(model -> model instanceof ElectricModel)
                .map(model -> (ElectricModel) model)
                .filter(electricModel -> electricModel.getBatteryCapacity() >= batteryCapacityLimit)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Model> getModels() {
        return new ArrayList<>(models);
    }
}