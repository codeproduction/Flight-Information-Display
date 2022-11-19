package ui;

import javax.swing.*;

public class DisplayGraphicalUI extends JFrame {
    private JPanel displayGraphicalUI;
    private JTable arrivingFlightsTable;
    private JTable departingFlightsTable;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button11;
    private JButton button12;
    private JTable table1;

    public DisplayGraphicalUI() {
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(displayGraphicalUI);
        this.pack();
        this.setVisible(true);
    }

    private void createUIComponents() {

    }

    public static void main(String[] args) {
        new DisplayGraphicalUI();
    }
}
