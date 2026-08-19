/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import levels.Wall;
import levels.Trap;
import java.util.concurrent.CopyOnWriteArrayList;

public class WaveManager {

    private List<SoundWave> waves = new CopyOnWriteArrayList<>();
    private List<Wall> walls;
    private List<Trap> traps = new ArrayList<>();

    
    private static final int RAYS_PER_STEP = 60;

    public WaveManager(List<Wall> walls) {
        this.walls = walls;
    }

    
    public void emitWave(float x, float y, Color color) {
        emitWave(x, y, color, RAYS_PER_STEP, 300f);
    }

    public void update() {
        
        List<SoundWave> toRemove = new ArrayList<>();

        for (SoundWave w : waves) {
            w.update(walls);
            w.checkTraps(traps);
            if (w.isDead()) toRemove.add(w);
        }

        waves.removeAll(toRemove);
    }

    public void render(Graphics g) {
        for (SoundWave w : waves) {
            w.render(g);
        }
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }
    
    public void emitWave(float x, float y, Color color, int rays, float maxDist) {
        float angleStep = (float)(2 * Math.PI / rays);
        for (int i = 0; i < rays; i++) {
            float angle = i * angleStep;
            SoundWave w = new SoundWave(x, y, angle, color, maxDist);
            w.setTraps(traps);  
            waves.add(w);
        }
    }
    
    public List<SoundWave> getWaves() {
        return waves;
    }
    
    public void setTraps(List<Trap> traps) {
        this.traps = traps;
    }
    
    public void clear() {
        waves.clear();
    }
    
   

    
    
}