/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


public class Usuario {
    private int usuarioId;
    private String username;
    private String password;
    private int muertes;

    public Usuario() {
    }

    public Usuario(int usuarioId, String username, String password, int muertes) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.password = password;
        this.muertes = muertes;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMuertes() {
        return muertes;
    }

    public void setMuertes(int muertes) {
        this.muertes = muertes;
    }

    public void addMuertes() {
        this.muertes++;
    }
    
    
}
