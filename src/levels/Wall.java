/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class Wall {

    public float x1, y1, x2, y2;

    
    private static final Color COLOR_LIT   = new Color(255, 255, 255, 220);
    
    private static final Color COLOR_DEBUG = new Color(150, 150, 150, 255);

    public Wall(float x1, float y1, float x2, float y2) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
    }

    
    public void drawLit(Graphics g, float alpha) {
        Graphics2D g2d = (Graphics2D) g;
        int a = (int) Math.max(0, Math.min(255, alpha * 255));
        g2d.setColor(new Color(255, 255, 255, a));
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    
    public void drawDebug(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(COLOR_DEBUG);
        g2d.setStroke(new BasicStroke(1f));
        g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }
}
