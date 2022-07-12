import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

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
        int x = this.land.getStatOffset() + 3;
        int y = type.equals(StatType.HEALTH) ? 2 :
                type.equals(StatType.STRENGTH) ? 4 :
                type.equals(StatType.DEFENSE) ? 6 :
                type.equals(StatType.MOVE_DISTANCE) ? 8 : 10;

        String stat = type.equals(StatType.HEALTH) ? String.valueOf(player.getHealth()) :
                type.equals(StatType.STRENGTH) ? String.valueOf(player.getStrength()) :
                type.equals(StatType.DEFENSE) ? String.valueOf(player.getDefense()) :
                type.equals(StatType.MOVE_DISTANCE) ? String.valueOf(player.getMoveDistance()) : null;

        if (stat != null) {
            updateStatOfHelper(x, y, stat);
        } else {
            updateStatOfHelper(x, y, String.valueOf(getGoblins().size()));
        }
    }
    private void updateStatOfHelper(int x, int y, String stat) {
        int i = 0;
        for (char s : stat.toCharArray()) {
            this.land.setElementPosition(x + i, y, s);
            i++;
        }
    }
    protected <T extends Specie> void setPlayers(ArrayList<T> T,Land land) {
        int x, y, xyElement,
                yBoundary = land.getGrid().length - (land.getGrid().length / 5),
                xBoundary = land.getGrid()[0].length - (land.getGrid()[0].length / 5) - 1;
        if (T.size() == 0) { return; }

        int playerIndex = 0;
        while(playerIndex < T.size()) {

            x = Land.getRandomWithin(xBoundary); // get the current player
            y = Land.getRandomWithin(yBoundary); // get random x and y
            xyElement = land.getElementAtPosition(x, y); // get current element at pos

            // determine which class
            String tClass = T.get(playerIndex).getClass().getName();

            // try, set player at location if empty
            if (xyElement == ' ') {
                if (tClass.equals("Human")) {
                    land.setElementPosition(x, y, ((Human) T.get(playerIndex)).getMarker() );
                    T.get(playerIndex).setCoordinate(x, y );
                } else if (tClass.equals("Goblin")) {
                    land.setElementPosition(x, y, ((Goblin) T.get(playerIndex)).getMarker() );
                    T.get(playerIndex).setCoordinate(x, y);
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

    protected char getCommandFrom(InputStream inputStream) {
        Scanner scanner;
        try {
            scanner = new Scanner(inputStream);
            String direction = scanner.next();
            scanner.close();
            return direction.charAt(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {

        // prompt player for action, i.e. walk or defend
            // when walk, loop until move max or enemy is encountered
            // get current xy pos, then if left, x-1, right x+1, north=y+1, south y-1
            // check if grid has enemy
                // when enemy, engage combat
                // when not enemy, update map, prompt for next action

        // when defend, engage in replenish health
        // end turn

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
