/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import persistencia.PartidaGuardada;
import persistencia.Usuario;

public class Sesion {
    private static Usuario usuarioActual;
    private static PartidaGuardada partidaActual;

    public static Usuario getUsuario() { 
        return usuarioActual;
    }
    
    public static void setUsuario(Usuario u) {
        usuarioActual = u; 
    }

    public static PartidaGuardada getPartida() {
        return partidaActual;
    }
    
    public static void setPartida(PartidaGuardada p) { 
        partidaActual = p;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        partidaActual = null;
    }
}
