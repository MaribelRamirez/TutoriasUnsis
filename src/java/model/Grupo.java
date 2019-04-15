package model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class Grupo {

    private int idGrupo;
    private String grupo;
    private int idPeriodo;
    private int idPrograma;
    private String periodo;
    private String programa;

    public Grupo() {
    }

    public Grupo(int idGrupo, String grupo, int idPeriodo, int idPrograma, String periodo, String programa) {
        this.idGrupo = idGrupo;
        this.grupo = grupo;
        this.idPeriodo = idPeriodo;
        this.idPrograma = idPrograma;
        this.periodo = periodo;
        this.programa = programa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    

}
