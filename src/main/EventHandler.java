package main;

public class EventHandler {
    EventRect[][] eventRect;
    GamePanel gamePanel;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldCol];
        int col = 0;
        int row = 0;
        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if (col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent() {
        // Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize) canTouchEvent = true;
        if (canTouchEvent) {
            if (hit(27, 16, "right")) damangePit(27, 16, gamePanel.dialougeState);
            if (hit(23, 19, "any")) damangePit(23, 19, gamePanel.dialougeState);
            if (hit(23, 12, "up")) healingPool(23, 12, gamePanel.dialougeState);
        }
    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRect[row][col].x = col * gamePanel.tileSize + eventRect[col][row].x;
        eventRect[row][col].y = row * gamePanel.tileSize + eventRect[col][row].y;

        if (gamePanel.player.solidArea.intersects(eventRect[row][col]) && !eventRect[col][row].eventDone) {
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        eventRect[row][col].x = eventRect[row][col].eventRectDefaultX;
        eventRect[row][col].y = eventRect[row][col].eventRectDefaultY;

        return hit;
    }

    public void damangePit(int col, int row, int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "You feel into a pit";
        gamePanel.player.currentLife--;
        // eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gamePanel.keyHandler.enterPressed) {

            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "You drink from the healing pool";
            gamePanel.player.currentLife++;
        }

    }
}
