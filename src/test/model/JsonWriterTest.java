package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
Inspiration for code here taken from Json Demo provided to CPSC 210 students on edX Edge course
 */

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            FlightDisplay wr = new FlightDisplay();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            FlightDisplay fd = new FlightDisplay();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(fd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            fd = reader.read();
            assertEquals(0, fd.getArrivingFlights().size());
            assertEquals(0, fd.getDepartingFlights().size());
            assertEquals(0, fd.getEmergencyAlerts().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            FlightDisplay fd = new FlightDisplay();
            fd.addArrivingFlight(new ArrivingFlight("KLM", 444,
                    "Doha", "Delayed","11:22", "11:44"));
            fd.addDepartingFlight(new DepartingFlight("KLM",444,
                    "Doha", "Delayed","11:22", "11:44"));
            fd.addEmergencyAlert(new Alert("Alert abc"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(fd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            fd = reader.read();
            List<ArrivingFlight> arrFlights = fd.getArrivingFlights();
            List<DepartingFlight> depFlights = fd.getDepartingFlights();
            List<Alert> emergencyAlerts = fd.getEmergencyAlerts();
            assertEquals(1, arrFlights.size());
            assertEquals(1, depFlights.size());
            assertEquals(1, emergencyAlerts.size());
            checkArrFlight("KLM", 444, arrFlights.get(0));
            checkDepFlight("KLM", 444, depFlights.get(0));
            checkAlert("Alert abc", emergencyAlerts.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
