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
                type.equals(StatType.MOVE_DISTANCE) ? String.valueOf(player.getMoveDistance()) : null;

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
        int movesLeft = player.getMoveDistance();
        char action;
        do {
            // prompt player for action, i.e. walk or defend
            // when walk, loop until move max or enemy is encountered
            out.println("Would you like to m (move) or d (defend)?");
            action = keyPress();
            if (action == 'm') {
                out.println("Do you want to move n, e, s oe w?");
                char key = keyPress();
                if (key == 'n' || key == 'e' || key == 's' || key == 'w') {
                    try {
                        handleOnePlayerMove(player, key);
                        movesLeft--;
                    } catch (IndexOutOfBoundsException e) {
                        out.println(e);
                    }

                } else {
                    out.println("Invalid option " + key + ". Try again?");
                }
            }

        } while (action != 'd' || movesLeft > 0 );

        return true;
    }

    protected void handleOnePlayerMove(Human player, char direction) throws IndexOutOfBoundsException {
        int row = player.getCoordinates()[0], col = player.getCoordinates()[1];
        int colBoundary = land.colWidth();
        int rowBoundary = land.rowHeight();
        char elementAtPlayerXy;
        if (direction == 'n') {
            // if index does not work, move fails, return for user input
            elementAtPlayerXy = land.getElementAtPosition(row, col - 1);
            if (elementAtPlayerXy == 'G') {
                // enter battle
            } else {
                // extract into own helper func to check boundary
                if ((row > 0 && row < rowBoundary) && (col > 0 && col < colBoundary)) {
                    // update last marker with default and new marker with human marker
                    land.setElementPosition(row, col, land.defaultMarker());
                    land.setElementPosition(row-1, col, player.getMarker());
                    player.setCoordinate(row-1, col);
                } else {
                    out.println("You cannot move in that direction");
                }
                out.println(land.displayTheHeader());
                out.println(land.displayTheGrid(land.getGrid()));
            }
        }
        out.println("You moved 1 space " + direction);
        // on each keypress, move player 1 space
        // get current xy pos, then if left, x-1, right x+1, north=y+1, south y-1
        // check if grid has enemy
        // when enemy, engage combat
        // when not enemy, update map, prompt for next action

        // when defend, engage in replenish health
        // end turn

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
