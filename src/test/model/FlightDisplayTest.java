package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightDisplayTest {
    private FlightDisplay testFlightDisplay;
    private ArrivingFlight testArrFlight;
    private DepartingFlight testDepFlight;
    private Alert testEmergencyAlert;

    @BeforeEach
    void setup() {
        testFlightDisplay = new FlightDisplay();
        testArrFlight = new ArrivingFlight(
                "Air Canada",
                58,
                "Vancouver",
                "On Time",
                "07:00",
                "07:07");
        testDepFlight = new DepartingFlight(
                "Air Canada",
                58,
                "Vancouver",
                "On Time",
                "07:00",
                "07:07");
        testEmergencyAlert = new Alert("Wallet found at Gate A66");
        testFlightDisplay.addArrivingFlight(testArrFlight);
        testFlightDisplay.addDepartingFlight(testDepFlight);
        testFlightDisplay.addEmergencyAlert(testEmergencyAlert);
    }

    @Test
    public void testConstructor() {
        boolean isArrFlight = testFlightDisplay.getArrivingFlights().contains(testArrFlight);
        assertTrue(isArrFlight);
        boolean isDepFlight = testFlightDisplay.getDepartingFlights().contains(testDepFlight);
        assertTrue(isDepFlight);
        boolean isAlert = testFlightDisplay.getEmergencyAlerts().contains(testEmergencyAlert);
        assertTrue(isAlert);
    }

    @Test
    public void testAddArrivingFlight() {
        ArrivingFlight testArrFlight1 = new ArrivingFlight("Pia", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addArrivingFlight(testArrFlight1);
        boolean containsTestFlight1 = testFlightDisplay.arrivingFlights.contains(testArrFlight1);
        assertTrue(containsTestFlight1);
    }

    @Test
    public void testAddArrivingFlightMultipleTimes() {
        ArrivingFlight testArrFlight1 = new ArrivingFlight("Pia", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addArrivingFlight(testArrFlight1);
        boolean containsTestFlight1 = testFlightDisplay.arrivingFlights.contains(testArrFlight1);
        assertTrue(containsTestFlight1);

        ArrivingFlight testArrFlight2 = new ArrivingFlight("KLM", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addArrivingFlight(testArrFlight2);
        boolean containsTestFlight2 = testFlightDisplay.arrivingFlights.contains(testArrFlight2);
        assertTrue(containsTestFlight2);
    }

    @Test
    public void testAddDepartingFlight() {
        DepartingFlight testDepFlight1 = new DepartingFlight("Pia", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addDepartingFlight(testDepFlight1);
        boolean containsTestFlight1 = testFlightDisplay.departingFlights.contains(testDepFlight1);
        assertTrue(containsTestFlight1);
    }

    @Test
    public void testAddDepartingFlightMultipleTimes() {
        DepartingFlight testDepFlight1 = new DepartingFlight("Pia", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addDepartingFlight(testDepFlight1);
        boolean containsTestFlight1 = testFlightDisplay.departingFlights.contains(testDepFlight1);
        assertTrue(containsTestFlight1);

        DepartingFlight testDepFlight2 = new DepartingFlight("KLM", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addDepartingFlight(testDepFlight2);
        boolean containsTestFlight2 = testFlightDisplay.departingFlights.contains(testDepFlight2);
        assertTrue(containsTestFlight2);
    }

    @Test
    public void testAddEmergencyAlert() {
        Alert testAlert1 = new Alert("Alert 1");
        testFlightDisplay.addEmergencyAlert(testAlert1);
        boolean containsTestAlert1 = testFlightDisplay.emergencyAlerts.contains(testAlert1);
        assertTrue(containsTestAlert1);
    }

    @Test
    public void testAddEmergencyAlertMultipleTimes() {
        Alert testAlert1 = new Alert("Alert 1");
        testFlightDisplay.addEmergencyAlert(testAlert1);
        boolean containsTestAlert1 = testFlightDisplay.emergencyAlerts.contains(testAlert1);
        assertTrue(containsTestAlert1);

        Alert testAlert2 = new Alert("Alert 2");
        testFlightDisplay.addEmergencyAlert(testAlert2);
        boolean containsTestAlert2 = testFlightDisplay.emergencyAlerts.contains(testAlert2);
        assertTrue(containsTestAlert2);
    }

    @Test
    public void testRemoveArrivingFlight() {
        testFlightDisplay.removeArrivingFlight(testArrFlight);
        boolean isEmpty = testFlightDisplay.arrivingFlights.isEmpty();
        assertTrue(isEmpty);
    }

    @Test
    public void testRemoveArrivingFlightMultipleTimes() {
        ArrivingFlight testArrFlight1 = new ArrivingFlight("Pia", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addArrivingFlight(testArrFlight1);
        testFlightDisplay.removeArrivingFlight(testArrFlight);
        assertFalse(testFlightDisplay.arrivingFlights.contains(testArrFlight));

        testFlightDisplay.removeArrivingFlight(testArrFlight1);
        assertFalse(testFlightDisplay.arrivingFlights.contains(testArrFlight1));

    }

    @Test
    public void testRemoveDepartingFlight() {
        testFlightDisplay.removeDepartingFlight(testDepFlight);
        boolean isEmpty = testFlightDisplay.departingFlights.isEmpty();
        assertTrue(isEmpty);
    }

    @Test
    public void testRemoveDepartingFlightMultipleTimes() {
        DepartingFlight testDepFlight1 = new DepartingFlight("Pia", 222,
                "Doha", "Delayed", "22:22", "22:22");
        testFlightDisplay.addDepartingFlight(testDepFlight1);
        testFlightDisplay.removeDepartingFlight(testDepFlight);
        assertFalse(testFlightDisplay.departingFlights.contains(testDepFlight));

        testFlightDisplay.removeDepartingFlight(testDepFlight1);
        assertFalse(testFlightDisplay.departingFlights.contains(testDepFlight1));
    }

    @Test
    public void testRemoveEmergencyAlert() {
        testFlightDisplay.removeEmergencyAlert(testEmergencyAlert);
        boolean isEmpty = testFlightDisplay.emergencyAlerts.isEmpty();
        assertTrue(isEmpty);
    }

    @Test
    public void testRemoveEmergencyAlertsMultipleTimes() {
        Alert testEmergencyAlert1 = new Alert("Alert 1");
        testFlightDisplay.addEmergencyAlert(testEmergencyAlert1);
        testFlightDisplay.removeEmergencyAlert(testEmergencyAlert);
        assertFalse(testFlightDisplay.emergencyAlerts.contains(testEmergencyAlert));

        testFlightDisplay.removeEmergencyAlert(testEmergencyAlert1);
        assertFalse(testFlightDisplay.emergencyAlerts.contains(testEmergencyAlert1));
    }

}
