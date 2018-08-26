/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marifer
 */
public class Profesor {

    private int idProfesor;
    private String nombre;
    private String estatus;
    private String grado;
    private int licenciatura;
    private int idLicenciatura; 
    public Profesor() {
    }

    public Profesor(int idProfesor, String nombre,String estatus,String grado,int idLicenciatura, int licenciatura) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.idLicenciatura=idLicenciatura;
        this.grado=grado;
        this.estatus=estatus;
        this.licenciatura = licenciatura;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public void setIdLicenciatura(int idLicenciatura) {
        this.idLicenciatura = idLicenciatura;
    }

    

    public int getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(int licenciatura) {
        this.licenciatura = licenciatura;
    }
}
