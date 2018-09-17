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
import model.Alumno;
import model.ConnectionClass;

/**
 *
 * @author Marifer
 */
public class AlumnoDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public AlumnoDAO() {
    }

    public AlumnoDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    // insertar artículo
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

    // listar todos los productos
    public List<Alumno> listarAlumnos() throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "SELECT matricula, alumnos.nombre ,alumnos.idGrupo, alumnos.idLicenciatura,licenciaturas.nombre, grupos.nombre FROM alumnos inner join licenciaturas inner join grupos on grupos.idGrupo=alumnos.idGrupo and alumnos.idLicenciatura=licenciaturas.idLicenciatura";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String matricula = resulSet.getString("matricula");
            String nombre = resulSet.getString("alumnos.nombre");
            int idgrupo = resulSet.getInt("alumnos.idGrupo");
            String grupo = resulSet.getString("grupos.nombre");
            int idLicenciatura = resulSet.getInt("alumnos.idLicenciatura");
            String Licenciatura = resulSet.getString("licenciaturas.nombre");
            Alumno alumno;
            alumno = new Alumno(matricula, nombre, idgrupo, grupo, idLicenciatura, Licenciatura);
            listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

    public Alumno obtenerAlumnoByMatricula(String matricula) throws SQLException {
        Alumno alumno = null;

        String sql = "SELECT matricula, alumnos.nombre ,alumnos.idGrupo, "
                + "alumnos.idLicenciatura,licenciaturas.nombre, grupos.nombre FROM"
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
                    res.getString("grupos.nombre"),
                    res.getInt("alumnos.idLicenciatura"),
                    res.getString("licenciaturas.nombre"));
        }
        res.close();
        con.desconectar();
 
        return alumno;
    }

    // actualizar
    public void updateAlumno(Alumno alumno) {
        try {

            String sql = "update alumnos set nombre=?,idGrupo=?,idLicenciatura=?" + 
                    " where matricula=?";
            con.conectar();
            connection = con.getJdbcConnection();
            System.out.println("esta es la sql de actualizar alumnos: " + sql);
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

    //eliminar
    /**
     * public boolean eliminar(Alumno alumno) throws SQLException { boolean
     * rowEliminar = false; String sql = "DELETE FROM alumnos WHERE
     * matricula=?"; con.conectar(); connection = con.getJdbcConnection();
     * PreparedStatement statement = connection.prepareStatement(sql);
     * statement.setString(1, alumno.getMatricula());
     *
     * rowEliminar = statement.executeUpdate() > 0; statement.close();
     * con.desconectar();
     *
     * return rowEliminar;
     *
     * @param matricula
     * @param userId}
     */
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
