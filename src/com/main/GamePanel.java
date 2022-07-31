package main;

import entity.Entity;
import entity.Goblin;
import entity.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.System.out;


//public class GamePanel extends JPanel {
public class GamePanel extends JFrame {

    // setup tile size and screen dimensions
    private int tileSize = 16;
    private int scale = 3;
    public int maxTileSize = tileSize * scale;

    // must conform to the map txt file height and width
    public int mapCol = 50;
    public int mapRow = 50;
    public int maxMapCol = mapCol * maxTileSize;
    public int maxMapRow = mapRow * maxTileSize;

    // window dimensions
    int screenCol = 24;
    int screenRow = 12;
    public int screenWidth = screenCol * maxTileSize;
    public int screenHeight = screenRow * maxTileSize;


    enum PlayerType {
        GOBLIN, HUMAN
    }


    // setup players
    private ArrayList<Human> humans;
    public int humanCount;
    private ArrayList<Goblin> goblins;
    public int goblinCount;

    // managers
    public Graphics2D g2;

    public GamePanel() {
        setUp();

        this.setTitle("Humans vs Goblins");
        this.setSize(screenWidth, screenHeight);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyManager());

        this.setVisible(true);

    }

    public void setUp() {
        this.goblins = initPlayers(PlayerType.GOBLIN, 8);
        this.humans = initPlayers(PlayerType.HUMAN, 1);
        this.humanCount = humans.size();
        this.goblinCount = goblins.size();
    }

    public void paint(Graphics g) {

        super.paintComponents(g);
        g2 = (Graphics2D) g;

        for (Goblin goblin : this.goblins) {
            goblin.draw(g2, humans.get(0));
        }

        this.humans.get(0).draw(g2);

    }


    public void checkCollision(Entity entity) {
        Human human = humans.get(0);

        if (human != null) {

            // entity solid area position, i.e. player, npc, other
            entity.solidArea.x = entity.mapX + entity.solidArea.x;
            entity.solidArea.y = entity.mapY + entity.solidArea.y;

            // objects solid area position on map
            human.solidArea.x = human.mapX + human.solidArea.x;
            human.solidArea.y = human.mapY + human.solidArea.y;

            // when direction of the entity is
            switch(entity.direction) {
                case "up" -> entity.solidArea.y -= entity.speed;
                case "down" -> entity.solidArea.y += entity.speed;
                case "left" -> entity.solidArea.x -= entity.speed;
                case "right" -> entity.solidArea.x += entity.speed;
            }

            if (entity.solidArea.intersects(human.solidArea)) {
                human.attack(human, entity);

            }

            // reset solidArea xy changes to default values for next frame
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            human.solidArea.x = human.solidAreaDefaultX;
            human.solidArea.y = human.solidAreaDefaultY;

        }

        // remove losing player
        if (human.getHealth() < 0 ) {
            System.exit(1);
        }
    }

    public <T extends Entity> void removeCombatLoser(T player) {
        switch (player.getName()) {
            case "Goblin" -> goblins.remove(player);
            case "Human" -> humans.remove(player);
        }
    }


    public class KeyManager extends KeyHandler {
        @Override
        public void keyPressed(KeyEvent e) {
            humans.get(0).keyPressed(e);

            ArrayList<Goblin> goblinCopy = new ArrayList<>(goblins);

            for (Goblin goblin : goblinCopy) {

                // set direction and engage combat when collision
                goblin.setAction();
                checkCollision(goblin);

                if (goblin.getHealth() < 0) {
                    removeCombatLoser(goblin);
                }
            }

            if (goblins.size() == 0) {
                System.exit(1);
            }

            repaint();
        }
    }

    private <T extends Entity> ArrayList<T> initPlayers(PlayerType playerType, int qty) {
        ArrayList<T> players = new ArrayList<>();

        while (qty > 0) {
            try {
                switch (playerType) {
                    case GOBLIN -> players.add((T) new Goblin(this));
                    case HUMAN -> players.add((T) new Human(this));
                }
            } catch (ClassCastException e) {
                out.println(e);
            }
            qty--;
        }
        return players;
    }





}
