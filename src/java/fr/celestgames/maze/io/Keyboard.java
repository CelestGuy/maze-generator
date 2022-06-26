package fr.celestgames.maze.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public boolean upKeyPressed, downKeyPressed, qKeyPressed, zKeyPressed, sKeyPressed, dKeyPressed, rKeyPressed;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upKeyPressed = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downKeyPressed = true;
        }
        if (keyCode == KeyEvent.VK_Q) {
            qKeyPressed = true;
        }
        if (keyCode == KeyEvent.VK_Z) {
            zKeyPressed = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            sKeyPressed = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            dKeyPressed = true;
        }


        if (keyCode == KeyEvent.VK_R) {
            rKeyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_Q) {
            qKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_Z) {
            zKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            sKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            dKeyPressed = false;
        }

        if (keyCode == KeyEvent.VK_R) {
            rKeyPressed = false;
        }
    }
}
