/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class CatalogoDAO extends Consultas<Catalogo> {

    public CatalogoDAO() {
    }

    @Override
    public boolean InsertarDato(Catalogo obj) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "INSERT INTO catalogo_item (Nombre, Descripcion) VALUES (?, ?)"
            );
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en insertar: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }
    }

    @Override
    public List<Catalogo> ObtenerTodo() {
        List<Catalogo> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM catalogo_item");
            rs = ps.executeQuery();

            while (rs.next()) {
                Catalogo c = new Catalogo();
                c.setItemId(rs.getInt("ItemId"));
                c.setNombre(rs.getString("Nombre"));
                c.setDescripcion(rs.getString("Descripcion"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerTodos: " + e.getMessage());
        } finally {
            cerrarRecursos(ps, rs);
        }

        return lista;
    }

    @Override
    public boolean ActualizarDato(Catalogo obj) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "UPDATE catalogo_item SET Nombre = ?, Descripcion = ? WHERE ItemId = ?"
            );
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getItemId());
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
            ps = con.prepareStatement("DELETE FROM catalogo_item WHERE ItemId = ?");
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
