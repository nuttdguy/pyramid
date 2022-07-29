package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

// general object construct of a game asset
public abstract class SuperObject {

    public BufferedImage image;
    public String name;

    // detect when collision is valid
    public boolean collision = false;

    // position of object on map
    public int worldX;
    public int worldY;

    // sets the solid area for the object
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public UtilityTool uTool = new UtilityTool();


    public void draw(Graphics2D g2, GamePanel gp) {

        // locate the players position on the map
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // determines whether tile to be drawn is within the bounds of the players viewable screen area
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

            // draw the image that is corresponds to the tile position, i.e. 0 == tile at position 0
//            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            // omit scaling dimensions when assets have already been scaled
            g2.drawImage(image, screenX, screenY,null);
        }

    }
}
