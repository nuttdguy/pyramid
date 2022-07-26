package character;

import java.awt.image.BufferedImage;

public abstract class Player {

    private double health;
    private double maxHealth;
    private double strength;
    private double defense;
    private int movesPerTurn;
    private int movesRemaining;
    private int coordX;
    private int coordY;
    private int pixelSpeed = 4;
    private char marker;
    private int id = 0;

    public BufferedImage up1, up2 , down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    Player() {
        setDefaults();;
    }

    Player(char marker, double health, double strength, double defense, int movesPerTurn, int coordX, int coordY) {
        setId();
        setMarker(marker);

        setHealth(health);
        setMaxHealth(getHealth());
        setStrength(strength);
        setDefense(defense);

        setMovesPerTurn(movesPerTurn);
        setCoordinates(coordX, coordY);
    }

    public void setDefaults() {
        setId();

        setHealth(10);
        setStrength(10);
        setDefense(10);

        setMovesPerTurn(1);
        setCoordinates(10, 10);
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
        return new int[] {this.getCoordX(), this.getCoordY()};
    }

    public int getCoordX() {
        return this.coordX;
    }

    public int getCoordY() {
        return this.coordY;
    }

    public int getPixelSpeed() {
        return pixelSpeed;
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
        this.coordX = x;
        this.coordY = y;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
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

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
