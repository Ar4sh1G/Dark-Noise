/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import entities.Player;
import java.awt.Graphics;
import levels.LevelManager;
import java.awt.Graphics2D;


public class Game implements Runnable{
    
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET =200;
    
    
    private Player player;
    private LevelManager levelManager;
    private Camera camera;
    private TutorialOverlay tutorialOverlay;
    private boolean running = true;
    private boolean wasEscapePressed = false;
    private boolean paused = false;
    private boolean tutorialVisto = false;
    
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    private float tiempoJugado = 0f;
    private static final float TIEMPO_POR_UPDATE = 1f / 200f; 
    
    
   
    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gamePanel.requestFocus();
        startGameLoop();
    }
    
    private void initClasses() {
        player        = new Player(200, 200);
        levelManager  = new LevelManager(this);
        camera        = new Camera();
        tutorialOverlay = new TutorialOverlay();  
        player.setWalls(levelManager.getCurrentLevel().getWalls());
    }
    
    private void startGameLoop(){
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update() {
        
        if (!paused) {
        tiempoJugado += TIEMPO_POR_UPDATE;
        }
        
        boolean escNow = gamePanel.isEscapePressed();
        if (escNow && !wasEscapePressed) {
            MainClass.mostrarEscape();
        }
        wasEscapePressed = escNow;

        player.update();
        levelManager.update();
        camera.update(
            player.getX() + utilz.Constants.PlayerConstants.DRAW_WIDTH  / 2f,
            player.getY() + utilz.Constants.PlayerConstants.DRAW_HEIGHT / 2f
        );

        
        if (levelManager.getCurrentIndex() == 0 && !tutorialVisto) {
            tutorialOverlay.update();
            if (player.isMoving()) 
                tutorialOverlay.playerMoved();
            if (player.isPressedSpace()) 
                tutorialOverlay.playerPressedSpace();
            if (!tutorialOverlay.isVisible()) 
                tutorialVisto = true;
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        
        g2d.translate(-camera.getX(), -camera.getY());
        levelManager.draw(g);
        player.render(g);
        g2d.translate(camera.getX(), camera.getY());

        
        if (levelManager.getCurrentIndex() == 0 && !tutorialVisto) {
                tutorialOverlay.render(g, GAME_WIDTH, GAME_HEIGHT);
        }
    }
    public Camera getCamera() { 
        return camera; 
    }
    
    
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        
        
        long previousTime = System.nanoTime();
        
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;

        while (running) {
            
            
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            
            if (deltaU >= 1) {
                if (!paused) update();  
                updates++;
                deltaU--;
            }
            
             if(deltaF >= 1){
                
                gamePanel.repaint();
                gamePanel.updateGame();
                frames++;
                deltaF--;
                
            }

            

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
               
                frames = 0;
                updates = 0;
            }
        }
    
    }
    
    public Player getPlayer(){
        return player;
    }
    public void resetLevel() {
        levelManager.resetCurrentLevel();
    }
    
    public GamePanel getGamePanel() { return gamePanel; }
    
    public void stopGameLoop() {
        running = false;
    }
    
    public void setPaused(boolean paused) { this.paused = paused; }
    public boolean isPaused() { return paused; }
    
    public void resetTutorial() {
        tutorialOverlay = new TutorialOverlay();
    }
    
    public float getTiempoJugado() { return tiempoJugado; }
    public void setTiempoJugado(float t) { tiempoJugado = t; }
    
    public levels.LevelManager getLevelManager() { return levelManager; }
    public void cargarDesdeNivel(int nivelIndex, float tiempo) {
        levelManager.loadLevel(nivelIndex);
        tiempoJugado = tiempo;
    }
    
    
}
