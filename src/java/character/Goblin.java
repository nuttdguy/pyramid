package character;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Goblin extends Entity {

    KeyHandler keyH;
    GamePanel gp;

    public Goblin() {
        super();
        setMaxHealth(getHealth());
        this.setMovesPerTurn(2);
        this.setMarker('G');
    }

    public Goblin(GamePanel gp, KeyHandler heyH) {
        this.gp = gp;
        this.keyH = heyH;
        // sets the character at the center of the screen and offsets by half the tile size
//        screenX = gp.screenWidth/2 - (gp.tileSize/2);
//        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        // positions the entity at random position of the world map
        int xy[] = randomXY();
        worldX = xy[0] / gp.tileSize;
        worldY = xy[1] / gp.tileSize;
        speed = 4;
        setDirection("down");
        solidArea = new Rectangle(8, 16, 32, 32); // rectangle used for collision detection

        loadGoblinImages();
    }


    public Goblin(char marker, double health, double strength, double defense, int movesPerTurn, int coordX, int coordY) {
        super(marker, health, strength, defense, movesPerTurn, coordX, coordY);
    }

    // SETUP
    public void loadGoblinImages() {
        try {
            setUp1(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_up_1.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_up_2.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_down_1.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_down_2.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_left_1.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_left_2.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_right_1.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/res/character/g1_right_2.png")));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void update() {

        // up-wrap logic is animation is desired at all times, i.e. when key is not pressed
        if (keyH.upPress || keyH.downPress || keyH.leftPress || keyH.rightPress) {
            if (spriteCounter == 10) {
                setDirection(randomDirection());
            }
            // always want to the turn the collision off before checking
            collisionOn = false;
            gp.checker.checkTile(this);

            // when the collision is false, then player can move in the selected direction
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY = worldY - getSpeed();
                    case "down" -> worldY = worldY + getSpeed();
                    case "left" -> worldX = worldX - getSpeed();
                    case "right" -> worldX = worldX + getSpeed();
                }
            }

            // sprite animation
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
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    // get random coords to set goblin
    private int[] randomXY() {
        int x = (int) (Math.random() * gp.worldWidth);
        int y = (int) (Math.random() * gp.worldHeight);
        return new int[]{x, y};
    }

    private String randomDirection() {
        String[] direction = new String[]{"up", "down", "left", "right"};
        return direction[(int) (Math.random() * direction.length)];
    }

    // OTHER METHODS
    private double characterFactor() {
        return Math.random() * 1;
    }

    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * characterFactor();
    }

    @Override
    public double defend() {
        // add the number of turns active
        double newDefense = (getDefense() + characterFactor()) + getDefense();
        setDefense(newDefense);
        return newDefense;
    }


    @Override
    public String toString() {
        return "character.Goblin{" +
                "id=" + this.getId() +
                ", marker=" + this.getMarker() +
                ", strength="+ this.getStrength() +
                ", health=" + this.getHealth() +
                ", defense=" + this.getDefense() +
                ", movesRemaining=" + this.getMovesRemaining() +
                ", row position=" + this.getWorldX() +
                ", col position=" + this.getWorldY() + '}';
    }
}
