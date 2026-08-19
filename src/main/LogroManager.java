/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import java.util.List;
import persistencia.Inventario;
import persistencia.InventarioDAO;

public class LogroManager {

  
    private static final int LOGRO_TUTORIAL    = 1;
    private static final int LOGRO_PRIMERA_MUERTE = 2;
    private static final int LOGRO_NIVEL_5     = 3;
    private static final int LOGRO_COMPLETAR   = 4;
    private static final int LOGRO_NIVEL_1    = 5;
    private static final int LOGRO_10_MUERTES  = 6;
    

    
    private static void otorgarLogro(int itemId) {
        if (Sesion.getUsuario() == null) return;

        InventarioDAO dao = new InventarioDAO();
        List<?> inventario = dao.ObtenerTodo();

        
        for (Object obj : inventario) {
            Inventario item = (Inventario) obj;
            if (item.getUsuarioId() == Sesion.getUsuario().getUsuarioId()
                    && item.getItemId() == itemId) {
                return; 
            }
        }

      
        Inventario nuevo = new Inventario();
        nuevo.setUsuarioId(Sesion.getUsuario().getUsuarioId());
        nuevo.setItemId(itemId);
        dao.InsertarDato(nuevo);

        System.out.println("Logro desbloqueado: " + itemId);
    }

    

    public static void completoTutorial() {
        otorgarLogro(LOGRO_TUTORIAL);
    }

    public static void terminarNivel(int nivel, int total) {
        if (nivel == 2) otorgarLogro(LOGRO_NIVEL_1);
        if (nivel == 6) otorgarLogro(LOGRO_NIVEL_5);
    }
    
    public static void terminarJuego(){
        otorgarLogro(LOGRO_COMPLETAR);
    }

    public static void jugadorMurio(int totalMuertes) {
        if (totalMuertes == 1)  otorgarLogro(LOGRO_PRIMERA_MUERTE);
        if (totalMuertes == 10) otorgarLogro(LOGRO_10_MUERTES);
    }
}
