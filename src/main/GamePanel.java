/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
 
import javax.swing.JPanel;
import java.awt.Graphics;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Dimension;
import java.awt.Color;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import java.awt.Graphics2D;
 
public class GamePanel extends JPanel {
 
    private KeyboardInputs keyInputs;
    private Game game;
    private float deathAlpha = 0f;
    private boolean dying = false;
    private static final float DEATH_FADE_SPEED = 0.02f;
 
    public GamePanel(Game game) {
        this.game = game;
 
        setPanelSize(); 
 
        setFocusable(true);
        requestFocusInWindow();
 
        
        keyInputs = new KeyboardInputs(game.getPlayer());
        addKeyListener(keyInputs);
        
        game.getPlayer().setKeyInputs(keyInputs);
 
        MouseInputs mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
 
    public void updateGame() {
        if (dying) {
            deathAlpha += DEATH_FADE_SPEED;
        }
    }
 
    public void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        game.render(g);

        
        if (dying) {
            Graphics2D g2d = (Graphics2D) g;
            int a = (int)(deathAlpha * 255);
            g2d.setColor(new Color(0, 0, 0, Math.min(a, 255)));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            if (deathAlpha >= 1f) {
                dying = false;
                deathAlpha = 0f;
                Sesion.getUsuario().addMuertes();
                LogroManager.jugadorMurio(Sesion.getUsuario().getMuertes());
                game.resetLevel();  
            }
        }
    }
    
    public void triggerDeath() {
        if (!dying) {
            dying     = true;
            deathAlpha = 0f;
            utilz.SoundManager.playDeath();
        }
    }   
 
    public Game getGame() {
        return game;
    }
    
    public boolean isEscapePressed() {
        return keyInputs != null && keyInputs.isEscapePressed();
    }
}
    
    
    
    

