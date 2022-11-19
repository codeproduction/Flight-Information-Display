package ui.roughWork;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoughWorkMyFrame extends JFrame {

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public RoughWorkMyFrame() {

        this.setTitle("Flight Information Display");

        JLabel label = new JLabel("Arriving Flights");
        ImageIcon imageIcon = new ImageIcon("FIDS_Icon.png");
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newImg);  // transform it back

        // JLabel = a GUI display area for a string of text, an image, or both
        Border border = BorderFactory.createLineBorder(Color.black,0);
        label.setIcon(imageIcon);
        label.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT, CENTER, RIGHT of imageIcon
        label.setVerticalTextPosition(JLabel.TOP); // set text TOP, CENTER, BOTTOM of imageIcon
        label.setForeground(Color.BLACK); //
        label.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        //label.setIconTextGap(); // set gap of text to image - can be negative
        label.setBackground(Color.YELLOW); // color doesn't change until setOpaque is set to true
        label.setOpaque(true); // display background color
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER); // set vert. pos of icon+text within label
        label.setHorizontalAlignment(JLabel.CENTER); // set horz. pos of icon+text within label
        label.setBounds(250 - (350/2), 250 -(350/2), 350, 350); // sets x,y pos of label within frame & its size
        //this.setLayout(null);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(true);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.yellow); // change color of background
        this.setIconImage(imageIcon.getImage());
        this.add(label); // highly important
        this.pack(); // auto formatter - useful in some circumstances

        // A BorderLayout places components in five areas: NORTH, SOUTH, WEST, EAST,
        //                                                 CENTER,
        // All extra space is placed in the center area.


    }
}
