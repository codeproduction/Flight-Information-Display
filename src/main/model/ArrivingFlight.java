package model;

// Represents a flight having an airline, flight number, origin, scheduled arrival time & estimated arrival time
public class ArrivingFlight implements Flight {
    private String airline;
    private int flightNumber;
    private String origin;
    private String status;
    private String scheduledArrivalTime;
    private String estimatedArrivalTime;

    // REQUIRES: flightNumber is int of max three digits length, min one digit,
    //           status is one of four possible status (On time, Early, Delayed, Cancelled),
    //           scheduledArrivalTime and estimatedArrivalTime must be in 24-hour format (xx:xx)
    //           origin, status, airline must be non-empty string
    public ArrivingFlight(String airline, int flightNumber, String origin, String status,
                          String scheduledArrivalTime, String estimatedArrivalTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.status = status;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public void cancelFlight(ArrivingFlight flight) {
        flight.setStatus("CANCELLED");
        flight.setEstimatedArrivalTime("XX:XX");
    }

    // getters and setters
    @Override
    public String getAirline() {
        return airline;
    }

    @Override
    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public int getFlightNumber() {
        return flightNumber;
    }

    @Override
    // REQUIRES: flight number of max three digits length && min one digit
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    // REQUIRES: one of four statuses (On time, Early, Delayed, Cancelled)
    public void setStatus(String status) {
        this.status = status;
    }

    public String getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(String scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    // REQUIRES: if status => Cancelled, then time must be input as "xx:xx"
    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }
}
