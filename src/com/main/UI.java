package main;

import character.Goblin;
import object.OBJ_Log;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    BufferedImage goblins;
    public boolean gameFinished = false;

    // message
    Font arial;
    public String message = "";
    public boolean messageOn = false;
    int messageTimer = 0;

    // other class objects
    GamePanel gp;

    public UI(GamePanel gp) {
        this.gp = gp;

        // set message font
        arial = new Font("Arial", Font.PLAIN, 24);

        // replace with goblins
        Goblin goblin = new Goblin(gp);
        goblins = goblin.down1;

    }

    // set the message text and flag too on
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        // set message properties
        g2.setFont(arial);
        g2.setColor(Color.white);

        String text;
        int textLength;
        int x, y;


        if (gameFinished) {
            text = "Game Over";

            // places the text at the bottom center of the screen
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - gp.tileSize * 2;
            g2.drawString(text, x, y);

            gp.gameThread = null; // end the game
        } else {

            // displays the icon at top left corner
            g2.drawImage(goblins, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, null);
            g2.drawString("="+gp.npc.length, 48, 42);

            // display the event message when true
            if (messageOn) {

                // places the text at the bottom center of the screen
                textLength = (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;;
                y = gp.screenHeight - gp.tileSize * 2;

                g2.setFont(g2.getFont().deriveFont(24F));
                g2.drawString(message, x, y);

                // displays the message until condition is satisfied
                messageTimer++;
                if (messageTimer > 120) {
                    messageTimer = 0;
                    messageOn = false;
                }
            }

        }

    }

}
