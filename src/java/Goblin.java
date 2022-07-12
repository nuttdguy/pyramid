public class Goblin extends Specie {

    public final char marker = 'G';

    Goblin() {}

    public char getMarker() {
        return marker;
    }
    public double factor() {
        return 0.33;
    }

    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * factor();
    }
}
