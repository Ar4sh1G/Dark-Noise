/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class InventarioDAO extends Consultas<Inventario> {

    public InventarioDAO() {
    }

    @Override
    public boolean InsertarDato(Inventario obj) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "INSERT INTO inventario (UsuarioId, ItemId) VALUES (?, ?)"
            );
            ps.setInt(1, obj.getUsuarioId());
            ps.setInt(2, obj.getItemId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en insertar: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }
    }

    @Override
    public List<Inventario> ObtenerTodo() {
        List<Inventario> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM inventario");
            rs = ps.executeQuery();

            while (rs.next()) {
                Inventario i = new Inventario();
                i.setInventarioId(rs.getInt("InventarioId"));
                i.setUsuarioId(rs.getInt("UsuarioId"));
                i.setItemId(rs.getInt("ItemId"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerTodos: " + e.getMessage());
        } finally {
            cerrarRecursos(ps, rs);
        }

        return lista;
    }
    
    public List<Inventario> ObtenerTodoUsuario(int id) {
        List<Inventario> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM inventario WHERE UsuarioId = "+id+"");
            rs = ps.executeQuery();

            while (rs.next()) {
                Inventario i = new Inventario();
                i.setInventarioId(rs.getInt("InventarioId"));
                i.setUsuarioId(rs.getInt("UsuarioId"));
                i.setItemId(rs.getInt("ItemId"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerTodos: " + e.getMessage());
        } finally {
            cerrarRecursos(ps, rs);
        }

        return lista;
    }

    @Override
    public boolean ActualizarDato(Inventario obj) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "UPDATE inventario SET UsuarioId = ?, ItemId = ? WHERE InventarioId = ?"
            );
            ps.setInt(1, obj.getUsuarioId());
            ps.setInt(2, obj.getItemId());
            ps.setInt(4, obj.getInventarioId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en actualizar: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }
    }

    @Override
    public boolean EliminarDato(int id) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM inventario WHERE InventarioId = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en eliminar: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }
    }

    
}
