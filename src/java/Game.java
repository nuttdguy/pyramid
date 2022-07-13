import java.text.DecimalFormat;
import java.util.ArrayList;
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
        goblins = this.playerFactory(PlayerType.GOBLIN, 2);
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
        // determine the row and col to set the value on
        int row = type.equals(StatType.HEALTH) ? 2 :
                type.equals(StatType.STRENGTH) ? 4 :
                type.equals(StatType.DEFENSE) ? 6 :
                type.equals(StatType.MOVE_DISTANCE) ? 8 :
                type.equals(StatType.MOVES_LEFT) ? 8 : 10;

        String stat = type.equals(StatType.HEALTH) ? String.valueOf(player.getHealth()) :
                type.equals(StatType.STRENGTH) ? String.valueOf(player.getStrength()) :
                type.equals(StatType.DEFENSE) ? String.valueOf(player.getDefense()) :
                type.equals(StatType.MOVE_DISTANCE) ? String.valueOf(player.getMovePerTurn()) :
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
                out.println("Do you want to move n, e, s or w?");
                char direction = keyPress();
                if (direction == 'n' || direction == 'e' || direction == 's' || direction == 'w') {
                    try {

                        int row = player.getCoordinates()[0], col = player.getCoordinates()[1];
                        int[] rowCol = rowColToMoveOnto(direction, row, col);
                        char rowColElement = land.getElementAtPosition(rowCol[0], rowCol[1]);
                        if (rowColElement == 'G') {
                            // get the correct goblin to battle by its position
                            Goblin goblin = null;
                            for (Goblin g : this.goblins) {
                                if (g.getCoordinateX() == rowCol[0] && g.getCoordinateY() == rowCol[1]) {
                                    goblin = g;
                                }
                            }
                            // combat
                            // check for null
                            if (goblin != null) {
                                player = engageCombat(player, goblin);
                            }

                            if (goblin.getHealth() <= 0) {
                                this.goblins.remove(goblin);
                                if (this.goblins.size() == 0) {
                                    return false;  // end game if enemy count is zero
                                }

                                land.setElementPosition(rowCol[0], rowCol[1], player.getMarker());
                                land.setElementPosition(row, col, land.defaultMarker());
                                player.setCoordinate(rowCol[0], rowCol[1]);

                                updateStatOf(player, StatType.HEALTH);
                                updateStatOf(player, StatType.GOBLINS);
                                displayGameBoard();
                            }

                            out.println("Battle!!!" + goblin.attack());
                        } else if (rowColElement == '+') {
                            out.println("You cannot move in that direction");
                        } else {
                            handleOnePlayerMove(player, direction, row, col);

                            if (player.getHealth() < player.getMaxHealth()) {
                                double health = player.regenerateHealth();
                                player.setHealth(health > player.getMaxHealth() ? 10 : health );
                            }
                            player.setMovesRemaining(player.getMovesRemaining()-1);

                            updateStatOf(player, StatType.HEALTH);
                            updateStatOf(player, StatType.MOVES_LEFT);
                            displayGameBoard();

                            movesLeft--;
                            out.println("You moved 1 space " + direction + ". You have " + movesLeft + " moves left.");
                            if (movesLeft == 0) {
                                player.setMovesRemaining(player.getMovePerTurn());
                                action = 'd';
                            }
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
    protected Human engageCombat(Human player, Goblin goblin) {

        double goblinHealth = goblin.getHealth();
        double playerHealth = player.getHealth();
        DecimalFormat df = new DecimalFormat("0.00");
        while (goblinHealth >= 0 && playerHealth >= 0) {
            double playerAttack = player.attack();
            double goblinAttack = goblin.attack();

            goblinHealth = goblinHealth - playerAttack;
            goblin.setHealth(Double.parseDouble(df.format(goblinHealth)));
            out.println(String.format("Player attack's Goblin for [ %.2f ]", playerAttack));
            if (goblinHealth <= 0) {
                out.println(String.format("Goblin's  [ health ] is now [ %.2f ]", 0.00));
                out.println(String.format("Victory! Player's [ health ] is now [ %.2f ]", player.getHealth()));
                out.println("----");
                return player;
            } else {
                out.println(String.format("Goblin's [ health ] is now [ %.2f ]", goblin.getHealth()));
            }

            out.println("----");
            playerHealth = playerHealth - goblinAttack;
            player.setHealth(Double.parseDouble(df.format(playerHealth)));
            out.println(String.format("Goblin attack's Player for [ %.2f ]", goblinAttack));

            if (playerHealth <= 0) {
                out.println(String.format("Defeat! Player's [ health ] is now [ %.2f ]", 0.00));
                out.println("----");
            } else {
                out.println(String.format("Player's [ health ] is now [ %.2f ]", player.getHealth()));
                out.println("----");
            }
        }

        return player;
    }
    protected void displayGameBoard() {
        out.println(land.displayTheHeader());
        out.println(land.displayTheGrid(land.getGrid()));
    }
    protected boolean isPlayerMoveWithinGameBounds(int row, int col) {
        return (row > 0 && row < land.rowHeight()) && (col > 0 && col < (land.colWidth() - land.colOffset()) );
    }
    protected int[] rowColToMoveOnto(char direction, int row, int col) {
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

        // loop game until goblins or humans have been defeated
        boolean continueGame = true;
        while(continueGame) {
            try {
                continueGame = moveOnePlayer(this.humans.get(0));
                // todo move enemies position position towards player
                out.println("END OF TURN. Enemy will move now. ");
            } catch (IndexOutOfBoundsException e) {
                out.println(e);
                break;
            }
        }

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
