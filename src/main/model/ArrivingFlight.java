package model;

// Represents an arriving flight having an airline, flight number, origin, scheduled arrival time &
// estimated arrival time
public class ArrivingFlight implements Flight {
    private String airline;
    private int flightNumber;
    private String origin;
    private String status;
    private String scheduledArrivalTime;
    private String estimatedArrivalTime;

    /*
     * REQUIRES: - flightNumber is int of max three digits length and min one digit
     *           - status is one of four possible status (On time, Early, Delayed, Cancelled),
     *           - scheduledArrivalTime and estimatedArrivalTime must be in 24-hour format (xx:xx)
     *           - origin, status, airline must be non-empty string
     * EFFECTS: Creates an instance of an arriving flight, with field values set from user input
     */
    public ArrivingFlight(String airline, int flightNumber, String origin, String status,
                          String scheduledArrivalTime, String estimatedArrivalTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.status = status;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    /*
     * REQUIRES: an ArrivingFlight with any status other than "Cancelled"
     * MODIFIES: this
     * EFFECTS: sets status of flight to "CANCELLED" AND sets estimatedArrivalTime of
     *          the arriving flight to xx:xx
     */
    public void cancelFlight(ArrivingFlight flight) {
        flight.setStatus("CANCELLED");
        flight.setEstimatedArrivalTime("xx:xx");
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

    public String getOrigin() {
        return origin;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    // MODIFIES: this
    // REQUIRES: one of four statuses (On time, Early, Delayed, Cancelled)
    // todo maybe: if statemt could be added. add checks elsewhere
    public void setStatus(String status) {
        this.status = status;
    }

    public String getScheduledArrivalTime() {
        return this.scheduledArrivalTime;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    // todo maybe: add if clause(s) - might need to use a regex expression
    // MODIFIES: this
    // REQUIRES: if status => Cancelled, then time must be input as "xx:xx"
    //           AND time must be String of format "HH:mm"
    // EFFECTS: Sets ETA to given time if status not already "Cancelled" otherwise sets it
    //          to "xx:xx"
    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        if (this.getStatus().equals("CANCELLED")) {
            this.estimatedArrivalTime = "xx:xx";
        } else {
            this.estimatedArrivalTime = estimatedArrivalTime;
        }
    }
}
