package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean checkDrawTime;
    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gamePanel.gameState == gamePanel.playState) {
            switch (code) {
                case KeyEvent.VK_W -> upPressed = true;
                case KeyEvent.VK_A -> leftPressed = true;
                case KeyEvent.VK_S -> downPressed = true;
                case KeyEvent.VK_D -> rightPressed = true;
                case KeyEvent.VK_T -> checkDrawTime = !checkDrawTime;
                case KeyEvent.VK_ESCAPE -> gamePanel.gameState = gamePanel.pauseState;
                case KeyEvent.VK_ENTER -> enterPressed = true;

            }
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            switch (code) {
                case KeyEvent.VK_ESCAPE -> gamePanel.gameState = gamePanel.playState;
            }
        } else if (gamePanel.gameState == gamePanel.dialougeState) {
            switch (code) {
                case KeyEvent.VK_ENTER -> gamePanel.gameState = gamePanel.playState;
            }
        } else if (gamePanel.gameState == gamePanel.titleState) {
            switch (code) {

                case KeyEvent.VK_W -> {
                    if (gamePanel.ui.commandNumber != 0) {
                        gamePanel.ui.commandNumber--;
                    }
                }
                case KeyEvent.VK_S -> gamePanel.ui.commandNumber++;
                case KeyEvent.VK_ENTER -> {
                    switch (gamePanel.ui.commandNumber) {
                        case 0:
                            gamePanel.gameState = gamePanel.playState;
                            gamePanel.playMusic(0);
                            break;
                        case 1:
                            // Add Later
                            break;
                        case 2:
                            System.exit(05);
                    }
                }
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
            // Debug
        }
    }
}
