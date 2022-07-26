package main;

import character.Human;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    // set and configure the game panel dimensions
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize  = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    double FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Human player = new Human(this, keyHandler);
    TileManager tileM = new TileManager(this);

    public GamePanel() {

        // set the game panel properties in the constructor
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        // listen for key presses
        this.addKeyListener(keyHandler);
        // sets whether the events should be focused, must be true, otherwise key events won't respond
        this.setFocusable(true);

    }

    public void startGameThread() {
        // applies the current instance of game panel into the thread and starts/runs.
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        // use delta game loop
        deltaGameLoop();
    }

    public void sleepGameLoop() {
        // set the interval of time to draw
        double drawInterval = 1000000000 / FPS; //
        double nextDrawTime = System.nanoTime() + drawInterval;

        // continue running the tread while its instance exists
        while(gameThread != null) {

            // on every iteration, update the game panel
            update();
            // then paint the changes to display
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

        }
    }

    public void deltaGameLoop() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        // for FPS
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
             currentTime = System.nanoTime();
             delta += (currentTime - lastTime) / drawInterval;
             timer += (currentTime - lastTime);
             lastTime = currentTime;

             if (delta >=1) {
                update();
                repaint();
                delta--;
                drawCount++;
             }

             // for displaying FPS
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }


    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // use the graphics2D to get more functions
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);

        // after paint, dispose the graphics2D instance
        g2.dispose();

    }
}
