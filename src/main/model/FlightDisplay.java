package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Inspiration for code here taken from Json Demo provided to CPSC 210 students on edX Edge course
 */

// Represents a flight information display
public class FlightDisplay implements Writable {
    protected List<ArrivingFlight> arrivingFlights;
    protected List<DepartingFlight> departingFlights;
    protected List<Alert> emergencyAlerts;

    public FlightDisplay() {
        this.arrivingFlights = new ArrayList<>();
        this.departingFlights = new ArrayList<>();
        this.emergencyAlerts = new ArrayList<>();
    }

    public List<ArrivingFlight> getArrivingFlights() {
        return arrivingFlights;
    }

    public List<DepartingFlight> getDepartingFlights() {
        return departingFlights;
    }

    public List<Alert> getEmergencyAlerts() {
        return emergencyAlerts;
    }

    public void addEmergencyAlert(Alert alert) {
        emergencyAlerts.add(alert);
        EventLog.getInstance().logEvent(new Event("Emergency Alert added."));
    }

    // REQUIRES: a flight not already in the list
    // MODIFIES: this
    // EFFECTS: adds an arriving flight to the list
    public void addArrivingFlight(ArrivingFlight flight) {
        arrivingFlights.add(flight);
        EventLog.getInstance().logEvent(new Event("Arriving Flight added."));
    }

    // REQUIRES: a flight not already in the list
    // MODIFIES: this
    // EFFECTS: adds a departing flight to the departingFlights
    public void addDepartingFlight(DepartingFlight flight) {
        departingFlights.add(flight);
        EventLog.getInstance().logEvent(new Event("Departing Flight added."));
    }

    // REQUIRES: alert and its ID must already be contained in the emergencyAlerts list
    // MODIFIES: this and List<String> emergencyAlerts
    // EFFECTS: Removes specified alert from List<Alert> emergencyAlerts
    public void removeEmergencyAlert(Alert alert) {
        emergencyAlerts.remove(alert);
        EventLog.getInstance().logEvent(new Event("Emergency Alert removed."));
    }

    // REQUIRES: ID and alert associated with it must already be contained in the emergencyAlerts list
    // MODIFIES: this and List<String> emergencyAlerts
    // EFFECTS: Removes specified alert from List<Alert> emergencyAlerts
    public void removeEmergencyAlert(int alertID) {
        emergencyAlerts.removeIf(emgAlert -> alertID == emgAlert.getId());
        EventLog.getInstance().logEvent(new Event("Emergency Alert removed."));
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes an arriving flight from the List arrivingFlights
    public void removeArrivingFlight(ArrivingFlight flight) {
        arrivingFlights.remove(flight);
        EventLog.getInstance().logEvent(new Event("Arriving Flight removed."));
    }

    // REQUIRES: a flight already in the list
    // MODIFIES: this
    // EFFECTS: removes a departing flight from List departingFlight
    public void removeDepartingFlight(DepartingFlight flight) {
        departingFlights.remove(flight);
        EventLog.getInstance().logEvent(new Event("Departing Flight removed"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Arriving Flights", arrivingFlightsToJson());
        json.put("Departing Flights", departingFlightsToJson());
        json.put("Emergency Alerts", emergencyAlertsToJson());
        return json;
    }

    // EFFECTS: returns arrivingFlights in this workroom as a JSON array
    private JSONArray arrivingFlightsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (ArrivingFlight flight : arrivingFlights) {
            jsonArray.put(flight.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns departingFlights in this workroom as a JSON array
    private JSONArray departingFlightsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (DepartingFlight flight : departingFlights) {
            jsonArray.put(flight.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns emergencyAlerts in this workroom as a JSON array
    private JSONArray emergencyAlertsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Alert alert : emergencyAlerts) {
            jsonArray.put(alert.toJson());
        }
        return jsonArray;
    }

}
