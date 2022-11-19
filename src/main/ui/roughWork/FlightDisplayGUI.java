package ui.roughWork;

import javax.swing.*;
import java.awt.*;

public class FlightDisplayGUI extends JFrame {

    Color airportYellow = new Color(255,233,0);

    public FlightDisplayGUI() {
        this.setTitle("Flight Information Display : YVR – VANCOUVER INTERNATIONAL AIRPORT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int) (525 * 1.75), (int) (525 * 1.0));
        this.setResizable(true);
        this.setVisible(true);
        this.getContentPane().setBackground(airportYellow);
    }
}
