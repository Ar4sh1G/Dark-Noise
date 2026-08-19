/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import javax.swing.*;
import java.awt.*;
import main.MainClass;

public class PantallaEscape extends JPanel {

    private JButton btnContinuar, btnOpciones, btnSalir;

    public PantallaEscape() {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 0, 0, 180));  
        setOpaque(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        
        JLabel titulo = new JLabel("Pausa");
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        btnContinuar = crearBoton("continuar.png", "Continuar");
        btnSalir     = crearBoton("salir.png",      "Salir");
       
        
        
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        btnContinuar.addActionListener(e -> MainClass.ocultarEscape());

        

        btnSalir.addActionListener(e -> {
            MainClass.ocultarEscape();
            int respuesta = JOptionPane.showConfirmDialog(this,
                        "¿Quieres guardar la partida?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                MainClass.guardarPartida();
            }
            ((PantallaJuego) MainClass.contenedor.getComponent(1)).getGame().stopGameLoop();
            MainClass.cambiarPantalla("MENU");
        });
        
        

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(40));
        panel.add(btnContinuar);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnSalir);

        add(panel);
    }

    private JButton crearBoton(String png, String fallback) {
        var url = getClass().getResource("/res/" + png);
        JButton btn;
        if (url != null) {
            btn = new JButton(new ImageIcon(url));
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
        } else {
            btn = new JButton(fallback);
        }
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
