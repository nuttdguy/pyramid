package object;

import javax.imageio.ImageIO;
import java.io.IOException;

// use this class to create object instances of a log
public class OBJ_Log  extends SuperObject {

    public OBJ_Log () {

        name = "Log";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/object/log_1.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        collision = true;

    }
}
