package entity;

import main.GamePanel;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Goblin extends Entity {

    // FIELDS
    private int drawCycles = 0;

    // CONSTRUCTORS
    public Goblin() {
        super();
        setDefaults();
    }

    public Goblin(GamePanel gp) {
        super(gp);
        setDefaults();
    }
    // END CONSTRUCTORS


    // SETUP
    @Override
    public void setDefaults() {
        this.mapX = randomPosition(gp.maxMapCol);
        this.mapY = randomPosition(gp.maxMapRow);
        setHealth(10);
        setMaxHealth(getHealth());
        setStrength(10);
        setDefense(10);
        this.speed = 2;
        this.name = "Goblin";
        loadImages();
    }

    public void loadImages() {
        String prefix = "/res/entity/";
        String ext = ".png";
        // load images from file
        up1 = loadImage(prefix+"g1_up_1"+ext);
        up2 = loadImage(prefix+"g1_up_2"+ext);
        down1 = loadImage(prefix+"g1_down_1"+ext);
        down2 = loadImage(prefix+"g1_down_2"+ext);
        left1 = loadImage(prefix+"g1_left_1"+ext);
        left2 = loadImage(prefix+"g1_left_2"+ext);
        right1 = loadImage(prefix+"g1_right_1"+ext);
        right2 = loadImage(prefix+"g1_right_2"+ext);
    }

    private int randomPosition(int max) {
        return (int) (Math.random() * max);
    }
    // END - SETUP


    // ACTIONS
    public void setAction() {

        Random random = new Random();
        int i = random.nextInt(101);

        if (drawCycles == 4) {
            if (i <= 25) {
                this.direction = "up";
                this.mapY -= speed;
            }
            if (i > 25 && i <= 50) {
                this.direction = "down";
                this.mapY += speed;
            }
            if (i > 50 && i <= 75) {
                this.direction = "left";
                this.mapX -= speed;
            }
            if (i > 75 && i <= 100) {
                this.direction = "right";
                this.mapX += speed;
            }
            drawCycles = 0;
        }
        drawCycles++;
    }
    // END - ACTIONS

    // RENDER
    public void draw(Graphics2D g2, Human player) {

        BufferedImage image = null;
        int screenX = mapX - player.mapX + player.screenX;
        int screenY = mapY - player.mapY + player.screenY;

        switch(direction) {
            case "up" -> image = up1;
            case "down" -> image = down1;
            case "left" -> image = left1;
            case "right" -> image = right1;
        }

        // draw the image that is corresponds to the tile position, i.e. 0 == tile at position 0
        g2.drawImage(image, screenX, screenY,null);

    }
    // END - RENDER


    // METHODS
    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * characterFactor();
    }

    private double characterFactor() {
        return Math.random() * 1;
    }


    @Override
    public String toString() {
        return "character.Goblin{" +
                ", strength="+ this.getStrength() +
                ", health=" + this.getHealth() +
                ", defense=" + this.getDefense() +
                ", row position=" + this.getMapX() +
                ", col position=" + this.getMapY() + '}';
    }
}
