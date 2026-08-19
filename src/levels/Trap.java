/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Trap {

    public float x, y, width, height;

    public Trap(float x, float y, float width, float height) {
        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;
    }

    
    public boolean contains(float px, float py) {
        return px >= x && px <= x + width &&
               py >= y && py <= y + height;
    }

    
    public void drawDebug(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 0, 0, 30));
        g2d.fillRect((int) x, (int) y, (int) width, (int) height);
    }
}
