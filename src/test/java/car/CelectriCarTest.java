package car;

import com.epam.cars.car.ElectricCar;
import com.epam.cars.model.ElectricModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest {

    private ElectricModel teslaModel;
    private ElectricCar teslaCar;

    @BeforeEach
    void setUp() {
        teslaModel = new ElectricModel("Tesla", "M3", 150, 80, 15);
        teslaCar = new ElectricCar("BWMTESLA", teslaModel, 0, 80);
    }

    @Test
    void drive50km() {
        teslaCar.drive(50);
        int expectedBattery = 80 - (int)Math.round(15 / 100.0 * 50);
        assertEquals(expectedBattery, teslaCar.getBatteryLevel());
        assertEquals(50, teslaCar.getDistanceDriven());
    }

    @Test
    void drive100km() {
        teslaCar.drive(100);
        int expectedBattery = 80 - (int)Math.round(15 / 100.0 * 100);
        assertEquals(expectedBattery, teslaCar.getBatteryLevel());
        assertEquals(100, teslaCar.getDistanceDriven());
    }

    @Test
    void drive100kmAnd50km() {
        teslaCar.drive(100);
        teslaCar.drive(50);
        int expectedBattery = 80 - (int)Math.round(15 / 100.0 * 150);
        assertEquals(expectedBattery, teslaCar.getBatteryLevel());
        assertEquals(150, teslaCar.getDistanceDriven());
    }

    @Test
    void driveTooFar_throwsException() {

        Exception exception = assertThrows(IllegalStateException.class, () -> teslaCar.drive(600));
        assertTrue(exception.getMessage().contains("Not enough battery"));
    }
}