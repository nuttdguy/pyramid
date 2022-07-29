package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

// use this class to create object instances of a log
public class OBJ_Log  extends SuperObject {

    public OBJ_Log (GamePanel gp) {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/object/log_1.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        name = "Log";
        collision = true;

    }
}
