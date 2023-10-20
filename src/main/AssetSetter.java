package main;

import entity.NPC_Boomer;
import monsters.Mon_GreenSlime;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_Boomer(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 21;
    }

    public void setObject() {

    }

    public void spawmMonster() {
        gamePanel.monster[0] = new Mon_GreenSlime(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize * 23;
        gamePanel.monster[0].worldY = gamePanel.tileSize * 36;

        gamePanel.monster[1] = new Mon_GreenSlime(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize * 23;
        gamePanel.monster[1].worldY = gamePanel.tileSize * 37;

    }

}
