/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.sql.*;
import java.util.List;


public abstract class Consultas<T>{
    
    protected Connection con;

    public Consultas() {
        this.con = Conexion.getConexion();
    }
    
    public abstract boolean InsertarDato(T obj);
    public abstract List<T> ObtenerTodo();
    public abstract boolean ActualizarDato(T obj);
    public abstract boolean EliminarDato(int id);
    
    protected void cerrarRecursos(PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            System.err.println("Error en : " + e.getMessage());
        }
    }
}
