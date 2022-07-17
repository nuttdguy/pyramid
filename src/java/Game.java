import java.text.DecimalFormat;
import java.util.*;

import static java.lang.System.*;

public class Game {

    enum PlayerType {
        GOBLIN, HUMAN
    }
    enum StatType {
        HEALTH, STRENGTH, DEFENSE, MOVE_DISTANCE, GOBLINS, MOVES_LEFT
    }
    private Map map;
    private ArrayList<Human> humans;
    private ArrayList<Goblin> goblins;

    Game() {
        setMap(new Map());
        setGoblins(this.initializePlayers(PlayerType.GOBLIN, 8));
        setHumans(this.initializePlayers(PlayerType.HUMAN, 1));
        updateStatOf(StatType.HEALTH, this.getHumans().get(0));
        updateStatOf(StatType.STRENGTH, this.getHumans().get(0));
        updateStatOf(StatType.DEFENSE, this.getHumans().get(0));
        updateStatOf(StatType.MOVE_DISTANCE, this.getHumans().get(0));
        updateStatOf(StatType.GOBLINS, this.getHumans().get(0));
        placePlayersOntoLand(this.getGoblins(), map);
        placePlayersOntoLand(this.getHumans(), map);

    }

    private char keyPress() {
        return new Scanner(in).next().charAt(0);
    }
    protected void displayGameBoard() {
        out.println(map.displayTheHeader());
        out.println(map.displayTheGrid(map.getGrid()));
    }
    private void updateStatOf(StatType type) {
        this.updateStatOf(type, null);
    }
    private void updateStatAll(Human player) {
        for (StatType stat : StatType.values()) {
            updateStatOf(stat, player);
        }
    }
    private void updateStatOf(StatType type, Human player) {
        int col = this.map.colWidth() - this.map.colOffset() + 3;
        int row = 10; // set default row to goblin count
        String stat = null;
        // determine the row and col to set the value on
        if (player != null) {
            row = type.equals(StatType.HEALTH) ? 2 :
                    type.equals(StatType.STRENGTH) ? 4 :
                            type.equals(StatType.DEFENSE) ? 6 :
                                    type.equals(StatType.MOVE_DISTANCE) ? 8 :
                                            type.equals(StatType.MOVES_LEFT) ? 8 : 10;

            stat = type.equals(StatType.HEALTH) ? String.valueOf(player.getHealth()) :
                    type.equals(StatType.STRENGTH) ? String.valueOf(player.getStrength()) :
                            type.equals(StatType.DEFENSE) ? String.valueOf(player.getDefense()) :
                                    type.equals(StatType.MOVE_DISTANCE) ? String.valueOf(player.getMovesPerTurn()) :
                                            type.equals(StatType.MOVES_LEFT) ? String.valueOf(player.getMovesRemaining()) : null;
        }

        if (stat != null) {
            updateStatOfHelper(col, row, stat);
        } else {
            // handle qty of goblins on the field
            updateStatOfHelper(col, row, String.valueOf(this.getGoblins().size()));
        }
    }
    private void updateStatOfHelper(int col, int row, String stat) {
        int i = 0;
        for (char s : stat.toCharArray()) {
            this.map.setElementPosition(row,col + i, s);
            i++;
        }
    }
    private <T extends Player> void placePlayersOntoLand(ArrayList<T> T, Map map) {
        int col, row, elementInRowCol,
                rowBoundary = map.rowHeight(),
                colBoundary = map.colWidth() - map.colOffset();
        if (T.size() == 0) { return; }

        int playerIndex = 0;
        while(playerIndex < T.size()) {

            col = Map.getRandomWithin(colBoundary); // get the current player
            row = Map.getRandomWithin(rowBoundary); // get random x and y
            elementInRowCol = map.getElementAtPosition(row, col); // get current element at pos

            // determine which class
            String tClass = T.get(playerIndex).getClass().getName();

            // try, set player at location if empty
            if (elementInRowCol == ' ') {
                if (tClass.equals("Human")) {
                    map.setElementPosition(row, col, T.get(playerIndex).getMarker() );
                    T.get(playerIndex).setCoordinate(row, col );
                } else if (tClass.equals("Goblin")) {
                    map.setElementPosition(row, col, T.get(playerIndex).getMarker() );
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
            for (T g : t) {
                if (g.getCoordinateX() == row && g.getCoordinateY() == col) {
                    return g;
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
            map.setElementPosition(nextRow, nextCol, player.getMarker());
            map.setElementPosition(oldRow, oldCol, map.defaultMarker());
            player.setCoordinate(nextRow, nextCol);
            return player;
        }

        return player;
    }
    protected <T extends Player> T moveOnePlayer(Human player) {
        int movesLeft = player.getMovesPerTurn();
//        char chosenAction;
        do {

//            out.println("Would you like to m (move)?");
//            chosenAction = keyPress();
//            if (chosenAction == 'd') {
//                replenishHealthOf(player);
//                displayGameBoard();
//                movesLeft--;
//            } else
//            if (chosenAction == 'm') {

            out.println("Do you want to move n, e, s or w?");
            char chosenDirection = keyPress();
            if (chosenDirection == 'n' || chosenDirection == 'e' || chosenDirection == 's' || chosenDirection == 'w') {

                // todo catch multi characters entries
                try {
                    int row = player.getCoordinates()[0], col = player.getCoordinates()[1];
                    char rowColElement = getMapElementAt(chosenDirection, row, col);

                    switch (rowColElement) {
                        case 'G' -> handleEncounterWithGoblin(chosenDirection, row, col, player);
                        case ' ' -> moveOnePlayer(chosenDirection, row, col, player);
                    }

                    // handle out of bounds and don't decrement move counter
                    if (rowColElement == '+') {
                        handleMoveOutOfBound();
                    } else {
                        movesLeft = handleMovesLeft(movesLeft, player);
                        if (movesLeft <= 0) {
                            updateStatAll(player);
                            return (T) player;
                        } // end turn when no moves are left
                    }

                } catch (IndexOutOfBoundsException e) {
                    out.println(e);
                }

            } else {
                out.println("Invalid option " + chosenDirection + ". Try again?");
            }
        } while (true);
//            }

//        } while (movesLeft > 0 || chosenAction != 'd' );

//        return (T) player;
    }
    protected <T extends Player> T moveOnePlayer(char direction, int row, int col, T player) throws IndexOutOfBoundsException {
        map.setElementPosition(row, col, map.defaultMarker()); // set the default marker before updating move

        // set the element values of the new position
        int[] rowCol = rowColToMoveOnto(direction, row, col);
        map.setElementPosition(rowCol[0], rowCol[1], player.getMarker());
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
    protected void replenishHealthOf(Human player) {
        if (player.getHealth() < player.getMaxHealth() && player.getHealth() > 0) {

            double health = player.regenerateHealth();
            double newHealth = Double.parseDouble(String.valueOf(health + player.getHealth()));
            player.setHealth(health < player.getMaxHealth() ? newHealth : player.getMaxHealth());
            out.printf("Your health regenerated by %.2f%n", health);
            out.println();
            updateStatOf(StatType.HEALTH, player);
        }
    }
    private void updateMoveStats(Human player) {
        updateStatOf(StatType.HEALTH, player);
        updateStatOf(StatType.MOVES_LEFT, player);
        displayGameBoard();
        out.println("You have " + player.getMovesRemaining() + " moves left.");
    }
    protected Human handleEncounterWithGoblin(char chosenDirection, int row, int col, Human player) {
        int[] rowCol = rowColToMoveOnto(chosenDirection, row, col);
        Goblin goblin = retrievePlayer(this.getGoblins(), rowCol[0], rowCol[1]);
        if (goblin != null) {
            engageCombatBetween(player, goblin);
        }
        this.goblins = removePlayer(this.getGoblins(), goblin);
        if (this.goblins.size() == 0) {
            return null; // end the game
        }

        // update player and stats
        player = updateMapMarkerOf(player, rowCol[0], rowCol[1], row, col);
        updateStatOf(StatType.HEALTH, player);
        updateStatOf(StatType.GOBLINS, player);
        return player;
    }
    private char getMapElementAt(char chosenDirection, int row, int col) {
        int[] rowCol = rowColToMoveOnto(chosenDirection, row, col);
        return map.getElementAtPosition(rowCol[0], rowCol[1]);
    }
    private int handleMovesLeft(int movesLeft, Human player) {
        movesLeft--;
        player.setMovesRemaining(movesLeft);
        updateMoveStats(player);
        if (movesLeft == 0) {
            player.setMovesRemaining(player.getMovesPerTurn()); // reset move to max move
        }
        return movesLeft;
    }
    private void handleMoveOutOfBound() {
        out.println("You cannot move in that direction");
    }
    private void displayCombatNarrative(int option, String attacker, String defender, double stat) {
        stat = stat < 0 ? 0 : stat;
        switch (option) {
            case 1 -> out.printf("%s attack's %s for [ %.2f ]%n", attacker, defender, stat);
            case 2 -> out.printf("%s [ health ] is now [ %.2f ]%n", defender, stat);
            case 3 -> out.printf("%s [ health ] is now [ %.2f ]%n", attacker, stat);
            case 4 -> out.printf("%s defeats %s. %s [ health ] is now [ %.2f ]%n", attacker, defender, attacker, stat);
        }
    }
    protected <T extends Player> void engageCombatBetween(T attacker, T defender) {

        double defenderHealth = defender.getHealth();
        double attackerHealth = attacker.getHealth();

        while (true) {
            double damageByAttacker = attacker.attack();
            double damageByDefender = defender.attack();

            defenderHealth = defenderHealth - damageByAttacker;
            if (defenderHealth <= 0) {
                defender.setHealth(Double.parseDouble(String.format("%.2f", defenderHealth)));
                displayCombatNarrative(1, attacker.getClass().getName(), defender.getClass().getName(), damageByAttacker);
                displayCombatNarrative(2, attacker.getClass().getName(), defender.getClass().getName(), defender.getHealth());
                displayCombatNarrative(4, attacker.getClass().getName(), defender.getClass().getName(), attacker.getHealth());
                out.println("----");
                return;
            } else {
                defender.setHealth(Double.parseDouble(String.format("%.2f", defenderHealth)));
                displayCombatNarrative(1, attacker.getClass().getName(), defender.getClass().getName(), damageByAttacker);
                displayCombatNarrative(2, attacker.getClass().getName(), defender.getClass().getName(), defender.getHealth());
            }

            out.println("----");
            attackerHealth = attackerHealth - damageByDefender;
            if (attackerHealth <= 0) {
                attacker.setHealth(Double.parseDouble(String.format("%.2f", attackerHealth)));
                displayCombatNarrative(1, defender.getClass().getName(), attacker.getClass().getName(), damageByDefender);
                displayCombatNarrative(2, defender.getClass().getName(), attacker.getClass().getName(), attacker.getHealth());
                displayCombatNarrative(4, defender.getClass().getName(), attacker.getClass().getName(), defender.getHealth());
                out.println("----");
                return;
            } else {
                attacker.setHealth(Double.parseDouble(String.format("%.2f", attackerHealth)));
                displayCombatNarrative(1, defender.getClass().getName(), attacker.getClass().getName(), damageByDefender);
                displayCombatNarrative(3, attacker.getClass().getName(), defender.getClass().getName(), attacker.getHealth());
                out.println("----");
            }
        }

    }
    private char[] directionToMove(int negOrPos) {
        return negOrPos > 0 ? new char[]{'n', 'w'} : new char[]{'s', 'e'};
    }
    protected <T extends Player> T handleTurnOf(ArrayList<T> t, Human player) {
        int[] rowColOfDefender = player.getCoordinates();
        ArrayList<T> goblins = new ArrayList<>( t.subList(0, t.size())); // make a copy to iterate

        for (T goblin : goblins) {
            int movesLeft = goblin.getMovesPerTurn();
            while (movesLeft > 0) {
                int[] rowColOfAttacker = goblin.getCoordinates();
                int rowDistanceToDefender = rowColOfAttacker[0] - rowColOfDefender[0];
                int colDistanceToDefender = rowColOfAttacker[1] - rowColOfDefender[1];

                char rowDirection = directionToMove(rowDistanceToDefender)[0];
                char colDirection = directionToMove(colDistanceToDefender)[1];

                // add collision detection of other goblins in path to take
                if (Math.abs(rowDistanceToDefender) >= Math.abs(colDistanceToDefender)) {
                    moveOnePlayer(rowDirection, rowColOfAttacker[0], rowColOfAttacker[1], goblin);
                    movesLeft--;
                } else if (Math.abs(rowDistanceToDefender) <= Math.abs(colDistanceToDefender)) {
                    moveOnePlayer(colDirection, rowColOfAttacker[0], rowColOfAttacker[1], goblin);
                    movesLeft--;
                }

                // if coordinates equal after move, engage combat
                rowColOfAttacker = goblin.getCoordinates();
                if ( Arrays.equals(rowColOfAttacker, rowColOfDefender))  {

                    engageCombatBetween(goblin, player);
                    if (goblin.getHealth() <= 0) {
                        removePlayer(t, goblin); // remove player from original
                        this.getMap().setElementPosition(player.getCoordinateX(), player.getCoordinateY(), player.getMarker());
                        updateStatOf(StatType.HEALTH, player);
                        updateStatOf(StatType.GOBLINS);
                        movesLeft = 0;
                    } else if (player.getHealth() <= 0) {
                        updateStatOf(StatType.HEALTH, player);
                        return (T) player;
                    }
                }
            }
//            out.println(goblin.toString());
        }
        displayGameBoard();
        return null;
    }

    public void start() {

        // refactor code by renaming methods, removing redundant code, modularizing / grouping methods by action
        // reduce side effects caused by referencing, i.e. not creating new copies
        // implement defend option in order to replenish heath
        // add collision detection logic to detect when obstacles are in the path
        // add inventory system to enhance characters stat
        // add many humans onto the map to combat many goblins
        // add treasure map to hande drops
        // improve starting stat mechanics
        while(true) {
            try {
                Human player = this.humans.get(0);

                if ( this.getGoblins().isEmpty() ) {
                    out.println("Victory - Human has defeated the Goblin's");
                    out.println("*** Game Over ***");
                    return;
                } else {
                    player = moveOnePlayer(player);
                    out.println("----");
                    out.println("Human - end of turn ");
                    out.println("----");
                    out.println("Goblin's turn - press and enter any letter to continue");
                    keyPress();
                    out.println("----");
                    // handle goblin moves
                    handleTurnOf(this.getGoblins(), player);
                    out.println("Goblin's - end of turn ");
                    out.println("----");
                    if (player.getHealth() < 0) {
                        out.println("Defeat - Goblin's defeated the human!");
                        out.println("*** Game Over ***");
                        return;
                    }
                    out.println("Human - its now your turn");
                }

            } catch (IndexOutOfBoundsException e) {
                out.println(e);
                break;
            }
        }

    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Human> getHumans() {
        return humans;
    }

    private void setHumans(ArrayList<Human> humans) {
        this.humans = humans;
    }

    public ArrayList<Goblin> getGoblins() {
        return goblins;
    }

    private void setGoblins(ArrayList<Goblin> goblins) {
        this.goblins = goblins;
    }

}
