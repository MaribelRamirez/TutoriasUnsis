package model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class Licenciatura {

    private int idLicenciatura;
    private String nombre;

    public Licenciatura() {
    }

    public Licenciatura(int idLicenciatura, String nombre) {

        this.idLicenciatura = idLicenciatura;
        this.nombre = nombre;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public void setIdLicenciatura(int idLicenciatura) {
        this.idLicenciatura = idLicenciatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
