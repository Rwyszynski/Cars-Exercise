package car;

import model.ElectricModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest {

    private ElectricModel model;
    private ElectricCar car;

    @BeforeEach
    void createCar() {
        model = new ElectricModel("Tesla", "M3", 150, 80, 15);
        car = new ElectricCar("TESLA123", model, 0, model.getBatteryCapacity());
    }

    @Test
    void drive100kmReducesBatteryAndRiseKm() {
        car.drive(100);

        assertEquals(65, car.getBatteryLevel());
        assertEquals(100, car.getDistanceDriven());
    }

    @Test
    void driveTwiceConsumesBatteryVel() {
        car.drive(100);
        car.drive(200);
        assertEquals(35, car.getBatteryLevel());
        assertEquals(300, car.getDistanceDriven());
    }

    @Test
    void driveTooFar_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> car.drive(600));
    }

    @Test
    void consoleOutputContainsExpectedValues() {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        car.drive(100);
        car.drive(200);

        String output = out.toString();

    }
}