/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Marifer
 */
public class Reporte {

    private int idReporte;
    private String curp;
    private String profesor;
    private String grado;
    private String licenciatura;
    private int idLicenciatura;
    private int idPeriodo;
    private String periodo;
    private String entrego;
    private String aTiempo;
    private Date fecha;
    private String tipoTutoria;
    private int noSesiones;
    private int noCanalizaciones;
    private int alumnosAsignados;
    private int alumnosReportados;
    private int alumnosAsistencia;
     private String observaciones;
    private String faltantes;
    
    public Reporte(){}
            
    public Reporte(int idReporte, String curp,String profesor,String grado,String licenciatura,int idLicenciatura,int idPeriodo,String periodo,String entrego, String aTiempo, Date fecha,String tipoTutoria, int noSesiones,int noCanalizaciones, int alumnosAsignados,int alumnosReportados, int alumnosAsistencia, String observaciones, String faltantes)
    {
    
    this.idReporte=idReporte;
    this.curp=curp;
     this.profesor=profesor;
     this.grado=grado;
    this.licenciatura=licenciatura;
    this.idLicenciatura=idLicenciatura;
    this.idPeriodo=idPeriodo;
    this.periodo=periodo;
    this.entrego=entrego;
    this.aTiempo=aTiempo;
    this.fecha=fecha;
    this.tipoTutoria=tipoTutoria;
    this.noSesiones=noSesiones;
    this.noCanalizaciones=noCanalizaciones;
    this.alumnosAsignados=alumnosAsignados;
    this.alumnosReportados=alumnosReportados;
    this.alumnosAsistencia=alumnosAsistencia;
    this.observaciones=observaciones;
    this.faltantes=faltantes;
    
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public void setIdLicenciatura(int idLicenciatura) {
        this.idLicenciatura = idLicenciatura;
    }

    public String getEntrego() {
        return entrego;
    }

    public void setEntrego(String entrego) {
        this.entrego = entrego;
    }

    public String getaTiempo() {
        return aTiempo;
    }

    public void setaTiempo(String aTiempo) {
        this.aTiempo = aTiempo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoTutoria() {
        return tipoTutoria;
    }

    public void setTipoTutoria(String tipoTutoria) {
        this.tipoTutoria = tipoTutoria;
    }

    public int getNoSesiones() {
        return noSesiones;
    }

    public void setNoSesiones(int noSesiones) {
        this.noSesiones = noSesiones;
    }

    public int getNoCanalizaciones() {
        return noCanalizaciones;
    }

    public void setNoCanalizaciones(int noCanalizaciones) {
        this.noCanalizaciones = noCanalizaciones;
    }

    public int getAlumnosAsignados() {
        return alumnosAsignados;
    }

    public void setAlumnosAsignados(int alumnosAsignados) {
        this.alumnosAsignados = alumnosAsignados;
    }

    public int getAlumnosReportados() {
        return alumnosReportados;
    }

    public void setAlumnosReportados(int alumnosReportados) {
        this.alumnosReportados = alumnosReportados;
    }

    public int getAlumnosAsistencia() {
        return alumnosAsistencia;
    }

    public void setAlumnosAsistencia(int alumnosAsistencia) {
        this.alumnosAsistencia = alumnosAsistencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFaltantes() {
        return faltantes;
    }

    public void setFaltantes(String faltantes) {
        this.faltantes = faltantes;
    }
        
    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    
    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}
