package main;

import object.Obj_Boots;
import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_Key;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        // Keys
        gamePanel.object[0] = new Obj_Key(this.gamePanel);
        gamePanel.object[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.object[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.object[1] = new Obj_Key(this.gamePanel);
        gamePanel.object[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 40 * gamePanel.tileSize;
        gamePanel.object[2] = new Obj_Key(this.gamePanel);
        gamePanel.object[2].worldX = 38 * gamePanel.tileSize;
        gamePanel.object[2].worldY = 8 * gamePanel.tileSize;
        // Doors
        gamePanel.object[3] = new Obj_Chest(this.gamePanel);
        gamePanel.object[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[3].worldY = 7 * gamePanel.tileSize;

        gamePanel.object[4] = new Obj_Door(this.gamePanel);
        gamePanel.object[4].worldX = 8 * gamePanel.tileSize;
        gamePanel.object[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.object[5] = new Obj_Door(this.gamePanel);
        gamePanel.object[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.object[5].worldY = 22 * gamePanel.tileSize;

        gamePanel.object[6] = new Obj_Door(this.gamePanel);
        gamePanel.object[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[6].worldY = 11 * gamePanel.tileSize;

        gamePanel.object[7] = new Obj_Boots(this.gamePanel);
        gamePanel.object[7].worldX = 37 * gamePanel.tileSize;
        gamePanel.object[7].worldY = 42 * gamePanel.tileSize;


    }

}
