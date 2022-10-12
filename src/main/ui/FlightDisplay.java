package ui;

import model.Alert;
import model.ArrivingFlight;
import model.DepartingFlight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightDisplay {
    /*
     * todo later...: summary statistics
     */

    protected List<ArrivingFlight> arrivingFlights;
    protected List<DepartingFlight> departingFlights;
    private List<Alert> emergencyAlerts;
    private Scanner input;

    public FlightDisplay() {
        runFlightDisplay();
    }

    // MODIFIES: this
    // EFFECTS: Runs the application which will set up the FlightDisplay using information from user
    private void runFlightDisplay() {
        /*
         * 1. Initialize setup
         * 2. Present options to user: ---- METHOD displayOptions()
         * 3. Display/Print FlightDisplay after user chooses an option other than quitting
         */
        boolean isDisplayNeeded = true;
        String option;
        initializeSetup();

        while (isDisplayNeeded) {
            displayOptionsMenu();
            option = input.next();
            option = option.toLowerCase();

            if (option.equals("l")) {
                isDisplayNeeded = false;
            } else {
                executeUserCommand(option);
                System.out.println("\nLatest flight information and status:\n");
                printFlightDisplay();
            }
        }
    }

    // MODIFIES: Fields of the class FlightList
    // EFFECTS: Initializes lists of Flights and emergency Alerts
    private void initializeSetup() {
        arrivingFlights = new ArrayList<>();
        departingFlights = new ArrayList<>();
        emergencyAlerts = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: Displays actions user can perform
    private void displayOptionsMenu() {
        System.out.println("\nChoose from the following actions:");
        System.out.println("\ta => add arriving flight");
        System.out.println("\tb => update estimated arrival time and status");
        System.out.println("\tc => cancel arriving flight");
        System.out.println("\td => remove arriving flight");
        System.out.println("\te => add departing flight");
        System.out.println("\tf => update estimated departure time and status");
        System.out.println("\tg => cancel departing flight");
        System.out.println("\th => remove departing flight");
        System.out.println("\ti => add emergency alert");
        System.out.println("\tj => remove emergency alert");
        System.out.println("\tk => print all flights");
        System.out.println("\tl => end program");
    }

    // REQUIRES:
    // MODIFIES
    // EFFECTS
    private void executeUserCommand(String action) {
        if (action.equals("a")) {
            addArrivingFlight();
        } else if (action.equals("b")) {
            updateArrivingFlight();
        } else if (action.equals("c")) {
            cancelArrivingFlight();
        } else if (action.equals("d")) {
            removeArrivingFlight();
        } else if (action.equals("e")) {
            addDepartingFlight();
        } else if (action.equals("f")) {
            updateDepartingFlight();
        } else if (action.equals("g")) {
            cancelDepartingFlight();
        } else if (action.equals("h")) {
            removeDepartingFlight();
        } else if (action.equals("i")) {
            addEmergencyAlert();
        } else if (action.equals("j")) {
            removeEmergencyAlert();
        } else if (action.equals("k")) {
            printFlightDisplay();
        }
    }

    private void printFlightDisplay() {
        System.out.println("ARRIVALS: ");
        printAllArrivingFlights();
        System.out.println("DEPARTURES: ");
        printAllDepartingFlights();
        System.out.println("EMERGENCY ALERTS: ");
        printAllEmergencyAlerts();
    }

    private void printAllArrivingFlights() {
        for (ArrivingFlight flight : arrivingFlights) {
            System.out.println(printArrivingFlight(flight));
        }
    }

    private void printAllDepartingFlights() {
        for (DepartingFlight flight : departingFlights) {
            System.out.println(printDepartingFlight(flight));
        }
    }

    private void printAllEmergencyAlerts() {
        for (Alert alert : emergencyAlerts) {
            printEmergencyAlert(alert);
        }
    }


    private void addEmergencyAlert() {
        System.out.println("Please type the emergency alert you would like to display");
        String alertString = input.next();
        Alert alert = new Alert(alertString);
        emergencyAlerts.add(alert);
        System.out.println("You added the following alert: (" + alert.getAlert() + ")");
    }

    // REQUIRES: alert passed to function must already be contained in the emergencyAlerts list
    // MODIFIES: List<String> emergencyAlerts
    // EFFECTS: removes specified alert
    private void removeEmergencyAlert() {
        System.out.println("Please type the id of the emergency alert you would like to remove");
        int alertId = input.nextInt();
        Alert removeAlert = findAndReturnAlert(alertId);
        emergencyAlerts.remove(removeAlert);
        System.out.println("You removed the following alert: (" + removeAlert.getAlert() + ")");
    }

    private Alert findAndReturnAlert(int alertId) {
        Alert requiredAlert = null;
        for (Alert alert : emergencyAlerts) {
            if (alert.getId() == alertId) {
                requiredAlert = alert;
            }
        }
        return requiredAlert;
    }


    // REQUIRES: a flight not already in the list
    // MODIFIES: this
    // EFFECTS: adds an arriving flight to the list
    private void addArrivingFlight() {
        System.out.println("Please enter airline");
        String airline = input.next().toUpperCase();
        System.out.println("Please enter flight number");
        int flightNumber = input.nextInt();
        System.out.println("Please enter origin");
        String origin = input.next();
        System.out.println("Please input status");
        String status = input.next().toUpperCase();
        System.out.println("Please enter scheduled arrival time");
        String scheduledTime = input.next();
        System.out.println("Please enter estimated arrival time");
        String estimatedTime = input.next();
        ArrivingFlight newFlight = new ArrivingFlight(airline, flightNumber, origin, status, scheduledTime,
                                       estimatedTime);
        arrivingFlights.add(newFlight);
        System.out.println("You added: (" + printArrivingFlight(newFlight) + ")");
    }

    private void updateArrivingFlight() {
        System.out.println("Please enter flight number of flight you would like to update ETA of: ");
        int flightToUpdate = input.nextInt();
        ArrivingFlight flightToBeUpdated = searchAndReturnArrFlight(flightToUpdate);
        System.out.println("Please enter updated ETA: ");
        String updatedETA = input.next();
        flightToBeUpdated.setEstimatedArrivalTime(updatedETA);
        System.out.println("Please enter new status: ");
        String newStatus = input.next().toUpperCase();
        flightToBeUpdated.setStatus(newStatus);
        System.out.println("New updated flight: (" + printArrivingFlight(flightToBeUpdated) + ")");
    }

    private ArrivingFlight searchAndReturnArrFlight(int flightToUpdate) {
        ArrivingFlight requiredFlight = null;
        for (ArrivingFlight flight : arrivingFlights) {
            if (flight.getFlightNumber() == flightToUpdate) {
                requiredFlight = flight;
            }
        }
        return requiredFlight;
    }

    private DepartingFlight searchAndReturnDepFlight(int flightToUpdate) {
        DepartingFlight requiredFlight = null;
        for (DepartingFlight flight : departingFlights) {
            if (flight.getFlightNumber() == flightToUpdate) {
                requiredFlight = flight;
            }
        }
        return requiredFlight;
    }

    private void cancelArrivingFlight() {
        System.out.println("Please input flight number for which you would like to update status to CANCELLED");
        int newCancelFlight = input.nextInt();
        ArrivingFlight flightToCancel = searchAndReturnArrFlight(newCancelFlight);
        flightToCancel.setStatus("CANCELLED");
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes an arriving flight to the list
    private void removeArrivingFlight() {
        System.out.println("Please input flight number for which you would like to remove");
        int flightToRemove = input.nextInt();
        ArrivingFlight flightToBeRemoved = searchAndReturnArrFlight(flightToRemove);
        arrivingFlights.remove(flightToBeRemoved);
        System.out.println("You removed the following flight: (" + printArrivingFlight(flightToBeRemoved) + ")");
    }

    // REQUIRES: a flight not already in the list
    // MODIFIES: this
    // EFFECTS: adds a departing flight to the list
    private void addDepartingFlight() {
        System.out.println("Please enter airline");
        String airline = input.next().toUpperCase();
        System.out.println("Please enter flight number");
        int flightNumber = input.nextInt();
        System.out.println("Please enter destination");
        String destination = input.next();
        System.out.println("Please input status");
        String status = input.next().toUpperCase();
        System.out.println("Please enter scheduled departure time");
        String scheduledTime = input.next();
        System.out.println("Please enter estimated departure time");
        String estimatedTime = input.next();
        DepartingFlight newFlight = new DepartingFlight(airline, flightNumber, destination, status, scheduledTime,
                                                        estimatedTime);
        departingFlights.add(newFlight);
        System.out.println("You added: (" + printDepartingFlight(newFlight) + ")");
    }

    private void updateDepartingFlight() {
        System.out.println("Please enter flight number of flight you would like to update ETD of: ");
        int flightToUpdate = input.nextInt();
        DepartingFlight flightToBeUpdated = searchAndReturnDepFlight(flightToUpdate);
        System.out.println("Please enter updated ETD: ");
        String updatedETD = input.next();
        flightToBeUpdated.setEstimatedDepartureTime(updatedETD);
        System.out.println("Please enter new status: ");
        String newStatus = input.next().toUpperCase();
        flightToBeUpdated.setStatus(newStatus);
        System.out.println("New updated flight: (" + printDepartingFlight(flightToBeUpdated) + ")");
    }

    private void cancelDepartingFlight() {
        System.out.println("Please input flight number for which you would like to update status to CANCELLED");
        int newCancelFlight = input.nextInt();
        DepartingFlight flightToCancel = searchAndReturnDepFlight(newCancelFlight);
        flightToCancel.setStatus("CANCELLED");
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes a departing flight to the list
    private void removeDepartingFlight() {
        System.out.println("Please input flight number for which you would like to remove");
        int flightToRemove = input.nextInt();
        DepartingFlight flightToBeRemoved = searchAndReturnDepFlight(flightToRemove);
        departingFlights.remove(flightToBeRemoved);
        System.out.println("You removed the following flight: (" + printDepartingFlight(flightToBeRemoved) + ")");
    }

    private String printArrivingFlight(ArrivingFlight flight) {
        return flight.getAirline() + ", " + flight.getFlightNumber() + ", " + "Arriving from: "
                + flight.getOrigin() + ", " + flight.getStatus() + ", " + flight.getScheduledArrivalTime() + ", "
                + flight.getEstimatedArrivalTime();
        //return arrFlight;
    }

    private String printDepartingFlight(DepartingFlight flight) {
        return flight.getAirline() + ", " + flight.getFlightNumber() + ", " + "Departing to: "
                + flight.getDestination() + ", " + flight.getStatus() + ", " + flight.getScheduledDepartureTime() + ", "
                + flight.getEstimatedDepartureTime();
        //return depFlight;
    }

    private void printEmergencyAlert(Alert alert) {
        System.out.println(alert.getId() + "). " + alert.getAlert());
    }
}
