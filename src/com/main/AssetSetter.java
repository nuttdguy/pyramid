package main;

import object.OBJ_Log;

// use this class to set object assets onto the map
public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {

        // instantiate the object and set the xy world map location to place the object
        gp.obj[0] = new OBJ_Log();
        gp.obj[0].worldX = 17 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;

    }

}
