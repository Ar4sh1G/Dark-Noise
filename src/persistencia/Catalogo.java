/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


public class Catalogo {
    private int itemId;
    private String nombre;
    private String descripcion;
    private boolean conseguido; 

    public Catalogo() {
    }

    public Catalogo(int itemId, String nombre, String tipo) {
        this.itemId = itemId;
        this.nombre = nombre;
        this.descripcion = tipo;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isConseguido() { 
        return conseguido; 
    }
    
    public void setConseguido(boolean conseguido) { 
        this.conseguido = conseguido; 
    }
    
}
