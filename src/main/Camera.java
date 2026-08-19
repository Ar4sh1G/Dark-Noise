/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import static main.Game.*;

public class Camera {

    private float x, y;

    public Camera() {
        this.x = 0;
        this.y = 0;
    }

    public void update(float playerX, float playerY) {
        
        x = playerX - GAME_WIDTH  / 2f;
        y = playerY - GAME_HEIGHT / 2f;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
