package ui;

import model.Alert;
import model.ArrivingFlight;
import model.DepartingFlight;
import model.FlightDisplay;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Console-driven Flight Information Display application which represents a Flight Information Display
public class FlightDisplayApp {
    /* todo later...: summary statistics (if complex features incorporated) */
    // JSON fields (added after starting phase 2)
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private FlightDisplay flightDisplay;
    private Scanner input;

    // EFFECTS: calls runFlightDisplay which initiates the application
    public FlightDisplayApp() {
        flightDisplay = new FlightDisplay();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFlightDisplay();
    }

    /**
     * NOTE TO GRADERS: The runFlightDisplay() method's while loop and initializeSetup,
     *                  printFlightDisplay and executeUserCommand method
     *                  [generally runFlightMethod()] were inspired by similar methods
     *                  found in the Teller application provided as example on the edX
     *                  course page.
     *                  Also, the separation of FlightDisplay and FlightDisplayApp was taken from Json Demo provided to
     *                  students of CPSC 210.
     */
    // MODIFIES: this
    // EFFECTS: Runs the application which will set up the FlightDisplay utilizing input from user
    //          Loop runs until user chooses to quit
    //          Initializes setup for application to run and displays menu
    //          Prints FlightDisplay after each option in menu
    private void runFlightDisplay() {
        boolean isDisplayNeeded = true;
        String option;
        initializeSetup();

        while (isDisplayNeeded) {
            displayOptionsMenu();
            option = input.next();
            option = option.toLowerCase();

            if (option.equals("n")) {
                isDisplayNeeded = false;
            } else {
                executeUserCommand(option);
                System.out.println("\nLatest flight information and status:\n");
                printFlightDisplay();
            }
        }
    }

    // MODIFIES: this and Fields of all Flight Lists and Alert lists
    // EFFECTS: Initializes lists of Flights and emergency Alerts
    private void initializeSetup() {
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
        System.out.println("\tl => save flight display to file");
        System.out.println("\tm => load flight display from file");
        System.out.println("\tn => end program");
    }

