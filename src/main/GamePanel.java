package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int playState = 1;
    public final int pauseState = 2;
    // Screen Setting
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 578px
    // FPS
    final int FPS = 60;
    // GAME ENGINE STUFF
    public AssetSetter assetSetter = new AssetSetter(this);                     // Places Assets
    public Sound sound = new Sound();                                                     // Manages Sound
    public CollisionChecker collisionChecker = new CollisionChecker(this);     // Collision Checker
    public Thread gameThread;                                                            // Game Thread
    public KeyHandler keyHandler = new KeyHandler(this);                      // Key Handler
    public TileManager tileManager = new TileManager(this);                   // Tile Manager
    public UI ui = new UI(this);                                              // UI
    // Game State
    public int gameState = 1;
    // ENTITY AND OBJECTS
    public Player player = new Player(this, keyHandler);
    public SuperObject[] object = new SuperObject[10];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObject();
        playMusic(0);
        gameState = playState;
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


    public void update() {
        switch (gameState) {
            case playState:
                player.update();
                break;
            case pauseState:
                break;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;


        long drawStart = 0;
        drawStart = System.nanoTime();

        // Draw Tiles
        tileManager.draw(graphics2D);

        // Draw Objects
        for (SuperObject superObject : object) {
            if (superObject != null) {
                superObject.draw(graphics2D, this);
            }
        }

        // Draw Player
        player.draw(graphics2D);

        // Draw UI
        ui.draw(graphics2D);
        if (keyHandler.checkDrawTime) {

            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            graphics2D.setColor(Color.white);
            graphics2D.drawString("Draw Time " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }


        // Dispose Graphics (saves cpu)
        graphics2D.dispose();
    }

    public void playMusic(int index) {
        sound.setFile(index);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSoundEffect(int index) {
        sound.setFile(index);
        sound.play();
    }
}
