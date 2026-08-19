/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Footprint {

    private float x, y;
    private float alpha = 1.0f;
    private static final float FADE_SPEED = 0.001f;;

    public Footprint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        alpha -= FADE_SPEED;
    }

    public void render(Graphics g) {
        if (alpha <= 0) return;
        Graphics2D g2d = (Graphics2D) g;
        int a = (int) (alpha * 255);
        g2d.setColor(new Color(120, 120, 120, a));
        g2d.fillOval((int) x - 5, (int) y - 5, 10, 10);
    }

    public boolean isDead() { return alpha <= 0; }
}
