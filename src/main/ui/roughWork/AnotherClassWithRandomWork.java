package ui.roughWork;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class AnotherClassWithRandomWork extends JFrame implements ActionListener {

    JButton button;
    JTextField textField;
    JCheckBox checkBox;
    JComboBox comboBox;
    JRadioButton radioButton;
    Color airportYellow = new Color(255,233,0);

    public AnotherClassWithRandomWork() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        checkBox = new JCheckBox("I'm not a robot");
        checkBox.setFocusable(false);
        checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        checkBox.setBackground(Color.black);
        checkBox.setForeground(airportYellow);

        radioButton = new JRadioButton("Airports !!!");

        this.getContentPane().setBackground(airportYellow);

        button = new JButton("Siuuuuuu");
        button.addActionListener(this);

        String[] animals = {"Dog", "Cat", "Bird"};
        comboBox = new JComboBox(animals);
        comboBox.addActionListener(this);
        // having fun with comboBox
        comboBox.setEditable(true);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 40));
        textField.setFont(new Font("Frutiger ", Font.PLAIN, 25));
        textField.setForeground(airportYellow);
        textField.setBackground(Color.black);
        textField.setCaretColor(Color.white);

        this.add(radioButton);
        this.add(checkBox);
        this.add(textField);
        this.add(button);
        this.add(comboBox);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("Welcome " + textField.getText());
            System.out.println(checkBox.isSelected());
        }
        if (e.getSource() == comboBox) {
            System.out.println(comboBox.getSelectedItem());
        }
    }

}
