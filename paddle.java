import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Define a class named "paddle" that extends Rectangle
public class paddle extends Rectangle {
    int id;
    int yVelocity;
    int speed = 10;

    // Constructor for the paddle class
    paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    // Handle key presses for paddle movement
    public void keyPressed(KeyEvent e) {
        // Switch statement to check the paddle ID and key pressed
        switch(id) {
            case 1:
                // If 'W' key is pressed, move paddle up
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                // If 'S' key is pressed, move paddle down
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                // If 'UP' arrow key is pressed, move paddle up
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                // If 'DOWN' arrow key is pressed, move paddle down
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    // Handle key releases to stop paddle movement
    public void keyReleased(KeyEvent e) {
        // Switch statement to check the paddle ID and key released
        switch(id) {
            case 1:
                // If 'W' or 'S' key is released, stop paddle movement
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                // If 'UP' or 'DOWN' arrow key is released, stop paddle movement
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    // Set the vertical direction of the paddle's movement
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    // Move the paddle based on its vertical velocity
    public void move() {
        y = y + yVelocity;
    }

    // Draw the paddle on the screen
    public void draw(Graphics g) {
        // Set color based on paddle ID and fill the paddle rectangle
        if (id == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }
}
