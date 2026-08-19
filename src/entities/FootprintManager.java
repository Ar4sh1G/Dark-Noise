/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FootprintManager {

    private List<Footprint> footprints = new CopyOnWriteArrayList<>();

    public void addFootprint(float x, float y) {
        footprints.add(new Footprint(x, y));
    }

    public void update() {
        List<Footprint> toRemove = new ArrayList<>();

        for (Footprint f : footprints) {
            f.update();
            if (f.isDead()) toRemove.add(f);
        }

        footprints.removeAll(toRemove);
    }

    public void render(Graphics g) {
        for (Footprint f : footprints) f.render(g);
    }
    
    public void clear() {
        footprints.clear();
    }
    
    
}
