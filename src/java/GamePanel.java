import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    // set and configure the game panel dimensions
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize  = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    double FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    int playerX = 50;
    int playerY = 50;
    int playerSpeed = 4;

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

    // control the speed
    public void startGameThread() {
        // applies the current instance of game panel into the thread and starts/runs.
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

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

    public void update() {
        if (keyHandler.upPress) {
            this.playerY += playerSpeed;
        } else if (keyHandler.rightPress) {
            this.playerX += playerSpeed;
        } else if (keyHandler.leftPress) {
            this.playerX -= playerSpeed;
        } else if (keyHandler.downPress) {
            this.playerY -= playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // use the graphics2D to get more functions
        Graphics2D g2 = (Graphics2D) g;

        // set temp placeholder as color
        g2.setColor(Color.white);
        // set temp placeholder as rect; set xy location and height and width
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        // after paint, dispose the graphics2D instance
        g2.dispose();

    }
}
