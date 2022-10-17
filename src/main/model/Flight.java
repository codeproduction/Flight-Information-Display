package model;

// Interface providing methods common to both types of flights - arriving and departing
public interface Flight {

    // getters and setters
    String getAirline();

    int getFlightNumber();

    String getStatus();

    void setStatus(String status);

}
