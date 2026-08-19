/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class RankingDAO extends Consultas<Ranking>{

    public RankingDAO() {
    }    

    @Override
    public boolean InsertarDato(Ranking obj) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO ranking (UsuarioId, TiempoMax, Fecha) VALUES (?, ?, ?)");
            ps.setInt(1, obj.getUsuarioId());
            ps.setFloat(2, obj.getTiempoMax());
            ps.setDate(3, obj.getFecha());
            
            return ps.executeUpdate() > 0; 

        } catch (SQLException e) {
            System.err.println("Error en InsertarDato: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }    
    }

    @Override
    public List ObtenerTodo() {
        List<Ranking> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement(
                "SELECT u.Username, r.TiempoMax, r.Fecha " +
                "FROM ranking r " +
                "JOIN usuarios u ON r.UsuarioId = u.UsuarioId " +
                "ORDER BY r.TiempoMax ASC"
            );
            rs = ps.executeQuery();

            while (rs.next()) {
                Ranking r = new Ranking();
                r.setNombre(rs.getString("Username"));
                r.setTiempoMax(rs.getFloat("TiempoMax"));
                r.setFecha(rs.getDate("Fecha"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerRankingCompleto: " + e.getMessage());
        } finally {
            cerrarRecursos(ps, rs);
        }

        return lista;   
    }

    @Override
    public boolean ActualizarDato(Ranking obj) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "UPDATE Ranking SET TiempoMax = ?, Fecha = ? WHERE UsuarioId = ?"
            );
            ps.setFloat(1, obj.getTiempoMax());
            ps.setDate(2, obj.getFecha());
            ps.setInt(3, obj.getUsuarioId());
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
            ps = con.prepareStatement("DELETE FROM Ranking WHERE UsuarioId = ?");
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
