public class Human extends Specie {

    public final char marker = 'H';

    Human() {}

    public char getMarker() {
        return marker;
    }

    public double factor() {
        return 1.32;
    }

    @Override
    public double attack() {
        return (Math.random() * this.getStrength()) * factor();
    }


}
