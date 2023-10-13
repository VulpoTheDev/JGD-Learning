package main;

import object.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    Graphics2D graphics2D;
    GamePanel gamePanel;
    Font arial_40, arial_80;
    BufferedImage keyImage;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        Obj_Key key = new Obj_Key(this.gamePanel);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        if (gamePanel.gameState == gamePanel.playState) {

        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
        if (gameFinished) {
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);
            String text;
            int textLength;
            int x, y;
            text = "You found the treasure!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();


            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial_80);
            graphics2D.setColor(Color.yellow);

            text = "CONGRATS!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();


            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 2);
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;


        } else {
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);
            graphics2D.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
            graphics2D.drawString("Key = " + gamePanel.player.keys, 74, 65);

            if (messageOn) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 5);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    message = "";
                    messageOn = false;
                }
            }
        }

    }

    public void drawPauseScreen() {
        graphics2D.setFont(arial_80);
        String text = "PAUSED";
        int x = getXForCenteredText(text);

        graphics2D.drawString(text, x, gamePanel.screenHeight / 2);
    }

    public int getXForCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return gamePanel.screenWidth / 2 - length / 2;
    }
}
