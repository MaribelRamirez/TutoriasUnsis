package model;

public class Profesor {

    private int idProfesor;
    private String nombre;
    private String estatus;
    private String grado;
    private String licenciatura;
    private int idLicenciatura;
    private String curp;
    private int tipoTutoria;

    public Profesor() {
    }

    public Profesor(int idProfesor, String nombre, String estatus, String grado, int idLicenciatura, String licenciatura, String curp, int tipoTutoria) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.idLicenciatura = idLicenciatura;
        this.grado = grado;
        this.estatus = estatus;
        this.licenciatura = licenciatura;
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
