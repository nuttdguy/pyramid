
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

    // INPUT / OUTPUT
    private char keyPress() {
        return new Scanner(in).next().charAt(0);
    }
    protected void displayGameBoard() {
        out.println(map.displayTheHeader());
        out.println(map.displayTheGrid(map.getGrid()));
    }

    // END - INPUT / OUTPUT


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
    private char requestDirectionByKeyPress() {

        do {
            out.println("Do you want to move n, e, s or w?");
            char chosenDirection = keyPress();
            if (chosenDirection == 'n' || chosenDirection == 'e' || chosenDirection == 's' || chosenDirection == 'w') {
                return chosenDirection;
            } else {
                out.println("Invalid option " + chosenDirection + ". Try again?");
            }
        } while (true);
    }

    private char[] directionToMove(int negOrPos) {
        return negOrPos > 0 ? new char[]{'n', 'w'} : new char[]{'s', 'e'};
    }

    private <T extends Player> int[] distanceToMarker(T attacker, T defender) {
        // get the directional pair which depends on whether the attacker is north west or south east of defender
        int[] rowColOfAttacker = attacker.getCoordinates();
        int[] rowColOfDefender = defender.getCoordinates();
        int rowDistanceToDefender =  rowColOfDefender[0] - rowColOfAttacker[0];
        int colDistanceToDefender = rowColOfDefender[1] - rowColOfAttacker[1];
        return new int[]{rowDistanceToDefender, colDistanceToDefender};
    }

    protected <T extends Player> T moveOne(T attacker, T defender,  char chosenDirection) {
        int movesLeft = attacker.getMovesPerTurn();

        while (movesLeft > 0) {

            try {
                int row = attacker.getCoordinates()[0];
                int col = attacker.getCoordinates()[1];
                char elementAtRowCol = getMapElementAt(chosenDirection, row, col);

                if (elementAtRowCol == '+') {
                    out.println("You cannot move in that direction");
                } else if (elementAtRowCol == 'G' && attacker.getName().equals("Human")) {

                    engageCombatBetween(attacker, getPlayer(getGoblins(), chosenDirection, row, col));
                    movesLeft = endCombat(attacker, chosenDirection);
//                    displayGameBoard();

                } else if (elementAtRowCol == 'H' && attacker.getName().equals("Goblin")) {

                    engageCombatBetween(attacker, defender);
                    movesLeft = endCombat(attacker, chosenDirection);

                } else {

                    movesLeft = endMove(attacker, chosenDirection);
                }

                if (attacker.getName().equals("Human")) {
                    chosenDirection = requestDirectionByKeyPress();
                }

                displayGameBoard();
                if (movesLeft <= 0) {
                    updateAllStat(attacker);
//                    displayGameBoard();
                    out.println("You have " + attacker.getMovesRemaining() + " moves left.");
                    return attacker;
                } // end turn when no moves are left

            } catch (IndexOutOfBoundsException e) {
                out.println(e);
            }
        }
        return attacker;
    }


    // END - MOVEMENT

    // MAP UPDATES

    private <T extends Player> T updateMapMarkerAfterMove(T player, char direction) throws IndexOutOfBoundsException {
        int[] nextRowCol = getTheRowColToMoveOnto(direction, player.getCoordX(), player.getCoordY());

        map.setElementPosition(nextRowCol[0], nextRowCol[1], player.getMarker());
        map.setElementPosition(player.getCoordX(), player.getCoordY(), map.defaultMarker());
        player.setCoordinates(nextRowCol[0], nextRowCol[1]);
        return player;

    }

    private char getMapElementAt(char chosenDirection, int row, int col) {
        int[] rowCol = getTheRowColToMoveOnto(chosenDirection, row, col);
        return map.getElementAtPosition(rowCol[0], rowCol[1]);
    }

    private int[] getTheRowColToMoveOnto(char direction, int row, int col) {
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

    // END - MAP UPDATES

    // COMBAT

    private <T extends Player> T getPlayer(ArrayList<T> playerList, char chosenDirection, int row, int col) {
        int[] rowCol = getTheRowColToMoveOnto(chosenDirection, row, col);
        for (T player : playerList) {
            if (player.getCoordX() == rowCol[0] && player.getCoordY() == rowCol[1]) {
                return player;
            }
        }
        return null;
    }

    private <T extends Player> T engageCombatBetween(T attacker, T defender) {

        do {
            if (attack(attacker, defender) <= 0 || defend(defender, attacker) <= 0) {
                break;
            }
        } while (true);

        // display combat winner
        if (attacker.getHealth() > 0 ) {
            displayCombatNarrative(4, attacker.getName(), defender.getName(), attacker.getHealth());
        } else {
            displayCombatNarrative(4, defender.getName(), attacker.getName(), defender.getHealth());
        }

        return attacker.getHealth() >= 0 ? attacker : defender;
    }

    private <T extends Player> int endCombat(T player, char direction) {
        removePlayer(getGoblins());
        removePlayer(getHumans());
        return endMove(player, direction);
    }

    private <T extends Player> int endMove(T player, char direction) {
        player.setOrDecrementMovesRemaining();
        updateMapMarkerAfterMove(player, direction);
        updateAllStat(player);
        return player.getMovesRemaining();
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

    private <T extends Player> int removePlayer(ArrayList<T> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getHealth() <= 0) {
                playerList.remove(playerList.get(i));
            }
        }
        return playerList.size();
    }

    private <T extends Player> void replenishHealthOf(T player) {
        if (player.getHealth() < player.getMaxHealth() && player.getHealth() > 0) {

            double health = player.defend();
            double newHealth = Double.parseDouble(String.valueOf(health + player.getHealth()));
            player.setHealth(health < player.getMaxHealth() ? newHealth : player.getMaxHealth());
            out.printf("Your health regenerated by %.2f%n", health);
            out.println();
            updateOneStat(StatType.HEALTH, player);
        }
    }

    // END - PLAYER UPDATES

    // todo refactor this method
    protected <T extends Player> int moveMany(ArrayList<T> attackers, Human defender) {
        ArrayList<T> attackersCopy = new ArrayList<>(attackers.subList(0, attackers.size())); // make a copy to iterate

        for (T attacker : attackersCopy) {
            int[] rowColDistanceToMarker = distanceToMarker(attacker, defender);

//            char direction = ' ';
//            if (rowColDistanceToMarker[0] > 0 &&  rowColDistanceToMarker[1] > 0) {
//                // s & e
//                if (Math.abs(rowColDistanceToMarker[0]) <  Math.abs(rowColDistanceToMarker[1])) {
//                    direction = 's';
//                } else {
//                    direction = 'e';
//                }
//            } else if (rowColDistanceToMarker[0] > 0 &&  rowColDistanceToMarker[1] < 0) {
//                // s & w
//                if (Math.abs(rowColDistanceToMarker[0]) <  Math.abs(rowColDistanceToMarker[1])) {
//                    direction = 's';
//                } else {
//                    direction = 'e';
//                }
//            } else if (rowColDistanceToMarker[0] < 0 &&  rowColDistanceToMarker[1] > 0) {
//                // n & e
//                if (Math.abs(rowColDistanceToMarker[0]) <  Math.abs(rowColDistanceToMarker[1])) {
//                    direction = 'n';
//                } else {
//                    direction = 'w';
//                }
//            } else if (rowColDistanceToMarker[0] < 0 &&  rowColDistanceToMarker[1] < 0) {
//                // n & w
//                if (Math.abs(rowColDistanceToMarker[0]) <  Math.abs(rowColDistanceToMarker[1])) {
//                    direction = 'n';
//                } else {
//                    direction = 'w';
//                }
//            }

            if (Math.abs(rowColDistanceToMarker[0]) >= Math.abs(rowColDistanceToMarker[1])) {

                char[] rowDirectionOptions = directionToMove(rowColDistanceToMarker[0]);
                moveOne(attacker, defender, rowDirectionOptions[0]);
//                moveOne(attacker, defender, direction);

            } else if (Math.abs(rowColDistanceToMarker[0]) <= Math.abs(rowColDistanceToMarker[1])) {

                char[] colDirectionOptions = directionToMove(rowColDistanceToMarker[1]);
                moveOne(attacker, defender, colDirectionOptions[1]);
//                moveOne(attacker, defender, direction);

            }
        }

        return attackers.size();
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
                Human player;
                if (this.getHumans().isEmpty()) {
                    return;
                } else {
                    player = this.getHumans().get(0); // todo has next method
                }

                if ( this.getGoblins().isEmpty() ) {
                    out.println("Victory - Human has defeated the Goblin's");
                    out.println("*** Game Over ***");
                    return;
                } else {
                    player = moveOne(player, null, ' ');

                    out.println("----");
                    out.println("Human - end of turn ");
                    out.println("----");
                    out.println("Goblin's turn - press and enter any letter to continue");

                    keyPress();

                    out.println("----");
                    // handle goblin moves

                    moveMany(getGoblins(), player);

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


}
