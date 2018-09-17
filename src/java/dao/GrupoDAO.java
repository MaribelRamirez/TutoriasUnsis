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

/**
 *
 * @author Marifer
 */
public class GrupoDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public GrupoDAO() {
    }

    public GrupoDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    // listar todos los productos
    public List<Grupo> listarGrupos() throws SQLException {

        List<Grupo> listaGrupos = new ArrayList<Grupo>();
        String sql = "SELECT *FROM grupos";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idGrupo");
            String nombre = resulSet.getString("nombre");

            Grupo grupo;
            grupo = new Grupo(id, nombre);
            listaGrupos.add(grupo);
        }
        con.desconectar();
        return listaGrupos;
    }

    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM grupos WHERE idGrupo=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    public boolean insertar(Grupo grupo) throws SQLException {

        try {
            String sql = "INSERT INTO grupos (idGrupo,nombre)"
                    + " VALUES (?,?)";
            //System.out.println(profesor.getDescripcion());
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, grupo.getNombre());

            statement.executeUpdate();
            statement.close();
            con.desconectar();

        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
        }
        return false;

    }
      public Grupo obtenerGrupoById(int idGrp) throws SQLException {
        Grupo grupo = null;

        String sql = "SELECT * FROM grupos WHERE idGrupo= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,idGrp );
        try (ResultSet res = statement.executeQuery()) {
            if (res.next()) {
                grupo = new Grupo(res.getInt("idGrupo"), res.getString("nombre"));
                
            }
        }
        con.desconectar();
        return grupo;
    }
      public void updateGrp(Grupo grupo) {
        try {
            String sql = "update grupos set idGrupo=?, nombre=?"
                    + "where idGrupo=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, grupo.getIdGrupo());
                statement.setString(2, grupo.getNombre());
                   statement.setInt(3, grupo.getIdGrupo());
                statement.executeUpdate();
            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.print("error al insertar:" + e);
        }
    }
}
