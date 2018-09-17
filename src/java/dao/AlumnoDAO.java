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
        String sql = "INSERT INTO alumnos (matricula,nombre,grupo,idLicenciatura)"
	     + " VALUES (?, ?, ?, ?, ?, ?)";
        //System.out.println(profesor.getDescripcion());
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, alumno.getMatricula());
        statement.setString(2, alumno.getNombre());
        statement.setInt(5, alumno.getGrupo());
        statement.setInt(6, alumno.getIdLicenciatura());
        System.err.println("consulta"+sql); 
       boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los alumnos
    public List<Alumno> listarAlumnos() throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "SELECT matricula, alumnos.nombre ,grupo, alumnos.idLicenciatura,licenciaturas.nombre FROM alumnos inner join licenciaturas on alumnos.idLicenciatura=licenciaturas.idLicenciatura ";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
	 String matricula = resulSet.getString("matricula");
	 String nombre = resulSet.getString("alumnos.nombre");
	 int grupo = resulSet.getInt("grupo");
	 int idLicenciatura=resulSet.getInt("alumnos.idLicenciatura");
	 String Licenciatura = resulSet.getString("licenciaturas.nombre");
	 Alumno alumno;
	 alumno = new Alumno(matricula, nombre,grupo,idLicenciatura,Licenciatura,0);
	 listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }

     // listar todos los alumnos
    public List<Alumno> listarAlumnosTutorados(String curp) throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        String sql = "select alumnos.matricula , alumnos.nombre, grupo , alumnos.idLicenciatura, " +
            "tipo , licenciaturas.nombre from tutores , alumnos inner join licenciaturas " +
            " on alumnos.idLicenciatura=licenciaturas.idLicenciatura where tutores.matricula=alumnos.matricula " +
            " and tutores.curp='"+curp+"';";
        System.out.println("consulta"+sql);
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
	 String matricula = resulSet.getString("alumnos.matricula");
	 String nombre = resulSet.getString("alumnos.nombre");
	 int grupo = resulSet.getInt("grupo");
	 int idLicenciatura=resulSet.getInt("alumnos.idLicenciatura");
	 String Licenciatura = resulSet.getString("licenciaturas.nombre");
         int tipo = resulSet.getInt("tipo");
	 Alumno alumno;
	 alumno = new Alumno(matricula, nombre,grupo,idLicenciatura,Licenciatura,tipo);
	 listaAlumnos.add(alumno);
        }
        con.desconectar();
        return listaAlumnos;
    }
    public Alumno obtenerAlumnoByMatricula(String matricula) throws SQLException {
        Alumno alumno = null;

        String sql = "SELECT * FROM alumnos WHERE matricula= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, matricula);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
	 alumno = new Alumno(res.getString("matricula"), res.getString("nombre"), res.getInt("grupo"), res.getInt("idLicenciatura"),res.getString("licenciaturas.nombre"),0);
        }
        res.close();
        con.desconectar();

        return alumno;
    }

    // actualizar
    public boolean actualizar(Alumno alumno) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE alumnos SET matricula=?,nombre=?,grupo=? ,licenciatura=? WHERE matricula=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, alumno.getMatricula());
        statement.setString(2, alumno.getNombre());
        statement.setInt(5, alumno.getGrupo());
        statement.setInt(5, alumno.getIdLicenciatura());

        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Alumno alumno) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM alumnos WHERE matricula=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, alumno.getMatricula());

        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        return rowEliminar;
    }

}
