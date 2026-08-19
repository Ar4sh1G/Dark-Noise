/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import javax.swing.*;
import java.awt.*;
import main.MainClass;
import main.Sesion;
import persistencia.Usuario;

import persistencia.UsuarioDAO;

public class PantallaLogin extends JDialog {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar, btnIrRegistro, btnsalir;
    
    public PantallaLogin(JFrame padre){
        super(padre, "Iniciar Sesión", true); 
        setSize(350, 400);
        setLocationRelativeTo(padre);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        
        JLabel titulo = new JLabel("INICIAR SESIÓN");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        txtUsuario = crearCampo("Usuario");
        txtPassword = new JPasswordField(20);
        estilizarCampo(txtPassword, "Contraseña");
        
        
        btnIngresar  = crearBoton("Ingresar");
        btnIrRegistro = crearBoton("Crear Cuenta");
        btnsalir = crearBoton("Salir");

        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIrRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnsalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(txtUsuario);
        panel.add(Box.createVerticalStrut(10));
        panel.add(crearCampoPassword(txtPassword,"Contraseña"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnIngresar);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnIrRegistro);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnsalir);
        
        setContentPane(panel);

        
        btnIngresar.addActionListener(e -> intentarLogin());
        btnIrRegistro.addActionListener(e -> {dispose(); 
            new PantallaRegistro(padre).setVisible(true);});
        btnsalir.addActionListener(e -> System.exit(0));
    }
    
    private void intentarLogin() {
        String usuario  = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Completá todos los campos.", "Aviso",
                JOptionPane.WARNING_MESSAGE);
       }
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.login(usuario, password);
        Sesion.setUsuario(u);
        if (u != null){
            System.out.println("Login: " + usuario + " / " + password);
            dispose();
            MainClass.cambiarPantalla("MENU"); 
        }
        else{
            JOptionPane.showMessageDialog(this,
                "Usuario o Contraseña incorrecto!\n intente devuelta", "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }

     private JTextField crearCampo(String placeholder) {
        JTextField campo = new JTextField(20);
        estilizarCampo(campo, placeholder);
        return campo;
    }

    private void estilizarCampo(JTextField campo, String placeholder) {
        campo.setMaximumSize(new Dimension(300, 40));
        campo.setBackground(Color.DARK_GRAY);
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        campo.setText(placeholder);
        campo.setForeground(Color.GRAY);
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY);
                }
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
      btn.setMaximumSize(new Dimension(250, 40));
      btn.setAlignmentX(Component.CENTER_ALIGNMENT);
      btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      return btn;
    }
   
    private JPanel crearCampoPassword(JPasswordField campo, String placeholder) {
        estilizarCampo(campo, placeholder);

        JButton btnMostrar = new JButton("*");
        btnMostrar.setBackground(Color.DARK_GRAY);
        btnMostrar.setForeground(Color.WHITE);
        btnMostrar.setBorderPainted(false);
        btnMostrar.setFocusPainted(false);
        btnMostrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMostrar.setFont(new Font("Arial", Font.PLAIN, 14));

        btnMostrar.addActionListener(e -> {
            if (campo.getEchoChar() == 0) {
                campo.setEchoChar('●');       
                btnMostrar.setText("*");
            } else {
                campo.setEchoChar((char) 0);  
                btnMostrar.setText("#");
            }
                
        });

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setOpaque(false);
        contenedor.setMaximumSize(new Dimension(300, 40));
        contenedor.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(campo, BorderLayout.CENTER);
        contenedor.add(btnMostrar, BorderLayout.EAST);

        return contenedor;
    }
}
