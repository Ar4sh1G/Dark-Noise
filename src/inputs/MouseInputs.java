/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inputs;
 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;
import entities.Player;
 
public class MouseInputs implements MouseListener, MouseMotionListener {
 
    private Player player;
 
    public MouseInputs(GamePanel gamePanel) {
        this.player = gamePanel.getGame().getPlayer();
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse click");
    }
 
    @Override public void mousePressed(MouseEvent e)  {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e)  {}
    @Override public void mouseExited(MouseEvent e)   {}
    @Override public void mouseDragged(MouseEvent e)  {}
 
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}