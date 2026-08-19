/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.sql.Date;

public class Ranking {
    private int usuarioId;
    private float TiempoMax;
    private Date Fecha;
    private String nombre;

    public Ranking() {
    }

    public Ranking(int usuarioId, float TiempoMax, Date Fecha) {
        this.usuarioId = usuarioId;
        this.TiempoMax = TiempoMax;
        this.Fecha = Fecha;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public float getTiempoMax() {
        return TiempoMax;
    }

    public void setTiempoMax(float TiempoMax) {
        this.TiempoMax = TiempoMax;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
