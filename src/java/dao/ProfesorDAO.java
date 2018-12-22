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
import model.Profesor;

/**
 *
 * @author Marifer
 */
public class ProfesorDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public ProfesorDAO() {
    }

    public ProfesorDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }
    
    public List<Profesor> obtenerProfesorTutor(String lic) throws SQLException {
        List<Profesor> listaProfesores = new ArrayList<Profesor>();
       String sql = "select distinct tutores.tipo,profesores.idProfesor,profesores.estatus,profesores.nombre,"
               + "profesores.grado,licenciaturas.nombre,licenciaturas.idLicenciatura,profesores.curp from profesores,tutores,"
               + "licenciaturas where profesores.curp=tutores.curp and licenciaturas.idLicenciatura=licenciatura"
               + " and licenciaturas.nombre='"+lic+"';";

        System.out.println("consulta"+sql);
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        
        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            System.out.println("id"+idProfesor);
            String estatus = resulSet.getString("profesores.estatus");
             System.out.println("estatus"+estatus);
            String nombre = resulSet.getString("profesores.nombre");
            System.out.println("nombre"+nombre);
            String licenciatura = resulSet.getString("licenciaturas.nombre");
            System.out.println("licenciatutra"+licenciatura);
            int tipoTutoria=resulSet.getInt("tutores.tipo");
            String grado = resulSet.getString("profesores.grado");
            System.out.println("grado"+grado);
            String curp = resulSet.getString("profesores.curp");
            System.out.println("curp"+curp);
            int idLic = resulSet.getInt("licenciaturas.idLicenciatura");
            System.out.println("idLic"+curp);
            Profesor profesor = new Profesor(idProfesor,nombre,estatus,grado,idLic,licenciatura,curp,tipoTutoria);
            
            listaProfesores.add(profesor);
            System.out.println("numero de profesores");
           
        }
        con.desconectar();
        return listaProfesores;
    }
   
       public Profesor obtenerProfesorRegistroReporte(String curp) throws SQLException {
        Profesor profesor = null;
        String sql="SELECT profesores.idProfesor,profesores.curp,profesores.nombre,profesores.grado,"
                + "profesores.estatus,profesores.licenciatura,licenciaturas.nombre, tutores.tipo FROM profesores "
                + "inner join licenciaturas inner join tutores on profesores.licenciatura=licenciaturas.idLicenciatura "
                + "and profesores.curp=tutores.curp WHERE profesores.curp= ?";


        con.conectar();
        connection = con.getJdbcConnection();
        System.out.println("este es el sql de profesor"+sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, curp);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            profesor = new Profesor(
                    res.getInt("profesores.idProfesor"), 
                    res.getString("profesores.nombre"),
                    res.getString("profesores.estatus"), 
                    res.getString("profesores.grado"),
                     res.getInt("profesores.licenciatura"),
                    res.getString("licenciaturas.nombre"),
                    res.getString("profesores.curp"),
            res.getInt("tutores.tipo"));
        }
        res.close();
        con.desconectar();

        return profesor;
    }
    public List<Profesor> tutorGrupal(int periodo) throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
       String sql = "select distinct profesores.idProfesor,profesores.estatus,profesores.nombre,profesores.grado,licenciaturas.nombre,profesores.curp from profesores,tutores,licenciaturas where profesores.curp=tutores.curp and licenciaturas.idLicenciatura=licenciatura and tutores.tipo=2 and tutores.idPeriodo='"+periodo+"';";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String licenciatura = resulSet.getString("licenciaturas.nombre");
            String curp = resulSet.getString("curp");
            int tipoTutoria=0;
            int idLicenciatura = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idLicenciatura, licenciatura, curp,tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
    
    public List<Profesor> tutorIndividual(int periodo) throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "select distinct profesores.idProfesor,profesores.estatus,profesores.nombre,profesores.grado,licenciaturas.nombre,profesores.curp from profesores,tutores,licenciaturas where profesores.curp=tutores.curp and licenciaturas.idLicenciatura=licenciatura and tutores.tipo=1 and tutores.idPeriodo='"+periodo+"';";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String licenciatura = resulSet.getString("licenciaturas.nombre");
            String curp = resulSet.getString("curp");
            int idLicenciatura = 0;
           int tipoTutoria=0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idLicenciatura, licenciatura, curp,tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
     public Profesor obtenerProfesorBycurp(String curp) throws SQLException {
        Profesor profesor = null;

        String sql ="SELECT profesores.idProfesor,profesores.curp,profesores.nombre,profesores.grado,profesores.estatus,"
                + "profesores.licenciatura,licenciaturas.nombre"
                + " FROM profesores inner join licenciaturas on profesores.licenciatura=licenciaturas.idLicenciatura WHERE profesores.curp= ? ";
        
        con.conectar();
        connection = con.getJdbcConnection();
        System.out.println("este es el sql de profesor"+sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, curp);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            profesor = new Profesor(
                    res.getInt("profesores.idProfesor"), 
                    res.getString("profesores.nombre"),
                    res.getString("profesores.estatus"), 
                    res.getString("profesores.grado"),
                     res.getInt("profesores.licenciatura"),
                    res.getString("licenciaturas.nombre"),
                    res.getString("profesores.curp"),
                    0);
        }
        res.close();
        con.desconectar();

        return profesor;
    }
     public List<Profesor> obtenerProfesorByCarrera(String carrera) throws SQLException {
        List<Profesor> listaProfesores = new ArrayList<Profesor>();

        String sql ="select *from profesores inner join licenciaturas on "
                + "profesores.licenciatura=licenciaturas.idLicenciatura where licenciaturas.nombre='"+carrera+"';";
        System.out.println("consulta"+sql);
       connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String licenciatura = resulSet.getString("licenciaturas.nombre");
            String curp = resulSet.getString("curp");
            int idLicenciatura = 0;
            int tipoTutoria=0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado,idLicenciatura, licenciatura,  curp,tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
     
    // insertar artículo
    public boolean insertar(Profesor profesor) throws SQLException {
       try {  String sql = "INSERT INTO profesores (idProfesor,curp,nombre,grado,estatus,licenciatura)"
                + " VALUES (?, ?,?,?,?,?)";
        //System.out.println(profesor.getDescripcion());
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, null);
        statement.setString(2, profesor.getCurp());
        statement.setString(3, profesor.getNombre());
        statement.setString(4, profesor.getGrado());
        statement.setString(5, profesor.getEstatus());
        statement.setInt(6, profesor.getIdLicenciatura());

       statement.executeUpdate();
        statement.close();
        con.desconectar();
       } catch (Exception e) {
             System.out.print("error al insertar:"+e);
        }
        return false;
    }
   

    // listar todos los profesores
    public List<Profesor> listarProfesores() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "SELECT idProfesor,curp, profesores.nombre ,grado,estatus,profesores.licenciatura,licenciaturas.nombre FROM profesores inner join licenciaturas on profesores.licenciatura=licenciaturas.idLicenciatura ";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String licenciatura = resulSet.getString("licenciaturas.nombre");
            String curp = resulSet.getString("curp");
            int idLicenciatura = 0;
            int tipoTutoria=0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idLicenciatura, licenciatura, curp,tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
