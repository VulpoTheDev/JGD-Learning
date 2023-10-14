package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    public final int screenX;
    public final int screenY;
    public int keys = 0;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void update() {

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            int object = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(object);

            // Check NPC COllsion
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "right" -> worldX += speed;
                    case "left" -> worldX -= speed;

                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void interactNPC(int index) {
        if (index != 999) {
            if (gamePanel.keyHandler.enterPressed) {

                gamePanel.gameState = gamePanel.dialougeState;
                gamePanel.npc[0].speak();
            }
        }
        gamePanel.keyHandler.enterPressed = false;
    }

    public void draw(Graphics2D graphics2D) {
        //  graphics2D.setColor(Color.white);
        //  graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                switch (spriteNum) {
                    case 1 -> image = up1;
                    case 2 -> image = up2;
                }
                break;
            case "down":
                switch (spriteNum) {
                    case 1 -> image = down1;
                    case 2 -> image = down2;
                }
                break;
            case "right":
                switch (spriteNum) {
                    case 1 -> image = right1;
                    case 2 -> image = right2;
                }
                break;
            case "left":
                switch (spriteNum) {
                    case 1 -> image = left1;
                    case 2 -> image = left2;
                }
                break;
        }
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String name = gamePanel.object[index].name;
            switch (name) {
                case "key":
                    keys++;
                    gamePanel.playSoundEffect(1);
                    gamePanel.ui.showMessage("You got a key!");
                    break;
                case "door":
                    if (keys == 0) {
                        gamePanel.ui.showMessage("You need a key");
                        return;
                    }
                    gamePanel.object[index] = null;
                    keys--;
                    gamePanel.ui.showMessage("You have unlocked a door");
                    gamePanel.playSoundEffect(3);

                    break;
                case "boots":
                    speed += 2;
                    gamePanel.ui.showMessage("You feel a little light on your feet");
                    gamePanel.playSoundEffect(2);

                    break;
                case "chest":
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSoundEffect(4);
                    break;
            }
            gamePanel.object[index] = null;
        }
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
    }
}
