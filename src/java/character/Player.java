package character;

public abstract class Player {

    private double health;
    private double maxHealth;
    private double strength;
    private double defense;
    private int movesPerTurn;
    private int movesRemaining;
    private int coordX;
    private int coordY;
    private char marker;
    private int id = 0;

    Player() {
        setId();

        setHealth(10);
        setMaxHealth(getHealth());
        setStrength(10);
        setDefense(10);

        setMovesPerTurn(1);
        setMovesRemaining();
        setCoordinates(0, 0);
    }

    Player(char marker, double health, double strength, double defense, int movesPerTurn, int coordX, int coordY) {
        setId();
        setMarker(marker);

        setHealth(health);
        setMaxHealth(getHealth());
        setStrength(strength);
        setDefense(defense);

        setMovesPerTurn(movesPerTurn);
        setMovesRemaining();
        setCoordinates(coordX, coordY);
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

    public void setMovesRemaining() {
        this.movesRemaining = this.movesRemaining == 0 ? getMovesPerTurn() : this.movesRemaining - 1;
    }

    public void setCoordinates(int x, int y) {
        this.coordX = x;
        this.coordY = y;
    }

}
