package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTile;


    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        // set the size of the map
        mapTile = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadTileImage("/res/tile/tile_1.png", 0, false);
        loadTileImage("/res/tile/tile_2.png", 1, true);
        loadTileImage("/res/tile/tile_3.png", 2, false);

        // load the map
        loadMap("/res/map/map_2.txt");
    }

    // load the tile images from resource package
    private void loadTileImage(String filePath, int index, boolean isCollision) {

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(filePath));
            tile[index].collision = isCollision;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // loads the map tiles from a map file
    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // iterate through the map file of max row and columns
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                // read the current line of the file
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    // split the current line and add each to an array
                    String[] numbers = line.split("");
                    int num = Integer.parseInt(numbers[col]);

                    mapTile[col][row] = num;
                    col++;
                }
                // when max columns is reached, reset col counter and increment row
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // draws the viewable map screen
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        // draw the map of max column width and worldCol height
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            // get the number value of the worldRow and worldCol
            int mapTile = this.mapTile[worldCol][worldRow];

            // Start at the corner of the world map
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            // locate the position of the player from the starting world map xy and then subtract players xy location
            // to determine where to begin drawing the viewable area of the map
            int screenX = worldX - gp.player.getWorldX() + gp.player.screenX;
            int screenY = worldY - gp.player.getWorldY() + gp.player.screenY;

            // condition checks whether tile to be drawn is within the bounds of the players viewable screen area
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
                // draw the image that is corresponds to the tile position, i.e. 0 == tile at position 0
                g2.drawImage(tile[mapTile].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            // increment the worldRow
            worldCol++;


            // when max cols, then result worldRow and x and increment worldCol and y
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }


}
