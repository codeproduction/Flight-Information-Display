package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrivingFlightTest {
    private ArrivingFlight testArrivingFlight0;

    @BeforeEach
    void setup() {
        testArrivingFlight0 = new ArrivingFlight("Air Canada",59,"Doha","On Time",
                "20:00", "19:55");
    }

    @Test
    public void testConstructor() {
        ArrivingFlight testArrivingFlight1 = new ArrivingFlight( "Air Arabia",
                559,
                "Doha",
                "On Time",
                "10:00",
                "13:05");

        assertEquals("Air Arabia", testArrivingFlight1.getAirline());
        assertEquals(559, testArrivingFlight1.getFlightNumber());
        assertEquals("Doha", testArrivingFlight1.getOrigin());
        assertEquals("On Time", testArrivingFlight1.getStatus());
        assertEquals("10:00", testArrivingFlight1.getScheduledArrivalTime());
        assertEquals("13:05", testArrivingFlight1.getEstimatedArrivalTime());
    }

    @Test
    public void TestCancelFlight() {
        testArrivingFlight0.cancelFlight(testArrivingFlight0);

        assertEquals("CANCELLED", testArrivingFlight0.getStatus());
        assertEquals("xx:xx", testArrivingFlight0.getEstimatedArrivalTime());
    }

    @Test
    public void TestCancelFlightMultipleTimes() {
        ArrivingFlight testArrivingFlight1 = new ArrivingFlight(
                "Air Canada",
                558,
                "Vancouver",
                "On Time",
                "07:50",
                "07:57");
        ArrivingFlight testArrivingFlight2 = new ArrivingFlight(
                "Air Canada",
                458,
                "Vancouver",
                "On Time",
                "07:50",
                "07:57");

        testArrivingFlight0.cancelFlight(testArrivingFlight0);
        testArrivingFlight1.cancelFlight(testArrivingFlight1);
        testArrivingFlight2.cancelFlight(testArrivingFlight2);

        assertEquals("CANCELLED", testArrivingFlight0.getStatus());
        assertEquals("xx:xx", testArrivingFlight0.getEstimatedArrivalTime());
        assertEquals("CANCELLED", testArrivingFlight1.getStatus());
        assertEquals("xx:xx", testArrivingFlight1.getEstimatedArrivalTime());
        assertEquals("CANCELLED", testArrivingFlight2.getStatus());
        assertEquals("xx:xx", testArrivingFlight2.getEstimatedArrivalTime());
    }

    @Test
    public void TestSetEstimatedArrivalTimeWhenFlightAlreadyCancelled() {
        ArrivingFlight testArrivingFlight1 = new ArrivingFlight(
                "Air Canada",
                458,
                "Vancouver",
                "CANCELLED",
                "07:50",
                "07:57");

        testArrivingFlight1.setEstimatedArrivalTime("08:30");
        assertEquals("xx:xx", testArrivingFlight1.getEstimatedArrivalTime());
    }

    @Test
    public void TestSetEstimatedArrivalTimeWhenFlightNotAlreadyCancelled() {
        ArrivingFlight testArrivingFlight5 = new ArrivingFlight(
                "Air Canada",
                567,
                "Vancouver",
                "DELAYED",
                "07:50",
                "07:57");

        testArrivingFlight5.setEstimatedArrivalTime("08:30");
        assertEquals("08:30", testArrivingFlight5.getEstimatedArrivalTime());
    }

}
