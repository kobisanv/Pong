import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Define a class named "gameFrame" that extends JFrame
public class gameFrame extends JFrame {
    // Declare a variable of type gamePanel
    gamePanel panel;

    // Constructor for the gameFrame class
    gameFrame() {
        // Initialize the gamePanel object
        panel = new gamePanel();
        // Add the gamePanel to the gameFrame
        this.add(panel);
        // Set the title of the game window to "Pong"
        this.setTitle("Pong");
        // Disable window resizing
        this.setResizable(false);
        // Set the background color of the window to black
        this.setBackground(Color.black);
        // Set default close operation to exit the application when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Pack the components of the window
        this.pack();
        // Make the window visible
        this.setVisible(true);
        // Center the window on the screen
        this.setLocationRelativeTo(null);
    }
}
