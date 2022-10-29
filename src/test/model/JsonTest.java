package model;

import model.ArrivingFlight;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Inspiration for code here taken from Json Demo provided to CPSC 210 students on edX Edge course
 */

public class JsonTest {
    protected void checkArrFlight(String airline, int flightNumber, ArrivingFlight thingy) {
        assertEquals(airline, thingy.getAirline());
        assertEquals(flightNumber, thingy.getFlightNumber());
    }

    protected void checkDepFlight(String airline, int flightNumber, DepartingFlight thingy) {
        assertEquals(airline, thingy.getAirline());
        assertEquals(flightNumber, thingy.getFlightNumber());
    }

    protected void checkAlert(String alertString, Alert alert) {
        assertEquals(alertString, alert.getAlert());
    }
}
