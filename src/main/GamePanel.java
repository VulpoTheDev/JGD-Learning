package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // Game States
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialougeState = 3;

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
    public EventHandler eventHandler = new EventHandler(this);
    // Game State
    public int gameState = 0;
    // ENTITY AND OBJECTS
    public Player player = new Player(this, keyHandler);
    public Entity[] object = new Entity[10];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[20];
    ArrayList<Entity> entityArrayList = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.spawmMonster();
        gameState = titleState;
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
                for (Entity npc_char : npc) if (npc_char != null) npc_char.update();
                for (Entity mons : monster) if (mons != null) mons.update();
                player.update();
                break;
            case pauseState:
                break;
            case dialougeState:
                ui.drawDialogueScreen();
                break;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;


        long drawStart = 0;
        drawStart = System.nanoTime();

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(graphics2D);

        } else {
            // Draw Tiles
            tileManager.draw(graphics2D);

            // Adds Players
            entityArrayList.add(player);
            // Adds NPCS
            for (Entity npcs : npc) if (npcs != null) entityArrayList.add(npcs);
            // Adds Objects
            for (Entity objs : object) if (objs != null) entityArrayList.add(objs);
            // Monsters
            for (Entity mons : monster) if (mons != null) entityArrayList.add(mons);

            // Sort
            entityArrayList.sort(Comparator.comparingInt(e -> e.worldY));

            // Draw Entities
            for (Entity entity : entityArrayList) entity.draw(graphics2D);

            // Resets
            entityArrayList.clear();

            // Draw UI
            ui.draw(graphics2D);
        }

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
