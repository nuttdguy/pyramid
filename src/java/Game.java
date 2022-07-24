
import character.*;
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
        setGoblins(this.initPlayers(PlayerType.GOBLIN, 8));
        setHumans(this.initPlayers(PlayerType.HUMAN, 1));
        updateOneStat(StatType.HEALTH, this.getHumans().get(0));
        updateOneStat(StatType.STRENGTH, this.getHumans().get(0));
        updateOneStat(StatType.DEFENSE, this.getHumans().get(0));
        updateOneStat(StatType.MOVE_DISTANCE, this.getHumans().get(0));
        updateOneStat(StatType.GOBLINS, this.getHumans().get(0));
        setPlayerOnTheMap(this.getGoblins(), map);
        setPlayerOnTheMap(this.getHumans(), map);

    }



    private char keyPress() {
        return new Scanner(in).next().charAt(0);
    }
    protected void displayGameBoard() {
        out.println(map.displayTheHeader());
        out.println(map.displayTheGrid(map.getGrid()));
    }

    // SETUP

    private <T extends Player> void setPlayerOnTheMap(ArrayList<T> T, Map map) {
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
            String tClass = T.get(playerIndex).getName();

            // try, set player at location if empty
            if (elementInRowCol == ' ') {
                if (tClass.equals("Human")) {
                    map.setElementPosition(row, col, T.get(playerIndex).getMarker() );
                    T.get(playerIndex).setCoordinates(row, col );
                } else if (tClass.equals("Goblin")) {
                    map.setElementPosition(row, col, T.get(playerIndex).getMarker() );
                    T.get(playerIndex).setCoordinates(row, col);
                }
                playerIndex++;
            }

        }

    }
    private <T extends Player> ArrayList<T> initPlayers(PlayerType playerType, int qty) {
        ArrayList<T> players = new ArrayList<>();

        while (qty > 0) {
            try {
                switch (playerType) {
                    case GOBLIN -> players.add((T) new Goblin());
                    case HUMAN -> players.add((T) new Human());
                }
            } catch (ClassCastException e) {
                out.println(e);
            }
            qty--;
        }
        return players;
    }

    // STAT UPDATES

    private <T extends Player> void updateOneStat(StatType type, T player) {
        int col = this.map.colWidth() - this.map.colOffset() + 3;
        int row = 10; // set default row to goblin count
        String stat = "";

        // determine the row and col to set the value
        if (player != null) {
            row = rowToUpdate(type);
            stat = getStatValueToUpdate(type, player);
        }
        updateStatFields(col, row, stat);
    }
    private <T extends Player> String getStatValueToUpdate(StatType type, T player) {
        return  type.equals(StatType.HEALTH) ? String.valueOf(player.getHealth()) :
                type.equals(StatType.STRENGTH) ? String.valueOf(player.getStrength()) :
                type.equals(StatType.DEFENSE) ? String.valueOf(player.getDefense()) :
                type.equals(StatType.MOVE_DISTANCE) ? String.valueOf(player.getMovesPerTurn()) :
                type.equals(StatType.MOVES_LEFT) ? String.valueOf(player.getMovesRemaining()) :
                String.valueOf(getGoblins().size());
    }
    private <T extends Player> void updateAllStat(T player) {
        for (StatType stat : StatType.values()) {
            updateOneStat(stat, player);
        }
    }
    private int rowToUpdate(StatType type) {
        return type.equals(StatType.HEALTH) ? 2 :
                type.equals(StatType.STRENGTH) ? 4 :
                        type.equals(StatType.DEFENSE) ? 6 :
                                type.equals(StatType.MOVE_DISTANCE) ? 8 :
                                        type.equals(StatType.MOVES_LEFT) ? 8 :
                                                10;
    }
    private void updateStatFields(int col, int row, String stat) {
        int i = 0;
        for (char s : stat.toCharArray()) {
            this.map.setElementPosition(row,col + i, s);
            i++;
        }
    }

    // END - STAT UPDATES

    // MOVEMENT
    private void directionOutOfBound() {
        out.println("You cannot move in that direction");
    }
    private char[] directionToMove(int negOrPos) {
        return negOrPos > 0 ? new char[]{'n', 'w'} : new char[]{'s', 'e'};
    }
    protected <T extends Player> T moveOnePlayer(Human player) {
        int movesLeft = player.getMovesPerTurn();

        do {

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
                        directionOutOfBound();
                    } else {
                        movesLeft = updatePlayerMoves(movesLeft, player);
                        if (movesLeft <= 0) {
                            updateAllStat(player);
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

    }

    // MAP UPDATES

    protected <T extends Player> T moveOnePlayer(char direction, int row, int col, T player) throws IndexOutOfBoundsException {
        map.setElementPosition(row, col, map.defaultMarker()); // set the default marker before updating move

        // set the element values of the new position
        int[] rowCol = rowColToMoveOnto(direction, row, col);
        map.setElementPosition(rowCol[0], rowCol[1], player.getMarker());
        player.setCoordinates(rowCol[0], rowCol[1]);
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

            double health = player.defend();
            double newHealth = Double.parseDouble(String.valueOf(health + player.getHealth()));
            player.setHealth(health < player.getMaxHealth() ? newHealth : player.getMaxHealth());
            out.printf("Your health regenerated by %.2f%n", health);
            out.println();
            updateOneStat(StatType.HEALTH, player);
        }
    }
    private void updateMoveStats(Human player) {
        updateOneStat(StatType.HEALTH, player);
        updateOneStat(StatType.MOVES_LEFT, player);
        displayGameBoard();
        out.println("You have " + player.getMovesRemaining() + " moves left.");
    }
    private char getMapElementAt(char chosenDirection, int row, int col) {
        int[] rowCol = rowColToMoveOnto(chosenDirection, row, col);
        return map.getElementAtPosition(rowCol[0], rowCol[1]);
    }
    protected <T extends Player> T updateMapMarkerOf(T player, int nextRow, int nextCol, int oldRow, int oldCol) {
        String tClass = player.getClass().getName();
        if (tClass.equals("character.Human")) {
            map.setElementPosition(nextRow, nextCol, player.getMarker());
            map.setElementPosition(oldRow, oldCol, map.defaultMarker());
            player.setCoordinates(nextRow, nextCol);
            return player;
        }

        return player;
    }

    // END - MAP UPDATES

    // COMBAT

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
        updateOneStat(StatType.HEALTH, player);
        updateOneStat(StatType.GOBLINS, player);
        return player;
    }

    protected <T extends Player> T engageCombatBetween(T attacker, T defender) {

        do {
            if (attack(attacker, defender) <= 0 || defend(defender, attacker) <= 0) {
                break;
            }
        } while (true);

        if (attacker.getHealth() > 0 ) {
            displayCombatNarrative(4, attacker.getName(), defender.getName(), attacker.getHealth());
        } else {
            displayCombatNarrative(4, defender.getName(), attacker.getName(), defender.getHealth());
        }

        return attacker.getHealth() >= 0 ? attacker : defender;
    }

    private <T extends Player> double attack(T attacker, T defender) {
        double defenderHealth = defender.getHealth();
        double damageByAttacker = attacker.attack();

        out.println("----");
        defenderHealth = defenderHealth - damageByAttacker;
        defender.setHealth(Double.parseDouble(String.format("%.2f", defenderHealth)));
        // combat narrative
        displayCombatNarrative(1, attacker.getName(), defender.getName(), damageByAttacker);
        displayCombatNarrative(2, attacker.getName(), defender.getName(), defender.getHealth());

        out.println("----");
        return defender.getHealth();
    }

    private <T extends Player> double defend(T defender, T attacker) {
        double attackerHealth = attacker.getHealth();
        double damageByDefender = defender.attack();

        out.println("----");
        attackerHealth = attackerHealth - damageByDefender;
        attacker.setHealth(Double.parseDouble(String.format("%.2f", attackerHealth)));
        // combat narrative
        displayCombatNarrative(1, defender.getName(), attacker.getName(), damageByDefender);
        displayCombatNarrative(2, defender.getName(), attacker.getName(), attacker.getHealth());

        out.println("----");
        return attacker.getHealth();
    }

    private void displayCombatNarrative(int option, String attacker, String defender, double stat) {
        stat = stat < 0 ? 0 : stat;

        switch (option) {
            case 1 -> out.printf("%s attack's %s for [ %.2f ]%n", attacker, defender, stat);
            case 2 -> out.printf("%s health is now [ %.2f ]%n", defender, stat);
            case 3 -> out.printf("%s health is now [ %.2f ]%n", attacker, stat);
            case 4 -> out.printf("%s defeats %s. %s [ health ] is now [ %.2f ]%n\n", attacker, defender, attacker, stat);
        }
    }

    // END COMBAT

    // PLAYER UPDATES
    protected <T extends Player> T retrievePlayer(ArrayList<T> t, int row, int col) {
        try {
            for (T g : t) {
                if (g.getCoordX() == row && g.getCoordY() == col) {
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

    private int updatePlayerMoves(int movesLeft, Human player) {
        movesLeft--;
        player.setMovesRemaining();
        updateMoveStats(player);
        return movesLeft;
    }

    // END - PLAYER UPDATES

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
                        this.getMap().setElementPosition(player.getCoordX(), player.getCoordY(), player.getMarker());
                        updateOneStat(StatType.HEALTH, player);
                        updateOneStat(StatType.GOBLINS, player);
                        movesLeft = 0;
                    } else if (player.getHealth() <= 0) {
                        updateOneStat(StatType.HEALTH, player);
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

    // GETTERS
    public Map getMap() {
        return map;
    }
    public ArrayList<Human> getHumans() {
        return humans;
    }
    public ArrayList<Goblin> getGoblins() {
        return goblins;
    }

    // END - GETTERS


    // SETTERS
    public void setMap(Map map) {
        this.map = map;
    }
    private void setHumans(ArrayList<Human> humans) {
        this.humans = humans;
    }
    private void setGoblins(ArrayList<Goblin> goblins) {
        this.goblins = goblins;
    }

    // END - SETTERS

}
