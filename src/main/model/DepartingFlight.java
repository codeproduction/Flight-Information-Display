package model;

import org.json.JSONObject;

// Represents a departing flight having an airline, flight number, origin, scheduled Departing time &
// estimated Departing time
public class DepartingFlight implements Flight, Writable {
    private String airline;
    private int flightNumber;
    private String destination;
    private String status;
    private String scheduledDepartureTime;
    private String estimatedDepartureTime;

    /*
     * REQUIRES: - flightNumber is int of max three digits length and min one digit
     *           - status is one of four possible status (On time, Early, Delayed, Cancelled),
     *           - scheduledDepartureTime and estimatedDepartureTime must be in 24-hour format (xx:xx)
     *           - destination, status, airline must be non-empty string
     * EFFECTS: Creates an instance of a departing flight, with field values set from user input
     */
    public DepartingFlight(String airline, int flightNumber, String destination, String status,
                           String scheduledDepartureTime, String estimatedDepartureTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.status = status;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.estimatedDepartureTime = estimatedDepartureTime;
    }

    /*
     * REQUIRES: a DepartingFlight with any status other than "Cancelled"
     * MODIFIES: this
     * EFFECTS: sets status of flight to "CANCELLED" AND sets estimatedDepartureTime of
     *          the Departure flight to xx:xx
     */
    public void cancelFlight(DepartingFlight flight) {
        flight.setStatus("CANCELLED");
        flight.setEstimatedDepartureTime("xx:xx");
    }

    // getters and setters
    @Override
    public String getAirline() {
        return airline;
    }

    @Override
    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    // MODIFIES: this
    // REQUIRES: one of four statuses (On time, Early, Delayed, Cancelled)
    // todo maybe: if statement could be added. add checks elsewhere
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    // todo maybe: add if clause(s) - might need to use a regex expression
    // MODIFIES: this
    // REQUIRES: if status => Cancelled, then time must be input as "xx:xx"
    //           AND time must be String of format "HH:mm"
    // EFFECTS: Sets ETD to given time if status not already "Cancelled" otherwise sets it
    //          to "xx:xx"
    public void setEstimatedDepartureTime(String estimatedDepartureTime) {
        if (this.getStatus().equals("CANCELLED")) {
            this.estimatedDepartureTime = "xx:xx";
        } else {
            this.estimatedDepartureTime = estimatedDepartureTime;
        }
    }

    public String getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Airline", airline);
        json.put("Flight Number", flightNumber);
        json.put("Destination", destination);
        json.put("Status", status);
        json.put("Scheduled Departure Time", scheduledDepartureTime);
        json.put("Estimated Departure Time", estimatedDepartureTime);
        return json;
    }
}

