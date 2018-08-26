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

    // insertar artículo
    public boolean insertar(Profesor profesor) throws SQLException {
        String sql = "INSERT INTO profesores (idProfesor,estatus,nombre,grado,licenciatura)"
	     + " VALUES (?, ?,?,?,?)";
        //System.out.println(profesor.getDescripcion());
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, null);
        statement.setString(2, profesor.getEstatus());
        statement.setString(3, profesor.getNombre());
        statement.setString(4, profesor.getGrado());
        statement.setInt(5, profesor.getIdLicenciatura());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los productos
    public List<Profesor> listarProfesores() throws SQLException {

        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        String sql = "SELECT * FROM profesores";
        connection =  con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
	 int idProfesor = resulSet.getInt("idProfesor");
	 String estatus = resulSet.getString("estatus");
	 String nombre = resulSet.getString("nombre");
	 String grado = resulSet.getString("grado");
	 String licenciatura = resulSet.getString("licenciatura");
         int idLicenciatura = 0;
	 Profesor profesor = new Profesor(idProfesor,  nombre, estatus, grado, idLicenciatura, licenciatura);
	 listaProfesores.add(profesor);
        }
        con.desconectar();
        return listaProfesores;
    }

    // obtener por id
    public Profesor obtenerProfsorById(int idProfesor) throws SQLException {
        Profesor profesor = null;

        String sql = "SELECT * FROM profesores WHERE idProfesor= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idProfesor);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
	 profesor = new Profesor(res.getInt("idProfesor"), res.getString("nombre"),
	         res.getString("estatus"), res.getString("grado"), 
                 res.getInt("licenciatura"),res.getString("licenciatura"));
        }
        res.close();
        con.desconectar();

        return profesor;
    }

    // actualizar
    public boolean actualizar(Profesor profesor) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE profesor SET idProfesor=?,nombre=?,apellidoP=?,apellidoM=?, licenciatura=? WHERE idProfesor=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, null);
//        statement.setString(2, profesor.getNombre());
//        statement.setString(3, profesor.getApellidoP());
//        statement.setString(4, profesor.getApellidoM());
//        statement.setInt(5, profesor.getLicenciatura());

        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Profesor profesor) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM profesores WHERE idProfesor=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, profesor.getIdProfesor());

        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        return rowEliminar;
    }

}
