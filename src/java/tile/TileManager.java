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
    Tile[] tile;
    int[][] mapTile;


    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTile = new int[gp.maxScreenCol][gp.maxScreenRow];

        loadTileImage("/res/tile/tile_1.png", 0, false);
        loadTileImage("/res/tile/tile_2.png", 1, true);
        loadMap("/res/map/map_1.txt");
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

    // loads the map from the resource package
    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // iterate through the map file of max row and columns
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                // read the current line of the file
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    // split the current line and add each to an array
                    String[] numbers = line.split("");
                    int num = Integer.parseInt(numbers[col]);

                    mapTile[col][row] = num;
                    col++;
                }
                // when max columns is reached, reset col counter and increment row
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {

        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;

        // draw the map of max column width and row height
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            // get the number value of the col and row
            int mapTileNum = mapTile[col][row];

            // draw the image that is corresponds to the tile position, i.e. 0 == tile at position 0
            g2.drawImage(tile[mapTileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            // increment the col
            col++;
            // gets the tiles next column starting position
            x += gp.tileSize;

            // when max cols, then result col and x and increment row and y
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }

        }

    }


}
