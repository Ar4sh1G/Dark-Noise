/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
   
    private static final String URL      = "jdbc:mysql://localhost:3306/mibase";
    private static final String USUARIO  = "root";
    private static final String CLAVE    = "";
    
    public static Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Coneccion exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return con;
    }
}
