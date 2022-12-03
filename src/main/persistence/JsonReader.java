package persistence;

import model.Alert;
import model.ArrivingFlight;
import model.DepartingFlight;
import model.FlightDisplay;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
Inspiration for code here taken from Json Demo provided to CPSC 210 students on edX Edge course
 */

// Represents a reader that reads a flight display from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlightDisplay read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlightDisplay(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private FlightDisplay parseFlightDisplay(JSONObject jsonObject) {
        FlightDisplay fd = new FlightDisplay();
        addThingies(fd, jsonObject);
        return fd;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(FlightDisplay fd, JSONObject jsonObject) {
        JSONArray arrFlightsJsonArray = jsonObject.getJSONArray("Arriving Flights");
        for (Object json : arrFlightsJsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addArrFlight(fd, nextThingy);
        }
        JSONArray depFlightsJsonArray = jsonObject.getJSONArray("Departing Flights");
        for (Object json : depFlightsJsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addDepFlight(fd, nextThingy);
        }
        JSONArray alertsJsonArray = jsonObject.getJSONArray("Emergency Alerts");
        for (Object json : alertsJsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addAlert(fd, nextThingy);
        }
    }

    // MODIFIES: fd
    // EFFECTS: parses arrFlight from JSON object and adds it to workroom
    private void addArrFlight(FlightDisplay fd, JSONObject jsonObject) {
        String airline = jsonObject.getString("Airline");
        int flightNumber = jsonObject.getInt("Flight Number");
        String origin = jsonObject.getString("Origin");
        String status = jsonObject.getString("Status");
        String scheduledArrivalTime = jsonObject.getString("Scheduled Arrival Time");
        String estimatedArrivalTime = jsonObject.getString("Estimated Arrival Time");
        ArrivingFlight flight = new ArrivingFlight(airline,
                flightNumber, origin, status, scheduledArrivalTime, estimatedArrivalTime);
        fd.addArrivingFlight(flight);
    }

    private void addDepFlight(FlightDisplay fd, JSONObject jsonObject) {
        String airline = jsonObject.getString("Airline");
        int flightNumber = jsonObject.getInt("Flight Number");
        String origin = jsonObject.getString("Destination");
        String status = jsonObject.getString("Status");
        String scheduledDepartureTime = jsonObject.getString("Scheduled Departure Time");
        String estimatedDepartureTime = jsonObject.getString("Estimated Departure Time");
        DepartingFlight flight = new DepartingFlight(airline,
                flightNumber, origin, status, scheduledDepartureTime, estimatedDepartureTime);
        fd.addDepartingFlight(flight);
    }

    private void addAlert(FlightDisplay fd, JSONObject jsonObject) {
        String alert = jsonObject.getString("Alert");
        int id = jsonObject.getInt("ID");
        Alert emergencyAlert = new Alert(id, alert);
        fd.addEmergencyAlert(emergencyAlert);
    }
}
