/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionClass;
import model.Grupo;
import model.Tutor;

/**
 *
 * @author Marifer
 */
public class TutorDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public TutorDAO() {
    }

    public TutorDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

//    // listar todos los productos
    public List<Tutor> listarTutorados(int idPer) throws SQLException {

        List<Tutor> listaTutores = new ArrayList<Tutor>();
        String sql = "select idtutorado, tutores.matricula, tutores.curp, tutores.idperiodo, tipo, alumnos.nombre, profesores.nombre, grupo, licenciaturas.nombre  "
                + "from tutores , alumnos , profesores, licenciaturas "
                + "where tutores.curp=profesores.curp and tutores.matricula=alumnos.matricula "
                + "and  tutores.idperiodo="+idPer+" and alumnos.idLicenciatura=licenciaturas.idLicenciatura;";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idtutorado");
            String matricula = resulSet.getString("tutores.matricula");
            String curp = resulSet.getString("tutores.curp");
            int idPerido = resulSet.getInt("tutores.idperiodo");
            int tipo = resulSet.getInt("tipo");
            String nombreA = resulSet.getString("alumnos.nombre");
            String nombreP = resulSet.getString("profesores.nombre");
            String grupo =resulSet.getString("grupo");
            String lic =resulSet.getString("licenciaturas.nombre");
            Tutor tutor;
            tutor = new Tutor(id, matricula,curp, idPerido, tipo,nombreA,nombreP, grupo, lic);
            listaTutores.add(tutor);
        }
        con.desconectar();
        return listaTutores;
    }
    
    
      public List<Tutor> listarAlumnosSinTutor(int idPer) throws SQLException {

        List<Tutor> listaAlumnos = new ArrayList<Tutor>();
        String sql = "select matricula , alumnos.nombre, grupo, licenciaturas.nombre  "
                + "from alumnos , grupos, licenciaturas where alumnos.matricula  "
                + "not in (select matricula from tutores where idperiodo="+idPer+")  "
                + "and  alumnos.idLicenciatura=licenciaturas.idLicenciatura "
                + "and alumnos.idgrupo = grupos.idgrupo and grupos.idperiodo="+idPer+";";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            
            String matricula = resulSet.getString("matricula");
            String curp = "";
            int idPerido = idPer;
            int tipo = 0;
            String nombreA = resulSet.getString("alumnos.nombre");
            String nombreP = "";
            String grupo =resulSet.getString("grupo");
            String lic =resulSet.getString("licenciaturas.nombre");
            Tutor tutor;
            tutor = new Tutor(0, matricula,curp, idPerido, tipo,nombreA,nombreP, grupo, lic);
            listaAlumnos.add(tutor);
        }
        con.desconectar();
        return listaAlumnos;
    }
//
//    public void eliminar(int id) throws SQLException {
//
//        String sql = "DELETE FROM grupos WHERE idGrupo=?";
//        con.conectar();
//        connection = con.getJdbcConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1, id);
//        preparedStatement.executeUpdate();
//
//    }
//
    public boolean insertar(Tutor tutor) throws SQLException {

        try {
            String sql = "INSERT INTO tutores (idTutorado,matricula, curp, idPeriodo, tipo, grupo)"
                    + " VALUES (?,?,?,?,?,?)";
            //System.out.println(profesor.getDescripcion());
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, tutor.getMatricula());
            statement.setString(3, tutor.getCurp());
            statement.setInt(4,tutor.getPeriodo());
            statement.setInt(5,tutor.getTipo());
            statement.setString(6,tutor.getGrupo());

            statement.executeUpdate();
            statement.close();
            con.desconectar();

        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
        }
        return false;

    }
    
    
     public boolean update(Tutor tutor) throws SQLException {

        try {
            String sql = "UPDATE tutores SET curp=?, tipo=?  WHERE matricula=? AND idPeriodo =? ;";
            //System.out.println(profesor.getDescripcion());
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tutor.getCurp());
            statement.setInt(2,tutor.getTipo());
            statement.setString(3, tutor.getMatricula());
            statement.setInt(4,tutor.getPeriodo());
            

            statement.executeUpdate();
            statement.close();
            con.desconectar();

        } catch (Exception e) {
            System.out.print("error al actualizar:" + e);
        }
        return false;

    }
    
    
    
      public int comprobarRegistro(int idPer, String matricula) throws SQLException {
          // verifica si esa matricula ya esta registrada para ese periodo regresa un 1 si no regresa un 0
        Grupo grupo = null;

        String sql = "select * from tutores where matricula =? and idPeriodo=?;";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,matricula );
        statement.setInt(2, idPer);
        try (ResultSet res = statement.executeQuery()) {
            if (res.next()) {
                con.desconectar();
                 return 1;
            }else{
                con.desconectar();
                return 0;
            }
        
    }
      }
      public Tutor tuturadoByMatricula(int idPer, String matricula) throws SQLException {
          // regresa el tutor para el tutoraado de de esa matricula en ese periodo
        

        String sql = "select * from tutores where matricula =? and idPeriodo=?;";
        Tutor tutor = null;
        
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, matricula);
        statement.setInt(2, idPer);
        ResultSet res = statement.executeQuery();
        if (res.next()) {

            tutor = new Tutor(res.getInt("idTutorado"), 
                    res.getString("matricula"),
                    res.getString("curp"),
                    res.getInt("idPeriodo"),
                    res.getInt("tipo"),
                    "",
                    "",
                    res.getString("grupo"),
                    "");
            System.err.println("si hay alumno");

        }
        res.close();
        con.desconectar();
 
        return tutor;
        
    }
      

}
