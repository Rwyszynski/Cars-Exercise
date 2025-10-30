package collection;

import model.ElectricModel;
import model.Model;
import perser.ModelParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelsCollection {
    private List<Model> models = new ArrayList<>();

    public void readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            models = reader.lines()
                    .map(ModelParser::parse)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: " + e.getMessage());
        }
    }

    public Optional<Model> getModel(String manufacturer, String modelName) {
        return models.stream()
                .filter(m -> m.getManufacturer().equals(manufacturer) && m.getModelName().equals(modelName))
                .findFirst();
    }

    public List<ElectricModel> getElectricModelsByBatteryCapacity(int limit) {
        return models.stream()
                .filter(m -> m instanceof ElectricModel)
                .map(m -> (ElectricModel) m)
                .filter(e -> e.getBatteryCapacity() >= limit)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Model> getAllModels() {
        return models;
    }
}
