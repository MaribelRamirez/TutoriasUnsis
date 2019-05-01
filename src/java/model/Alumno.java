package model;

public class Alumno {

    private String matricula;
    private String nombre;
    private int idGrupo;
    private String Grupo;
    private int idPrograma;
    private String Programa;
    private int tipo;

    public Alumno() {
    }

    public Alumno(String matricula, String nombre, int idGrupo, String Grupo, int idPrograma, String Programa, int tipo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.idGrupo = idGrupo;
        this.Grupo = Grupo;
        this.idPrograma = idPrograma;
        this.Programa = Programa;
        this.tipo = tipo;

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

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return Programa;
    }

    public void setPrograma(String Programa) {
        this.Programa = Programa;
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

   

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
