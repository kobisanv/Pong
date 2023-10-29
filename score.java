import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Define a class named "score"
public class score {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;

    // Constructor for the score class
    score(int GAME_WIDTH, int GAME_HEIGHT) {
        // Set the static variables GAME_WIDTH and GAME_HEIGHT
        score.GAME_WIDTH = GAME_WIDTH;
        score.GAME_HEIGHT = GAME_HEIGHT;
    }

    // Draw the scores and center line on the screen
    public void draw(Graphics g) {
        // Set the color and font for drawing scores
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        // Draw the center line of the game screen
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);

        // Draw player 1's score on the left side of the center line
        g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), (GAME_WIDTH/2) - 85, 50);

        // Draw player 2's score on the right side of the center line
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (GAME_WIDTH/2) + 20, 50);
    }
}

