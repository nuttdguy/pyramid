package entity;
import main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Human extends Entity {

    String message1 = "";
    String message2 = "";
    boolean messageOn = false;
    int messageTimer = 0;


    // constructors

    public Human() {
        super();
        setDefaults();
    }

    public Human(GamePanel gp) {
        super(gp);
        setDefaults();
    }

    // setup

    public void setDefaults() {

        setHealth(12);
        setMaxHealth(getHealth());

        // sets the character at the center of the screen and offsets by half the tile size
        this.screenX = gp.screenWidth/2 - (gp.maxTileSize/2);  // 768 / 2 - 48 = 360
        this.screenY = gp.screenHeight/2 - (gp.maxTileSize/2);  // 576 / 2 - 48 = 264
        // positions the entity at the center of the world map
        this.mapX = gp.maxTileSize * 10;  // 1000
        this.mapY = gp.maxTileSize * 10;  // 1000

        this.speed = 8; // sets the default pixel movement per frame

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

    // draws the entity image onto the screen
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up" -> image = up1;
            case "down" -> image = down1;
            case "left" -> image = left1;
            case "right" -> image = right1;
        }

        if (messageOn) {
            int textLength;
            int x, y;

            gp.g2.setColor(Color.RED);
            gp.g2.setFont(new Font("MV Boli",Font.PLAIN,24));
            textLength = (int) gp.g2.getFontMetrics().getStringBounds(message1, gp.g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;;
            y = gp.screenHeight - gp.maxTileSize * 2;

            gp.g2.drawString(message1, x, y);
            gp.g2.drawString(message2, x, y+48);

            messageTimer++;
        }


        if (messageTimer == 2) {
            messageTimer = 0;
            messageOn = false;
            message1 = "";
            message2 = "";
        }

        // draw the image that is corresponds to the tile position, i.e. 0 == tile at position 0
        g2.drawImage(image, screenX, screenY,null);

    }

    // update the selected direction and coordinates
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.mapY -= speed;
            this.direction = "up";
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.mapY += speed;
            this.direction = "down";
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.mapX -= speed;
            this.direction = "left";
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.mapX += speed;
            this.direction = "right";
        }

    }

    // actions


    // OTHER METHODS

    private double characterFactor() { return (Math.random() * 2);}

    public <T extends Entity> double attack(T attacker, T defender) {
        double defenderHealth = defender.getHealth();
        double damageByAttacker = attacker.attack();

        defenderHealth = defenderHealth - damageByAttacker;
        defender.setHealth(Double.parseDouble(String.format("%.2f", defenderHealth)));

        displayCombatNarrative(1, attacker.getName(), defender.getName(), damageByAttacker);
        displayCombatNarrative(2, defender.getName(), defender.getName(), defender.getHealth());
        return defender.getHealth();
    }

    private void displayCombatNarrative(int option, String attacker, String defender, Double stat) {
        messageOn = true;
        // set text for combat narrative
        if (stat < 0) {
            option = 3;
        }

        switch(option) {
            case 1 -> message1 = String.format("%s attack's %s for %.2f %n", attacker, defender, stat);
            case 2 -> message2 = String.format("%s health is now %.2f %n", defender, stat);
            case 3 -> message2 = String.format("%s is now dead!%n", defender, stat);
        }

    }

    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * characterFactor();
    }

    @Override
    public String toString() {
        return "character.Human{" +
                ", strength="+ this.getStrength() +
                ", health=" + this.getHealth() +
                ", defense=" + this.getHealth() +
                ", maxHealth=" + this.getMaxHealth() +
                ", row position=" + this.getMapX() +
                ", col position=" + this.getMapY() + '}';
    }

}
