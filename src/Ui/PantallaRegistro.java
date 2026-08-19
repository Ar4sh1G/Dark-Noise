/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import javax.swing.*;
import java.awt.*;
import main.MainClass;
import persistencia.Usuario;
import persistencia.UsuarioDAO;
import java.util.List;


public class PantallaRegistro extends JDialog {
    private JTextField txtUsuario;
    private JPasswordField txtPassword, txtConfirmar;
    private JButton btnRegistrar, btnVolver;
    private JFrame padre;

    public PantallaRegistro(JFrame padre) {
        super(padre, "Crear cuenta", true);
        this.padre = padre;
        setSize(350, 450);
        setLocationRelativeTo(padre);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);

        
        JLabel titulo = new JLabel("CREAR CUENTA");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        txtUsuario  = crearCampo("Usuario");
        txtPassword = new JPasswordField(20);
        txtConfirmar = new JPasswordField(20);
        estilizarCampo(txtPassword,  "Contraseña");
        estilizarCampo(txtConfirmar, "Confirmar contraseña");

        
        btnRegistrar = crearBoton("Registrar Cuenta");
        btnVolver    = crearBoton("Volver");

        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(txtUsuario);
        panel.add(Box.createVerticalStrut(10));
        panel.add(txtPassword);
        panel.add(Box.createVerticalStrut(10));
        panel.add(txtConfirmar);
        panel.add(Box.createVerticalStrut(25));
        panel.add(btnRegistrar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVolver);

        setContentPane(panel);

        
        btnRegistrar.addActionListener(e -> intentarRegistro());
        btnVolver.addActionListener(e -> {dispose();
               new PantallaLogin(padre).setVisible(true); });
    }

    private void intentarRegistro() {
        String usuario   = txtUsuario.getText().trim();
        String password  = new String(txtPassword.getPassword()).trim();
        String confirmar = new String(txtConfirmar.getPassword()).trim();
        boolean esta = false;

        if (usuario.isEmpty() || usuario.equals("Usuario")|| password.isEmpty() || confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Completá todos los campos.", "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(confirmar)) {
            JOptionPane.showMessageDialog(this,
                "Las contraseñas no coinciden.", "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> user = dao.ObtenerTodo();
        Usuario U = new Usuario(usuario, password);
        for(Usuario dato : user ){
            esta = !dato.getUsername().equals(U.getUsername());
        }
        if(esta){
            dao.InsertarDato(U);
            System.out.println("Registro: " + usuario);
            JOptionPane.showMessageDialog(this,
            "Cuenta creada correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new PantallaLogin(this.padre).setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(this,
                "Nombre de Usurio fa registrado", "Aviso",
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
   
}
