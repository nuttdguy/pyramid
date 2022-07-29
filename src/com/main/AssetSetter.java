package main;

import character.Goblin;
import object.OBJ_Log;

// use this class to set object assets onto the map
public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {

        // instantiate the object and set the xy world map location to place the object
        gp.obj[0] = new OBJ_Log(gp);
        gp.obj[0].worldX = 17 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;

    }

    public void setNPC(int qty) {

        for (int i = 0; i < qty; i++) {
            gp.npc[i] = new Goblin(gp);
            gp.npc[i].worldX = randomInt(gp.tileSize) * gp.tileSize;
            gp.npc[i].worldY = randomInt(gp.tileSize) * gp.tileSize;
        }

    }

    public int randomInt(int max) {
        return (int) (Math.random() * max);
    }

}
