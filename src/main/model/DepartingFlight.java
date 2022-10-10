package model;

// Represents a flight having an airline, flight number, origin, scheduled arrival time & estimated arrival time
public class DepartingFlight implements Flight {
    private String airline;
    private int flightNumber;
    private String origin;
    private String status;
    private String scheduledDepartureTime;
    private String estimatedDepartureTime;


    public DepartingFlight(String airline, int flightNumber, String origin, String status,
                           String scheduledDepartureTime, String estimatedDepartureTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.status = status;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.estimatedDepartureTime = estimatedDepartureTime;
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
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

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

