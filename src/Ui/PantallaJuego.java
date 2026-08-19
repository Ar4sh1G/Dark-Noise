/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import javax.swing.*;
import java.awt.*;

public class PantallaJuego extends JPanel {

    private main.Game game;
    private main.GamePanel gamePanel;

    public PantallaJuego() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
    }

    public void iniciarJuego() {
    
        if (game != null) {
            game.stopGameLoop();
            removeAll();
            game      = null;
            gamePanel = null;
        }

        game      = new main.Game();
        gamePanel = game.getGamePanel();
        add(gamePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        gamePanel.requestFocusInWindow();
    }
    
    public main.GamePanel getGamePanel() {
        return gamePanel;
    }
    
    public main.Game getGame() { return game; }
}

