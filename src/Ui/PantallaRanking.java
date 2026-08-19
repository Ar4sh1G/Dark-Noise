/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import persistencia.RankingDAO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import main.MainClass;
import persistencia.Ranking;

public class PantallaRanking extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnVolver;

    public PantallaRanking() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        
        JLabel titulo = new JLabel("RANKING", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        
        String[] columnas = {"#", "Jugador", "Tiempo", "Fecha"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla de solo lectura
            }
        };

        tabla = new JTable(modelo);
        estilizarTabla();

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBackground(Color.BLACK);
        scroll.getViewport().setBackground(Color.BLACK);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        add(scroll, BorderLayout.CENTER);

        
        btnVolver = crearBoton("Volver");
        btnVolver.addActionListener(e -> MainClass.cambiarPantalla("MENU"));

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.BLACK);
        panelBoton.setBorder(BorderFactory.createEmptyBorder(15, 0, 35, 0));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    
    public void cargarDatos() {
        modelo.setRowCount(0); 

        RankingDAO dao = new RankingDAO();
        List<Ranking> lista = dao.ObtenerTodo();

        int posicion = 1;
        for (Ranking r : lista) {
            modelo.addRow(new Object[]{
                posicion++,
                r.getNombre(),          
                r.getTiempoMax(),          
                r.getFecha()           
            });
        }
    }

    private void estilizarTabla() {
        tabla.setBackground(new Color(0x1A1A1A));
        tabla.setForeground(Color.WHITE);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(35);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionBackground(new Color(0x2E6DA4));
        tabla.setSelectionForeground(Color.WHITE);

        
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(0x2E6DA4));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setReorderingAllowed(false);

        
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);  
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200); 
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100); 
        tabla.getColumnModel().getColumn(3).setPreferredWidth(120); 

        
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        centrado.setBackground(new Color(0x1A1A1A));
        centrado.setForeground(Color.WHITE);
        tabla.getColumnModel().getColumn(0).setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(2).setCellRenderer(centrado);

        
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                if (isSelected) {
                    setBackground(new Color(0x2E6DA4));
                } else {
                    setBackground(row % 2 == 0 ? new Color(0x1A1A1A) : new Color(0x222222));
                }
                setForeground(Color.WHITE);
                if (col == 0 || col == 2) setHorizontalAlignment(SwingConstants.CENTER);
                else setHorizontalAlignment(SwingConstants.LEFT);
                return this;
            }
        });
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setPreferredSize(new Dimension(150, 40));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