public List<Profesor> listarProfesoresActivos() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "SELECT idProfesor,curp, profesores.nombre ,grado,estatus,profesores.licenciatura,licenciaturas.nombre FROM "
                + "profesores inner join licenciaturas on profesores.licenciatura=licenciaturas.idLicenciatura WHERE profesores.estatus='Activo'";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String licenciatura = resulSet.getString("licenciaturas.nombre");
            String curp = resulSet.getString("curp");
            int idLicenciatura = 0;
            int tipoTutoria=0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idLicenciatura, licenciatura, curp,tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
    // obtener por id
    public Profesor obtenerProfesorById(int idprf) throws SQLException {
        Profesor profesor = null;

        String sql = "SELECT profesores.idProfesor,profesores.curp,profesores.nombre,profesores.grado,profesores.estatus,"
                + "profesores.licenciatura,licenciaturas.nombre"
                + " FROM profesores inner join licenciaturas on profesores.licenciatura=licenciaturas.idLicenciatura WHERE profesores.idProfesor= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        System.out.println("este es el sql de profesor"+sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idprf);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            profesor = new Profesor(
                    res.getInt("profesores.idProfesor"), 
                    res.getString("profesores.nombre"),
                    res.getString("profesores.estatus"), 
                    res.getString("profesores.grado"),
                     res.getInt("profesores.licenciatura"),
                    res.getString("licenciaturas.nombre"),
                    res.getString("profesores.curp"),
           0);
        }
        res.close();
        con.desconectar();

        return profesor;
    }

    // actualizar
   public void updateProfesor(Profesor profesor) {
        try {

            String sql = "update profesores set idProfesor=?,curp=?,nombre=?,grado=?,estatus=?,licenciatura=?" 
                    + " where idProfesor=?";
            con.conectar();
            connection = con.getJdbcConnection();
            System.out.println("esta es la sql de actualizar profesores" + sql);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, profesor.getIdProfesor());
                statement.setString(2, profesor.getCurp());
                statement.setString(3, profesor.getNombre());
                statement.setString(4, profesor.getGrado());
                statement.setString(5, profesor.getEstatus());
                statement.setInt(6, profesor.getIdLicenciatura());
                statement.setInt(7, profesor.getIdProfesor());
                statement.executeUpdate();
            }
            con.desconectar();

        } catch (SQLException e) {
            System.out.print("error al actualizar:" + e);
        }}
   
public void eliminar(int idprf) throws SQLException {
        String sql = "DELETE FROM profesores WHERE idProfesor=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idprf);
        preparedStatement.executeUpdate();
con.desconectar();
    }

}
