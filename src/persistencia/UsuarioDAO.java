/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class UsuarioDAO extends Consultas<Usuario>{

    public UsuarioDAO() {
    }

    
    public Usuario login(String user, String pass){
        List<Usuario> L = ObtenerTodo();
        Usuario U = new Usuario(user,pass);
        for(Usuario dato : L){
            if(dato.getPassword().equals(U.getPassword()) && dato.getUsername().equals(U.getUsername())){
                U.setUsuarioId(dato.getUsuarioId());
                return U;
            }
        }
        return null;
    }
    @Override
    public boolean InsertarDato(Usuario obj) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO usuarios ( Username, Password) VALUES (?, ?)");
            ps.setString(1, obj.getUsername());
            ps.setString(2, obj.getPassword());
            return ps.executeUpdate() > 0; 
        } catch (SQLException e) {
            System.err.println("Error en InsertarDato: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }    
    }

    @Override
    public List<Usuario> ObtenerTodo() {
        List<Usuario> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM usuarios");
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setUsuarioId(rs.getInt("UsuarioId"));
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setMuertes(rs.getInt("muertes"));
                lista.add(u);
            }
        } catch (SQLException e) {
           System.err.println("Error en ObtenerTodo: " + e.getMessage());
        } finally {
            cerrarRecursos(ps, rs);
        }

        return lista;
    }

    @Override
    public boolean ActualizarDato(Usuario obj) {
        PreparedStatement ps = null;
        
        try{
            ps = con.prepareStatement("UPDATE usuarios SET Username = ?, Password = ?, muertes = ? WHERE id = ?");
            ps.setString(1, obj.getUsername());
            ps.setString(2, obj.getPassword());
            ps.setInt(3, obj.getMuertes());
            ps.setInt(4, obj.getUsuarioId());
            return ps.executeUpdate() > 0;
  
        }
        catch(SQLException e){
            System.err.println("Error en ActualizarDato: " + e.getMessage());
            return false;
        }
        finally{
            cerrarRecursos(ps, null);
        }
    }

    @Override
    public boolean EliminarDato(int id) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en EliminarDato: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }
    }
    
    public void actualizarMuertes(int usuarioId, int muertes) {
    PreparedStatement ps = null;

    try {
        ps = con.prepareStatement(
            "UPDATE usuarios SET muertes = ? WHERE UsuarioId = ?"
        );
        ps.setInt(1, muertes);
        ps.setInt(2, usuarioId);
        
    } catch (SQLException e) {
        System.err.println("Error en actualizarMuertes: " + e.getMessage());
    } finally {
        cerrarRecursos(ps, null);
    }
}

    
}
