import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Human extends Specie {

    public final char marker = 'H';
    private int movesRemaining;
    private double maxHealth;

    Human() {
        setMovesRemaining(super.getMovePerTurn());
        this.maxHealth = super.getHealth();
    }
    public char getMarker() {
        return marker;
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
        return movesRemaining;
    }

    public void setMovesRemaining(int movesRemaining) {
        this.movesRemaining = movesRemaining;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

}
