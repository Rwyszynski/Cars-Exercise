package collection;

import model.ElectricModel;
import model.Model;
import perser.ModelParser;

import java.io.BufferedReader;
import java.io.FileReader;
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
            List<String> lines = Files.readAllLines(Path.of(filename));
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    Model model = ModelParser.parse(line);
                    models.add(model);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading file: " + e.getMessage(), e);
        }
    }

    public Optional<Model> getModel(String manufacturer, String modelName) {
        return models.stream()
                .filter(model -> manufacturer.equals(model.getManufacturer()) &&
                        modelName.equals(model.getModelName()))
                .findFirst();
    }

    public List<Model> getElectricModelsByBatteryCapacity(int batteryCapacityLimit) {
        return models.stream()
                .filter(model -> model instanceof model.ElectricModel)
                .map(model -> (model.ElectricModel) model)
                .filter(electricModel -> electricModel.getBatteryCapacity() >= batteryCapacityLimit)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Model> getModels() {
        return new ArrayList<>(models);
    }
}