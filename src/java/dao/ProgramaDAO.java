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
import model.Programa;

public class ProgramaDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public ProgramaDAO() {
    }

    public ProgramaDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    public int verificar(String Prg) {

        int count = 0;
        try {

            String sql = "select *from programas where nombre='" + Prg + "';";

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

    public int countProgramas() {

        int count = 0;
        try {
            String sql = "select *from programas;";
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

    // listar todos los productos
    public List<Programa> listarProgramas() throws SQLException {

        List<Programa> listaProgramas = new ArrayList<Programa>();
        String sql = "SELECT *FROM programas ";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idPrograma");
            String nombre = resulSet.getString("nombre");
            String des = resulSet.getString("des");

            Programa programa;
            programa = new Programa(id, nombre,des);
            listaProgramas.add(programa);
        }
        con.desconectar();
        return listaProgramas;
    }

    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM programas WHERE idPrograma=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    public boolean insertar(Programa programa) throws SQLException {

        try {
            String sql = "INSERT INTO programas (idPrograma,nombre,des)"
                    + " VALUES (?,?)";
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);

            statement.setString(2, programa.getNombre());
            statement.setString(3, programa.getDes());
            statement.executeUpdate();
            statement.close();
            con.desconectar();
            return true;
        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
            return false;
        }

    }

    public Programa obtenerProgramaById(int idLic) throws SQLException {
        Programa programa = null;

        String sql = "SELECT * FROM programas WHERE idPrograma= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idLic);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            programa = new Programa(res.getInt("idPrograma"), res.getString("nombre"), res.getString("des"));

        }
        res.close();
        con.desconectar();
        return programa;
    }

    public boolean updatePrg(Programa programa) {
        try {
            String sql = "update programas set idPrograma=?, nombre=?, des=?"
                    + "where idPrograma=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, programa.getIdPrograma());
                statement.setString(2, programa.getNombre());
                 statement.setString(3, programa.getDes());
                statement.setInt(4, programa.getIdPrograma());
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
