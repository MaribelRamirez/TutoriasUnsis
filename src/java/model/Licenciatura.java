package model;

public class Licenciatura {

    private int idLicenciatura;
    private String nombre;
    private String des;

    public Licenciatura() {
    }

    public Licenciatura(int idLicenciatura, String nombre, String des) {

        this.idLicenciatura = idLicenciatura;
        this.nombre = nombre;
         this.des = des;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public void setIdLicenciatura(int idLicenciatura) {
        this.idLicenciatura = idLicenciatura;
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
