package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartingFlightTest {
    private DepartingFlight testDepartingFlight0;

    @BeforeEach
    void setup() {
        testDepartingFlight0 = new DepartingFlight(
                "Air Canada",
                58,
                "Vancouver",
                "On Time",
                "07:00",
                "07:07");
    }

    @Test
    public void testConstructor() {
        assertEquals("Air Canada", testDepartingFlight0.getAirline());
        assertEquals(58, testDepartingFlight0.getFlightNumber());
        assertEquals("Vancouver", testDepartingFlight0.getDestination());
        assertEquals("On Time", testDepartingFlight0.getStatus());
        assertEquals("07:00", testDepartingFlight0.getScheduledDepartureTime());
        assertEquals("07:07", testDepartingFlight0.getEstimatedDepartureTime());
    }

    @Test
    public void TestCancelFlight() {
        testDepartingFlight0.cancelFlight(testDepartingFlight0);

        assertEquals("CANCELLED", testDepartingFlight0.getStatus());
        assertEquals("xx:xx", testDepartingFlight0.getEstimatedDepartureTime());
    }

    @Test
    public void TestCancelFlightMultipleTimes() {
        DepartingFlight testDepartingFlight1 = new DepartingFlight(
                "Air Canada",
                558,
                "Vancouver",
                "On Time",
                "07:50",
                "07:57");
        DepartingFlight testDepartingFlight2 = new DepartingFlight(
                "Air Canada",
                458,
                "Vancouver",
                "On Time",
                "07:50",
                "07:57");

        testDepartingFlight0.cancelFlight(testDepartingFlight0);
        testDepartingFlight0.cancelFlight(testDepartingFlight1);
        testDepartingFlight0.cancelFlight(testDepartingFlight2);

        assertEquals("CANCELLED", testDepartingFlight0.getStatus());
        assertEquals("xx:xx", testDepartingFlight0.getEstimatedDepartureTime());
        assertEquals("CANCELLED", testDepartingFlight1.getStatus());
        assertEquals("xx:xx", testDepartingFlight1.getEstimatedDepartureTime());
        assertEquals("CANCELLED", testDepartingFlight2.getStatus());
        assertEquals("xx:xx", testDepartingFlight2.getEstimatedDepartureTime());
    }

    @Test
    public void TestSetEstimatedDepartingTimeWhenFlightAlreadyCancelled() {
        DepartingFlight testDepartingFlight1 = new DepartingFlight(
                "Air Canada",
                458,
                "Vancouver",
                "CANCELLED",
                "07:50",
                "07:57");

        testDepartingFlight1.setEstimatedDepartureTime("08:30");
        assertEquals("xx:xx", testDepartingFlight1.getEstimatedDepartureTime());
    }

    @Test
    public void TestSetEstimatedDepartingTimeWhenFlightNotAlreadyCancelled() {
        DepartingFlight testDepartingFlight5 = new DepartingFlight(
                "Air Canada",
                321,
                "Vancouver",
                "ON TIME",
                "07:50",
                "07:57");

        testDepartingFlight5.setEstimatedDepartureTime("08:30");
        assertEquals("08:30", testDepartingFlight5.getEstimatedDepartureTime());
    }
}
