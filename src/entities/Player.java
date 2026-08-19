/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
 
import inputs.KeyboardInputs;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.Constants.PlayerSprites;
import utilz.LoadSave;
import levels.Wall;
import java.util.List;
import utilz.CollisionHandler;
import java.awt.Color;
import java.util.ArrayList;
import levels.Trap;
import utilz.SoundManager;
 
import static utilz.Constants.PlayerConstants.*;
 
public class Player extends Entity {
 
   
    private BufferedImage[][] animations;
    private int aniTick, aniIndex;
 
    
    private int playerAction = IDLE;
    private int playerDir    = DIR_DOWN;
 
    
    private float xDelta = 100, yDelta = 100;
    private List<Wall> walls;
 
    
    private KeyboardInputs keyInputs;
    private boolean wasSpacePressed = false;
    private int spaceCooldown = 0;
    private static final int SPACE_COOLDOWN_MAX = 200; 
 
   
    
    private WaveManager waveManager;
    private int stepTick = 0;
    private static final int STEPS_PER_WAVE = 100;
    private boolean wasMoving = false;
    
   
    
    private FootprintManager footprintManager = new FootprintManager();
    
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
        waveManager = new WaveManager(new ArrayList<>());
    }
 
    public void setKeyInputs(KeyboardInputs keyInputs) {
        this.keyInputs = keyInputs;
    }
 
  
    private void loadAnimations() {
        
        animations = new BufferedImage[3][4];
 
        
        animations[0][DIR_DOWN]  = LoadSave.GetSpriteAtlas(PlayerSprites.IDLE_DOWN);
        animations[0][DIR_UP]    = LoadSave.GetSpriteAtlas(PlayerSprites.IDLE_UP);
        animations[0][DIR_RIGHT] = LoadSave.GetSpriteAtlas(PlayerSprites.IDLE_RIGHT);
        animations[0][DIR_LEFT]  = LoadSave.GetSpriteAtlas(PlayerSprites.IDLE_LEFT);
 
       
        animations[1][DIR_DOWN]  = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_RIGHT_FOOT_DOWN);
        animations[1][DIR_UP]    = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_RIGHT_FOOT_UP);
        animations[1][DIR_RIGHT] = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_RIGHT_FOOT_RIGHT);
        animations[1][DIR_LEFT]  = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_RIGHT_FOOT_LEFT);
 
      
        animations[2][DIR_DOWN]  = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_LEFT_FOOT_DOWN);
        animations[2][DIR_UP]    = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_LEFT_FOOT_UP);
        animations[2][DIR_RIGHT] = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_LEFT_FOOT_RIGHT);
        animations[2][DIR_LEFT]  = LoadSave.GetSpriteAtlas(PlayerSprites.WALK_LEFT_FOOT_LEFT);
    }
 
   
    public void update() {
        updatePos();
        if (keyInputs != null && keyInputs.isMoving()) {
            
            if (!wasMoving) {
                float centerX = xDelta + DRAW_WIDTH  / 2f;
                float centerY = yDelta + DRAW_HEIGHT / 2f;
                float offset  = 40f;
                float emitX   = centerX;
                float emitY   = centerY;
                switch (playerDir) {
                    case DIR_UP:    emitY -= offset; break;
                    case DIR_DOWN:  emitY += offset; break;
                    case DIR_LEFT:  emitX -= offset; break;
                    case DIR_RIGHT: emitX += offset; break;
                }
                waveManager.emitWave(emitX, emitY, Color.WHITE);
                SoundManager.playStepRight();
            }
            wasMoving = true;
            aniTick++;
            if (aniTick >= ANIMATION_SPEED) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(playerAction)) {
                    aniIndex = 0;
                }
                if (playerAction == WALKING) {
                    if (aniIndex == 0) {
                        SoundManager.playStepRight();
                    } else {
                        SoundManager.playStepLeft();
                    }
                }
                float centerX = xDelta + DRAW_WIDTH  / 2f;
                float centerY = yDelta + DRAW_HEIGHT / 2f;
                float offset  = 20f;
                float emitX   = centerX;
                float emitY   = centerY;
                switch (playerDir) {
                    case DIR_UP:    emitY -= offset; break;
                    case DIR_DOWN:  emitY += offset; break;
                    case DIR_LEFT:  emitX -= offset; break;
                    case DIR_RIGHT: emitX += offset; break;
                }
                waveManager.emitWave(emitX, emitY, Color.WHITE);
                float trailX = centerX;
                float trailY = centerY;
                switch (playerDir) {
                    case DIR_UP:    trailY += offset; break;
                    case DIR_DOWN:  trailY -= offset; break;
                    case DIR_LEFT:  trailX += offset; break;
                    case DIR_RIGHT: trailX -= offset; break;
                }
                footprintManager.addFootprint(trailX, trailY);
            }
        } else {
            aniTick   = 0;
            aniIndex  = 0;
            stepTick  = 0;
            wasMoving = false;
        }
        waveManager.update();
        footprintManager.update();

        
        if (spaceCooldown > 0) spaceCooldown--;

        if (keyInputs.isSpacePressed() && !wasSpacePressed && spaceCooldown == 0) {
            float centerX = xDelta + DRAW_WIDTH  / 2f;
            float centerY = yDelta + DRAW_HEIGHT / 2f;
            waveManager.emitWave(centerX, centerY, Color.WHITE, 90, 600f);
            SoundManager.playSpace();
            spaceCooldown = SPACE_COOLDOWN_MAX;
        }
        wasSpacePressed = keyInputs.isSpacePressed();
    }
 
    private void updatePos() {
        if (keyInputs == null) return;

        playerAction = IDLE;
        float dx = 0, dy = 0;

        if (keyInputs.isUpPressed())    { dy -= MOVE_SPEED; playerDir = DIR_UP;    playerAction = WALKING; }
        if (keyInputs.isDownPressed())  { dy += MOVE_SPEED; playerDir = DIR_DOWN;  playerAction = WALKING; }
        if (keyInputs.isLeftPressed())  { dx -= MOVE_SPEED; playerDir = DIR_LEFT;  playerAction = WALKING; }
        if (keyInputs.isRightPressed()) { dx += MOVE_SPEED; playerDir = DIR_RIGHT; playerAction = WALKING; }

      
        if (dx != 0 && dy != 0) {
            dx /= (float) Math.sqrt(2);
            dy /= (float) Math.sqrt(2);
        }

        xDelta += dx;
        yDelta += dy;

        
        if (walls != null) {
            float centerX = xDelta + DRAW_WIDTH  / 2f;
            float centerY = yDelta + DRAW_HEIGHT / 2f;

            float[] corrected = CollisionHandler.resolveCollisions(centerX, centerY, walls);

            
            xDelta = corrected[0] - DRAW_WIDTH  / 2f;
            yDelta = corrected[1] - DRAW_HEIGHT / 2f;
        }
}
 
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANIMATION_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }
 
    
    public void render(Graphics g) {
        footprintManager.render(g);  
        waveManager.render(g);      

        if (animations == null) return;

        int actionRow;
        if (playerAction == IDLE) {
            actionRow = 0;
        } else {
            actionRow = aniIndex + 1;
        }

        BufferedImage frame = animations[actionRow][playerDir];

        if (frame != null) {
            g.drawImage(frame, (int) xDelta, (int) yDelta, DRAW_WIDTH, DRAW_HEIGHT, null);
        } else {
            System.err.println("[Player] frame null: acción=" + actionRow + " dir=" + playerDir);
        }
    }
 
    
    public void changeXDelta(int value) { this.xDelta += value; }
    public void changeYDelta(int value) { this.yDelta += value; }
 
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }
    
    public void setWalls(List<Wall> walls) {
        this.walls = walls;
        waveManager.setWalls(walls);
    }
    
    public float getX() { 
        return xDelta; 
    }
    public float getY(){ 
        return yDelta; 
    }
    
    public List<SoundWave> getWaves() {
        return waveManager.getWaves();
    }
    public void setTraps(List<Trap> traps) {
        waveManager.setTraps(traps);
    }
    public boolean isMoving() {
        return keyInputs != null && keyInputs.isMoving();
    }
    public boolean isPressedSpace() {
        return keyInputs != null && keyInputs.isSpacePressed();
    }
    
    public void clearWaves() {
        waveManager.clear();
        footprintManager.clear();  
    }

    
    
    
}