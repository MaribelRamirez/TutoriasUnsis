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
import model.Tutor;

/**
 *
 * @author Marifer
 */
public class TutorDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public TutorDAO() {
    }

    public TutorDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

//    // listar todos los productos
//    public List<Grupo> listarGrupos() throws SQLException {
//
//        List<Grupo> listaGrupos = new ArrayList<Grupo>();
//        String sql = "select idGrupo, grupo, grupos.idPeriodo , grupos.idLicenciatura, periodo.periodo, nombre " +
//                    "from grupos , periodo, licenciaturas " +
//                    "where grupos.idPeriodo= periodo.idPeriodo and grupos.idLicenciatura=licenciaturas.idLicenciatura;";
//        connection = con.conectar();
//        Statement statement = connection.createStatement();
//        ResultSet resulSet = statement.executeQuery(sql);
//
//        while (resulSet.next()) {
//            int id = resulSet.getInt("idGrupo");
//            String gru = resulSet.getString("grupo");
//            int idPerido = resulSet.getInt("grupos.idPeriodo");
//            int idLicenciatura = resulSet.getInt("grupos.idLicenciatura");
//            String periodo = resulSet.getString("periodo.periodo");
//            String lic = resulSet.getString("nombre");
//            Grupo grupo;
//            grupo = new Grupo(id, gru, idPerido, idLicenciatura,periodo,lic);
//            listaGrupos.add(grupo);
//        }
//        con.desconectar();
//        return listaGrupos;
//    }
//
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
//
    public boolean insertar(Tutor tutor) throws SQLException {

        try {
            String sql = "INSERT INTO tutores (idTutorado,matricula, curp, idPeriodo, tipo)"
                    + " VALUES (?,?,?,?,?)";
            //System.out.println(profesor.getDescripcion());
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, tutor.getMatricula());
            statement.setString(3, tutor.getCurp());
            statement.setInt(4,tutor.getPeriodo());
            statement.setInt(5,tutor.getTipo());

            statement.executeUpdate();
            statement.close();
            con.desconectar();

        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
        }
        return false;

    }
//      public Grupo obtenerGrupoById(int idGrp) throws SQLException {
//        Grupo grupo = null;
//
//        String sql = "select idGrupo, grupo, grupos.idPeriodo , grupos.idLicenciatura, periodo.periodo, nombre " +
//                    "from grupos , periodo, licenciaturas " +
//                    "where grupos.idPeriodo= periodo.idPeriodo and grupos.idLicenciatura=licenciaturas.idLicenciatura and idGrupo= ? ";
//        con.conectar();
//        connection = con.getJdbcConnection();
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setInt(1,idGrp );
//        try (ResultSet res = statement.executeQuery()) {
//            if (res.next()) {
//                grupo = new Grupo(res.getInt("idGrupo"), res.getString("grupo"),
//                        res.getInt("grupos.idPeriodo"), res.getInt("grupos.idLicenciatura"),
//                        res.getString("periodo.periodo"), res.getString("nombre"));
//                
//            }
//        }
//        con.desconectar();
//        return grupo;
//    }
//      public void updateGrp(Grupo grupo) {
//        try {
//            String sql = "update grupos set idGrupo=?, grupo=?, idPeriodo=?, idLicenciatura=? "
//                    + "where idGrupo=?";
//            con.conectar();
//            connection = con.getJdbcConnection();
//            try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                statement.setInt(1, grupo.getIdGrupo());
//                statement.setString(2, grupo.getGrupo());
//                statement.setInt(3,grupo.getIdPeriodo());
//                statement.setInt(4,grupo.getIdLicenciatura());
//                 statement.setInt(5, grupo.getIdGrupo());
//                statement.executeUpdate();
//            }
//            con.desconectar();
//        } catch (SQLException e) {
//            System.out.print("error al insertar:" + e);
//        }
//    }
}
