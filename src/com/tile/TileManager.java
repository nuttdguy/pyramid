package tile;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

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

        // set the size of the array
        tile = new Tile[5];
        // sets the 2D array size based on maps max row and column
        mapTile = new int[gp.maxMapCol][gp.maxMapRow];

        // load image files
        loadTileImage("tile_1", 0, false);
        loadTileImage("tile_2", 1, true);
        loadTileImage("tile_3", 2, false);

        // load the map
        loadMap("/res/map/map_2.txt");
    }

    // load the tile images from resource package
    private void loadTileImage(String imageName, int index, boolean isCollision) {

        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/"+imageName+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.maxTileSize, gp.maxTileSize);
            tile[index].collision = isCollision;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // loads the map from a map txt file
    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // iterate through the map file of max row and columns
            while (col < gp.mapCol && row < gp.mapRow) {

                // read the current line of the file
                String line = br.readLine();

                while (col < gp.mapCol) {
                    // split the current line and add each to an array
                    String[] numbers = line.split("");
                    int num = Integer.parseInt(numbers[col]);

                    mapTile[col][row] = num;
                    col++;
                }
                // when max columns is reached, reset col counter and increment row
                if (col == gp.mapCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // draws the viewable area of map onto the screen
    public void draw(Graphics2D g2, Entity entity) {

        int mapCol = 0;
        int mapRow = 0;

        // draw the map of max column width and mapCol height
        while (mapCol < gp.maxMapCol && mapRow < gp.maxMapRow) {
            // get the number value of the mapRow and mapCol
            int mapTile = this.mapTile[mapCol][mapRow];

            // start at the corner of the map
            int mapX = mapCol * gp.maxTileSize;
            int mapY = mapRow * gp.maxTileSize;

            // locate the position of the player from the starting map xy and then subtract players xy location
            // to determine where to begin drawing the viewable area of the map
            int screenX = mapX - entity.mapX + entity.screenX;
            int screenY = mapY - entity.mapY + entity.screenY;

            // condition checks whether tile to be drawn is within the bounds of the players viewable screen area
            if (mapX + gp.maxTileSize > entity.mapX - entity.screenX &&
                mapX - gp.maxTileSize < entity.mapX + entity.screenX &&
                mapY + gp.maxTileSize > entity.mapY - entity.screenY &&
                mapY - gp.maxTileSize < entity.mapY + entity.screenY ) {

                // draw the image that corresponds to the tile position, i.e. 0 == draw tile image at index 0
                g2.drawImage(tile[mapTile].image, screenX, screenY, null);
            }

            // increment the mapCol
            mapCol++;


            // when max cols, then reset map col and increment to next row
            if (mapCol == gp.maxMapCol) {
                mapCol = 0;
                mapRow++;
            }

        }

    }

}
