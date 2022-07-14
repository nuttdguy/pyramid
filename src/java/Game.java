import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.*;

public class Game {

    enum PlayerType {
        GOBLIN, HUMAN
    }
    enum StatType {
        HEALTH, STRENGTH, DEFENSE, MOVE_DISTANCE, GOBLINS, MOVES_LEFT
    }

    private Land land;
    private ArrayList<Human> humans;
    private ArrayList<Goblin> goblins;

    Game() {
        land = new Land();
        goblins = this.initializePlayers(PlayerType.GOBLIN, 2);
        humans = this.initializePlayers(PlayerType.HUMAN, 1);
        updateStatOf(humans.get(0), StatType.HEALTH);
        updateStatOf(humans.get(0), StatType.STRENGTH);
        updateStatOf(humans.get(0), StatType.DEFENSE);
        updateStatOf(humans.get(0), StatType.MOVE_DISTANCE);
        updateStatOf(humans.get(0), StatType.GOBLINS);
        setPlayers(goblins, land);
        setPlayers(humans, land);

    }
    public static HashMap<String, Integer> setQtyOfHumanGoblinPlayers(Enum playerType, Integer qty) {
        HashMap<String, Integer> type =  new HashMap<>();
        type.put(playerType.name(), qty);
        return type;
    }
    private char keyPress() {
        return new Scanner(in).next().charAt(0);
    }
    protected void displayGameBoard() {
        out.println(land.displayTheHeader());
        out.println(land.displayTheGrid(land.getGrid()));
    }
    private void updateStatOf(Human player, Enum type) {
        int col = this.land.colWidth() - this.land.colOffset() + 3;
        // determine the row and col to set the value on
        int row = type.equals(StatType.HEALTH) ? 2 :
                type.equals(StatType.STRENGTH) ? 4 :
                type.equals(StatType.DEFENSE) ? 6 :
                type.equals(StatType.MOVE_DISTANCE) ? 8 :
                type.equals(StatType.MOVES_LEFT) ? 8 : 10;

        String stat = type.equals(StatType.HEALTH) ? String.valueOf(player.getHealth()) :
                type.equals(StatType.STRENGTH) ? String.valueOf(player.getStrength()) :
                type.equals(StatType.DEFENSE) ? String.valueOf(player.getDefense()) :
                type.equals(StatType.MOVE_DISTANCE) ? String.valueOf(player.getMovesPerTurn()) :
                type.equals(StatType.MOVES_LEFT) ? String.valueOf(player.getMovesRemaining()) : null;

        if (stat != null) {
            updateStatOfHelper(col, row, stat);
        } else {
            // handle qty of goblins on the field
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
    private <T extends Player> void setPlayers(ArrayList<T> T, Land land) {
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
    private <T extends Player> ArrayList<T> initializePlayers(Enum playerType, int qty) {
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
    protected <T extends Player> T retrievePlayer(ArrayList<T> t, int row, int col) {
        try {
            for (Player g : t) {
                if (g.getCoordinateX() == row && g.getCoordinateY() == col) {
                    return (T) g;
                }
            }
        } catch (ClassCastException e ) {
            out.println(e);
        }
        return null;
    }
    protected <T extends Player> ArrayList<T> removePlayer(ArrayList <T> t, T player) {
        if (player != null && player.getHealth() <= 0) {
            t.remove(player);
        }
        return t;
    }
    protected <T extends Player> T updateMapMarkerOf(T player, int nextRow, int nextCol, int oldRow, int oldCol) {
        String tClass = player.getClass().getName();
        if (tClass.equals("Human")) {
            land.setElementPosition(nextRow, nextCol, ((Human) player).getMarker());
            land.setElementPosition(oldRow, oldCol, land.defaultMarker());
            player.setCoordinate(nextRow, nextCol);
            return player;
        }

        // add goblin implementation

        return player;
    }
    protected Human moveOnePlayer(char direction, int row, int col, Human player) throws IndexOutOfBoundsException {
        land.setElementPosition(row, col, land.defaultMarker()); // set the default marker before updating move
        // set the element values of the new position
        int[] rowCol = rowColToMoveOnto(direction, row, col);
        land.setElementPosition(rowCol[0], rowCol[1], player.getMarker());
        player.setCoordinate(rowCol[0], rowCol[1]);
        return player;
    }
    private int[] rowColToMoveOnto(char direction, int row, int col) {
        if (direction == 'n') {
            row -= 1;
        } else if (direction == 's') {
            row += 1;
        } else if (direction == 'w') {
            col -= 1;
        } else if (direction == 'e') {
            col += 1;
        }
        return new int[]{row, col};
    }
    protected Human replenishHealthOf(Human player) {
        if (player.getHealth() < player.getMaxHealth()) {
            double health = player.regenerateHealth();
            player.setHealth(health > player.getMaxHealth() ? 10 : health );
        }
        return player;
    }
    private void updateMoveStats(Human player) {
        updateStatOf(player, StatType.HEALTH);
        updateStatOf(player, StatType.MOVES_LEFT);
        displayGameBoard();
        out.println("You have " + player.getMovesRemaining() + " moves left.");
    }
    protected Human handleEncounterWithGoblin(char chosenDirection, int row, int col, Human player) {
        int[] rowCol = rowColToMoveOnto(chosenDirection, row, col);
        Goblin goblin = retrievePlayer(this.getGoblins(), rowCol[0], rowCol[1]);
        if (goblin != null) {
            player = engageCombatBetween(player, goblin);
        }
        this.goblins = removePlayer(this.getGoblins(), goblin);
        if (this.goblins.size() == 0) {
            return null; // end the game
        }

        // update player and stats
        player = updateMapMarkerOf(player, rowCol[0], rowCol[1], row, col);
        updateStatOf(player, StatType.HEALTH);
        updateStatOf(player, StatType.GOBLINS);
        return player;
    }
    private char getMapElementAt(char chosenDirection, int row, int col) {
        int[] rowCol = rowColToMoveOnto(chosenDirection, row, col);
        return land.getElementAtPosition(rowCol[0], rowCol[1]);
    }
    private int handleMovesLeft(int movesLeft, Human player) {
        movesLeft--;
        if (movesLeft > 0 ) {
            player.setMovesRemaining(movesLeft);
            replenishHealthOf(player);
            updateMoveStats(player);
            return movesLeft;
        }
        updateMoveStats(player);
        player.setMovesRemaining(player.getMovesPerTurn()); // reset move to max move
        return -1;
    }
    private void handleMoveOutOfBound() {
        out.println("You cannot move in that direction");
    }
    protected <T extends Player> T engageCombatBetween(Human human, Goblin goblin) {

        double goblinHealth = goblin.getHealth();
        double humanHealth = human.getHealth();
        DecimalFormat df = new DecimalFormat("0.00");

        while (goblinHealth >= 0 && humanHealth >= 0) {
            double humanAttack = human.attack();
            double goblinAttack = goblin.attack();

            goblinHealth = goblinHealth - humanAttack;
            goblin.setHealth(Double.parseDouble(df.format(goblinHealth)));
            out.println(String.format("Player attack's Goblin for [ %.2f ]", humanAttack));
            if (goblinHealth <= 0) {
                out.println(String.format("Goblin's  [ health ] is now [ %.2f ]", 0.00));
                out.println(String.format("Victory! Player's [ health ] is now [ %.2f ]", human.getHealth()));
                out.println("----");
                return (T) human;
            } else {
                out.println(String.format("Goblin's [ health ] is now [ %.2f ]", goblin.getHealth()));
            }

            out.println("----");
            humanHealth = humanHealth - goblinAttack;
            human.setHealth(Double.parseDouble(df.format(humanHealth)));
            out.println(String.format("Goblin attack's Player for [ %.2f ]", goblinAttack));

            if (humanHealth <= 0) {
                out.println(String.format("Defeat! Player's [ health ] is now [ %.2f ]", 0.00));
                out.println("----");
            } else {
                out.println(String.format("Player's [ health ] is now [ %.2f ]", human.getHealth()));
                out.println("----");
            }
        }

        return (T) human; // should remove return since objects are reference types
    }
    protected <T extends Player> T moveOnePlayer(Human player) {
        int movesLeft = player.getMovesPerTurn();
        char chosenAction;
        do {

            out.println("Would you like to m (move) or d (defend)?");
            chosenAction = keyPress();
            if (chosenAction == 'm') {

                out.println("Do you want to move n, e, s or w?");
                char chosenDirection = keyPress();
                if (chosenDirection == 'n' || chosenDirection == 'e' || chosenDirection == 's' || chosenDirection == 'w') {

                    // todo catch multi characters entries
                    try {
                        int row = player.getCoordinates()[0], col = player.getCoordinates()[1];
                        char rowColElement = getMapElementAt(chosenDirection, row, col);

                        switch(rowColElement) {
                            case 'G' -> handleEncounterWithGoblin(chosenDirection, row, col, player);
                            case ' ' -> moveOnePlayer(chosenDirection, row, col, player);
                            default -> out.println("There was an uncaught character...");
                        }

                        // handle out of bounds and don't decrement move counter
                        if (rowColElement == '+') {
                            handleMoveOutOfBound();
                        } else {
                            movesLeft = handleMovesLeft(movesLeft, player);
                            if (movesLeft <= 0) {
                                return (T) player;
                            } // end turn when no moves are left
                        }

                    } catch (IndexOutOfBoundsException e) {
                        out.println(e);
                    }

                } else {
                    out.println("Invalid option " + chosenDirection + ". Try again?");
                }
            }

        } while (movesLeft > 0 || chosenAction != 'd' );

        return (T) player;
    }

    protected <T extends Player> T handleTurnOf(ArrayList<T> t, Human player) {
        // get the human players position
        // move goblins towards human position
            // calc distance to human position, then move the shortest path
                // when goblin is w or e, move +1 or -1
                // when goblin is n or s, move +1 or -1
                //
        return null;
    }

    public void start() {

        // loop game until goblins or humans have been defeated
        boolean continueGame = true;
        while(continueGame) {
            try {
                Human player = this.humans.get(0);
                player = moveOnePlayer(player);

                if ( this.goblins.size() == 0) {
                    out.println("END OF GAME - YOU DEFEATED ALL THE GOBLINS!");
                    continueGame = false;
                } else {
                    // todo move enemies position towards player
                    out.println("END OF TURN. ");
                    out.println("GOBLINS WILL MOVE NOW. ");
                    // handle goblin moves

                }

            } catch (IndexOutOfBoundsException e) {
                out.println(e);
                break;
            }
        }

    }

    public Land getLand() {
        return land;
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
