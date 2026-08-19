/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import persistencia.Catalogo;
import persistencia.CatalogoDAO;
import main.MainClass;
import main.Sesion;
import persistencia.Inventario;
import persistencia.InventarioDAO;

public class PantallaOpciones extends JPanel {

    
    private JPanel panelGrilla;
    private JButton btnVolver;

    public PantallaOpciones() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        
        JLabel titulo = new JLabel("LOGROS", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        
        panelGrilla = new JPanel(new GridLayout(0, 6, 15, 15));
        panelGrilla.setBackground(Color.BLACK);
        panelGrilla.setBorder(BorderFactory.createEmptyBorder(10, 30, 15, 30));

        JScrollPane scroll = new JScrollPane(panelGrilla);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.BLACK);
        scroll.setBackground(Color.BLACK);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(600, 350)); 
        add(scroll, BorderLayout.CENTER);
        
        
        btnVolver = crearBoton("Volver");
        btnVolver.addActionListener(e -> MainClass.cambiarPantalla("MENU"));

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.BLACK);
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 0, 60, 0));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    
    public void cargarLogros() {
        panelGrilla.removeAll();

        if (Sesion.getUsuario() == null) return;

        CatalogoDAO dao = new CatalogoDAO();
        List<Catalogo> logros = dao.ObtenerTodo();
        InventarioDAO dao2 = new InventarioDAO();
        List<Inventario> logros_conseguidos = dao2.ObtenerTodoUsuario(Sesion.getUsuario().getUsuarioId());

        for (Catalogo logro : logros) {
            panelGrilla.add(crearTarjetaLogro(logro, logros_conseguidos));
        }

        panelGrilla.revalidate();
        panelGrilla.repaint();
    }

    private JPanel crearTarjetaLogro(Catalogo logro, List<Inventario> inve) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setPreferredSize(new Dimension(110, 110));
        tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        
        Color fondo = logro.isConseguido() ? new Color(0x2E6DA4) : new Color(0x2A2A2A);
        tarjeta.setBackground(fondo);
        tarjeta.setBorder(BorderFactory.createLineBorder(
            logro.isConseguido() ? new Color(0x5A9BD4) : new Color(0x444444), 2));

        
        for(Inventario i : inve){     
            if(i.getItemId() == logro.getItemId()){
                logro.setConseguido(true);
            }
        }
        JLabel icono = new JLabel(logro.isConseguido() ? "UNLOCKED" : "BLOCKED", SwingConstants.CENTER);
        icono.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        icono.setAlignmentX(Component.CENTER_ALIGNMENT);
        icono.setForeground(Color.WHITE);

        
        JLabel nombre = new JLabel(logro.getNombre(), SwingConstants.CENTER);
        nombre.setForeground(logro.isConseguido() ? Color.WHITE : Color.GRAY);
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombre.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        tarjeta.add(Box.createVerticalGlue());
        tarjeta.add(icono);
        tarjeta.add(nombre);
        tarjeta.add(Box.createVerticalGlue());

        
        tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                mostrarDetalle(logro);
            }
        });

        return tarjeta;
    }

    private void mostrarDetalle(Catalogo logro) {
        JDialog dialog = new JDialog();
        dialog.setTitle(logro.getNombre());
        dialog.setModal(true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.BLACK);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel estado = new JLabel(logro.isConseguido() ? "+ Conseguido" : "- No conseguido");
        estado.setForeground(logro.isConseguido() ? new Color(0x5A9BD4) : Color.GRAY);
        estado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        estado.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descripcion = new JLabel("<html><center>" + logro.getDescripcion() + "</center></html>");
        descripcion.setForeground(Color.WHITE);
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        descripcion.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JButton btnCerrar = crearBoton("Cerrar");
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.addActionListener(e -> dialog.dispose());

        panel.add(estado);
        panel.add(descripcion);
        panel.add(btnCerrar);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setMaximumSize(new Dimension(150, 40));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}