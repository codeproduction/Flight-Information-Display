package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrivingFlightTest {
    private ArrivingFlight testArrivingFlight;

    @BeforeEach
    void setup() {
        testArrivingFlight = new ArrivingFlight(
                "Air Canada",
                59,
                "Doha",
                "On Time",
                "20:00",
                "19:55");
    }

    @Test
    public void testConstructor() {
        assertEquals("Air Canada", testArrivingFlight.getAirline());
        assertEquals(59, testArrivingFlight.getFlightNumber());
        assertEquals("Doha", testArrivingFlight.getOrigin());
        assertEquals("On Time", testArrivingFlight.getStatus());
        assertEquals("20:00", testArrivingFlight.getScheduledArrivalTime());
        assertEquals("19:55", testArrivingFlight.getEstimatedArrivalTime());
    }

}
