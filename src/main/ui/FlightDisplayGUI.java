package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class FlightDisplayGUI extends JPanel implements ActionListener {

    JButton arrAddButton;
    JButton arrUpdateButton;
    JButton arrCancelButton;
    JButton arrRemoveButton;
    JButton depAddButton;
    JButton depUpdateButton;
    JButton depCancelButton;
    JButton depRemoveButton;
    JButton alertAddButton;
    JButton alertRemoveButton;
    JTable table;
    DefaultTableModel modelTable;
    JTable table1;
    DefaultTableModel modelTable1;
    JTable table2;
    DefaultTableModel modelTable2;
    FlightDisplay fd;
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    GridBagConstraints gbc = new GridBagConstraints();
    //static JFrame frame;

    public static void main(String[] args) {
        FlightDisplayGUI m = new FlightDisplayGUI();

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(904, 642));
        frame.setTitle("Manual Attempt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(m);
        frame.pack();
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public FlightDisplayGUI() {
        fd = new FlightDisplay();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setLayout(new GridBagLayout());

        String[] columnNames = {"Airline",
                "Flight Number",
                "Origin",
                "Status",
                "Scheduled Arrival Time",
                "Estimated Arrival Time"};
        String[] alertColumnNames = {"ID", "Alert"};

        Object[][] data = flightTableCreator();
        Object[][] alertData = {};

        JPanel arrFlightsPanel = new JPanel();
        table = new JTable(new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setPreferredScrollableViewportSize(new Dimension(770, 150));
        table.setFillsViewportHeight(true);
        modelTable = (DefaultTableModel) table.getModel();

        arrFlightsPanel.add(new JScrollPane(table, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED));

        JPanel depFlightsPanel = new JPanel();
        table1 = new JTable(new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });
        table1.setPreferredScrollableViewportSize(new Dimension(770, 150));
        table1.setFillsViewportHeight(true);
        table1.setFocusable(false);
        table1.setRowSelectionAllowed(false);
        modelTable1 = (DefaultTableModel) table1.getModel();

        depFlightsPanel.add(new JScrollPane(table1, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED));

        JPanel emgAlertsPanel = new JPanel();
        table2 = new JTable(new DefaultTableModel(data, alertColumnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });
        table2.setPreferredScrollableViewportSize(new Dimension(770, 150));
        table2.setFillsViewportHeight(true);
        table2.setFocusable(false);
        table2.setRowSelectionAllowed(false);
        modelTable2 = (DefaultTableModel) table2.getModel();
        emgAlertsPanel.add(new JScrollPane(table2, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED));

        JLabel arrFlightsLabel = new JLabel("    Arriving Flights");
        JLabel depFlightsLabel = new JLabel("    Departing Flights");
        JLabel emgAlertsLabel = new JLabel("    Emergency Alerts");
        // setting coordinates of all panels containing tables, buttons, and labels
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(arrFlightsLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(arrFlightsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(depFlightsLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(depFlightsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(emgAlertsLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(emgAlertsPanel, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(3, 3, 3, 3);

        JPanel arrFlightButtons = new JPanel(new GridBagLayout());
        arrAddButton = new JButton("Add Arriving Flight");
        arrAddButton.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipady = 40;  //make this component tall
        gbc.gridx = 2;
        gbc.gridy = 1;
        arrFlightButtons.add(arrAddButton, gbc);
        arrUpdateButton = new JButton("Update Arriving Flight");
        arrUpdateButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 1;
        arrFlightButtons.add(arrUpdateButton, gbc);
        arrCancelButton = new JButton("Cancel Arriving Flight");
        arrCancelButton.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 2;
        arrFlightButtons.add(arrCancelButton, gbc);
        arrRemoveButton = new JButton("Remove Arriving Flight");
        arrRemoveButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 2;
        arrFlightButtons.add(arrRemoveButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(arrFlightButtons, gbc);

        JPanel depFlightButtons = new JPanel(new GridBagLayout());
        depAddButton = new JButton("Add Departing Flight");
        depAddButton.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        depFlightButtons.add(depAddButton, gbc);
        depUpdateButton = new JButton("Update Departing Flight");
        depUpdateButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 1;
        depFlightButtons.add(depUpdateButton, gbc);
        depCancelButton = new JButton("Cancel Departing Flight");
        depCancelButton.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 2;
        depFlightButtons.add(depCancelButton, gbc);
        depRemoveButton = new JButton("Remove Departing Flight");
        depRemoveButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 2;
        depFlightButtons.add(depRemoveButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(depFlightButtons, gbc);

        JPanel emergencyAlertButtons = new JPanel(new GridBagLayout());
        alertAddButton = new JButton("Add Alert");
        alertAddButton.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        emergencyAlertButtons.add(alertAddButton, gbc);
        alertRemoveButton = new JButton("Remove Alert");
        alertRemoveButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 0;
        emergencyAlertButtons.add(alertRemoveButton, gbc);
        JButton saveButton = new JButton("Save to File");
        saveButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 6;
        //gbc.anchor = GridBagConstraints.PAGE_END;
        emergencyAlertButtons.add(saveButton, gbc);
        JButton loadButton = new JButton("Load from File");
        loadButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 6;
        emergencyAlertButtons.add(loadButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(emergencyAlertButtons, gbc);
//        gbc.fill = GridBagConstraints.NONE;
//        JPanel persistencePanel = new JPanel();
//
//        JButton saveButton = new JButton("Save to File");
//        gbc.gridx = 0;
//        gbc.gridy = 6;
//        gbc.anchor = GridBagConstraints.PAGE_END;
//        persistencePanel.add(saveButton, gbc);
//
//        JButton loadButton = new JButton("Load from File");
//        gbc.gridx = 1;
//        gbc.gridy = 6;
//        persistencePanel.add(loadButton, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 6;
//        add(persistencePanel, gbc);
    }

    public Object[][] flightTableCreator() {

        ArrivingFlight flight = new ArrivingFlight("adk", 333, "data", "sjdf",
                "dkfja", "dksfj");

        List<ArrivingFlight> flightList = fd.getArrivingFlights();

        Object[][] toBeReturned = new Object[flightList.size()][];

        for (int i = 0; i <= flightList.size() - 1; i++) {
            toBeReturned[i] = new Object[]{flightList.get(i).getAirline(), flightList.get(i).getFlightNumber(),
                    flightList.get(i).getOrigin(), flightList.get(i).getStatus(),
                    flightList.get(i).getEstimatedArrivalTime(), flightList.get(i).getScheduledArrivalTime()};
        }
        return toBeReturned;
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Arriving Flight")) {

            String airline = JOptionPane.showInputDialog("Please Input Airline");
            String flightNumber = JOptionPane.showInputDialog("Please Input flightNumber");
            String origin = JOptionPane.showInputDialog("Please Input origin");
            String status = JOptionPane.showInputDialog("Please Input status");
            String scheduledArrivalTime = JOptionPane.showInputDialog("Please Input scheduledArrivalTime");
            String estimatedArrivalTime = JOptionPane.showInputDialog("Please Input estimatedArrivalTime");

            ArrivingFlight arrFlightToAdd = null;
            if (!airline.equals("") && !flightNumber.equals("") && !origin.equals("") && !status.equals("")
                    && !scheduledArrivalTime.equals("") && !estimatedArrivalTime.equals("")) {
                arrFlightToAdd = new ArrivingFlight(airline, Integer.parseInt(flightNumber), origin, status,
                        scheduledArrivalTime, estimatedArrivalTime);
                fd.addArrivingFlight(arrFlightToAdd);
                modelTable.addRow(new Object[]{arrFlightToAdd.getAirline(), arrFlightToAdd.getFlightNumber(),
                        arrFlightToAdd.getStatus(), arrFlightToAdd.getOrigin(),
                        arrFlightToAdd.getEstimatedArrivalTime(), arrFlightToAdd.getScheduledArrivalTime()});
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Add Arriving Flight' "
                        + "button again and enter flight data in all fields");
            }

        } else if (e.getActionCommand().equals("Add Departing Flight")) {

            String airline = JOptionPane.showInputDialog("Please Input Airline");
            String flightNumber = JOptionPane.showInputDialog("Please Input flightNumber");
            String destination = JOptionPane.showInputDialog("Please Input destination");
            String status = JOptionPane.showInputDialog("Please Input status");
            String scheduledArrivalTime = JOptionPane.showInputDialog("Please Input scheduledDepartureTime");
            String estimatedArrivalTime = JOptionPane.showInputDialog("Please Input estimatedDepartureTime");

            DepartingFlight depFlightToAdd = null;
            if (!airline.equals("") && !flightNumber.equals("") && !destination.equals("") && !status.equals("")
                    && !scheduledArrivalTime.equals("") && !estimatedArrivalTime.equals("")) {
                depFlightToAdd = new DepartingFlight(airline, Integer.parseInt(flightNumber), destination, status,
                        scheduledArrivalTime, estimatedArrivalTime);
                fd.addDepartingFlight(depFlightToAdd);
                modelTable1.addRow(new Object[]{depFlightToAdd.getAirline(), depFlightToAdd.getFlightNumber(),
                        depFlightToAdd.getStatus(), depFlightToAdd.getDestination(),
                        depFlightToAdd.getEstimatedDepartureTime(), depFlightToAdd.getScheduledDepartureTime()});
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Add Departing Flight' "
                        + "button again and enter flight data in all fields");
            }

        } else if (e.getActionCommand().equals("Update Arriving Flight")) {

            String rowOfFlightToUpdate = JOptionPane.showInputDialog("Please Input row number of the flight"
                    + " you wish to update");
            String statusToUpdate = JOptionPane.showInputDialog("Please Input new status");
            String estimatedArrivalTimeToUpdate = JOptionPane.showInputDialog("Please Input new estimatedArrivalTime");

            if (!rowOfFlightToUpdate.equals("") && !statusToUpdate.equals("")
                    && !estimatedArrivalTimeToUpdate.equals("")) {
                modelTable.setValueAt(statusToUpdate, Integer.parseInt(rowOfFlightToUpdate) - 1, 3);
                modelTable.setValueAt(estimatedArrivalTimeToUpdate, Integer.parseInt(rowOfFlightToUpdate) - 1, 5);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Update Arriving Flight' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Update Departing Flight")) {

            String rowOfFlightToUpdate = JOptionPane.showInputDialog("Please Input row number of the flight"
                    + " you wish to update");
            String statusToUpdate = JOptionPane.showInputDialog("Please Input new status");
            String estimatedDepartureTimeToUpdate = JOptionPane.showInputDialog("Please Input new"
                    + "estimatedDepartureTime");

            if (!rowOfFlightToUpdate.equals("") && !statusToUpdate.equals("")
                    && !estimatedDepartureTimeToUpdate.equals("")) {
                modelTable1.setValueAt(statusToUpdate, Integer.parseInt(rowOfFlightToUpdate) - 1, 3);
                modelTable1.setValueAt(estimatedDepartureTimeToUpdate, Integer.parseInt(rowOfFlightToUpdate) - 1, 5);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Update Departing Flight' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Cancel Arriving Flight")) {
            String flightNumberToCancel = JOptionPane.showInputDialog("Please Input row of flight to cancel");

            if (!flightNumberToCancel.equals("")) {
                modelTable.setValueAt("CANCELLED", Integer.parseInt(flightNumberToCancel) - 1, 3);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Cancel Arriving Flight' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Remove Arriving Flight")) {
            String flightNumberToRemove = JOptionPane.showInputDialog("Please Input row number of flight to remove");

            if (!flightNumberToRemove.equals("")) {
                modelTable.removeRow(Integer.parseInt(flightNumberToRemove) - 1);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Remove Arriving Flight' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Cancel Departing Flight")) {
            String flightNumberToCancel = JOptionPane.showInputDialog("Please Input row of flight to cancel");

            if (!flightNumberToCancel.equals("")) {
                modelTable1.setValueAt("CANCELLED", Integer.parseInt(flightNumberToCancel) - 1, 3);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Cancel Arriving Flight' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Remove Departing Flight")) {
            String flightNumberToRemove = JOptionPane.showInputDialog("Please Input row number of flight to remove");

            if (!flightNumberToRemove.equals("")) {
                modelTable1.removeRow(Integer.parseInt(flightNumberToRemove) - 1);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Remove Arriving Flight' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Add Alert")) {

            String alertIDToAdd = JOptionPane.showInputDialog("Please Input alert ID to add \n "
                    + "It should be a positive integer");
            String alertToAdd = JOptionPane.showInputDialog("Please Input alert to add");

            if (!alertToAdd.equals("")) {
                Alert emgAlertToAdd = new Alert(Integer.parseInt(alertIDToAdd), alertToAdd);
                modelTable2.addRow(new Object[]{alertIDToAdd, alertToAdd});
                fd.addEmergencyAlert(emgAlertToAdd);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Add Alert' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Remove Alert")) {

            String rowOfAlertToRemove = JOptionPane.showInputDialog("Please Input row number of alert to remove");

            DepartingFlight depFlightToAdd;
            if (!rowOfAlertToRemove.equals("")) {
                int idOfAlertToRemove = Integer.parseInt(modelTable2.getValueAt(
                        Integer.parseInt(rowOfAlertToRemove) - 1, 0).toString());
                fd.removeEmergencyAlert(idOfAlertToRemove);
                modelTable2.removeRow(Integer.parseInt(rowOfAlertToRemove) - 1);
            } else {
                JOptionPane.showMessageDialog(this, "Please click on the 'Remove Alert' "
                        + "button again and enter flight data in all fields");
            }
        } else if (e.getActionCommand().equals("Save to File")) {
//            JOptionPane.showMessageDialog(this, "Please click on the 'Remove Alert' "
//                    + "button again and enter flight data in all fields");
//            String rowOfFlightToUpdate = JOptionPane.showInputDialog("Please Input row number of the flight"
//                    + " you wish to update");
            try {
                jsonWriter.open();
                jsonWriter.write(fd);
                jsonWriter.close();
                System.out.println("Saved " + /* flight display.getName() +*/ " to " + JSON_STORE);
            } catch (FileNotFoundException exc) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        } else if (e.getActionCommand().equals("Load from File")) {
            try {
                fd = jsonReader.read();
                String[] columnNames = {"Airline",
                        "Flight Number",
                        "Origin",
                        "Status",
                        "Scheduled Arrival Time",
                        "Estimated Arrival Time"};
                Object[][] data = flightTableCreator();

                for (ArrivingFlight arrFlight : fd.getArrivingFlights()) {
                    modelTable.addRow(new Object[]{arrFlight.getAirline(), arrFlight.getFlightNumber(),
                            arrFlight.getStatus(), arrFlight.getOrigin(),
                            arrFlight.getEstimatedArrivalTime(), arrFlight.getScheduledArrivalTime()});
                }

                for (DepartingFlight depFlight : fd.getDepartingFlights()) {
                    modelTable1.addRow(new Object[]{depFlight.getAirline(), depFlight.getFlightNumber(),
                            depFlight.getStatus(), depFlight.getDestination(),
                            depFlight.getEstimatedDepartureTime(), depFlight.getScheduledDepartureTime()});
                }

                for (Alert emgAlert : fd.getEmergencyAlerts()) {
                    modelTable2.addRow(new Object[]{emgAlert.getId(),emgAlert.getAlert()});
                }

                System.out.println("Loaded Flight Information Display from " + JSON_STORE);
            } catch (IOException exc) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }
}


