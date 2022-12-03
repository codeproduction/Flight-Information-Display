package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Inspiration for code here taken from demo Java projects from Java docs published by Oracle
 * This class runs the splash screen at the beginning of the GUI program.
 */
public class SplashJava extends JWindow {
    Image splashScreen;
    ImageIcon imageIcon;

    public SplashJava() {
        splashScreen = Toolkit.getDefaultToolkit().getImage("src/main/ui/flightDisplayGIF.gif");
        // Create ImageIcon from Image
        imageIcon = new ImageIcon(splashScreen);
        // Set JWindow size from image size
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // Get current screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Get x coordinate on screen for make JWindow locate at center
        int x = (screenSize.width - getSize().width) / 2;
        // Get y coordinate on screen for make JWindow locate at center
        int y = (screenSize.height - getSize().height) / 2;
        // Set new location for JWindow
        setLocation(x, y);
        // Make JWindow visible
        setVisible(true);
    }

    // Paint image onto JWindow
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(splashScreen, 0, 0, this);
    }
}