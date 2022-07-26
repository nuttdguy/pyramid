package character;

import main.GamePanel;
import main.KeyHandler;

public class Goblin extends Player {

    public Goblin() {
        super();
        setMaxHealth(getHealth());
        this.setMovesPerTurn(2);
        this.setMarker('G');
    }

    public Goblin(char marker, double health, double strength, double defense, int movesPerTurn, int coordX, int coordY) {
        super(marker, health, strength, defense, movesPerTurn, coordX, coordY);
    }

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
                ", row position=" + this.getCoordX() +
                ", col position=" + this.getCoordY() + '}';
    }
}
