package model;

public class Alumno {

    private String matricula;
    private String nombre;
    private int idGrupo;
    private String Grupo;
    private int idLicenciatura;
    private String Licenciatura;
    private int tipo;

    public Alumno() {
    }

    public Alumno(String matricula, String nombre, int idGrupo, String Grupo, int idLicenciatura, String Licenciatura, int tipo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.idGrupo = idGrupo;
        this.Grupo = Grupo;
        this.idLicenciatura = idLicenciatura;
        this.Licenciatura = Licenciatura;
        this.tipo = tipo;
        this.Licenciatura = Licenciatura;

    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public String getLicenciatura() {
        return Licenciatura;
    }

    public void setLicenciatura(String Licenciatura) {
        this.Licenciatura = Licenciatura;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public void setIdLicenciatura(int idLicenciatura) {
        this.idLicenciatura = idLicenciatura;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
