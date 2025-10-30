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

    public void readFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            models = reader.lines()
                    .map(ModelParser::parse)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading file: " + e.getMessage());
        }
    }

    public List<Model> getAllModels() {
        return models;
    }

    public Optional<Model> getModel(String manufacturer, String modelName) {
        return models.stream()
                .filter(m -> m.getManufacturer().equalsIgnoreCase(manufacturer)
                        && m.getModelName().equalsIgnoreCase(modelName))
                .findFirst();
    }

    public List<Model> getElectricModelsByBatteryCapacity(int minCapacity) {
        return models.stream()
                .filter(m -> m instanceof model.ElectricModel)
                .map(m -> (model.ElectricModel) m)
                .filter(em -> em.getBatteryCapacity() >= minCapacity)
                .sorted()
                .collect(Collectors.toList());
    }
}