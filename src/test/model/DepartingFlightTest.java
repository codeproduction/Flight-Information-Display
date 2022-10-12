package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartingFlightTest {
    private DepartingFlight testDepartingFlight;

    @BeforeEach
    void setup() {
        testDepartingFlight = new DepartingFlight(
                "Air Canada",
                58,
                "Vancouver",
                "On Time",
                "07:00",
                "07:07");
    }

    @Test
    public void testConstructor() {
        assertEquals("Air Canada", testDepartingFlight.getAirline());
        assertEquals(58, testDepartingFlight.getFlightNumber());
        assertEquals("Vancouver", testDepartingFlight.getDestination());
        assertEquals("On Time", testDepartingFlight.getStatus());
        assertEquals("07:00", testDepartingFlight.getScheduledDepartureTime());
        assertEquals("07:07", testDepartingFlight.getEstimatedDepartureTime());
    }

    @Test
    public void TestCancelFlight() {
        testDepartingFlight.cancelFlight(testDepartingFlight);

        assertEquals("CANCELLED", testDepartingFlight.getStatus());
        assertEquals("XX:XX", testDepartingFlight.getEstimatedDepartureTime());
    }
}
