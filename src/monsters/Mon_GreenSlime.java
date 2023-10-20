package monsters;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Mon_GreenSlime extends Entity {
    public Mon_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        name = "green_slime";
        speed = 1;
        maxLife = 4;
        currentLife = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        setAction();
    }

    public void getImage() {
        up1 = setup("/monsters/greenslime_down_1");
        up2 = setup("/monsters/greenslime_down_2");
        down1 = setup("/monsters/greenslime_down_1");
        down2 = setup("/monsters/greenslime_down_2");
        left1 = setup("/monsters/greenslime_down_1");
        left2 = setup("/monsters/greenslime_down_2");
        right1 = setup("/monsters/greenslime_down_1");
        right2 = setup("/monsters/greenslime_down_2");
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 60 * 2) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; /// Picks a number from 1 to 100
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;

        }
    }
}
