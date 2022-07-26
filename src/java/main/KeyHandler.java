package main;

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
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPress = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPress = true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPress = true;
            default -> invalidPress = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPress = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPress = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPress = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPress = false;
            default -> invalidPress = false;
        }

    }
}
