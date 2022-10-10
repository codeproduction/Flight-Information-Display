package model;

public interface Flight {
    // getters and setters
    String getAirline();

    void setAirline(String airline);

    int getFlightNumber();

    void setFlightNumber(int flightNumber);

    boolean isCancelled();

    void setCancelled(boolean isCancelled);
}
