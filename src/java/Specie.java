abstract class Specie implements Stat, Mobilize, Combatable {

    private double health;
    private double strength;
    private double defense;
    private int moveDistance;
    private int coordinateX;
    private int coordinateY;

    Specie() {
        setHealth(10);
        setStrength(10);
        setDefense(10);
        setMoveDistance(4);
        setCoordinate(0, 0);
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
    public int getMovePerTurn() {
        return this.moveDistance;
    }

    @Override
    public void setMoveDistance(int _moveDistance) {
        this.moveDistance = _moveDistance;
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
    public String walk() {
        // todo implement
        // choose direction
        // move
            // when obstacle / enemy  exists
                // enter combat using math.random
                // continue battle until one player health equals to or less than zero
                    // set position x and y of the winning player
                    // update the map
            // when no obstacle exists and move equals max
                // set position x and y
                // update the map
        return "todo";
    }

    @Override
    public String run() {
        return null;
    }

    @Override
    public double defend() {
        return 0;
    }

    @Override
    public double attack() {
        return Math.random() * getStrength();
    }

    public static double getARandom(double _max) {
        return Math.random() * _max;
    }
}
