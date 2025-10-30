package perser;

import model.ElectricModel;
import model.Model;
import model.RegularModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelParserTest {



    @Test
    void testParseValidRegularModel() {
        String line = "Opel,A,81,1199,5.2";
        Model model = ModelParser.parse(line);
        assertTrue(model instanceof RegularModel, "Expected an instance of RegularModel");
        RegularModel rm = (RegularModel) model;
        assertEquals("Opel", rm.getManufacturer());
        assertEquals("A", rm.getModelName());
        assertEquals(81, rm.getPower());
        assertEquals(1199, rm.getEngineDisplacement());
        assertEquals(5.2, rm.getFuelConsumption());
    }

    @Test
    void testParseInvalidFormatRaisesError() {
        String line = "Invalid,Format,123";
        assertThrows(IllegalArgumentException.class, () -> ModelParser.parse(line));
    }

    @Test
    void testParseInvalidTypeRaisesError() {
        String line = "Unknown,Model,1,2,3,4,5";
        assertThrows(IllegalArgumentException.class, () -> ModelParser.parse(line));
    }
}

