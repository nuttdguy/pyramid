package character;

import main.GamePanel;
import java.util.Random;

public class Goblin extends Entity {

    public Goblin() {
        super();
        // console program settings
        setMaxHealth(getHealth());
        this.setMovesPerTurn(2);
        this.setMarker('G');
    }

    public Goblin(GamePanel gp) {
        super(gp);
        speed = 2;  // set the entity pixel speed per frame
        loadImages();
    }


    // SETUP
    public void loadImages() {
        String prefix = "/res/character/";
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

    @Override
    public void setAction() {

        // increment the sprite animation counter
        spriteFPSLock++;

        // when sprite counter equal to 120, then select a random direction
        if (spriteFPSLock == 240) {
            Random random = new Random();
            int i = random.nextInt(101);

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            // reset the counter to zero
            spriteFPSLock = 0;
        }

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
