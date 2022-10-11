package ui;

import model.ArrivingFlight;
import model.DepartingFlight;

import java.util.List;
import java.util.Scanner;

public class FlightDisplay {
    /*
     * TODO PURPOSE Ask user to add/remove flights to this since
     *       I don't want to parse from file
     * TODO: CONSIDER ADDING AFTER IMPLEMENTATION OF OTHERS: add emergency passenger alert section
     * TODO: take inspiration from the Teller application
     * possible things to use this list for:
     *   present options to user:
     *     to modify contents of the list of BOTH types of lists,
     *     to modify/update fields of individual Flights in both lists
     * todo later...........................: summary statistics
     */

    protected List<ArrivingFlight> arrivingFlights;
    protected List<DepartingFlight> departingFlights;
    private List<String> emergencyAlerts;
    private Scanner input;

    public FlightDisplay() {
        runFlightDisplay();
    }

    // MODIFIES: this
    // EFFECTS: Runs the application which will set up the FlightDisplay using information from user
    public void runFlightDisplay() {
        /*
         * 1. Initialize setup
         * 2. Present options to user: ---- METHOD displayOptions()
         *      - add/remove/update arrivingFlight METHOD to add + remove + update status
         *      - add/remove/update departingFlight METHOD to add + remove + update status
         *      - add/remove emergency alerts METHOD to add + remove emergency alerts
         *      - end/quit program ???
         * 3. Display/Print FlightDisplay after user chooses an option other than quitting
         */
        boolean isDisplayNeeded = true;
        String option;
        init();

        while (isDisplayNeeded) {
            displayOptionsMenu();
            option = input.next();

            if (option.equals("q")) {
                isDisplayNeeded = false;
            } else {
                processCommand(option);
            }
        }
    }

    // MODIFIES: Fields of the class FlightList
    // EFFECTS: Initializes lists of Flights and emergency Alerts
    public void init() {
        // stub
    }

    // EFFECTS: Displays actions user can perform
    public void displayOptionsMenu() {
        // stub
    }

    // rename method
    // REQUIRES:
    // MODIFIES
    // EFFECTS
    public void processCommand(String action) {
        // stub
    }

    public void addEmergencyAlert(String alert) {
        emergencyAlerts.add(alert);
    }

    // REQUIRES: alert passed to function must already be contained in the emergencyAlerts list
    // MODIFIES: List<String> emergencyAlerts
    // EFFECTS: removes specified alert
    public void removeEmergencyAlert(String alert) {
        emergencyAlerts.remove(alert);
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
