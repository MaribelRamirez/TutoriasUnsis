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
public class Grupo {

    private int idGrupo;
    private String grupo;
    private int idPeriodo;
    private int idLicenciatura;
    private String periodo;
    private String licenciatura;

    public Grupo() {
    }

    public Grupo(int idGrupo, String grupo, int idPeriodo, int idLicenciatura, String periodo, String licenciatura) {
        this.idGrupo = idGrupo;
        this.grupo = grupo;
        this.idPeriodo = idPeriodo;
        this.idLicenciatura = idLicenciatura;
        this.periodo = periodo;
        this.licenciatura = licenciatura;
    }

    

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura;
    }

    
    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public void setIdLicenciatura(int idLicenciatura) {
        this.idLicenciatura = idLicenciatura;
    }

   

}
