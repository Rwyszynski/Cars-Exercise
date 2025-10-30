package parser;

import model.ElectricModel;
import model.Model;
import model.RegularModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelParserTest {

    @Test
    void testParseValidElectricModel() {
        // Given
        String line = "Electric,Tesla,Model 3 RWD,208,60,20";

        // When
        Model model = ModelParser.parse(line);

        // Then
        assertTrue(model instanceof ElectricModel);
        ElectricModel electricModel = (ElectricModel) model;
        assertEquals("Tesla", electricModel.getManufacturer());
        assertEquals("Model 3 RWD", electricModel.getModelName());
        assertEquals(208, electricModel.getPower());
        assertEquals(60, electricModel.getBatteryCapacity());
        assertEquals(20, electricModel.getEnergyConsumption());
    }

    @Test
    void testParseValidRegularModel() {
        // Given
        String line = "Regular,Opel,Astra K,81,1199,5.2";

        // When
        Model model = ModelParser.parse(line);

        // Then
        assertTrue(model instanceof RegularModel);
        RegularModel regularModel = (RegularModel) model;
        assertEquals("Opel", regularModel.getManufacturer());
        assertEquals("Astra K", regularModel.getModelName());
        assertEquals(81, regularModel.getPower());
        assertEquals(1199, regularModel.getEngineDisplacement());
        assertEquals(5.2, regularModel.getFuelConsumption(), 0.001);
    }

    @Test
    void testParseInvalidType() {
        // Given
        String line = "Diesel,Test,Model,100,50,10";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ModelParser.parse(line));
        assertTrue(exception.getMessage().contains("Unknown type"));
    }

    @Test
    void testParseInvalidFormat() {
        // Given
        String line = "Electric,Tesla,Model 3"; // Too few parameters

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ModelParser.parse(line));
        assertTrue(exception.getMessage().contains("Invalid number of parameters"));
    }

    @Test
    void testParseEmptyLine() {
        // Given
        String line = "";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ModelParser.parse(line));
        assertTrue(exception.getMessage().contains("Line is empty or null"));
    }

    @Test
    void testParseNullLine() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ModelParser.parse(null));
        assertTrue(exception.getMessage().contains("Line is empty or null"));
    }

    @Test
    void testParseWithWhitespace() {
        // Given
        String line = "  Electric  ,  Tesla  ,  Model 3 RWD  ,  208  ,  60  ,  20  ";

        // When
        Model model = ModelParser.parse(line);

        // Then
        assertTrue(model instanceof ElectricModel);
        ElectricModel electricModel = (ElectricModel) model;
        assertEquals("Tesla", electricModel.getManufacturer());
        assertEquals("Model 3 RWD", electricModel.getModelName());
        assertEquals(208, electricModel.getPower());
        assertEquals(60, electricModel.getBatteryCapacity());
        assertEquals(20, electricModel.getEnergyConsumption());
    }
}