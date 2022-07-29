package character;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {

    // general stats for an entity
    private double health;
    private double maxHealth;
    private double strength;
    private double defense;
    private int movesPerTurn;
    private int movesRemaining;

    // identity markers for the entity
    private char marker;
    private int id = 0;

    // position of entity on world map
    public int worldX;
    public int worldY;
    public int speed;  // number of pixels the entity will move per turn

    // the direction entity can move and the images for sprite animation
    public String direction = "down";
    public BufferedImage up1, up2 , down1, down2, left1, left2, right1, right2;

    // setting for the animation of sprites
    public int spriteAnimationCounter = 0;
    public int spriteAnimationNum = 1;
    public int spriteFPSLock = 0;

    // default rectangle setting for collision detection
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;
    public boolean collisionOn = false;

    // other class objects
    GamePanel gp;

    // constructors

    Entity() {
        setDefaults();;
    }

    Entity(GamePanel gp) {
        this.gp = gp;
        setDefaults();
    }

    // setup

    public void setDefaults() {
        setId();
        setMarker(' ');

        setHealth(10);
        setMaxHealth(getHealth());
        setStrength(10);
        setDefense(10);

        setMovesPerTurn(2);
//        setCoordinates(100 , 100);

    }

    public BufferedImage loadImage(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);  // scale the image

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return image;
    }

    // actions

    public abstract void setAction();

    // updates the entity direction and xy
    public void update() {

        // set characters action
        setAction();
        // always want to the turn the collision off before checking
        collisionOn = false;
        // check for tile collision
        gp.checker.checkTile(this);
        gp.checker.checkPlayer(this);

        // when the collision is false, then player can move in the selected direction
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" ->  worldX -= speed;
                case "right" ->  worldX += speed;
            }
        }

        // sprite animation
        // changes the sprite every 10 frames, or six times since FPS is set to 60
        spriteAnimationCounter++;
        if (spriteAnimationCounter > 10) {
            if (spriteAnimationNum == 1) {
                spriteAnimationNum = 2;
            } else if (spriteAnimationNum == 2) {
                spriteAnimationNum = 1;
            }
            spriteAnimationCounter = 0;
        }

    }

    // draws the entity image onto the screen
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        // player position on the map
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // condition checks whether tile to be drawn is within the bounds of the players viewable screen area
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

            switch (direction) {
                case "up" -> {
                    if (spriteAnimationNum == 1) {
                        image = getUp1();
                    }
                    if (spriteAnimationNum == 2) {
                        image = getUp2();
                    }
                }
                case "down" -> {
                    if (spriteAnimationNum == 1) {
                        image = getDown1();
                    }
                    if (spriteAnimationNum == 2) {
                        image = getDown2();
                    }
                }
                case "left" -> {
                    if (spriteAnimationNum == 1) {
                        image = getLeft1();
                    }
                    if (spriteAnimationNum == 2) {
                        image = getLeft2();
                    }
                }
                case "right" -> {
                    if (spriteAnimationNum == 1) {
                        image = getRight1();
                    }
                    if (spriteAnimationNum == 2) {
                        image = getRight2();
                    }
                }
            }

            // draw the image that is corresponds to the tile position, i.e. 0 == tile at position 0
            g2.drawImage(image, screenX, screenY,null);
        }

    }


    // METHODS

    public abstract double attack();

    public abstract double defend();

    // GETTERS //

    public int getId() {
        return id;
    }

    public String getName() {
        return this.getClass().getName().split("\\.")[1];
    }

    public char getMarker() { return this.marker; }

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

    public int getMovesPerTurn() {
        return this.movesPerTurn;
    }

    public int getMovesRemaining() {
        return this.movesRemaining;
    }

    public int[] getCoordinates() {
        return new int[] {this.getWorldX(), this.getWorldY()};
    }

    public int getWorldX() {
        return this.worldX;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public String getDirection() {
        return direction;
    }

    // SETTERS //

    public void setDefense(double _defense) {
        this.defense = _defense;
    }

    public void setId() {
        this.id = (int) (Math.random() * Integer.MAX_VALUE);
    }

    public void setMarker(char marker) {
        this.marker = marker;
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

    public void setMovesPerTurn(int _moveDistance) {
        this.movesPerTurn = _moveDistance;
    }

    public void setOrDecrementMovesRemaining() {
        if (this.getMovesRemaining() == 0 ) {
            this.movesRemaining = this.getMovesPerTurn();
        } else {
            this.movesRemaining--;
        }
    }

    public void setCoordinates(int x, int y) {
        this.worldX = x;
        this.worldY = y;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public void setAction(String direction) {
        this.direction = direction;
    }
}
