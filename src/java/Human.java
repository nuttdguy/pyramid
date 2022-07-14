import java.text.DecimalFormat;

public class Human extends Player {

    public final char marker = 'H';
    private int movesRemaining;
    private double maxHealth;

    Human() {
        setMovesRemaining(super.getMovesPerTurn());
        this.maxHealth = super.getHealth();
    }
    public char getMarker() {
        return this.marker;
    }

    public double factor() { return 1.32;}

    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * factor();
    }

    public double regenerateHealth() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(super.getHealth() + (Math.random() * 0.3)) );
    }

    public int getMovesRemaining() {
        return this.movesRemaining;
    }

    public void setMovesRemaining(int movesRemaining) {
        this.movesRemaining = movesRemaining;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }
    @Override
    public String toString() {
        return "Human{" +
                "id=" + super.getObjectId() +
                "marker=" + marker +
                "strength="+ super.getStrength() +
                "health=" + super.getHealth() +
                "defense=" + super.getHealth() +
                "movesRemaining=" + movesRemaining +
                "maxHealth=" + maxHealth +
                "row position=" + super.getCoordinateX() +
                "col position=" + super.getCoordinateY() + '}';
    }
}
