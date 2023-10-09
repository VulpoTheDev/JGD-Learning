package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    final int FPS = 60;
    // Screen Setting
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 578px
    public SuperObject[] object = new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {

//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void setUpGame() {
        assetSetter.setObject();
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);
        for (SuperObject superObject : object) {
            if (superObject != null) {
                superObject.draw(graphics2D, this);
            }
        }
        player.draw(graphics2D);
        graphics2D.dispose();
    }


}
