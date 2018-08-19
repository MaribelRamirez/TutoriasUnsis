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
    private String apellidoP;
    private String apellidoM;
    private int licenciatura;

    public Profesor() {
    }

    public Profesor(int idProfesor, String nombre, String apellidoP, String apellidoM, int licenciatura) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
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

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public int getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(int licenciatura) {
        this.licenciatura = licenciatura;
    }
}
