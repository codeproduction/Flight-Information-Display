package model;

// Alert is (int index, String alert) and a data type for representing alerts with associated methods
public class Alert {
    private static int nextAlertId = 1;
    private int id;
    private String alert;

    // REQUIRES: Non-empty String
    // EFFECTS: creates an instance of an Alert object
    public Alert(String alert) {
        id = nextAlertId++;
        this.alert = alert;
    }

    // MODIFIES: this
    // EFFECTS: Resets class variable/static field nextAlertId to 1 which
    //          is the ID of the first alert on the display
    //          Used in AlertTest Class, not model or ui package
    public static void resetId() {
        nextAlertId = 1;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public String getAlert() {
        return alert;
    }

}
