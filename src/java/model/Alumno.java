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
public class Alumno {
    private String matricula;
    private String nombre;
    private int idGrupo;
    private String Grupo;
    private int idLicenciatura;
    private String Licenciatura;
  
      public Alumno() {
    }

    public Alumno(String matricula, String nombre, int idGrupo,String Grupo, int idLicenciatura, String Licenciatura) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.idGrupo = idGrupo;
        this.Grupo=Grupo;
        this.idLicenciatura = idLicenciatura;
        this.Licenciatura=Licenciatura;
       
        
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
    

//    public Alumno(String matricula, String nombre, String apellidoP, String apellidoM, int grupo,int idLicenciatura, String Licenciatura) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    

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
    
    
}
