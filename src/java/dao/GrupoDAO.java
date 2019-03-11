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
import model.Grupo;

public class GrupoDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public GrupoDAO() {
    }

    public GrupoDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    public int verificar(String grupo) {

        int count = 0;
        try {

            String sql = "SELECT *from grupos where grupos.grupo='" + grupo + "'; ";

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

    public Grupo obtenerGrupobyProf(String curp, int periodo) throws SQLException {
        Grupo grupo = null;

        String sql = "select distinct tutores.grupo from tutores inner join alumnos on tutores.matricula=alumnos.matricula  where tutores.tipo=2 and tutores.curp=? and tutores.idPeriodo=?";

        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, curp);
        statement.setInt(2, periodo);
        ResultSet res = statement.executeQuery();
        if (res.next()) {

            grupo = new Grupo(0, res.getString("grupo"), 0, 0, "", "");

        }
        res.close();
        con.desconectar();

        return grupo;
    }

    // listar todos los productos
    public List<Grupo> listarGrupos() throws SQLException {

        List<Grupo> listaGrupos = new ArrayList<Grupo>();
        String sql = "select idGrupo, grupo, grupos.idPeriodo , grupos.idLicenciatura, periodo.periodo, nombre "
                + "from grupos , periodo, licenciaturas "
                + "where grupos.idPeriodo= periodo.idPeriodo and grupos.idLicenciatura=licenciaturas.idLicenciatura;";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idGrupo");
            String gru = resulSet.getString("grupo");
            int idPerido = resulSet.getInt("grupos.idPeriodo");
            int idLicenciatura = resulSet.getInt("grupos.idLicenciatura");
            String periodo = resulSet.getString("periodo.periodo");
            String lic = resulSet.getString("nombre");
            Grupo grupo;
            grupo = new Grupo(id, gru, idPerido, idLicenciatura, periodo, lic);
            listaGrupos.add(grupo);
        }
        con.desconectar();
        return listaGrupos;
    }

    public List<Grupo> listarGruposByLic(int lic, int per) throws SQLException {

        List<Grupo> listaGrupos = new ArrayList<Grupo>();
        String sql = "select idGrupo, grupo, grupos.idPeriodo , grupos.idLicenciatura, periodo.periodo, nombre "
                + "from grupos , periodo, licenciaturas where grupos.idPeriodo= periodo.idPeriodo "
                + "and grupos.idLicenciatura=licenciaturas.idLicenciatura and grupos.idLicenciatura=" + lic + " and grupos.idPeriodo=" + per + ";";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idGrupo");
            String gru = resulSet.getString("grupo");
            int idPerido = resulSet.getInt("grupos.idPeriodo");
            int idLicenciatura = resulSet.getInt("grupos.idLicenciatura");
            String periodo = resulSet.getString("periodo.periodo");
            String li = resulSet.getString("nombre");
            Grupo grupo;
            grupo = new Grupo(id, gru, idPerido, idLicenciatura, periodo, li);
            listaGrupos.add(grupo);
        }
        con.desconectar();
        return listaGrupos;
    }

    public List<Grupo> listarGruposActuales(int per) throws SQLException {

        List<Grupo> listaGrupos = new ArrayList<Grupo>();
        String sql = "select * from grupos where idPeriodo=" + per + ";";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("idGrupo");
            String gru = resulSet.getString("grupo");
            int idPerido = resulSet.getInt("idPeriodo");
            int idLicenciatura = resulSet.getInt("idLicenciatura");
            String periodo = "";
            String li = "";
            Grupo grupo;
            grupo = new Grupo(id, gru, idPerido, idLicenciatura, periodo, li);
            listaGrupos.add(grupo);
        }
        con.desconectar();
        return listaGrupos;
    }
    //regresa los grupos de determinada carrera a los que tutora determinado profesor 

    public List<Grupo> listarGruposTutorados(String curp, String carrera, int periodo) throws SQLException {

        List<Grupo> listaGrupos = new ArrayList<Grupo>();
        String sql = "select distinct grupos.grupo  from tutores ,  grupos ,alumnos inner join licenciaturas  on alumnos.idLicenciatura=licenciaturas.idLicenciatura where tutores.matricula=alumnos.matricula  and tutores.curp='" + curp + "' and tutores.tipo=2 and licenciaturas.nombre='" + carrera + "' group by(grupos.grupo) and tutores.idPeriodo='" + periodo + "';";

        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String nombre = resulSet.getString("grupos.grupo");

            Grupo grupo;
            grupo = new Grupo(0, nombre, 0, 0, "", "");
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
            String sql = "INSERT INTO grupos (idGrupo,grupo, idPeriodo, idLicenciatura)"
                    + " VALUES (?,?,?,?)";
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, grupo.getGrupo());
            statement.setInt(3, grupo.getIdPeriodo());
            statement.setInt(4, grupo.getIdLicenciatura());

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
        String sql = "select idGrupo, grupo, grupos.idPeriodo , grupos.idLicenciatura, periodo.periodo, nombre "
                + "from grupos , periodo, licenciaturas "
                + "where grupos.idPeriodo= periodo.idPeriodo and grupos.idLicenciatura=licenciaturas.idLicenciatura and idGrupo= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idGrp);
        try (ResultSet res = statement.executeQuery()) {
            if (res.next()) {
                grupo = new Grupo(res.getInt("idGrupo"), res.getString("grupo"),
                        res.getInt("grupos.idPeriodo"), res.getInt("grupos.idLicenciatura"),
                        res.getString("periodo.periodo"), res.getString("nombre"));

            }
        }
        con.desconectar();
        return grupo;
    }

    public void updateGrp(Grupo grupo) {
        try {
            String sql = "update grupos set idGrupo=?, grupo=?, idPeriodo=?, idLicenciatura=? "
                    + "where idGrupo=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, grupo.getIdGrupo());
                statement.setString(2, grupo.getGrupo());
                statement.setInt(3, grupo.getIdPeriodo());
                statement.setInt(4, grupo.getIdLicenciatura());
                statement.setInt(5, grupo.getIdGrupo());
                statement.executeUpdate();
            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.print("error al insertar:" + e);
        }
    }
}
