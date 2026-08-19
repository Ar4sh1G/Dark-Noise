/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import javax.swing.*;
import java.awt.*;
import main.MainClass;

public class MenuPrincipal extends JPanel {

    private JButton btnJugar, btnOpciones, btnSalir, btnPartidaG, btntienda, btnContinuar,  btnRanking;

    public MenuPrincipal() {
        setLayout(new GridBagLayout());  
        setBackground(Color.BLACK);      

       
        btnJugar    = crearBotonImagen("play.png",    "play.png");
        btnOpciones = crearBotonImagen("logros.png", "logros.png");
        btnSalir    = crearBotonImagen("salir.png",    "salir.png");
        btnContinuar = crearBotonImagen("continuar.png", "continuar.png");
        btnRanking = crearBotonImagen("Ranking.png", "Ranking.png");

       
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setOpaque(false);

        btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRanking.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(btnJugar);
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(btnContinuar);  // ← agregalo acá
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(btnOpciones);
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(btnRanking);
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(btnSalir);

        add(panelBotones);

       
        btnJugar.addActionListener(e -> {
            MainClass.cambiarPantalla("JUEGO");
           ((PantallaJuego) MainClass.contenedor.getComponent(1)).iniciarJuego();
        });
        btnOpciones.addActionListener(e ->{
            MainClass.cambiarPantalla("OPCIONES");
            ((PantallaOpciones) MainClass.contenedor.getComponent(2)).cargarLogros();
        });
        btnRanking.addActionListener(e ->{
            MainClass.cambiarPantalla("RANKING");
            ((PantallaRanking) MainClass.contenedor.getComponent(4)).cargarDatos();
        });
        btnSalir.addActionListener(e -> System.exit(0));
        btnContinuar.addActionListener(e -> MainClass.cargarPartida());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private JButton crearBotonImagen(String rutaNormal, String rutaHover) {
        var urlNormal = getClass().getResource("/res/" + rutaNormal);
        var urlHover  = getClass().getResource("/res/" + rutaHover);

        System.out.println("[Menu] Botón normal: " + urlNormal);
        System.out.println("[Menu] Botón hover:  " + urlHover);

        if (urlNormal == null) {
            System.err.println("No se encontró: " + rutaNormal);
            return new JButton(rutaNormal); 
        }

        ImageIcon iconoNormal = new ImageIcon(urlNormal);
        ImageIcon iconoHover  = urlHover != null ? new ImageIcon(urlHover) : iconoNormal;

        JButton btn = new JButton(iconoNormal);
        btn.setRolloverIcon(iconoHover);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
