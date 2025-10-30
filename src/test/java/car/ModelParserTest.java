package car;

import model.ElectricModel;
import model.Model;
import model.RegularModel;
import org.junit.jupiter.api.Test;
import perser.ModelParser;

import static org.junit.jupiter.api.Assertions.*;

    class ModelParserTest {

        @Test
        void testParseValidElectricModel() {
            String line = "Electric,Tesla,Model 3 RWD,208,60,20";
            Model model = ModelParser.parse(line);
            assertTrue(model instanceof ElectricModel); // To powinno przejść
        }

        @Test
        void testParseValidRegularModel() {
            String line = "Opel,A,81,1199,5.2"; // Test używa 5 części bez typu
            Model model = ModelParser.parse(line);
            assertTrue(model instanceof RegularModel); // To powinno przejść
        }

        @Test
        void testParseInvalidTypeRaisesError() {
            String line = "Diesel,Test,Model,100,50,10";
            assertThrows(IllegalArgumentException.class, () -> ModelParser.parse(line));
        }

        @Test
        void testParseInvalidFormatRaisesError() {
            String line = "Invalid,Format,123";
            assertThrows(IllegalArgumentException.class, () -> ModelParser.parse(line));
        }
    }