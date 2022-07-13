public class Goblin extends Specie {

    public final char marker = 'G';

    Goblin() {}

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
}
