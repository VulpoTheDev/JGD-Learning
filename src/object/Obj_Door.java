package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends SuperObject {
    GamePanel gamePanel;

    public Obj_Door(GamePanel gamePanel) {
        name = "door";
        this.gamePanel = gamePanel;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
