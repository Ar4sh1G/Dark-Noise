/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import levels.Wall;
import entities.WaveManager;

public class ExitBeacon {

    private float x, y;
    private WaveManager waveManager;

    
    private static final int PULSE_INTERVAL = 300;
    private int pulseTick = PULSE_INTERVAL;

    
    private static final Color EXIT_COLOR = new Color(0, 200, 180);

    public ExitBeacon(float x, float y, List<Wall> walls) {
        this.x = x;
        this.y = y;
        waveManager = new WaveManager(walls);
    }

    public void update() {
        pulseTick++;
        if (pulseTick >= PULSE_INTERVAL) {
            pulseTick = 0;
            waveManager.emitWave(x, y, EXIT_COLOR, 60, 500f);
        }
        waveManager.update();
    }

    public void render(Graphics g) {
        waveManager.render(g);
    }

    public void setWalls(List<Wall> walls) {
        waveManager.setWalls(walls);
    }
    
    public void setTraps(List<levels.Trap> traps) {
        waveManager.setTraps(traps);
    }
}
