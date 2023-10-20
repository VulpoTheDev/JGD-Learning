package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Door extends Entity {
    public Obj_Door(GamePanel gamePanel) {
        super(gamePanel);
        name = "door";
        setup("/objects/door");
        collision = true;
    }
}

