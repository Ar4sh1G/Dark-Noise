/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilz;

import javazoom.jl.player.Player;
import java.io.InputStream;

public class SoundManager {

    private static void playSound(String fileName) {
        new Thread(() -> {
            try {
                InputStream is = SoundManager.class.getResourceAsStream("/res/" + fileName);
                if (is == null) {
                    return;
                }
                Player player = new Player(is);
                player.play();
            } catch (Exception e) {
                System.err.println("[Sound] Error: " + e.getMessage());
            }
        }).start();
    }

    public static void playStepRight() { playSound("paso_derecho.MP3"); }
    public static void playStepLeft()  { playSound("paso_izquierdo.MP3"); }
    public static void playSpace() { playSound("space_sound.MP3"); }
    public static void playDeath()    { playSound("muerte.MP3"); }
    public static void playExit()     { playSound("exit.MP3"); }
    public static void playEnemyKill(){ playSound("enemy_kill.MP3"); }
    
    
}
