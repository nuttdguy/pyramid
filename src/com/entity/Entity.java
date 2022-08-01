package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {

    // STATS
    private double health;
    private double maxHealth;
    private double strength;
    private double defense;

    // IDENTITY
    public String name;

    // ENTITY XY ON MAP
    public int mapX;
    public int mapY;
    public int speed;  // number of pixels the entity will move per turn

    // ENTITY VIEWABLE SCREEN AREA
    public int screenX;
    public int screenY;

    // SPRITE / DIRECTION
    public String direction = "down";
    public BufferedImage up1, up2 , down1, down2, left1, left2, right1, right2;

    // RECTANGLE FOR COLLISION
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;


    // OTHER CLASS OBJECTS
    GamePanel gp;

    // CONSTRUCTORS
    Entity() {
        setDefaults();
    }

    Entity(GamePanel gp) {
        this.gp = gp;
        setDefaults();
    }
    // END - CONSTRUCTORS


    // SETUP
    public void setDefaults() {
        setHealth(10);
        setMaxHealth(getHealth());
        setStrength(10);
        setDefense(10);
    }

    public BufferedImage loadImage(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, gp.maxTileSize, gp.maxTileSize);  // scale the image

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return image;
    }
    // END - SETUP


    // METHODS

    public abstract double attack();

    // GETTERS //

    public String getName() {
        return this.getClass().getName().split("\\.")[1];
    }

    public double getHealth() {
        return this.health;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double getStrength() {
        return this.strength;
    }

    public double getDefense() { return this.defense; }

    public int getMapX() {
        return this.mapX;
    }

    public int getMapY() {
        return this.mapY;
    }


    // SETTERS //

    public void setDefense(double _defense) {
        this.defense = _defense;
    }

    public void setHealth(double _health) {
        this.health = _health;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setStrength(double _strength) {
        this.strength = _strength;
    }


}
