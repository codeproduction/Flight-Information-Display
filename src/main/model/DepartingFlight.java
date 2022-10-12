package model;

// Represents a flight having an airline, flight number, origin, scheduled arrival time & estimated arrival time
public class DepartingFlight implements Flight {
    private String airline;
    private int flightNumber;
    private String destination;
    private String status;
    private String scheduledDepartureTime;
    private String estimatedDepartureTime;


    // REQUIRES: flightNumber is int of max three digits length, min one digit,
    //           status is one of four possible status (On time, Early, Delayed, Cancelled),
    //           estimatedDepartingTime and scheduledDepartingTime must be in 24-hour format (xx:xx)
    //           origin, status, airline must be non-empty string
    public DepartingFlight(String airline, int flightNumber, String destination, String status,
                           String scheduledDepartureTime, String estimatedDepartureTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.status = status;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.estimatedDepartureTime = estimatedDepartureTime;
    }

    public void cancelFlight(DepartingFlight flight) {
        flight.setStatus("CANCELLED");
        flight.setEstimatedDepartureTime("XX:XX");
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
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setOrigin(String destination) {
        this.destination = destination;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    // REQUIRES: if status => Cancelled, then time must be input as "xx:xx"
    public void setEstimatedDepartureTime(String estimatedDepartureTime) {
        this.estimatedDepartureTime = estimatedDepartureTime;
    }

    public String getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(String scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }
}

