/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 *
 * @author Marifer
 */
public class usuario {
    private int nivel;
    private String usuario;
    private String passw;
      public usuario() {
    }

    public usuario( int nivel, String usuario, String passw) {
        this.nivel = nivel;
        this.usuario = usuario;
        this.passw = passw;
    }

   

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String pass) {
        this.passw = pass;
    }

    

    

   
    
    
}
