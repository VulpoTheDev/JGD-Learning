package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boots extends SuperObject {
    GamePanel gamePanel;

    public Obj_Boots(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
