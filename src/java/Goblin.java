public class Goblin extends Player {

    Goblin() {
        this.setObjectId();
        this.setMovesPerTurn(2);
        this.setMarker('G');
        this.setMovesRemaining(this.getMovesPerTurn());
    }

    public double factor() {
        return Math.random() * 1;
    }
    @Override
    public double attack() {
        return (Math.random() * factor()) * this.getStrength();
    }
    @Override
    public String toString() {
        return "Goblin{" +
                "id=" + this.getObjectId() +
                ", marker=" + this.getMarker() +
                ", strength="+ this.getStrength() +
                ", health=" + this.getHealth() +
                ", defense=" + this.getDefense() +
                ", movesRemaining=" + this.getMovesRemaining() +
                ", row position=" + this.getCoordinateX() +
                ", col position=" + this.getCoordinateY() + '}';
    }
}
