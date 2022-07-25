package character;
import java.text.DecimalFormat;

public class Human extends Player {

    public Human() {
        super();
        setMovesPerTurn(2);
        setOrDecrementMovesRemaining();
        setHealth(12);
        setMaxHealth(getHealth());
        setMarker('H');
    }

    public Human(char marker, double health, double strength, double defense, int movesPerTurn, int coordX, int coordY) {
        super(marker, health, strength, defense, movesPerTurn, coordX, coordY);
    }

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
                ", row position=" + this.getCoordX() +
                ", col position=" + this.getCoordY() + '}';
    }

}
