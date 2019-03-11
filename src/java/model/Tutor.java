package model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class Tutor {

    private int idTutorado;
    private String matricula;
    private String curp;
    private int periodo;
    private int tipo;
    private String alumno;
    private String profesor;
    private String grupo;
    private String lic;

    public Tutor() {
    }

    public Tutor(int idTutorado, String matricula, String curp, int periodo, int tipo, String alumno, String profesor, String grupo, String lic) {
        this.idTutorado = idTutorado;
        this.matricula = matricula;
        this.curp = curp;
        this.periodo = periodo;
        this.tipo = tipo;
        this.alumno = alumno;
        this.profesor = profesor;
        this.grupo = grupo;
        this.lic = lic;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getLic() {
        return lic;
    }

    public void setLic(String lic) {
        this.lic = lic;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public int getIdTutorado() {
        return idTutorado;
    }

    public void setIdTutorado(int idTutorado) {
        this.idTutorado = idTutorado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
