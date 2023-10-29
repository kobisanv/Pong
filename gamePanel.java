import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Define a class named "gamePanel" that extends JPanel and implements the Runnable interface
public class gamePanel extends JPanel implements Runnable {

    // Declare constants for game dimensions, ball diameter, and paddle dimensions
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 75;

    // Declare variables for game thread, image, graphics, random object, paddles, ball, and score
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    paddle PADDLE1;
    paddle PADDLE2;
    ball BALL;
    score SCORE;

    // Constructor for the gamePanel class
    gamePanel() {
        // Initialize paddles and ball
        newPaddles();
        newBall();
        // Initialize the SCORE object with game dimensions
        SCORE = new score(GAME_WIDTH, GAME_HEIGHT);
        // Set the focus on the panel and add KeyListener for paddle movement
        this.setFocusable(true);
        this.addKeyListener(new AL());
        // Set the preferred size of the panel
        this.setPreferredSize(SCREEN_SIZE);
        // Create and start a new game thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Method to create a new ball with random position
    public void newBall() {
        random = new Random();
        // Set the BALL object with a random position within game boundaries
        BALL = new ball(random.nextInt((GAME_WIDTH/2)-(BALL_DIAMETER/2)),
                random.nextInt(GAME_HEIGHT-BALL_DIAMETER),
                BALL_DIAMETER, BALL_DIAMETER);
    }

    // Method to create new paddles
    public void newPaddles() {
        // Initialize PADDLE1 at the left side and PADDLE2 at the right side of the screen
        PADDLE1 = new paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        PADDLE2 = new paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    // Method to paint components on the panel
    public void paint(Graphics g) {
        // Create an off-screen image buffer
        image = createImage(getWidth(), getHeight());
        // Get the graphics context of the off-screen image buffer
        graphics = image.getGraphics();
        // Draw components on the off-screen image buffer
        draw(graphics);
        // Draw the off-screen image buffer onto the panel
        g.drawImage(image, 0, 0, this);
    }

    // Method to draw components on the off-screen image buffer
    public void draw(Graphics g) {
        // Draw paddles, ball, and score on the off-screen image buffer
        PADDLE1.draw(g);
        PADDLE2.draw(g);
        BALL.draw(g);
        SCORE.draw(g);
        // Synchronize the graphics state
        Toolkit.getDefaultToolkit().sync();
    }

    // Method to move components (paddles and ball)
    public void move() {
        // Move paddles and ball
        PADDLE1.move();
        PADDLE2.move();
        BALL.move();
    }

    // Method to check collisions and handle game logic
    public void checkCollisions() {
        // Prevent paddles from moving out of the game screen
        if (PADDLE1.y <= 0) {
            PADDLE1.y = 0;
        }
        if (PADDLE1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            PADDLE1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (PADDLE2.y <= 0) {
            PADDLE2.y = 0;
        }
        if (PADDLE2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            PADDLE2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // Bounce ball off the top and bottom window edges
        if (BALL.y <= 0) {
            // Reverse the vertical direction of the ball's movement
            BALL.setYDirection(-BALL.yVelocity);
        }
        if (BALL.y >= GAME_HEIGHT - BALL_DIAMETER) {
            // Reverse the vertical direction of the ball's movement
            BALL.setYDirection(-BALL.yVelocity);
        }

        // Bounce ball off paddles and increase speed after collision
        if (BALL.intersects(PADDLE1)) {
            // Change the ball's horizontal direction based on its current direction
            BALL.xVelocity = Math.abs(BALL.xVelocity);
            // Increase the ball's horizontal speed
            BALL.xVelocity++;
            // Increase the ball's vertical speed if it was moving downward, decrease if upward
            if (BALL.yVelocity > 0) BALL.yVelocity++;
            else BALL.yVelocity--;
            // Set the ball's new directions
            BALL.setXDirection(BALL.xVelocity);
            BALL.setYDirection(BALL.yVelocity);
        }

        if (BALL.intersects(PADDLE2)) {
            // Change the ball's horizontal direction based on its current direction
            BALL.xVelocity = Math.abs(BALL.xVelocity);
            // Increase the ball's horizontal speed
            BALL.xVelocity++;
            // Increase the ball's vertical speed if it was moving downward, decrease if upward
            if (BALL.yVelocity > 0) BALL.yVelocity++;
            else BALL.yVelocity--;
            // Set the ball's new directions
            BALL.setXDirection(-BALL.xVelocity);
            BALL.setYDirection(BALL.yVelocity);
        }

        // Award a point to player 1, reset paddles and ball, and print player 2's score
        if (BALL.x <= 0) {
            // Increase player 2's score
            SCORE.player2++;
            // Reset paddles and create a new ball
            newPaddles();
            newBall();
            // Print player 2's score to the console
            System.out.println("Player 2: " + SCORE.player2);
        }

        // Award a point to player 2, reset paddles and ball, and print player 1's score
        if (BALL.x >= GAME_WIDTH - BALL_DIAMETER) {
            // Increase player 1's score
            SCORE.player1++;
            // Reset paddles and create a new ball
            newPaddles();
            newBall();
            // Print player 1's score to the console
            System.out.println("Player 1: " + SCORE.player1);
        }
    }

    // Main game loop
    public void run() {
        // Record the current time in nanoseconds
        long lastTime = System.nanoTime();
        // Set the desired number of ticks per second
        double amountOfTicks = 60.0;
        // Calculate the time per tick in nanoseconds
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        // Game loop
        while(true) {
            // Get the current time in nanoseconds
            long now = System.nanoTime();
            // Add the time passed since the last loop to delta
            delta += (now - lastTime) / ns;
            // Update lastTime to the current time
            lastTime = now;

            // If enough time has passed to execute a tick
            if (delta >= 1) {
                // Move components, check collisions, and repaint the panel
                move();
                checkCollisions();
                repaint();
                // Decrement delta by 1 to represent one tick
                delta--;
            }
        }
    }

    // Inner class for handling keyboard input
    public class AL extends KeyAdapter {
        // Handle key presses for paddles
        public void keyPressed(KeyEvent e) {
            // Call the keyPressed method of paddles based on the pressed key
            PADDLE1.keyPressed(e);
            PADDLE2.keyPressed(e);
        }

        // Handle key releases for paddles
        public void keyReleased(KeyEvent e) {
            // Call the keyReleased method of paddles based on the released key
            PADDLE1.keyReleased(e);
            PADDLE2.keyReleased(e);
        }
    }
}