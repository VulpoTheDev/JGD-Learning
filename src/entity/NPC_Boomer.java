package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Boomer extends Entity {
    public NPC_Boomer(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Good Day!";
        dialogues[1] = "Are you HERE to find ZE treasure?";
        dialogues[2] = "I used to be a great wizard until \nI took a spell to the knee.";
        dialogues[3] = "Alrighty then, may luck find you!";
    }

    public void speak() {
        super.speak();
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

    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
    }
}
