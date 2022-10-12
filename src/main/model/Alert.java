package model;

// Alert is (int index, String alert)

public class Alert {
    private static int nextAlertId = 1;
    private int id;
    private String alert;

    public Alert(String alert) {
        id = nextAlertId++;
        this.alert = alert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}
