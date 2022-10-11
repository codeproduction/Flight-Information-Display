package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartingFlightTest {
    private DepartingFlight testArrivingFlight;

    @BeforeEach
    void setup() {
        testArrivingFlight = new DepartingFlight(
                "Air Canada",
                58,
                "Vancouver",
                "On Time",
                "07:00",
                "07:07");
    }

    @Test
    public void testConstructor() {
        assertEquals("Air Canada", testArrivingFlight.getAirline());
        assertEquals(58, testArrivingFlight.getFlightNumber());
        assertEquals("Vancouver", testArrivingFlight.getOrigin());
        assertEquals("On Time", testArrivingFlight.getStatus());
        assertEquals("07:00", testArrivingFlight.getScheduledDepartureTime());
        assertEquals("07:07", testArrivingFlight.getEstimatedDepartureTime());
    }
}
