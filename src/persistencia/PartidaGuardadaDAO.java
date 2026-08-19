/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class PartidaGuardadaDAO extends Consultas<PartidaGuardada> {

    public PartidaGuardadaDAO() {
    }

    
    public PartidaGuardada Partida(int usuarioID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PartidaGuardada P = new PartidaGuardada();

        try {
            ps = con.prepareStatement(
                "SELECT * FROM partida_guardada WHERE UsuarioId = ?");
            ps.setInt(1, usuarioID);  
            rs = ps.executeQuery();

            if (rs.next()) {  
                P.setPartidaId(rs.getInt("PartidaId"));
                P.setUsuarioId(rs.getInt("UsuarioId"));
                P.setNivelActual(rs.getInt("NivelActual"));
                P.setTiempo(rs.getFloat("Tiempo"));
            }
        } catch (SQLException e) {
            System.err.println("Error en Partida: " + e.getMessage());
        } finally {
            cerrarRecursos(ps, rs);  
        }
        return P;
    }

    @Override
    public boolean InsertarDato(PartidaGuardada obj) {
        PartidaGuardada p = (PartidaGuardada) obj;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "INSERT INTO partida_guardada (UsuarioId, NivelActual, Tiempo) VALUES (?, ?, ?)"
            );
            ps.setInt(1, p.getUsuarioId());
            ps.setInt(2, p.getNivelActual());
            ps.setFloat(3, p.getTiempo());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en insertar: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(ps, null);
        }
    }

    @Override
    public List<PartidaGuardada> ObtenerTodo() {
        List<PartidaGuardada> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM partida_guardada");
            rs = ps.executeQuery();

            while (rs.next()) {
                PartidaGuardada p = new PartidaGuardada();
                p.setPartidaId(rs.getInt("PartidaId"));
                p.setUsuarioId(rs.getInt("UsuarioId"));
                p.setNivelActual(rs.getInt("NivelActual"));
                p.setTiempo(rs.getFloat("Tiempo"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerTodos: " + e.getMessage());
        } finally {
            cerrarRecursos(ps, rs);
        }

        return lista;
    }

    @Override
    public boolean ActualizarDato(PartidaGuardada obj) {
        PartidaGuardada p = (PartidaGuardada) obj;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "UPDATE partida_guardada SET UsuarioId = ?, NivelActual = ?, Tiempo = ? WHERE PartidaId = ?"
            );
            ps.setInt(1, p.getUsuarioId());
            ps.setInt(2, p.getNivelActual());
            ps.setFloat(3, p.getTiempo());
            ps.setInt(4, p.getPartidaId());
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
            ps = con.prepareStatement("DELETE FROM partida_guardada WHERE UsuarioId = ?");
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
