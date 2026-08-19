/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilz;

import levels.Wall;
import java.util.List;

public class CollisionHandler {

    
    public static final float PLAYER_RADIUS = 20f;

    
    public static float[] resolveCollisions(float px, float py, List<Wall> walls) {
        for (Wall w : walls) {
            float[] nearest = nearestPointOnSegment(px, py, w.x1, w.y1, w.x2, w.y2);
            float nx = nearest[0];
            float ny = nearest[1];

            float dx = px - nx;
            float dy = py - ny;
            float dist = (float) Math.sqrt(dx * dx + dy * dy);

            if (dist < PLAYER_RADIUS && dist > 0) {
                
                float overlap = PLAYER_RADIUS - dist;
                px += (dx / dist) * overlap;
                py += (dy / dist) * overlap;
            }
        }
        return new float[]{px, py};
    }

    
    private static float[] nearestPointOnSegment(
            float px, float py,
            float x1, float y1,
            float x2, float y2) {

        float dx = x2 - x1;
        float dy = y2 - y1;
        float lenSq = dx * dx + dy * dy;

        if (lenSq == 0) return new float[]{x1, y1}; 

        
        float t = ((px - x1) * dx + (py - y1) * dy) / lenSq;
        t = Math.max(0, Math.min(1, t));

        return new float[]{x1 + t * dx, y1 + t * dy};
    }
}
