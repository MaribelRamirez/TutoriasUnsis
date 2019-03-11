package model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class usuario {

    private int nivel;
    private String usuario;
    private String passw;

    public usuario() {
    }

    public usuario(int nivel, String usuario, String passw) {
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
