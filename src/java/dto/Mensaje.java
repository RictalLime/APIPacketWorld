/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Tron7
 */
public class Mensaje {
    //Atributos
    private boolean error;
    private String mensaje;
    
    //Constructor por defecto
    public Mensaje() {
    }
    
    //Constructor
    public Mensaje(boolean error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }
    
    //Metodos Set
    public void setError(boolean error) {
        this.error = error;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    //Metodo cath
    public boolean isError() {
        return error;
    }
    
    //Metodo Get
    public String getMensaje() {
        return mensaje;
    }
}
