package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
Inspiration for code here taken from Json Demo provided to CPSC 210 students on edX Edge course
 */

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlightDisplay fd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFlightDisplay() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            FlightDisplay fd = reader.read();
            assertTrue(fd.arrivingFlights.isEmpty());
            assertTrue(fd.departingFlights.isEmpty());
            assertTrue(fd.emergencyAlerts.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFlightDisplay() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            FlightDisplay fd = reader.read();
            List<ArrivingFlight> arrFlights = fd.getArrivingFlights();
            List<DepartingFlight> depFlights = fd.getDepartingFlights();
            List<Alert> alertsList = fd.getEmergencyAlerts();
            assertEquals(1, arrFlights.size());
            assertEquals(1, depFlights.size());
            assertEquals(1, alertsList.size());
            checkArrFlight("PIA", 111, arrFlights.get(0));
            checkDepFlight("PIA", 112, depFlights.get(0));
            checkAlert("Alert xyz", alertsList.get(0));
            //checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
