/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import levels.Wall;
import utilz.CollisionHandler;

public class Enemy extends Entity {

   
    private enum State { IDLE, CHASING }
    private State state = State.IDLE;

    
    private float targetX, targetY;
    private static final float MOVE_SPEED = 0.6f;
    private List<Wall> walls;

   
    private static final float DETECTION_RADIUS = 30f;
    private int loseTrackTick = 0;
    private static final int LOSE_TRACK_TIME = 300;

    
    public static final float HITBOX_RADIUS = 15f;

    
    private float pulseScale = 1.0f;
    private float pulseDir   = 0.02f;
    private static final float PULSE_MIN = 0.7f;
    private static final float PULSE_MAX = 1.3f;
    private static final Color ENEMY_COLOR = new Color(220, 30, 30);
    private float spawnX, spawnY;
    private static final float MAX_CHASE_DIST = 400f;

   
    private float visibility = 0f;
    private static final float VISIBILITY_FADE = 0.008f;

    public Enemy(float x, float y) {
        super(x, y);
        targetX  = x;
        targetY  = y;
        spawnX   = x;  
        spawnY   = y;
    }

    
    public void update(float playerX, float playerY, List<SoundWave> waves) {
        updatePulse();
        detectSound(waves, playerX, playerY);  

        if (visibility > 0) visibility -= VISIBILITY_FADE;

        if (state == State.CHASING) {
            moveTowardsTarget();
            loseTrackTick++;
            if (loseTrackTick >= LOSE_TRACK_TIME) {
                state = State.IDLE;
                loseTrackTick = 0;
            }
        }
    }

    public void hit() {
        visibility = 1.0f;
    }

    private void updatePulse() {
        pulseScale += pulseDir;
        if (pulseScale >= PULSE_MAX) pulseDir = -0.02f;
        if (pulseScale <= PULSE_MIN) pulseDir =  0.02f;
    }

    
    private void detectSound(List<SoundWave> waves, float playerX, float playerY) {
        for (SoundWave w : waves) {
            float dx = w.getX() - x;
            float dy = w.getY() - y;
            float dist = (float) Math.sqrt(dx * dx + dy * dy);

            if (dist < DETECTION_RADIUS) {
                
                targetX = playerX;
                targetY = playerY;
                state = State.CHASING;
                loseTrackTick = 0;
                hit();  
                break;
            }
        }
    }

    private void moveTowardsTarget() {
        float dx = targetX - x;
        float dy = targetY - y;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        
        float dSpawnX = x - spawnX;
        float dSpawnY = y - spawnY;
        float distFromSpawn = (float) Math.sqrt(dSpawnX * dSpawnX + dSpawnY * dSpawnY);
        if (distFromSpawn > MAX_CHASE_DIST) {
            targetX = spawnX;
            targetY = spawnY;
        }

        if (dist > 2f) {
            x += (dx / dist) * MOVE_SPEED;
            y += (dy / dist) * MOVE_SPEED;

            if (walls != null) {
                float[] corrected = CollisionHandler.resolveCollisions(x, y, walls);
                x = corrected[0];
                y = corrected[1];
            }
        } else {
           
            state = State.IDLE;
        }
    }

  
   public void render(Graphics g) {
        if (visibility <= 0) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int a  = (int)(visibility * 255);
        int cx = (int) x;
        int cy = (int) y;

        float innerRadius = 5f  * pulseScale;
        float outerRadius = 25f * pulseScale;

        g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        
        int numLines = 12;
        for (int i = 0; i < numLines; i++) {
            float angle = (float)(i * 2 * Math.PI / numLines);

           
            int x1 = cx + (int)(Math.cos(angle) * innerRadius);
            int y1 = cy + (int)(Math.sin(angle) * innerRadius);
            int x2 = cx + (int)(Math.cos(angle) * outerRadius);
            int y2 = cy + (int)(Math.sin(angle) * outerRadius);

            
            float lineFade = 0.6f + 0.4f * (float)Math.abs(Math.cos(angle));
            int la = (int)(a * lineFade);
            g2d.setColor(new Color(220, 30, 30, Math.min(la, 255)));
            g2d.drawLine(x1, y1, x2, y2);
        }

       
        g2d.setColor(new Color(255, 80, 80, a));
        g2d.fillOval(cx - 3, cy - 3, 6, 6);
    }

    
    public boolean isTouchingPlayer(float playerX, float playerY) {
        float dx = playerX - x;
        float dy = playerY - y;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        return dist < HITBOX_RADIUS + CollisionHandler.PLAYER_RADIUS;
    }

    public void setWalls(List<Wall> walls) { this.walls = walls; }
    public float getX() { return x; }
    public float getY() { return y; }
}
