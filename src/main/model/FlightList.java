package model;

import java.util.ArrayList;
import java.util.List;

public class FlightList {
    /*
     * TODO: take inspiration from the Teller application
     * TODO: determine the function of this class: Ask user to add/remove flights to this since
     *       I don't want to parse from file
     * possible things to use this list for:
     * present options to user:
     *     to modify contents of the list,
     *     to modify fields of Flights in the list
     * make two separate lists for arriving and departing flight which could then be used separately in the different
     * departure and arrival sections on the display.
     */

    protected List<Flight> arrivingFlights = new ArrayList<>();
    protected List<Flight> departingFlights = new ArrayList<>();

    // REQUIRES: a flight not already in the list
    // MODIFIES: this
    // EFFECTS: adds an arriving flight to the list
    public void addArrivingFlight(ArrivingFlight flight) {
        arrivingFlights.add(flight);
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes an arriving flight to the list
    public void removeArrivingFlight(ArrivingFlight flight) {
        arrivingFlights.remove(flight);
    }

    // REQUIRES: a flight not already in the list
    // MODIFIES: this
    // EFFECTS: adds a departing flight to the list
    public void addDepartingFlight(ArrivingFlight flight) {
        arrivingFlights.add(flight);
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes a departing flight to the list
    public void removeDepartingFlight(ArrivingFlight flight) {
        arrivingFlights.remove(flight);
    }
}
