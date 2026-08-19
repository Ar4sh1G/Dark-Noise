/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

public class TutorialOverlay {

    private BufferedImage wasdImage;
    private BufferedImage spaceImage;

    private enum TutorialState { WASD_FADEIN, WASD_VISIBLE, WASD_FADEOUT, SPACE_FADEIN, SPACE_VISIBLE, SPACE_FADEOUT, DONE }
    private TutorialState state = TutorialState.WASD_FADEIN;

    private float alpha = 0f;
    private static final float FADE_IN_SPEED  = 0.02f;
    private static final float FADE_OUT_SPEED = 0.02f;
    private static final int   FADE_DELAY     = 90;
    private int fadeTick = 0;

    public TutorialOverlay() {
        wasdImage  = LoadSave.GetSpriteAtlas("WASD.png");
        spaceImage = LoadSave.GetSpriteAtlas("space.png");
    }

    public void playerMoved() {
        if (state == TutorialState.WASD_VISIBLE) {
            state    = TutorialState.WASD_FADEOUT;
            fadeTick = 0;
        }
    }

    public void update() {
        if (state == TutorialState.DONE) return;

        switch (state) {
            case WASD_FADEIN:
                alpha += FADE_IN_SPEED;
                if (alpha >= 1f) { alpha = 1f; state = TutorialState.WASD_VISIBLE; }
                break;

            case WASD_VISIBLE:
               
                break;

            case WASD_FADEOUT:
                fadeTick++;
                if (fadeTick >= FADE_DELAY) {
                    alpha -= FADE_OUT_SPEED;
                    if (alpha <= 0) {
                        alpha    = 0f;
                        fadeTick = 0;
                        state    = TutorialState.SPACE_FADEIN;
                    }
                }
                break;

            case SPACE_FADEIN:
                alpha += FADE_IN_SPEED;
                if (alpha >= 1f) { alpha = 1f; state = TutorialState.SPACE_VISIBLE; }
                break;

            case SPACE_VISIBLE:
                
                break;

            case SPACE_FADEOUT:
                alpha -= FADE_OUT_SPEED;
                if (alpha <= 0) {
                    alpha = 0f;
                    state = TutorialState.DONE;
                }
                break;
        }
    }

    public void render(Graphics g, int screenWidth, int screenHeight) {
        if (state == TutorialState.DONE) return;

        BufferedImage img = null;
        if (state == TutorialState.WASD_FADEIN  || 
            state == TutorialState.WASD_VISIBLE || 
            state == TutorialState.WASD_FADEOUT) {
            img = wasdImage;
        } else {
            img = spaceImage;
        }

        if (img == null) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        int x = (screenWidth  - img.getWidth())  / 2;
        int y =  screenHeight - img.getHeight() - 40;

        g2d.drawImage(img, x, y, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public boolean isVisible() { return state != TutorialState.DONE; }
    
    public void playerPressedSpace() {
        if (state == TutorialState.SPACE_VISIBLE) {
            state    = TutorialState.SPACE_FADEOUT;
            fadeTick = 0;
        }
    }
    
}
