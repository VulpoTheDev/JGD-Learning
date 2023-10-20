package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Chest extends Entity {
    public Obj_Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = "chest";
        down1 = setup("/objects/chest");

    }
}
