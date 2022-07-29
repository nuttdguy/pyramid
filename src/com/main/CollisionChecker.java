package main;

import character.Entity;

public class CollisionChecker {

    GamePanel gp;
    public int collisionCheckIndex = 1000; // set to any value greater than the num of objects in a given list

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

        // default index
        int index = collisionCheckIndex;

        // for every object for a collision with an entity
        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {

                // entity solid area position, i.e. player, npc, other
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // objects solid area position on map
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // when direction of the entity is
                switch(entity.direction) {
                    case "up":
                        // set the entity solidArea x or y
                        entity.solidArea.y -= entity.speed;

                        // then check if the solid area of the entity and object intersect
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {

                            // set the entity collision flag to true, so entity cannot move onto into the y position
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i; // return the index of the object
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
                            if (isPlayer) {
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

                // reset solidArea xy changes to default values for next frame
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = entity.solidAreaDefaultX;
                gp.obj[i].solidArea.y = entity.solidAreaDefaultY;

            }

        }

        // when collision is on, return the index of the object or the default when there is no collision
        return index;
    }

    // NPC collision
    public int checkEntity(Entity entity, Entity[] target) {

        // default index
        int index = collisionCheckIndex;

        // for every object for a collision with an entity
        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {

                // entity solid area position, i.e. player, npc, other
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // objects solid area position on map
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                // when direction of the entity is
                switch(entity.direction) {
                    case "up":
                        // set the entity solidArea x or y
                        entity.solidArea.y -= entity.speed;

                        // then check if the solid area of the entity and object intersect
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i; // return the index of the object
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }

                // reset solidArea xy changes to default values for next frame
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }

        }

        // when collision is on, return the index of the object or the default when there is no collision
        return index;

    }


    public void checkPlayer(Entity entity) {

        if (gp.player != null) {

            // entity solid area position, i.e. player, npc, other
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            // objects solid area position on map
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            // when direction of the entity is
            switch(entity.direction) {
                case "up":
                    // set the entity solidArea x or y
                    entity.solidArea.y -= entity.speed;

                    // then check if the solid area of the entity and object intersect
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
            }

            // reset solidArea xy changes to default values for next frame
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        }
    }

}
