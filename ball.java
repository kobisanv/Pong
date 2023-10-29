import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Define a class named "ball" that extends the Rectangle class
public class ball extends Rectangle {
    // Declare a Random object for generating random numbers
    Random random;
    // Declare variables for x and y velocity, and initial speed
    int xVelocity;
    int yVelocity;
    int initSpeed = 6;

    // Constructor for the ball class, takes x, y, width, and height as parameters
    ball(int x, int y, int width, int height) {
        // Call the constructor of the superclass (Rectangle) with the provided parameters
        super(x, y, width, height);
        // Initialize the random object
        random = new Random();

        // Generate a random number (0 or 1) for the x direction
        int randomXDirection = random.nextInt(2);
        // If randomXDirection is 0, set it to -1; otherwise, set it to 1, then multiply by initial speed
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initSpeed);

        // Generate a random number (0 or 1) for the y direction
        int randomYDirection = random.nextInt(2);
        // If randomYDirection is 0, set it to -1; otherwise, set it to 1, then multiply by initial speed
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initSpeed);
    }

    // Method to set the x direction of the ball
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    // Method to set the y direction of the ball
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    // Method to move the ball based on its velocity
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    // Method to draw the ball on the screen using the provided Graphics object
    public void draw(Graphics g) {
        // Set the color of the ball to white
        g.setColor(Color.white);
        // Fill an oval with the specified x, y, width, and height (which are inherited from Rectangle class)
        g.fillOval(x, y, height, width);
    }
}
