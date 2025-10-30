package collection;

import com.epam.assessment.cars.Application.logic.ModelsCollection;
import com.epam.assessment.cars.Application.model.ElectricModel;
import com.epam.assessment.cars.Application.model.Model;
import com.epam.assessment.cars.Application.model.RegularModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ModelsCollectionTest {

    private ModelsCollection collection;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        collection = new ModelsCollection();
    }

    @Test
    void testReadFromFileValidData() throws IOException {
        // Given
        File testFile = createTestFile(
                "Regular,Opel,Astra K,81,1199,5.2\n" +
                        "Electric,Tesla,Model 3 RWD,208,60,20\n" +
                        "Electric,BMW,i4,250,83,19\n" +
                        "Regular,Volkswagen,Golf 8,110,1395,5.1"
        );

        // When
        collection.readFromFile(testFile.getAbsolutePath());

        // Then
        List<Model> models = collection.getModels();
        assertEquals(4, models.size());

        // Verify specific models
        Optional<Model> tesla = collection.getModel("Tesla", "Model 3 RWD");
        assertTrue(tesla.isPresent());
        assertTrue(tesla.get() instanceof ElectricModel);

        Optional<Model> opel = collection.getModel("Opel", "Astra K");
        assertTrue(opel.isPresent());
        assertTrue(opel.get() instanceof RegularModel);
    }

    @Test
    void testReadFromFileNotFound() {
        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> collection.readFromFile("nonexistent_file.csv"));
        assertTrue(exception.getMessage().contains("Error occurred while reading file"));
    }

    @Test
    void testReadFromFileWithInvalidData() throws IOException {
        // Given
        File testFile = createTestFile(
                "Regular,Opel,Astra K,81,1199,5.2\n" +
                        "Invalid,Data,Here\n" +  // Invalid line
                        "Electric,Tesla,Model 3 RWD,208,60,20"
        );

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> collection.readFromFile(testFile.getAbsolutePath()));
    }

    @Test
    void testGetModelFound() throws IOException {
        // Given
        File testFile = createTestFile(
                "Regular,Opel,Astra K,81,1199,5.2\n" +
                        "Electric,Tesla,Model 3 RWD,208,60,20"
        );
        collection.readFromFile(testFile.getAbsolutePath());

        // When
        Optional<Model> result = collection.getModel("Tesla", "Model 3 RWD");

        // Then
        assertTrue(result.isPresent());
        assertEquals("Tesla", result.get().getManufacturer());
        assertEquals("Model 3 RWD", result.get().getModelName());
        assertTrue(result.get() instanceof ElectricModel);
    }

    @Test
    void testGetModelNotFound() throws IOException {
        // Given
        File testFile = createTestFile(
                "Regular,Opel,Astra K,81,1199,5.2\n" +
                        "Electric,Tesla,Model 3 RWD,208,60,20"
        );
        collection.readFromFile(testFile.getAbsolutePath());

        // When
        Optional<Model> result = collection.getModel("Audi", "A4");

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    void testGetModelCaseSensitive() throws IOException {
        // Given
        File testFile = createTestFile("Electric,Tesla,Model 3 RWD,208,60,20");
        collection.readFromFile(testFile.getAbsolutePath());

        // When
        Optional<Model> result1 = collection.getModel("tesla", "Model 3 RWD"); // lowercase
        Optional<Model> result2 = collection.getModel("Tesla", "model 3 rwd"); // lowercase

        // Then
        assertFalse(result1.isPresent()); // Should not find due to case sensitivity
        assertFalse(result2.isPresent()); // Should not find due to case sensitivity
    }

    @Test
    void testGetElectricModelsByBatteryCapacity() throws IOException {
        // Given
        File testFile = createTestFile(
                "Electric,Tesla,Model 3 RWD,208,60,20\n" +  // 60 kWh
                        "Electric,BMW,i4,250,83,19\n" +              // 83 kWh
                        "Electric,Mercedes-Benz,EQA,140,66,17\n" +   // 66 kWh
                        "Electric,Nissan,Leaf,110,40,17\n" +         // 40 kWh (below limit)
                        "Regular,Opel,Astra K,81,1199,5.2"           // Should be filtered out
        );
        collection.readFromFile(testFile.getAbsolutePath());

        // When
        List<ElectricModel> result = collection.getElectricModelsByBatteryCapacity(50);

        // Then
        assertEquals(3, result.size());

        // Verify specific models and order (should be sorted by manufacturer, modelName)
        assertEquals("BMW", result.get(0).getManufacturer());
        assertEquals("Mercedes-Benz", result.get(1).getManufacturer());
        assertEquals("Tesla", result.get(2).getManufacturer());

        // Verify battery capacities
        assertTrue(result.stream().allMatch(model -> model.getBatteryCapacity() >= 50));
    }

    @Test
    void testGetElectricModelsByBatteryCapacityNoMatches() throws IOException {
        // Given
        File testFile = createTestFile(
                "Electric,Nissan,Leaf,110,40,17\n" +  // 40 kWh
                        "Electric,Smart,Fortwo,55,28,16\n" +   // 28 kWh
                        "Regular,Opel,Astra K,81,1199,5.2"
        );
        collection.readFromFile(testFile.getAbsolutePath());

        // When
        List<ElectricModel> result = collection.getElectricModelsByBatteryCapacity(50);

        // Then
        assertEquals(0, result.size());
    }

    @Test
    void testGetElectricModelsByBatteryCapacityEmptyCollection() {
        // When (no data loaded)
        List<ElectricModel> result = collection.getElectricModelsByBatteryCapacity(50);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetModelsReturnsCopy() throws IOException {
        // Given
        File testFile = createTestFile("Electric,Tesla,Model 3 RWD,208,60,20");
        collection.readFromFile(testFile.getAbsolutePath());

        // When
        List<Model> original = collection.getModels();
        original.clear(); // Try to modify the returned list

        // Then - original collection should not be affected
        List<Model> afterModification = collection.getModels();
        assertEquals(1, afterModification.size());
        assertEquals(0, original.size()); // The copy was cleared, not the original
    }

    @Test
    void testReadFromFileWithEmptyLines() throws IOException {
        // Given
        File testFile = createTestFile(
                "Regular,Opel,Astra K,81,1199,5.2\n" +
                        "\n" +  // Empty line
                        "  \n" + // Whitespace line
                        "Electric,Tesla,Model 3 RWD,208,60,20\n" +
                        "" // Another empty line at end
        );

        // When
        collection.readFromFile(testFile.getAbsolutePath());

        // Then - should ignore empty lines
        assertEquals(2, collection.getModels().size());
    }

    @Test
    void testReadFromFileWithWhitespace() throws IOException {
        // Given
        File testFile = createTestFile(
                "  Regular  ,  Opel  ,  Astra K  ,  81  ,  1199  ,  5.2  \n" +
                        "  Electric  ,  Tesla  ,  Model 3 RWD  ,  208  ,  60  ,  20  "
        );

        // When
        collection.readFromFile(testFile.getAbsolutePath());

        // Then - should trim whitespace
        Optional<Model> opel = collection.getModel("Opel", "Astra K");
        assertTrue(opel.isPresent());

        Optional<Model> tesla = collection.getModel("Tesla", "Model 3 RWD");
        assertTrue(tesla.isPresent());
    }

    // Helper method to create temporary test files
    private File createTestFile(String content) throws IOException {
        File testFile = tempDir.resolve("test_models.csv").toFile();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(content);
        }
        return testFile;
    }
}