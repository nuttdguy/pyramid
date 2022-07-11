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
    public int getMoveDistance() {
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
    public int[] getCoordinatesXY() {
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
        // todo implement
        // while health of player1 or player 2 not less than or equal to zero
            // get player1 attack damage using math.random * strength
                // debit health of player2 (player1 attack - player2 defense)
                // display combat narrative
                // update player2 stats
                // if player2 health is not zero
                    // get player2 attack damage using math.random * strength
                        // debit health of player1 by player 2 attack - player1 defense
                        // display combat narrative
                        // update player1 stats
                        // if player2 health is not zero
                            // return to top
                // if player1 or player2 health is zero
                    // return the winning player
                    // update position xy of player
        return 2.3;
    }

    public static double getARandom(double _max) {
        return Math.random() * _max;
    }
}
