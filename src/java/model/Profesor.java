package model;

public class Profesor {

    private int idProfesor;
    private String nombre;
    private String estatus;
    private String grado;
    private String programa;
    private int idPrograma;
    private String curp;
    private int tipoTutoria;

    public Profesor() {
    }

    public Profesor(int idProfesor, String nombre, String estatus, String grado, int idPrograma, String programa, String curp, int tipoTutoria) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.idPrograma = idPrograma;
        this.grado = grado;
        this.estatus = estatus;
        this.programa = programa;
        this.curp = curp;
        this.tipoTutoria = tipoTutoria;
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

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

 

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getTipoTutoria() {
        return tipoTutoria;
    }

    public void setTipoTutoria(int tipoTutoria) {
        this.tipoTutoria = tipoTutoria;
    }

}
