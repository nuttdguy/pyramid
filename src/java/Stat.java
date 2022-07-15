public interface Stat {

    double getHealth();
    double getStrength();
    double getDefense();
    int getMovesPerTurn();

    void setHealth(double _health);
    void setStrength(double _strength);
    void setDefense(double _defense);
    void setMovesPerTurn(int _moveDistance);

    double getMaxHealth();
    void setMaxHealth(double maxHealth);

    char getMarker();
    void setMarker(char marker);
}
