package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boots extends SuperObject {
    public Obj_Boots() {
        name = "boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
