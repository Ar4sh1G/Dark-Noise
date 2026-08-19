/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


public class PartidaGuardada {
    private int PartidaId;
    private int usuarioId;
    private int nivelActual;
    private float tiempo;

    public PartidaGuardada() {
    }

    public PartidaGuardada(int PartidaId, int usuarioId, int nivelActual, float tiempo) {
        this.PartidaId = PartidaId;
        this.usuarioId = usuarioId;
        this.nivelActual = nivelActual;
        this.tiempo = tiempo;
    }

    public int getPartidaId() {
        return PartidaId;
    }

    public void setPartidaId(int PartidaId) {
        this.PartidaId = PartidaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }
    
    
}
