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
                    .map(line -> (Model) ModelParser.parse(line))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading file: " + e.getMessage(), e);
        }
    }

    public List<Model> getModels() {
        return models;
    }
}