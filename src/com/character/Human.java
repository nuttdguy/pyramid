package character;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;


public class Human extends Entity {

    KeyHandler keyH;

    public int screenX;
    public int screenY;
    public int hasLog;

    // constructors

    public Human() {
        super();
        setDefaults();;
    }

    public Human(GamePanel gp, KeyHandler keyH) {
        super(gp);
        setDefaults(keyH);
    }


    // setup

    public void setDefaults() {
        setMovesPerTurn(2);
        setOrDecrementMovesRemaining();
        setHealth(12);
        setMaxHealth(getHealth());
        setMarker('H');
    }

    public void setDefaults(KeyHandler keyH) {
        this.keyH = keyH;

        // sets the character at the center of the screen and offsets by half the tile size
        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
        // positions the entity at the center of the world map
        worldX = gp.tileSize * 21;
        worldY = gp.tileSize * 23;

        speed = 4; // sets the default pixel movement per frame
        solidArea = new Rectangle(8, 16, 32, 32); // rectangle used for collision detection
        solidAreaDefaultX = solidArea.x; // requires the default values to revert to after changes
        solidAreaDefaultY = solidArea.y;

        // load images from file
        loadImages();
    }

    public void loadImages() {

        String prefix = "/res/character/";
        String ext = ".png";
        // load images from file
        up1 = loadImage(prefix+"h1_up_1"+ext);
        up2 = loadImage(prefix+"h1_up_2"+ext);
        down1 = loadImage(prefix+"h1_down_1"+ext);
        down2 = loadImage(prefix+"h1_down_2"+ext);
        left1 = loadImage(prefix+"h1_left_1"+ext);
        left2 = loadImage(prefix+"h1_left_2"+ext);
        right1 = loadImage(prefix+"h1_right_1"+ext);
        right2 = loadImage(prefix+"h1_right_2"+ext);
    }

    // updates

    // update the selected direction and coordinates
    public void update() {

        // up-wrap logic is animation is desired at all times, i.e. when key is not pressed
        if (keyH.upPress || keyH.downPress || keyH.leftPress || keyH.rightPress) {

            // wrap logic inside when animation at all times is not the desired behavior
            if (keyH.upPress) {
                setAction("up");
            } else if (keyH.downPress) {
                setAction("down");
            } else if (keyH.leftPress) {
                setAction("left");
            } else if (keyH.rightPress) {
                setAction("right");
            }

            // always want to the turn the collision off before checking
            collisionOn = false;
            // check for tile collision
            gp.checker.checkTile(this);

            // check for object collision
//            int objIndex = gp.checker.checkObject(this, true);
//            pickUpObject(objIndex);

            // check for NPC collision
            int npcIndex = gp.checker.checkEntity(this, gp.npc);
            interactWithNpc(npcIndex);

            // when the collision is false, then player can move in the selected direction
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> setWorldY(getWorldY() - getSpeed());
                    case "down" -> setWorldY(getWorldY() + getSpeed());
                    case "left" -> setWorldX(getWorldX() - getSpeed());
                    case "right" -> setWorldX(getWorldX() + getSpeed());
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

    }

    // select and draw the image based on the selected direction
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
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

        // draws the image at xy location of height and width
//        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image, screenX, screenY,null);
    }

    // actions

    @Override
    public void setAction() {

    }

    public void pickUpObject(int i) {

        if (i != gp.checker.collisionCheckIndex) {

            String objectName = gp.obj[i].name;
            int goblins = 0; //todo remove temp with implementation

            switch(objectName) {
                case "Log" -> {
                    gp.obj[i] = null;
                    hasLog++;
                    gp.ui.showMessage("You got a log!");
                }
                case "Goblin" -> {
                    gp.obj[i] = null;
                    goblins--;
                    gp.ui.gameFinished = true;
                }
            }
        }
    }

    public void interactWithNpc(int npcIndex) {

        if (npcIndex != gp.checker.collisionCheckIndex) {
            System.out.println("you are hitting npc ");
        }

    }

    // OTHER METHODS

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
                ", row position=" + this.getWorldX() +
                ", col position=" + this.getWorldY() + '}';
    }

}
