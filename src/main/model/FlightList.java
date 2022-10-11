package model;

import java.util.ArrayList;
import java.util.List;

public class FlightList {
    /*
     * TODO: CONSIDER ADDING AFTER IMPLEMENTATION OF OTHERS: add emergency passenger alert section
     * TODO: take inspiration from the Teller application
     * TODO: determine the function of this class: Ask user to add/remove flights to this since
     *       I don't want to parse from file
     * todo later...........................: summary statistics
     * possible things to use this list for:
     *   present options to user:
     *     to modify contents of the list,
     *     to modify fields of Flights in the list
     */

    protected List<ArrivingFlight> arrivingFlights = new ArrayList<>();
    protected List<DepartingFlight> departingFlights = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: runs the application which will set up the FlightDisplay using information from user
    public void runFlightDisplay() {
        /*
         * 1. Display/Print FlightDisplay         *
         * 2. Present options to user:
         *      - add/remove/update arrivingFlight
         *      - add/remove/update departingFlight
         *      - add/remove emergency alerts
         *      - end/quit program ???
         */
    }



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
    public void addDepartingFlight(DepartingFlight flight) {
        departingFlights.add(flight);
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes a departing flight to the list
    public void removeDepartingFlight(DepartingFlight flight) {
        departingFlights.remove(flight);
    }
}
