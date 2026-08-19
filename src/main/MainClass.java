/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import Ui.*;
import javax.swing.*;
import java.awt.*;

public class MainClass {
    public static JFrame       ventana;
    public static CardLayout   cardLayout;
    public static JPanel       contenedor;
    public static JLayeredPane layeredPane;
    public static String       pantallaAnterior = "MENU";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ventana = new JFrame("Dark Noise");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setResizable(false);

            cardLayout = new CardLayout();
            contenedor = new JPanel(cardLayout);
            
            contenedor.add(new MenuPrincipal(),    "MENU");
            contenedor.add(new PantallaJuego(),    "JUEGO");
            contenedor.add(new PantallaOpciones(), "OPCIONES");
            contenedor.add(new PantallaCreditos(), "CREDITOS");
            contenedor.add(new PantallaRanking(), "RANKING");
            

            layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(1248, 672));
            contenedor.setBounds(0, 0, 1248, 672);
            layeredPane.add(contenedor, JLayeredPane.DEFAULT_LAYER);

            ventana.setContentPane(layeredPane);
            ventana.setSize(1248, 672);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            
            new PantallaLogin(ventana).setVisible(true);
            cardLayout.show(contenedor, "MENU");
        });
    }

    public static void mostrarEscape() {
        PantallaJuego pj = (PantallaJuego) contenedor.getComponent(1);
        if (pj.getGame() == null) return;  
        pj.getGame().setPaused(true);
        PantallaEscape escape = new PantallaEscape();
        escape.setBounds(0, 0, 1248, 672);
        layeredPane.add(escape, JLayeredPane.POPUP_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public static void ocultarEscape() {
        for (Component c : layeredPane.getComponentsInLayer(JLayeredPane.POPUP_LAYER)) {
            layeredPane.remove(c);
        }
        layeredPane.revalidate();
        layeredPane.repaint();

        SwingUtilities.invokeLater(() -> {
            PantallaJuego pj = (PantallaJuego) contenedor.getComponent(1);
            if (pj.getGame() != null) {
                pj.getGame().setPaused(false);
                pj.getGamePanel().requestFocusInWindow();
            }
        });
    }

    public static void cambiarPantalla(String nombre) {
        cardLayout.show(contenedor, nombre);
    }
    
    public static void ejecutarEnPantalla(String nombre, java.util.function.Consumer<Component> accion) {
        for (Component c : contenedor.getComponents()) {
            if (c.getName() != null && c.getName().equals(nombre)) {
                accion.accept(c);
                break;
            }
        }
    }

    public static void mostrarCreditos(float tiempo) {
        persistencia.UsuarioDAO dao2 = new persistencia.UsuarioDAO();
        dao2.actualizarMuertes(main.Sesion.getUsuario().getUsuarioId(), main.Sesion.getUsuario().getMuertes());
        
        LogroManager.terminarJuego();
         
        PantallaCreditos pc = (PantallaCreditos) contenedor.getComponent(3);
        cambiarPantalla("CREDITOS");
        pc.requestFocusInWindow();
        pc.iniciar(tiempo);
    }

   
    public static void iniciarNuevaPartida() {
        PantallaJuego pj = (PantallaJuego) contenedor.getComponent(1);
        pj.iniciarJuego();
        cambiarPantalla("JUEGO");
    }
    
    public static void guardarPartida() {
    PantallaJuego pj = (PantallaJuego) contenedor.getComponent(1);
    main.Game game = pj.getGame();
    if (game == null) return;

    persistencia.Usuario u = main.Sesion.getUsuario();
    if (u == null) return;

    persistencia.PartidaGuardada p = new persistencia.PartidaGuardada();
    p.setUsuarioId(u.getUsuarioId());
    p.setNivelActual(game.getLevelManager().getCurrentIndex());
    p.setTiempo(game.getTiempoJugado());

    persistencia.PartidaGuardadaDAO dao = new persistencia.PartidaGuardadaDAO();
    persistencia.UsuarioDAO dao2 = new persistencia.UsuarioDAO();
    dao2.actualizarMuertes(u.getUsuarioId(), u.getMuertes());
    
    persistencia.PartidaGuardada existente = dao.Partida(u.getUsuarioId());
    if (existente.getPartidaId() != 0) {
        existente.setNivelActual(game.getLevelManager().getCurrentIndex());
        existente.setTiempo(game.getTiempoJugado());
        dao.ActualizarDato(existente);
    } else {
        dao.InsertarDato(p);
    }

    System.out.println("[Guardado] Nivel=" + p.getNivelActual() + " Tiempo=" + p.getTiempo());
    }
    
    public static void cargarPartida() {
        persistencia.Usuario u = main.Sesion.getUsuario();
        if (u == null) {
            JOptionPane.showMessageDialog(null, "Iniciá sesión primero.");
            return;
        }

        persistencia.PartidaGuardadaDAO dao = new persistencia.PartidaGuardadaDAO();
        persistencia.PartidaGuardada p = dao.Partida(u.getUsuarioId());

        if (p == null || p.getPartidaId() == 0) {
            JOptionPane.showMessageDialog(null, "No hay partida guardada.");
            return;
        }

        PantallaJuego pj = (PantallaJuego) contenedor.getComponent(1);
        pj.iniciarJuego();
        cambiarPantalla("JUEGO");

       
        SwingUtilities.invokeLater(() -> {
            main.Game game = pj.getGame();
            if (game != null) {
                game.cargarDesdeNivel(p.getNivelActual(), p.getTiempo());
                pj.getGamePanel().requestFocusInWindow();
            }
        });
    }
}
