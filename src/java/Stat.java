public interface Stat {

    double getHealth();
    double getStrength();
    double getDefense();
    int getMoveDistance();

    void setHealth(double _health);
    void setStrength(double _strength);
    void setDefense(double _defense);
    void setMoveDistance(int _moveDistance);

}
