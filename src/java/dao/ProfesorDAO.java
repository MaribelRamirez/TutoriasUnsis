package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionClass;
import model.Profesor;

public class ProfesorDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public ProfesorDAO() {
    }

    public ProfesorDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    public List<Profesor> obtenerProfesorTutor(String prg) throws SQLException {
        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "select distinct tutores.tipo,profesores.idProfesor,profesores.estatus,profesores.nombre,"
                + "profesores.grado,programas.nombre,programas.idPrograma,profesores.curp from profesores,tutores,"
                + "programas where profesores.curp=tutores.curp and programas.idPrograma=programa"
                + " and programas.nombre='" + prg + "';";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("profesores.estatus");
            String nombre = resulSet.getString("profesores.nombre");
            String programa = resulSet.getString("programas.nombre");
            int tipoTutoria = resulSet.getInt("tutores.tipo");
            String grado = resulSet.getString("profesores.grado");
            String curp = resulSet.getString("profesores.curp");
            int idLic = resulSet.getInt("programas.idPrograma");
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idLic, programa, curp, tipoTutoria);

            listaProfesores.add(profesor);

        }
        con.desconectar();
        return listaProfesores;
    }

    public Profesor obtenerProfesorRegistroReporte(String curp) throws SQLException {
        Profesor profesor = null;
        String sql = "SELECT profesores.idProfesor,profesores.curp,profesores.nombre,profesores.grado,"
                + "profesores.estatus,profesores.programa,programas.nombre, tutores.tipo FROM profesores "
                + "inner join programas inner join tutores on profesores.programa=programas.idPrograma "
                + "and profesores.curp=tutores.curp WHERE profesores.curp= ?";

        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, curp);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            profesor = new Profesor(
                    res.getInt("profesores.idProfesor"),
                    res.getString("profesores.nombre"),
                    res.getString("profesores.estatus"),
                    res.getString("profesores.grado"),
                    res.getInt("profesores.programa"),
                    res.getString("programas.nombre"),
                    res.getString("profesores.curp"),
                    res.getInt("tutores.tipo"));
        }
        res.close();
        con.desconectar();

        return profesor;
    }
    public List<Profesor> tutorGrupalbyPeriodo(int periodo) throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "select distinct profesores.idProfesor,profesores.estatus,profesores.nombre,profesores.grado,programas.nombre,profesores.curp from profesores,tutores,programas where profesores.curp=tutores.curp and programas.idPrograma=programa and tutores.tipo=2 and tutores.idPeriodo='" + periodo + "';";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int tipoTutoria = 0;
            int idPrograma = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp, tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
     public List<Profesor> tutorGrupal() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
       String sql = "select distinct profesores.idProfesor,profesores.estatus,profesores.nombre,profesores.grado,programas.nombre,profesores.curp from profesores,tutores,programas where profesores.curp=tutores.curp and programas.idPrograma=programa and tutores.tipo=2;";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int tipoTutoria=0;
            int idPrograma = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp,tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
    public List<Profesor> tutorIndividual() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "select distinct profesores.idProfesor,profesores.estatus,profesores.nombre,profesores.grado,programas.nombre,profesores.curp from profesores,tutores,programas where profesores.curp=tutores.curp and programas.idPrograma=programa and tutores.tipo=1;";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int idPrograma = 0;
           int tipoTutoria=0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp,tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
    public List<Profesor> tutorIndividualbyPeriodo(int periodo) throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "select distinct profesores.idProfesor,profesores.estatus,profesores.nombre,profesores.grado,programas.nombre,profesores.curp from profesores,tutores,programas where profesores.curp=tutores.curp and programas.idPrograma=programa and tutores.tipo=1 and tutores.idPeriodo='" + periodo + "';";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int idPrograma = 0;
            int tipoTutoria = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp, tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }

    public Profesor obtenerProfesorBycurp(String curp) throws SQLException {
        Profesor profesor = null;

        String sql = "SELECT profesores.idProfesor,profesores.curp,profesores.nombre,profesores.grado,profesores.estatus,"
                + "profesores.programa,programas.nombre"
                + " FROM profesores inner join programas on profesores.programa=programas.idPrograma WHERE profesores.curp= ? ";

        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, curp);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            profesor = new Profesor(
                    res.getInt("profesores.idProfesor"),
                    res.getString("profesores.nombre"),
                    res.getString("profesores.estatus"),
                    res.getString("profesores.grado"),
                    res.getInt("profesores.programa"),
                    res.getString("programas.nombre"),
                    res.getString("profesores.curp"),
                    0);
        }
        res.close();
        con.desconectar();

        return profesor;
    }

    public List<Profesor> obtenerProfesorByCarrera(String carrera) throws SQLException {
        List<Profesor> listaProfesores = new ArrayList<Profesor>();

        String sql = "select *from profesores inner join programas on "
                + "profesores.programa=programas.idPrograma where programas.nombre='" + carrera + "';";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int idPrograma = 0;
            int tipoTutoria = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp, tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }

    // insertar artículo
    public boolean insertar(Profesor profesor) throws SQLException {
        try {
            String sql = "INSERT INTO profesores (idProfesor,curp,nombre,grado,estatus,programa)"
                    + " VALUES (?, ?,?,?,?,?)";
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, profesor.getCurp());
            statement.setString(3, profesor.getNombre());
            statement.setString(4, profesor.getGrado());
            statement.setString(5, profesor.getEstatus());
            statement.setInt(6, profesor.getIdPrograma());

            statement.executeUpdate();
            statement.close();
            con.desconectar();
        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
        }
        return false;
    }

    // listar todos los profesores
    public List<Profesor> listarProfesores() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "SELECT idProfesor,curp, profesores.nombre ,grado,estatus,profesores.programa,programas.nombre FROM profesores inner join programas on profesores.programa=programas.idPrograma ";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int idPrograma = 0;
            int tipoTutoria = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp, tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
 public List<Profesor> listarProfesoresSociales() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "SELECT idProfesor,curp, profesores.nombre ,grado,estatus,profesores.programa,programas.nombre FROM profesores inner join programas on profesores.programa=programas.idPrograma and programas.des='SOCIALES' order by(programas.nombre)";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int idPrograma = 0;
            int tipoTutoria = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp, tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
  public List<Profesor> listarProfesoresSalud() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "SELECT idProfesor,curp, profesores.nombre ,grado,estatus,profesores.programa,programas.nombre FROM profesores inner join programas on profesores.programa=programas.idPrograma and programas.des='SALUD' order by(programas.nombre)";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int idPrograma = 0;
            int tipoTutoria = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp, tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }
    public List<Profesor> listarProfesoresActivos() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "SELECT idProfesor,curp, profesores.nombre ,grado,estatus,profesores.programa,programas.nombre FROM "
                + "profesores inner join programas on profesores.programa=programas.idPrograma WHERE profesores.estatus='Activo'";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int idProfesor = resulSet.getInt("idProfesor");
            String estatus = resulSet.getString("estatus");
            String nombre = resulSet.getString("nombre");
            String grado = resulSet.getString("grado");
            String programa = resulSet.getString("programas.nombre");
            String curp = resulSet.getString("curp");
            int idPrograma = 0;
            int tipoTutoria = 0;
            Profesor profesor = new Profesor(idProfesor, nombre, estatus, grado, idPrograma, programa, curp, tipoTutoria);
            listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }

    // obtener por id
    public Profesor obtenerProfesorById(int idprf) throws SQLException {
        Profesor profesor = null;

        String sql = "SELECT profesores.idProfesor,profesores.curp,profesores.nombre,profesores.grado,profesores.estatus,"
                + "profesores.programa,programas.nombre"
                + " FROM profesores inner join programas on profesores.programa=programas.idPrograma WHERE profesores.idProfesor= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idprf);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            profesor = new Profesor(
                    res.getInt("profesores.idProfesor"),
                    res.getString("profesores.nombre"),
                    res.getString("profesores.estatus"),
                    res.getString("profesores.grado"),
                    res.getInt("profesores.programa"),
                    res.getString("programas.nombre"),
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

            String sql = "update profesores set idProfesor=?,curp=?,nombre=?,grado=?,estatus=?,programa=?"
                    + " where idProfesor=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, profesor.getIdProfesor());
                statement.setString(2, profesor.getCurp());
                statement.setString(3, profesor.getNombre());
                statement.setString(4, profesor.getGrado());
                statement.setString(5, profesor.getEstatus());
                statement.setInt(6, profesor.getIdPrograma());
                statement.setInt(7, profesor.getIdProfesor());
                statement.executeUpdate();
            }
            con.desconectar();

        } catch (SQLException e) {
            System.out.print("error al actualizar:" + e);
        }
    }

    public void eliminar(int idprf) throws SQLException {
        String sql = "DELETE FROM profesores WHERE idProfesor=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idprf);
        preparedStatement.executeUpdate();
        con.desconectar();
    }
     public int countTutoradosByPeriodo(int periodo,String curp) {

        int count = 0;
        try {
            String sql="select *from tutores where curp='" + curp + "' and idPeriodo='"+periodo+"' ;";
            
            connection = con.conectar();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                count++;
            }
            con.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