    // REQUIRES: Letter from a to k - can be both lower and upper casea
    // MODIFIES: this
    // EFFECTS: Processes user command and executes relevant process/method

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void executeUserCommand(String action) {
        switch (action) {
            case "a":
                addArrivingFlight();
                break;
            case "b":
                updateArrivingFlight();
                break;
            case "c":
                cancelArrivingFlight();
                break;
            case "d":
                removeArrivingFlight();
                break;
            case "e":
                addDepartingFlight();
                break;
            case "f":
                updateDepartingFlight();
                break;
            case "g":
                cancelDepartingFlight();
                break;
            case "h":
                removeDepartingFlight();
                break;
            case "i":
                addEmergencyAlert();
                break;
            case "j":
                removeEmergencyAlert();
                break;
            case "l":
                saveFlightDisplay();
                break;
            case "m":
                loadFlightDisplay();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Prints FlightDisplay
    private void printFlightDisplay() {
        System.out.println("ARRIVALS: (Airline, Flight Number, Origin, Status, Scheduled Arrival Time, "
                + "Estimated Arrival Time)");
        printAllArrivingFlights();
        System.out.println("DEPARTURES: (Airline, Flight Number, Destination, Status, Scheduled Departure Time, "
                + "Estimated Departure Time)");
        printAllDepartingFlights();
        System.out.println("EMERGENCY ALERTS: ");
        printAllEmergencyAlerts();
    }

    // MODIFIES: this
    // EFFECTS: Prints all Arriving Flights
    private void printAllArrivingFlights() {
        List<ArrivingFlight> arrivingFlights = flightDisplay.getArrivingFlights();
        for (ArrivingFlight flight : arrivingFlights) {
            System.out.println(printArrivingFlight(flight));
        }
    }

    // MODIFIES: this
    // EFFECTS: Prints all Departing Flights
    private void printAllDepartingFlights() {
        List<DepartingFlight> departingFlights = flightDisplay.getDepartingFlights();
        for (DepartingFlight flight : departingFlights) {
            System.out.println(printDepartingFlight(flight));
        }
    }

    // MODIFIES: this
    // EFFECTS: Prints all Alerts
    private void printAllEmergencyAlerts() {
        List<Alert> emergencyAlerts = flightDisplay.getEmergencyAlerts();
        for (Alert alert : emergencyAlerts) {
            printEmergencyAlert(alert);
        }
    }


    // MODIFIES: this
    // EFFECTS: Adds Alerts to List of Alerts
    private void addEmergencyAlert() {
        System.out.println("Please type the emergency alert you would like to display");
        String alertString = input.next();
        Alert alert = new Alert(alertString);
        flightDisplay.addEmergencyAlert(alert);
        System.out.println("You added the following alert: (" + alert.getAlert() + ")");
    }

    // REQUIRES: alert and its ID must already be contained in the emergencyAlerts list
    // MODIFIES: this and List<String> emergencyAlerts
    // EFFECTS: Removes specified alert from List<Alert> emergencyAlerts
    private void removeEmergencyAlert() {
        System.out.println("Please type the id of the emergency alert you would like to remove");
        int alertId = input.nextInt();
        Alert removeAlert = findAndReturnAlert(alertId);
        flightDisplay.removeEmergencyAlert(removeAlert);
        System.out.println("You removed the following alert: (" + removeAlert.getAlert() + ")");
    }

    // REQUIRES: alert ID must already be contained in the emergencyAlerts List
    // MODIFIES: this
    // EFFECTS: returns Alert with given alertId from List emergencyAlerts
    private Alert findAndReturnAlert(int alertId) {
        Alert requiredAlert = null;
        List<Alert> emergencyAlerts = flightDisplay.getEmergencyAlerts();
        for (Alert alert : emergencyAlerts) {
            if (alert.getId() == alertId) {
                requiredAlert = alert;
            }
        }
        return requiredAlert;
    }

    // TODO maybe: could add check for this
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
        flightDisplay.addArrivingFlight(newFlight);
        System.out.println("You added: (" + printArrivingFlight(newFlight) + ")");
    }

    // REQUIRES: Flight input must already be in List ArrivingFlights
    // MODIFIES: this
    // EFFECTS: updates status and ETA of input flight according to user input
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

    // REQUIRES: Given flightNumber must already be contained in the ArrivingFlights List
    // MODIFIES: this
    // EFFECTS: returns ArrivingFlight with given flightNumber from List arrivingFlights
    private ArrivingFlight searchAndReturnArrFlight(int noOfFlightToUpdate) {
        ArrivingFlight requiredFlight = null;
        List<ArrivingFlight> arrivingFlights = flightDisplay.getArrivingFlights();
        for (ArrivingFlight flight : arrivingFlights) {
            if (flight.getFlightNumber() == noOfFlightToUpdate) {
                requiredFlight = flight;
            }
        }
        return requiredFlight;
    }

    // REQUIRES: Given flightNumber must already be contained in the DepartingFlights List
    // MODIFIES: this
    // EFFECTS: returns DepartingFlight with given flightNumber from List departingFlights
    private DepartingFlight searchAndReturnDepFlight(int flightToUpdate) {
        DepartingFlight requiredFlight = null;
        List<DepartingFlight> departingFlights = flightDisplay.getDepartingFlights();
        for (DepartingFlight flight : departingFlights) {
            if (flight.getFlightNumber() == flightToUpdate) {
                requiredFlight = flight;
            }
        }
        return requiredFlight;
    }

    // REQUIRES: Flight passed to function must be in List arrivingFlights
    // MODIFIES: this
    // EFFECTS: updates time and also status of given flight to "cancelled"
    private void cancelArrivingFlight() {
        System.out.println("Please input flight number for which you would like to update status to CANCELLED");
        int newCancelFlight = input.nextInt();
        ArrivingFlight flightToCancel = searchAndReturnArrFlight(newCancelFlight);
        flightToCancel.cancelFlight(flightToCancel);
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes an arriving flight from the List arrivingFlights
    private void removeArrivingFlight() {
        System.out.println("Please input flight number for which you would like to remove");
        int flightToRemove = input.nextInt();
        ArrivingFlight flightToBeRemoved = searchAndReturnArrFlight(flightToRemove);
        flightDisplay.removeArrivingFlight(flightToBeRemoved);
        System.out.println("You removed the following flight: (" + printArrivingFlight(flightToBeRemoved) + ")");
    }

    // REQUIRES: a flight not already in the list
    // MODIFIES: this
    // EFFECTS: adds a departing flight to the departingFlights
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
        flightDisplay.addDepartingFlight(newFlight);
        System.out.println("You added: (" + printDepartingFlight(newFlight) + ")");
    }

    // REQUIRES: Flight input must already be in List departingFlights
    // MODIFIES: this
    // EFFECTS: updates status and ETD of input flight according to user input
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

    // REQUIRES: Flight passed to function must be in List departingFlights
    // MODIFIES: this
    // EFFECTS: updates time and also status of given flight to "cancelled"
    private void cancelDepartingFlight() {
        System.out.println("Please input flight number for which you would like to update status to CANCELLED");
        int newCancelFlight = input.nextInt();
        DepartingFlight flightToCancel = searchAndReturnDepFlight(newCancelFlight);
        flightToCancel.cancelFlight(flightToCancel);
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes a departing flight from List departingFlight
    private void removeDepartingFlight() {
        System.out.println("Please input flight number for which you would like to remove");
        int flightToRemove = input.nextInt();
        DepartingFlight flightToBeRemoved = searchAndReturnDepFlight(flightToRemove);
        flightDisplay.removeDepartingFlight(flightToBeRemoved);
        System.out.println("You removed the following flight: (" + printDepartingFlight(flightToBeRemoved) + ")");
    }

    // REQUIRES: A ArrivingFlight that is already in the List arrivingList
    // MODIFIES: this
    // EFFECTS: Prints the information in fields of ArrivingFlight input to flight
    private String printArrivingFlight(ArrivingFlight flight) {
        return flight.getAirline() + ", " + flight.getFlightNumber() + ", " + "Arriving from: "
                + flight.getOrigin() + ", " + flight.getStatus() + ", " + flight.getScheduledArrivalTime() + ", "
                + flight.getEstimatedArrivalTime();
    }


    // REQUIRES: A DepartingFlight that is already in the List departingList
    // MODIFIES: this
    // EFFECTS: Prints the information in fields of DepartingFlight input to flight
    private String printDepartingFlight(DepartingFlight flight) {
        return flight.getAirline() + ", " + flight.getFlightNumber() + ", " + "Departing to: "
                + flight.getDestination() + ", " + flight.getStatus() + ", " + flight.getScheduledDepartureTime() + ", "
                + flight.getEstimatedDepartureTime();
    }

    // REQUIRES: Alert must already be in the List emergencyAlerts
    // MODIFIES: this
    // EFFECTS: Prints the emergencyAlert with information in both of its instance variables
    private void printEmergencyAlert(Alert alert) {
        System.out.println(alert.getId() + "). " + alert.getAlert());
    }

    // JSON methods:

    // EFFECTS: saves the flight display to file
    private void saveFlightDisplay() {
        try {
            jsonWriter.open();
            jsonWriter.write(flightDisplay);
            jsonWriter.close();
            System.out.println("Saved " + /* flight display.getName() +*/ " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFlightDisplay() {
        try {
            flightDisplay = jsonReader.read();
            System.out.println("Loaded Flight Information Display from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
