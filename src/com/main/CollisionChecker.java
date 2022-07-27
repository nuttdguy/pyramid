package main;

import character.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        // find the  x and y of the rectangle within the players square
        int leftMapX = entity.getWorldX() - entity.solidArea.x;
        int rightMapX = entity.getWorldX() + entity.solidArea.x + entity.solidArea.width;
        int topMapY = entity.getWorldY() + entity.solidArea.y;
        int bottomMapY = entity.getWorldY() + entity.solidArea.y + entity.solidArea.height;

        // calculates and determines the next square to move onto, reduced by the scaled tile size
        int leftCol = leftMapX/gp.tileSize;
        int rightCol = rightMapX/gp.tileSize;
        int topRow = topMapY/gp.tileSize;
        int bottomRow = bottomMapY/gp.tileSize;

        // need the two tiles that will be checked for collision
        int tileNum1, tileNum2;

        // depending on the direction, checks whether the tiles are collision types
        switch(entity.direction) {
            case "up":
                topRow = (topMapY - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[leftCol][topRow]; // get the tile at the top left
                tileNum2 = gp.tileM.mapTile[rightCol][topRow];  // get the tile at the top right
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;  // when either is true, then the tile is collision type
                }
                break;
            case "down":
                bottomRow = (bottomMapY + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[rightCol][bottomRow]; // get the right bottom tile
                tileNum2 = gp.tileM.mapTile[leftCol][bottomRow]; // get the left bottom tile
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftMapX - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[leftCol][topRow];
                tileNum2 = gp.tileM.mapTile[leftCol][bottomRow];
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightMapX + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[rightCol][topRow];
                tileNum2 = gp.tileM.mapTile[rightCol][bottomRow];
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {

        int index = 1000;

        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {

                // entity solid area position, i.e. player
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // objects solid ara position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                }

                // reset xy changes to default values
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = entity.solidAreaDefaultX;
                gp.obj[i].solidArea.y = entity.solidAreaDefaultY;

            }

        }

        // return the index if isPlayer is hitting the object
        return index;
    }

}
