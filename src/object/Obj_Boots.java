package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Boots extends Entity {
    public Obj_Boots(GamePanel gamePanel) {
        super(gamePanel);
        name = "boots";
        down1 = setup("/objects/boots");
    }
}
