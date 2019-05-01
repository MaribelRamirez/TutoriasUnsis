package model;

public class Programa {

    private int idPrograma;
    private String nombre;
    private String des;

    public Programa() {
    }

    public Programa(int idPrograma, String nombre, String des) {

        this.idPrograma = idPrograma;
        this.nombre = nombre;
         this.des = des;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
