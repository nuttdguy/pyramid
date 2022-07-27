package main;

import character.Player;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Player player) {

        // find the  x and y of the rectangle within the players square
        int leftMapX = player.getCoordX() - player.solidArea.x;
        int rightMapX = player.getCoordX() + player.solidArea.x + player.solidArea.width;
        int topMapY = player.getCoordY() + player.solidArea.y;
        int bottomMapY = player.getCoordY() + player.solidArea.y + player.solidArea.height;

        // calculates and determines the next square to move onto, reduced by the scaled tile size
        int leftCol = leftMapX/gp.tileSize;
        int rightCol = rightMapX/gp.tileSize;
        int topRow = topMapY/gp.tileSize;
        int bottomRow = bottomMapY/gp.tileSize;

        // need the two tiles that will be checked for collision
        int tileNum1, tileNum2;

        // depending on the direction, checks whether the tiles are collision types
        switch(player.direction) {
            case "up":
                topRow = (topMapY - player.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[leftCol][topRow]; // get the tile at the top left
                tileNum2 = gp.tileM.mapTile[rightCol][topRow];  // get the tile at the top right
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    player.collisionOn = true;  // when either is true, then the tile is collision type
                }
                break;
            case "down":
                bottomRow = (bottomMapY + player.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[rightCol][bottomRow]; // get the right bottom tile
                tileNum2 = gp.tileM.mapTile[leftCol][bottomRow]; // get the left bottom tile
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    player.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftMapX - player.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[leftCol][topRow];
                tileNum2 = gp.tileM.mapTile[leftCol][bottomRow];
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    player.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightMapX + player.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[rightCol][topRow];
                tileNum2 = gp.tileM.mapTile[rightCol][bottomRow];
                if (gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum2].collision) {
                    player.collisionOn = true;
                }
                break;

        }
    }

}
