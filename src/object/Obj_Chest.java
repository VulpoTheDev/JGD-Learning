package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject {
    GamePanel gamePanel;

    public Obj_Chest(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
