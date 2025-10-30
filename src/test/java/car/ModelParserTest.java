package perser;

import model.ElectricModel;
import model.Model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelParserTest {

    private final ModelParser parser = new ModelParser();

    @Test
    public void testParseValidElectricModel() {
        String line = "Electric,Tesla,Model 3 RWD,208,60,20";
        Model model = parser.parse(line);

        assertTrue(model instanceof ElectricModel, "Expected an instance of ElectricModel");
        ElectricModel em = (ElectricModel) model;
        assertEquals("Tesla", em.getManufacturer());
        assertEquals("Model 3 RWD", em.getModelName());
        assertEquals(208, em.getPower());
        assertEquals(60, em.getBatteryCapacity());
        assertEquals(20, em.getEnergyConsumption());
    }
}