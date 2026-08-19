/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;
import javax.swing.*;
import java.awt.*;
import main.MainClass;

public class PantallaCreditos extends JPanel {
    private float alpha = 0f;
    private boolean fadingIn  = true;
    private boolean fadingOut = false;
    private int waitTick = 0;
    private static final int WAIT_TIME  = 180;
    private static final float FADE_SPEED = 0.01f;
    private Timer timer;
    private float tiempoFinal = 0f;  
    
    public PantallaCreditos() {
        setBackground(Color.BLACK);
        setLayout(null);
    }

   
    public void iniciar() {
        iniciar(0f);
    }

    
    public void iniciar(float tiempoJugado) {
        this.tiempoFinal = tiempoJugado;
        alpha     = 0f;
        fadingIn  = true;
        fadingOut = false;
        waitTick  = 0;

        if (timer != null) timer.stop();

        timer = new Timer(16, e -> {
            if (fadingIn) {
                alpha += FADE_SPEED;
                if (alpha >= 1f) { alpha = 1f; fadingIn = false; }
            } else if (!fadingOut) {
                waitTick++;
                if (waitTick >= WAIT_TIME) fadingOut = true;
            } else {
                alpha -= FADE_SPEED;
                if (alpha <= 0f) {
                    alpha = 0f;
                    timer.stop();
                    MainClass.cambiarPantalla("MENU");
                }
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        int cx = getWidth() / 2;

        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        FontMetrics fm = g2d.getFontMetrics();
        String titulo = "¡Juego Terminado!";
        g2d.drawString(titulo, cx - fm.stringWidth(titulo) / 2, 180);

       
        int min = (int)(tiempoFinal / 60);
        int seg = (int)(tiempoFinal % 60);
        String tiempo = String.format("Tiempo: %02d:%02d", min, seg);
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        fm = g2d.getFontMetrics();
        g2d.setColor(new Color(255, 215, 0));  // dorado
        g2d.drawString(tiempo, cx - fm.stringWidth(tiempo) / 2, 260);

       
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        fm = g2d.getFontMetrics();
        String creditos = "Créditos";
        g2d.setColor(new Color(180, 180, 180));
        g2d.drawString(creditos, cx - fm.stringWidth(creditos) / 2, 330);

        
        g2d.setFont(new Font("Arial", Font.PLAIN, 22));
        fm = g2d.getFontMetrics();
        String[] nombres = {
            " — Lucas Valero —",
            "— Martin Curuchet —",
        };
        int y = 390;
        for (String nombre : nombres) {
            g2d.drawString(nombre, cx - fm.stringWidth(nombre) / 2, y);
            y += 40;
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
