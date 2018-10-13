/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionClass;
import model.Periodo;

/**
 *
 * @author Marifer
 */
public class PeriodoDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public PeriodoDAO() {
    }

    public PeriodoDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    // listar todos los productos
    public List<Periodo> listarPeriodos() throws SQLException {

        List<Periodo> listaPeriodos = new ArrayList<Periodo>();
        String sql = "SELECT *FROM periodo";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idPeriodo");
            String nombre = resulSet.getString("periodo");
            Date fechaInicio = resulSet.getDate("fechaInicio");
            Date fechaFin = resulSet.getDate("fechaFin");

            Periodo periodo;
            periodo = new Periodo(id, nombre, fechaInicio, fechaFin);
            listaPeriodos.add(periodo);
        }
        con.desconectar();
        return listaPeriodos;
    }

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

    public boolean insertar(Periodo periodo) throws SQLException {
        
        try {
            String sql = "INSERT INTO periodo (idPeriodo,periodo, fechaInicio, fechaFin)"
                    + " VALUES (?,?,?,?)";
            //System.out.println(profesor.getDescripcion());
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, periodo.getPeriodo());
            statement.setDate(3, periodo.getFechaInicio());
            statement.setDate(4, periodo.getFechaFin());

            statement.executeUpdate();
            statement.close();
            con.desconectar();

        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
        }
        return false;

    }
      public Periodo obtenerPeriodoById(int idPdo) throws SQLException {
        Periodo periodo = null;

        String sql = "SELECT * FROM periodo WHERE idPeriodo= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,idPdo );
        try (ResultSet res = statement.executeQuery()) {
            if (res.next()) {
                periodo = new Periodo(res.getInt("idPeriodo"),
                        res.getString("periodo"),
                        res.getDate("fechaInicio"),
                        res.getDate("fechaFin"));
                
            }
        }
        con.desconectar();
        return periodo;
    }
      public void updatePeriodo(Periodo periodo) {
        try {
            String sql = "update periodo set  periodo=? , fechaInicio=?, fechaFin=?"
                    + "where idPeriodo=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, periodo.getPeriodo());
                statement.setDate(2, periodo.getFechaInicio());
                statement.setDate(3, periodo.getFechaFin());
                statement.setInt(4, periodo.getIdPeriodo());
                statement.executeUpdate();
            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.print("error al actualizar:" + e);
        }
    }
}
