/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


public class Inventario {
    private int InventarioId;
    private int UsuarioId;
    private int ItemId;

    public Inventario() {
    }

    public Inventario(int InventarioId, int UsuarioId, int ItemId) {
        this.InventarioId = InventarioId;
        this.UsuarioId = UsuarioId;
        this.ItemId = ItemId;
    }

    public int getInventarioId() {
        return InventarioId;
    }

    public void setInventarioId(int InventarioId) {
        this.InventarioId = InventarioId;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int ItemId) {
        this.ItemId = ItemId;
    }
    
    
}
