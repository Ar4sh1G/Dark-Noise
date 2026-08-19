/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import levels.Wall;
import java.util.List;
import levels.Trap;
import java.util.ArrayList;


public class SoundWave {

    
    private float originX, originY;

    
    private float angle;

   
    private float x, y;

   
    private static final float SPEED = 1f;

    
    private static final float MAX_DIST = 500f;
    private float maxDist;

    
    private float distTraveled = 0;

    
    private float alpha = 1.0f;

   
    private static final float FADE_SPEED = 0.04f;

    
    private boolean hitWall = false;

    
    private Color color;

   
    private boolean dead = false;
    
    private static final int MAX_BOUNCES = 2; 
    private int bounces = 0;
    
    private Wall wallHit = null;
    
    private List<Trap> traps = new ArrayList<>();
    
    private boolean inTrap = false;
    
    public SoundWave(float originX, float originY, float angle, Color color) {
        this(originX, originY, angle, color, 500f);
    }

    public SoundWave(float originX, float originY, float angle, Color color, float maxDist) {
        this.originX = originX;
        this.originY = originY;
        this.x       = originX;
        this.y       = originY;
        this.angle   = angle;
        this.color   = color;
        this.maxDist = maxDist;
    }

    public void update(List<Wall> walls) {
        if (dead) return;

        if (!hitWall) {
            float dx = (float) Math.cos(angle) * SPEED;
            float dy = (float) Math.sin(angle) * SPEED;

            x += dx;
            y += dy;
            distTraveled += SPEED;

            for (Wall w : walls) {
                if (intersectsWall(w)) {
                    if (bounces < MAX_BOUNCES) {
                            bounces++;

                            
                            float wallDx = w.x2 - w.x1;
                            float wallDy = w.y2 - w.y1;
                            float len = (float) Math.sqrt(wallDx * wallDx + wallDy * wallDy);
                            float nx = -wallDy / len;
                            float ny =  wallDx / len;

                           
                            float dot = (float) Math.cos(angle) * nx + (float) Math.sin(angle) * ny;
                            float reflectX = (float) Math.cos(angle) - 2 * dot * nx;
                            float reflectY = (float) Math.sin(angle) - 2 * dot * ny;
                            angle = (float) Math.atan2(reflectY, reflectX);

                           
                            float nudge = 3f;
                            originX = x + (float) Math.cos(angle) * nudge;
                            originY = y + (float) Math.sin(angle) * nudge;
                            x = originX;
                            y = originY;

                    } else {
                            hitWall = true;
                            wallHit = w;
                            }
                    break;
                }
            }

            if (distTraveled >= maxDist) {
                dead = true;
            }

        } else {
            alpha -= FADE_SPEED;
            if (alpha <= 0) {
                alpha = 0;
                dead  = true;
            }
        }
    }

    public void render(Graphics g) {
        if (dead) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1f));

        if (!hitWall) {
          
            float segmentSize = 4f;
            float totalDist   = (float) Math.sqrt(
                Math.pow(x - originX, 2) + Math.pow(y - originY, 2));

            float dirX = (x - originX) / (totalDist == 0 ? 1 : totalDist);
            float dirY = (y - originY) / (totalDist == 0 ? 1 : totalDist);

            float segX = originX;
            float segY = originY;
            float traveled = 0;

            while (traveled < totalDist) {
                float nextX = segX + dirX * segmentSize;
                float nextY = segY + dirY * segmentSize;

                
                boolean segInTrap = false;
                for (Trap t : traps) {
                    if (t.contains(segX, segY)) {
                        segInTrap = true;
                        break;
                    }
                }

                
                float distAlpha = 1.0f - (distTraveled / maxDist);
                int da = (int)(distAlpha * 255);

                if (segInTrap) {
                    g2d.setColor(new Color(255, 30, 30, da));
                } else {
                    g2d.setColor(new Color(
                        color.getRed(), color.getGreen(), color.getBlue(), da));
                }

                g2d.drawLine((int) segX, (int) segY, (int) nextX, (int) nextY);

                segX     = nextX;
                segY     = nextY;
                traveled += segmentSize;
            }
        }
    }

    
    private boolean intersectsWall(Wall w) {
        float x1 = originX, y1 = originY;
        float x2 = x,       y2 = y;
        float x3 = w.x1,    y3 = w.y1;
        float x4 = w.x2,    y4 = w.y2;

        float denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (Math.abs(denom) < 0.0001f) return false; 

        float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denom;
        float u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / denom;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            
            x = x1 + t * (x2 - x1);
            y = y1 + t * (y2 - y1);
            return true;
        }
        return false;
    }
    
    public void checkTraps(List<Trap> traps) {
        for (Trap t : traps) {
            if (t.contains(x, y)) {
                inTrap = true;
                return;
            }
        }
        inTrap = false;
    }

    public boolean isInTrap() { return inTrap; }

    public boolean isDead() { return dead; }
    public float getX()       { return x;       }
    public float getY()       { return y;       }
    public float getOriginX() { return originX; }
    public float getOriginY() { return originY; }
    
    public void setTraps(List<Trap> traps) {
        this.traps = traps;
    }
    
    
}
