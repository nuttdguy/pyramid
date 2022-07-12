import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;

public class Game {

    enum PlayerType {
        GOBLIN, HUMAN
    }
    enum StatType {
        HEALTH, STRENGTH, DEFENSE, MOVE_DISTANCE, GOBLINS
    }

    private Land land;
    private ArrayList<Human> humans;
    private ArrayList<Goblin> goblins;

    Game() {
        land = new Land();
        goblins = this.playerFactory(PlayerType.GOBLIN, 5);
        humans = this.playerFactory(PlayerType.HUMAN, 1);
        updateStatOf(humans.get(0), StatType.HEALTH);
        updateStatOf(humans.get(0), StatType.STRENGTH);
        updateStatOf(humans.get(0), StatType.DEFENSE);
        updateStatOf(humans.get(0), StatType.MOVE_DISTANCE);
        updateStatOf(humans.get(0), StatType.GOBLINS);
        setPlayers(goblins, land);
        setPlayers(humans, land);

    }

    protected void updateStatOf(Human player, Enum type) {
        int col = this.land.colWidth() - this.land.colOffset() + 3;
        int row = type.equals(StatType.HEALTH) ? 2 :
                type.equals(StatType.STRENGTH) ? 4 :
                type.equals(StatType.DEFENSE) ? 6 :
                type.equals(StatType.MOVE_DISTANCE) ? 8 : 10;

        String stat = type.equals(StatType.HEALTH) ? String.valueOf(player.getHealth()) :
                type.equals(StatType.STRENGTH) ? String.valueOf(player.getStrength()) :
                type.equals(StatType.DEFENSE) ? String.valueOf(player.getDefense()) :
                type.equals(StatType.MOVE_DISTANCE) ? String.valueOf(player.getMovePerTurn()) : null;

        if (stat != null) {
            updateStatOfHelper(col, row, stat);
        } else {
            updateStatOfHelper(col, row, String.valueOf(getGoblins().size()));
        }
    }
    private void updateStatOfHelper(int col, int row, String stat) {
        int i = 0;
        for (char s : stat.toCharArray()) {
            this.land.setElementPosition(row,col + i, s);
            i++;
        }
    }
    protected <T extends Specie> void setPlayers(ArrayList<T> T,Land land) {
        int col, row, elementInRowCol,
                rowBoundary = land.rowHeight(),
                colBoundary = land.colWidth() - land.colOffset();
        if (T.size() == 0) { return; }

        int playerIndex = 0;
        while(playerIndex < T.size()) {

            col = Land.getRandomWithin(colBoundary); // get the current player
            row = Land.getRandomWithin(rowBoundary); // get random x and y
            elementInRowCol = land.getElementAtPosition(row, col); // get current element at pos

            // determine which class
            String tClass = T.get(playerIndex).getClass().getName();

            // try, set player at location if empty
            if (elementInRowCol == ' ') {
                if (tClass.equals("Human")) {
                    land.setElementPosition(row, col, ((Human) T.get(playerIndex)).getMarker() );
                    T.get(playerIndex).setCoordinate(row, col );
                } else if (tClass.equals("Goblin")) {
                    land.setElementPosition(row, col, ((Goblin) T.get(playerIndex)).getMarker() );
                    T.get(playerIndex).setCoordinate(row, col);
                }
                playerIndex++;
            }

        }

    }
    protected <T extends Specie> ArrayList<T> playerFactory(Enum playerType, int qty) {
        ArrayList<T> players = new ArrayList<>();
        while (qty > 0) {
            if (PlayerType.GOBLIN.equals(playerType)) {
                players.add((T) new Goblin());
            } else if (PlayerType.HUMAN.equals(playerType)) {
                players.add((T) new Human());
            }
            qty--;
        }
        return players;
    }
    protected char keyPress() {
        return new Scanner(in).next().charAt(0);
    }

    protected boolean moveOnePlayer(Human player) {
        int movesLeft = player.getMovePerTurn();
        char action;
        do {
            // prompt player for action, i.e. walk or defend
            // when walk, loop until move max or enemy is encountered
            out.println("Would you like to m (move) or d (defend)?");
            action = keyPress();
            if (action == 'm') {
                out.println("Do you want to move n, e, s oe w?");
                char direction = keyPress();
                if (direction == 'n' || direction == 'e' || direction == 's' || direction == 'w') {
                    try {
                        // check boundary here
                        int row = player.getCoordinates()[0], col = player.getCoordinates()[1];
                        if (isPlayerMoveWithinGameBounds(row, col)) {
                            char elementAtPlayerXy = land.getElementAtPosition(row, col - 1);
                            if (elementAtPlayerXy == 'G') {
                                // combat
                            } else {
                                handleOnePlayerMove(player, direction, row, col);
                                out.println(land.displayTheHeader());
                                out.println(land.displayTheGrid(land.getGrid()));
                                movesLeft--;
                                out.println("You moved 1 space " + direction + ". You have " + movesLeft + " moves left.");
                                if (movesLeft == 0) { action = 'd'; }
                            }

                        } else {
                            out.println("You cannot move in that direction");
                        }

                    } catch (IndexOutOfBoundsException e) {
                        out.println(e);
                    }

                } else {
                    out.println("Invalid option " + direction + ". Try again?");
                }
            }

        } while (movesLeft > 0 || action != 'd' );

        return true;
    }
    protected boolean isPlayerMoveWithinGameBounds(int row, int col) {
        return (row > 0 && row < land.rowHeight() - 1) && (col > 0 && col < land.colWidth() - land.colOffset() - 1);
    }

    protected Human handleOnePlayerMove(Human player, char direction, int row, int col) throws IndexOutOfBoundsException {
        land.setElementPosition(row, col, land.defaultMarker()); // set the default marker before updating move
        if (direction == 'n') {
            row -= 1;
        } else if (direction == 's') {
            row += 1;
        } else if (direction == 'w') {
            col -= 1;
        } else if (direction == 'e') {
            col += 1;
        }
        land.setElementPosition(row, col, player.getMarker());
        player.setCoordinate(row, col);
        return player;
    }

    public void start() {

        moveOnePlayer(this.humans.get(0));

    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public ArrayList<Human> getHumans() {
        return humans;
    }

    public void setHumans(ArrayList<Human> humans) {
        this.humans = humans;
    }

    public ArrayList<Goblin> getGoblins() {
        return goblins;
    }

    public void setGoblins(ArrayList<Goblin> goblins) {
        this.goblins = goblins;
    }

}
