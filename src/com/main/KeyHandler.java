package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

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

    // key press handler for console gui
    public char keyPressNext() {
        return new Scanner(in).next().charAt(0);
    }

    // key press handler for getting direction of console gui
    public char keyPressCompassDirection() {

        do {
            out.println("Do you want to move n, e, s or w?");
            char chosenDirection = keyPressNext();
            if (chosenDirection == 'n' || chosenDirection == 'e' || chosenDirection == 's' || chosenDirection == 'w') {
                return chosenDirection;
            } else {
                out.println("Invalid option " + chosenDirection + ". Try again?");
            }
        } while (true);
    }

}
