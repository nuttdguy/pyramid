import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPress, downPress, leftPress, rightPress, invalidPress;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPress = true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> rightPress = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPress = true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> downPress = true;
            default -> invalidPress = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPress = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> rightPress = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPress = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> downPress = false;
            default -> invalidPress = false;
        }

    }
}
