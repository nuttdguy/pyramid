package main;

import main.GamePanel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Set up JPanel Frame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Humans vs Goblins");

        // Add a game panel
        GamePanel gamePanel = new GamePanel();

        // add the game panel and place into the JFrame window
        window.add(gamePanel);
        // pack the window into the configured / set screen size
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // run the game loop
        gamePanel.startGameThread();

    }

}
