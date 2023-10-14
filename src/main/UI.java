package main;

import object.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public Font maruMonica, purisaB;
    Graphics2D graphics2D;
    GamePanel gamePanel;
    Font arial_40, arial_80;
    BufferedImage keyImage;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.tff");
            assert inputStream != null;
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/fonts/Purisa Bold.tff");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException err) {
            err.printStackTrace();
        }

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
        if (gamePanel.gameState == gamePanel.dialougeState) {
            drawDialogueScreen();
        }
//        if (gameFinished) {
//            graphics2D.setFont(arial_40);
//            graphics2D.setColor(Color.white);
//            String text;
//            int textLength;
//            int x, y;
//            text = "You found the treasure!";
//            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
//
//
//            x = gamePanel.screenWidth / 2 - textLength / 2;
//            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
//            graphics2D.drawString(text, x, y);
//
//            graphics2D.setFont(arial_80);
//            graphics2D.setColor(Color.yellow);
//
//            text = "CONGRATS!";
//            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
//
//
//            x = gamePanel.screenWidth / 2 - textLength / 2;
//            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 2);
//            graphics2D.drawString(text, x, y);
//
//            gamePanel.gameThread = null;
//
//
//        } else {
//            graphics2D.setFont(arial_40);
//            graphics2D.setColor(Color.white);
//            graphics2D.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
//            graphics2D.drawString("Key = " + gamePanel.player.keys, 74, 65);
//
//            if (messageOn) {
//                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
//                graphics2D.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 5);
//                messageCounter++;
//                if (messageCounter > 120) {
//                    messageCounter = 0;
//                    message = "";
//                    messageOn = false;
//                }
//            }
//        }

    }

    public void drawPauseScreen() {
        graphics2D.setFont(maruMonica);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 64F));
        graphics2D.setColor(Color.WHITE);
        String text = "PAUSED";
        int x = getXForCenteredText(text);

        graphics2D.drawString(text, x, gamePanel.screenHeight / 2);
    }

    public void drawDialogueScreen() {
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        x += gamePanel.tileSize / 2;
        y += gamePanel.tileSize;
        graphics2D.setColor(new Color(255, 255, 255));
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 32F));
        for (String line : currentDialogue.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color black = new Color(0, 0, 0, 100);
        graphics2D.setColor(black);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);
        Color white = new Color(255, 255, 255);
        graphics2D.setColor(white);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return gamePanel.screenWidth / 2 - length / 2;
    }
}
