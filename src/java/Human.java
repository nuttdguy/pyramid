import java.text.DecimalFormat;

public class Human extends Player {

    Human() {
        this.setObjectId();
        this.setMovesPerTurn(1);
        this.setHealth(20);
        this.setMaxHealth(this.getHealth());
        this.setMarker('H');
        this.setMovesRemaining(this.getMovesPerTurn());
    }

    public double factor() { return 1.32;}
    public double regenerateHealth() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(super.getHealth() + (Math.random() * 0.3)) );
    }

    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * factor();
    }
    @Override
    public String toString() {
        return "Human{" +
                "id=" + this.getObjectId() +
                ", marker=" + this.getMarker() +
                ", strength="+ this.getStrength() +
                ", health=" + this.getHealth() +
                ", defense=" + this.getHealth() +
                ", movesRemaining=" + this.getMovesRemaining() +
                ", maxHealth=" + this.getMaxHealth() +
                ", row position=" + this.getCoordinateX() +
                ", col position=" + this.getCoordinateY() + '}';
    }

}
