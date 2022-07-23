abstract class Player implements Stat, Mobilize, Combatable {

    private double health;
    private double maxHealth;
    private double strength;
    private double defense;
    private int movesPerTurn;
    private int movesRemaining;
    private int coordinateX;
    private int coordinateY;
    private char marker;
    private int objectId = 0;

    Player() {
        setHealth(10);
        setStrength(10);
        setDefense(10);
        setMovesPerTurn(1);
        setCoordinate(0, 0);
    }

    public int getObjectId() {
        return objectId;
    }

    protected void setObjectId() {
        this.objectId = (int) (Math.random() * Integer.MAX_VALUE);
    }
    @Override
    public char getMarker() {
        return this.marker;
    }
    @Override
    public void setMarker(char marker) {
        this.marker = marker;
    }
    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(double _health) {
        this.health = _health;
    }

    @Override
    public double getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public double getStrength() {
        return this.strength;
    }

    @Override
    public void setStrength(double _strength) {
        this.strength = _strength;
    }

    @Override
    public double getDefense() {
        return this.defense;
    }

    @Override
    public void setDefense(double _defense) {
        this.defense = _defense;
    }

    @Override
    public int getMovesPerTurn() {
        return this.movesPerTurn;
    }

    @Override
    public void setMovesPerTurn(int _moveDistance) {
        this.movesPerTurn = _moveDistance;
    }
    @Override
    public void setCoordinate(int x) {
        this.coordinateX = x;
    }

    @Override
    public void setCoordinate(int x, int y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }

    @Override
    public int getCoordinateX() {
        return this.coordinateX;
    }

    @Override
    public int getCoordinateY() {
        return this.coordinateY;
    }

    @Override
    public int[] getCoordinates() {
        return new int[] {this.getCoordinateX(), this.getCoordinateY()};
    }

    @Override
    public int getMovesRemaining() {
        return this.movesRemaining;
    }
    @Override
    public void setMovesRemaining(int movesRemaining){
        this.movesRemaining = movesRemaining;
    }

    @Override
    public double attack() {
        return Math.random() * getStrength();
    }
}
