public class Goblin extends Player {

    public final char marker = 'G';

    Goblin() {super.setObjectId();}

    public char getMarker() {
        return marker;
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
                "id=" + super.getObjectId() +
                "marker=" + marker +
                "strength="+ super.getStrength() +
                "health=" + super.getHealth() +
                "defense=" + super.getHealth() +
                "row position=" + super.getCoordinateX() +
                "col position=" + super.getCoordinateY() + '}';
    }
}
