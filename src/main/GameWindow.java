/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import javax.swing.JFrame;
        
public class GameWindow extends JFrame{
    
    public GameWindow(GamePanel gamePanel){
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        add(gamePanel);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        
        setVisible(true);
    }
    
}
