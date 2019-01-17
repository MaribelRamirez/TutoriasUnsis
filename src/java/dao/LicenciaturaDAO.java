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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionClass;
import model.Licenciatura;

/**
 *
 * @author Marifer
 */
public class LicenciaturaDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public LicenciaturaDAO() {
    }

    public LicenciaturaDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    public int verificar(String lic) {
       
         int count=0;
        try {
            
            
            String sql = "select *from licenciaturas where nombre='"+lic+"';";
            
            System.out.println("consulta"+sql);
            connection = con.conectar();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
           
            while (resulSet.next()) {
                count ++;
            }
            con.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("este es el valor de conunt"+count);
        return count;
}
     public int countLicenciaturas() {
       
         int count=0;
        try {
            String sql = "select *from licenciaturas;";
            connection = con.conectar();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
           
            while (resulSet.next()) {
                count ++;
            }
            con.desconectar();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
}
    
    // listar todos los productos
    public List<Licenciatura> listarLicenciaturas() throws SQLException {

        List<Licenciatura> listaLicenciaturas = new ArrayList<Licenciatura>();
        String sql = "SELECT *FROM licenciaturas";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idLicenciatura");
            String nombre = resulSet.getString("nombre");

            Licenciatura licenciatura;
            licenciatura = new Licenciatura(id, nombre);
            listaLicenciaturas.add(licenciatura);
        }
        con.desconectar();
        return listaLicenciaturas;
    }

    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM licenciaturas WHERE idLicenciatura=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    public boolean insertar(Licenciatura licenciatura) throws SQLException {

        try {
            String sql = "INSERT INTO licenciaturas (idLicenciatura,nombre)"
                    + " VALUES (?,?)";
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);

            statement.setString(2, licenciatura.getNombre());

            statement.executeUpdate();
            statement.close();
            con.desconectar();
 return true;
        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
            return false;
        }
        

    }

    public Licenciatura obtenerLicenciaturaById(int idLic) throws SQLException {
        Licenciatura licenciatura = null;

        String sql = "SELECT * FROM licenciaturas WHERE idLicenciatura= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idLic);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            licenciatura = new Licenciatura(res.getInt("idLicenciatura"), res.getString("nombre"));

        }
        res.close();
        con.desconectar();
        return licenciatura;
    }

   public boolean updateLic(Licenciatura licenciatura) {
        try {
            String sql = "update licenciaturas set idLicenciatura=?, nombre=?"
                    + "where idLicenciatura=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, licenciatura.getIdLicenciatura());
                statement.setString(2, licenciatura.getNombre());
                   statement.setInt(3, licenciatura.getIdLicenciatura());
                statement.executeUpdate();
            }
            con.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.print("error al insertar:" + e);
            return false;
        }
    }

}
