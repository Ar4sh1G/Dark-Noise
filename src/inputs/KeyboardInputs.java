/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inputs;
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;
import entities.Player;
 
public class KeyboardInputs implements KeyListener {
 
    private Player player;
 
    private boolean upPressed    = false;
    private boolean downPressed  = false;
    private boolean leftPressed  = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    private boolean escapePressed = false;
 
    public KeyboardInputs(Player player) {
        this.player = player;
    }
 
    @Override
    public void keyTyped(KeyEvent e) {}
 
   @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:      upPressed     = true;  break;
            case KeyEvent.VK_A:      leftPressed   = true;  break;
            case KeyEvent.VK_S:      downPressed   = true;  break;
            case KeyEvent.VK_D:      rightPressed  = true;  break;
            case KeyEvent.VK_SPACE:  spacePressed  = true;  break;
            case KeyEvent.VK_ESCAPE: escapePressed = true;  break;
        }
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:      upPressed     = false; break;
            case KeyEvent.VK_A:      leftPressed   = false; break;
            case KeyEvent.VK_S:      downPressed   = false; break;
            case KeyEvent.VK_D:      rightPressed  = false; break;
            case KeyEvent.VK_SPACE:  spacePressed  = false; break;
            case KeyEvent.VK_ESCAPE: escapePressed = false; break;
        }
    }
 
    public boolean isUpPressed()    { return upPressed;    }
    public boolean isDownPressed()  { return downPressed;  }
    public boolean isLeftPressed()  { return leftPressed;  }
    public boolean isRightPressed() { return rightPressed; }
    public boolean isSpacePressed() { return spacePressed; }
    public boolean isEscapePressed() { return escapePressed; }
 
    public boolean isMoving() {
        return upPressed || downPressed || leftPressed || rightPressed;
    }
}
