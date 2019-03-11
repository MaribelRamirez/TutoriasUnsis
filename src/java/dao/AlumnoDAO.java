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
import model.Alumno;
import model.ConnectionClass;

public class AlumnoDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public AlumnoDAO() {
    }

    public AlumnoDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    public int countEstadistica3(int periodo) {

        int count = 0;
        try {
            String sql = "select *"
                    + "from tutores ,profesores,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura \n"
                    + "where tutores.matricula=alumnos.matricula "
                    + "and tutores.curp=profesores.curp and tutores.idPeriodo='" + periodo + "';";
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

    public int countEstadistica2(int carreraT, int periodo) {

        int count = 0;
        try {
            String sql = "select *"
                    + "from tutores ,profesores ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura \n"
                    + "                     where tutores.matricula=alumnos.matricula "
                    + "                     and tutores.curp=profesores.curp and profesores.licenciatura='" + carreraT + "' and tutores.idPeriodo='" + periodo + "';";
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

    public int countEstadistica(int carreraT, int carreraA, int periodo) {

        int count = 0;
        try {
            String sql = "select *"
                    + "from tutores ,profesores,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura \n"
                    + "                     where tutores.matricula=alumnos.matricula "
                    + "                     and tutores.curp=profesores.curp and profesores.licenciatura='" + carreraT + "' and alumnos.idLicenciatura='" + carreraA + "' and tutores.idPeriodo='" + periodo + "';";
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

    public int countAlumnosTutorados(String curp, int periodo) {

        int count = 0;
        try {
            String sql = "select * "
                    + " from tutores  ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura "
                    + " where tutores.matricula=alumnos.matricula  "
                    + " and tutores.curp='" + curp + "' and tutores.idPeriodo='" + periodo + "'; ";
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

    public int countAlumnosTutoradosByCarrera(String curp, String carrera, int periodo) {

        int count = 0;
        try {
            String sql = "select * "
                    + " from tutores  ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura "
                    + " where tutores.matricula=alumnos.matricula "
                    + " and tutores.curp='" + curp + "' and licenciaturas.nombre='" + carrera + "'  and tutores.idPeriodo='" + periodo + "';";
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

    public boolean insertar(Alumno alumno) throws SQLException {
        try {
            String sql = "INSERT INTO alumnos (matricula,nombre,idGrupo,idLicenciatura)"
                    + " VALUES (?,?,?,?)";
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, alumno.getMatricula());
            statement.setString(2, alumno.getNombre());
            statement.setInt(3, alumno.getIdGrupo());
            statement.setInt(4, alumno.getIdLicenciatura());

            statement.executeUpdate();
            statement.close();
            con.desconectar();
        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
        }
        return false;
    }

    // listar todos los alumnos
    public List<Alumno> listarAlumnos() throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "SELECT matricula, alumnos.nombre ,alumnos.idGrupo, alumnos.idLicenciatura,licenciaturas.nombre, "
                + "grupos.grupo FROM alumnos inner join licenciaturas inner join grupos on "
                + "grupos.idGrupo=alumnos.idGrupo and alumnos.idLicenciatura=licenciaturas.idLicenciatura";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);
        while (resulSet.next()) {
            String matricula = resulSet.getString("matricula");
            String nombre = resulSet.getString("alumnos.nombre");
            int idgrupo = resulSet.getInt("alumnos.idGrupo");
            String grupo = resulSet.getString("grupos.grupo");
            int idLicenciatura = resulSet.getInt("alumnos.idLicenciatura");
            String Licenciatura = resulSet.getString("licenciaturas.nombre");
            Alumno alumno;
            alumno = new Alumno(matricula, nombre, idgrupo, grupo, idLicenciatura, Licenciatura, 0);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public List<Alumno> listarAlumnosByGrupo(int idGpo) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "SELECT matricula, alumnos.nombre ,alumnos.idGrupo, alumnos.idLicenciatura,licenciaturas.nombre, grupos.grupo "
                + " FROM alumnos inner join licenciaturas inner join grupos on grupos.idGrupo=alumnos.idGrupo "
                + " and alumnos.idLicenciatura=licenciaturas.idLicenciatura where grupos.idGrupo =" + idGpo + ";";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String matricula = resulSet.getString("matricula");
            String nombre = resulSet.getString("alumnos.nombre");
            int idgrupo = resulSet.getInt("alumnos.idGrupo");
            String grupo = resulSet.getString("grupos.grupo");
            int idLicenciatura = resulSet.getInt("alumnos.idLicenciatura");
            String Licenciatura = resulSet.getString("licenciaturas.nombre");
            Alumno alumno;
            alumno = new Alumno(matricula, nombre, idgrupo, grupo, idLicenciatura, Licenciatura, 0);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public List<Alumno> listarAlumnosTutorados(String curp) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "select alumnos.matricula , alumnos.nombre, grupos.grupo , alumnos.idLicenciatura, tipo , licenciaturas.nombre "
                + " from tutores ,  grupos ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura "
                + " where tutores.matricula=alumnos.matricula and  grupos.idGrupo=alumnos.idGrupo "
                + " and tutores.curp='" + curp + "';";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);
        while (resulSet.next()) {
            String matricula = resulSet.getString("alumnos.matricula");
            String nombre = resulSet.getString("alumnos.nombre");
            String grupo = resulSet.getString("grupos.grupo");
            int idLicenciatura = resulSet.getInt("alumnos.idLicenciatura");
            String Licenciatura = resulSet.getString("licenciaturas.nombre");
            int tipo = resulSet.getInt("tipo");
            Alumno alumno;
            alumno = new Alumno(matricula, nombre, 0, grupo, idLicenciatura, Licenciatura, tipo);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public List<Alumno> listarAlumnosTutoradosByCarrera(String curp, String carrera, int periodo) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "select alumnos.matricula , alumnos.nombre, tutores.grupo , alumnos.idLicenciatura, tipo , licenciaturas.nombre "
                + " from tutores  ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura "
                + " where tutores.matricula=alumnos.matricula and licenciaturas.nombre='" + carrera + "' and tutores.curp='" + curp + "' and tutores.idPeriodo='" + periodo + "';";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String matricula = resulSet.getString("alumnos.matricula");
            String nombre = resulSet.getString("alumnos.nombre");
            String grupo = resulSet.getString("tutores.grupo");
            int idLicenciatura = resulSet.getInt("alumnos.idLicenciatura");
            String Licenciatura = resulSet.getString("licenciaturas.nombre");
            int tipo = resulSet.getInt("tipo");
            Alumno alumno;
            alumno = new Alumno(matricula, nombre, 0, grupo, idLicenciatura, Licenciatura, tipo);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public List<Alumno> listarAlumnosTutoradosIndividual(String curp, int periodo) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();

        String sql = "select alumnos.nombre, tutores.grupo  from tutores  ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura where tutores.matricula=alumnos.matricula and tutores.curp='" + curp + "' and tutores.tipo=1 and tutores.idPeriodo='" + periodo + "';";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String nombre = resulSet.getString("alumnos.nombre");
            String grupo = resulSet.getString("tutores.grupo");

            Alumno alumno;
            alumno = new Alumno(null, nombre, 0, grupo, 0, null, 0);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public List<Alumno> listarAlumnosTutoradosIndividualByCarrera(String curp, String carrera, int periodo) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "select alumnos.matricula , alumnos.nombre, tutores.grupo , alumnos.idLicenciatura, tipo , licenciaturas.nombre "
                + " from tutores  ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura "
                + " where tutores.matricula=alumnos.matricula  "
                + " and tutores.curp='" + curp + "' and tutores.tipo=1 and licenciaturas.nombre='" + carrera + "' and tutores.idPeriodo='" + periodo + "';";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String matricula = resulSet.getString("alumnos.matricula");
            String nombre = resulSet.getString("alumnos.nombre");
            String grupo = resulSet.getString("tutores.grupo");
            int idLicenciatura = resulSet.getInt("alumnos.idLicenciatura");
            String Licenciatura = resulSet.getString("licenciaturas.nombre");
            int tipo = resulSet.getInt("tipo");
            Alumno alumno;
            alumno = new Alumno(matricula, nombre, 0, grupo, idLicenciatura, Licenciatura, tipo);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public List<Alumno> listarAlumnosTutoradosGrupalByCarrera(String curp, String carrera) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "select alumnos.matricula , alumnos.nombre, grupos.nombre , alumnos.idLicenciatura, tipo , licenciaturas.nombre "
                + " from tutores ,  grupos ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura "
                + " where tutores.matricula=alumnos.matricula and  grupos.idGrupo=alumnos.idGrupo "
                + " and tutores.curp='" + curp + "' and tutores.tipo=2 and licenciaturas.nombre='" + carrera + "';";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String matricula = resulSet.getString("alumnos.matricula");
            String nombre = resulSet.getString("alumnos.nombre");
            String grupo = resulSet.getString("grupos.nombre");
            int idLicenciatura = resulSet.getInt("alumnos.idLicenciatura");
            String Licenciatura = resulSet.getString("licenciaturas.nombre");
            int tipo = resulSet.getInt("tipo");
            Alumno alumno;
            alumno = new Alumno(matricula, nombre, 0, grupo, idLicenciatura, Licenciatura, tipo);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public List<Alumno> listarAlumnosTutoradosGrupal(String curp, int periodo) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();

        String sql = "select  alumnos.nombre from tutores  inner join alumnos where tutores.matricula=alumnos.matricula and tutores.curp='" + curp + "' and tutores.tipo=2 and tutores.idPeriodo='" + periodo + "'";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String nombre = resulSet.getString("alumnos.nombre");

            Alumno alumno;
            alumno = new Alumno(null, nombre, 0, null, 0, null, 0);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public Alumno obtenerAlumnoByMatricula(String matricula) throws SQLException {
        Alumno alumno = null;

        String sql = "SELECT matricula, alumnos.nombre ,alumnos.idGrupo, "
                + "alumnos.idLicenciatura,licenciaturas.nombre, grupos.grupo FROM"
                + " alumnos inner join licenciaturas inner join grupos on grupos.idGrupo=alumnos.idGrupo and "
                + "alumnos.idLicenciatura=licenciaturas.idLicenciatura WHERE matricula= ? ";

        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, matricula);
        ResultSet res = statement.executeQuery();
        if (res.next()) {

            alumno = new Alumno(res.getString("matricula"),
                    res.getString("alumnos.nombre"),
                    res.getInt("alumnos.idGrupo"),
                    res.getString("grupos.grupo"),
                    res.getInt("alumnos.idLicenciatura"),
                    res.getString("licenciaturas.nombre"), 0);

        }
        res.close();
        con.desconectar();

        return alumno;
    }

    // actualizar
    public void updateAlumno(Alumno alumno) {
        try {

            String sql = "update alumnos set nombre=?,idGrupo=?,idLicenciatura=?"
                    + " where matricula=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, alumno.getNombre());
                statement.setInt(2, alumno.getIdGrupo());
                statement.setInt(3, alumno.getIdLicenciatura());
                statement.setString(4, alumno.getMatricula());
                statement.executeUpdate();
                con.desconectar();
            }

        } catch (SQLException e) {
            System.out.print("error al actualizar:" + e);
        }
    }

    public void eliminar(String matricula) throws SQLException {

        String sql = "DELETE FROM alumnos WHERE matricula=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, matricula);
        preparedStatement.executeUpdate();

        con.desconectar();
    }

}
