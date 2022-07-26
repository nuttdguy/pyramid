package character;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;


public class Human extends Player {

    KeyHandler keyH;
    GamePanel gp;

    // todo correct the use of set methods in the constructor
    public Human() {
        super();
        setMovesPerTurn(2);
        setOrDecrementMovesRemaining();
        setHealth(12);
        setMaxHealth(getHealth());
        setMarker('H');
    }

    public Human(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        getPlayerImage();
        setDirection("down");
        setCoordinates(50, 50);

        setMovesPerTurn(2);
        setOrDecrementMovesRemaining();
        setHealth(12);
        setMaxHealth(getHealth());
        setMarker('H');
    }


    public Human(char marker, double health, double strength, double defense, int movesPerTurn, int coordX, int coordY) {
        super(marker, health, strength, defense, movesPerTurn, coordX, coordY);
    }


    public void getPlayerImage() {
        try {

            setUp1(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/res/character/human_1.png")));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // update the selected direction and coordinates
    public void update() {

        // up-wrap logic is animation is desired at all times, i.e. when key is not pressed
        if (keyH.upPress || keyH.downPress || keyH.leftPress || keyH.rightPress) {

            // wrap logic inside when animation at all times is not the desired behavior
            if (keyH.upPress) {
                setDirection("down");
                setCoordY(getCoordY() - getPixelSpeed());
            } else if (keyH.rightPress) {
                setDirection("right");
                setCoordX(getCoordX() + getPixelSpeed());
            } else if (keyH.leftPress) {
                setDirection("left");
                setCoordX(getCoordX() - getPixelSpeed());
            } else {
                setDirection("up");
                setCoordY(getCoordY() + getPixelSpeed());
            }

            // changes the sprite every 10 frames, or six times since FPS is set to 60
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    // select and draw the image based on the selected direction
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (getDirection()) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = getUp1();
                }
                if (spriteNum == 2) {
                    image = getUp2();
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = getDown1();
                }
                if (spriteNum == 2) {
                    image = getDown2();
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = getLeft1();
                }
                if (spriteNum == 2) {
                    image = getLeft2();
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = getRight1();
                }
                if (spriteNum == 2) {
                    image = getRight2();
                }
            }
        }

        // draws the image at xy location of height and width
        g2.drawImage(image, getCoordX(), getCoordY(), gp.tileSize, gp.tileSize, null);
    }

    private double characterFactor() { return (Math.random() * 2);}

    private double regenerateHealth() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(Math.random() * 1));
    }

    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * characterFactor();
    }

    @Override
    public double defend() {
        double newHealth = regenerateHealth() + getHealth();
        setHealth(newHealth);
        return newHealth;
    }

    @Override
    public String toString() {
        return "character.Human{" +
                "id=" + this.getId() +
                ", marker=" + this.getMarker() +
                ", strength="+ this.getStrength() +
                ", health=" + this.getHealth() +
                ", defense=" + this.getHealth() +
                ", movesRemaining=" + this.getMovesRemaining() +
                ", maxHealth=" + this.getMaxHealth() +
                ", row position=" + this.getCoordX() +
                ", col position=" + this.getCoordY() + '}';
    }

}
