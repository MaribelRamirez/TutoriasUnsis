package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionClass;
import model.Reporte;

public class ReporteDAO {

    ConnectionClass con = new ConnectionClass();
    private Connection connection;

    public ReporteDAO() {
    }

    public ReporteDAO(ConnectionClass con, Connection connection) {
        this.con = con;
        this.connection = connection;
    }

    public int verificar(String curp, int idPeriodo) {

        int count = 0;
        try {

            String sql = "SELECT profesores.nombre, periodo.idPeriodo, periodo.periodo, programas.idPrograma,\n"
                    + "	programas.nombre,reportes.idReporte,reportes.curp, reportes.entrego,reportes.aTiempo,\n"
                    + "	reportes.fecha,reportes.tipoTutoria,reportes.noSesiones,reportes.noCanalizaciones,\n"
                    + "	reportes.alumnosAsignados,reportes.alumnosReportados,reportes.alumnosAsistencia,\n"
                    + "	reportes.observaciones,reportes.faltantes FROM profesores inner join reportes inner join\n"
                    + "	programas inner join periodo on profesores.curp=reportes.curp and \n"
                    + "	reportes.idPeriodo=periodo.idPeriodo and reportes.programa=programas.idPrograma\n"
                    + "    where profesores.curp='" + curp + "' and periodo.idPeriodo='" + idPeriodo + "' ; ";

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

    public List<Reporte> listarReportes() throws SQLException {

        List<Reporte> listaReportes = new ArrayList<Reporte>();
        String sql = "SELECT profesores.nombre,profesores.grado, periodo.idPeriodo, periodo.periodo, programas.idPrograma,"
                + "programas.nombre,reportes.idReporte,reportes.curp, reportes.entrego,reportes.aTiempo,"
                + "reportes.fecha,reportes.tipoTutoria,reportes.noSesiones,reportes.noCanalizaciones,"
                + "reportes.alumnosAsignados,reportes.alumnosReportados,reportes.alumnosAsistencia,"
                + "reportes.observaciones,reportes.faltantes  FROM profesores "
                + "inner join reportes inner join programas inner join periodo on profesores.curp=reportes.curp "
                + "and reportes.idPeriodo=periodo.idPeriodo and reportes.programa=programas.idPrograma";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {

            int idReporte = resulSet.getInt("reportes.idReporte");
            String curp = resulSet.getString("reportes.curp");
            String profesor = resulSet.getString("profesores.nombre");
            String grado = resulSet.getString("profesores.grado");
            String programa = resulSet.getString("programas.nombre");
            int idPrograma = resulSet.getInt("programas.idPrograma");
            int idPeriodo = resulSet.getInt("periodo.idPeriodo");
            String periodo = resulSet.getString("periodo.periodo");
            String entrego = resulSet.getString("reportes.entrego");
            String aTiempo = resulSet.getString("reportes.aTiempo");
            Date fecha = resulSet.getDate("reportes.fecha");
            String tipoTutoria = resulSet.getString("reportes.tipoTutoria");
            int noSesiones = resulSet.getInt("reportes.noSesiones");
            int noCanalizaciones = resulSet.getInt("reportes.noCanalizaciones");
            int alumnosAsignados = resulSet.getInt("reportes.alumnosAsignados");
            int alumnosReportados = resulSet.getInt("reportes.alumnosReportados");
            int alumnosAsistencia = resulSet.getInt("reportes.alumnosAsistencia");
            String observaciones = resulSet.getString("reportes.observaciones");
            String faltantes = resulSet.getString("reportes.faltantes");
            Reporte reporte = new Reporte(idReporte, curp, profesor, grado, programa, idPrograma, idPeriodo, periodo, entrego, aTiempo, fecha, tipoTutoria, noSesiones, noCanalizaciones, alumnosAsignados, alumnosReportados, alumnosAsistencia, observaciones, faltantes);
            listaReportes.add(reporte);
        }
        con.desconectar();
        return listaReportes;
    }

    public void eliminarById(int id) throws SQLException {
        String sql = "DELETE FROM reportes WHERE idReporte=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        con.desconectar();
    }

    public Reporte obtenerReporteById(int id) throws SQLException {
        Reporte reporte = null;

        String sql = "SELECT profesores.nombre,profesores.grado, periodo.idPeriodo, periodo.periodo, programas.idPrograma,"
                + "programas.nombre,reportes.idReporte,reportes.curp, reportes.entrego,reportes.aTiempo,"
                + "reportes.fecha,reportes.tipoTutoria,reportes.noSesiones,reportes.noCanalizaciones,"
                + "reportes.alumnosAsignados,reportes.alumnosReportados,reportes.alumnosAsistencia,"
                + "reportes.observaciones,reportes.faltantes FROM profesores inner join reportes inner join"
                + " programas inner join periodo on profesores.curp=reportes.curp and "
                + "reportes.idPeriodo=periodo.idPeriodo and reportes.programa=programas.idPrograma WHERE"
                + " reportes.idReporte =? ; ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            reporte = new Reporte(
                    res.getInt("reportes.idReporte"),
                    res.getString("reportes.curp"),
                    res.getString("profesores.nombre"),
                    res.getString("profesores.grado"),
                    res.getString("programas.nombre"),
                    res.getInt("programas.idPrograma"),
                    res.getInt("periodo.idPeriodo"),
                    res.getString("periodo.periodo"),
                    res.getString("reportes.entrego"),
                    res.getString("reportes.aTiempo"),
                    res.getDate("reportes.fecha"),
                    res.getString("reportes.tipoTutoria"),
                    res.getInt("reportes.noSesiones"),
                    res.getInt("reportes.noCanalizaciones"),
                    res.getInt("reportes.alumnosAsignados"),
                    res.getInt("reportes.alumnosReportados"),
                    res.getInt("reportes.alumnosAsistencia"),
                    res.getString("reportes.observaciones"),
                    res.getString("reportes.faltantes")
            );
        }
        res.close();
        con.desconectar();

        return reporte;
    }

    public boolean insertar(Reporte reporte) throws SQLException {
        try {
            String sql = "INSERT INTO reportes (idReporte,idPeriodo,curp,programa,entrego,aTiempo,fecha,tipoTutoria,noSesiones,noCanalizaciones,"
                    + "alumnosAsignados ,alumnosReportados,alumnosAsistencia,observaciones,faltantes) VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            statement.setInt(2, reporte.getIdPeriodo());
            statement.setString(3, reporte.getCurp());
            statement.setInt(4, reporte.getIdPrograma());
            statement.setString(5, reporte.getEntrego());
            statement.setString(6, reporte.getaTiempo());
            statement.setDate(7, reporte.getFecha());
            statement.setString(8, reporte.getTipoTutoria());
            statement.setInt(9, reporte.getNoSesiones());
            statement.setInt(10, reporte.getNoCanalizaciones());
            statement.setInt(11, reporte.getAlumnosAsignados());
            statement.setInt(12, reporte.getAlumnosReportados());
            statement.setInt(13, reporte.getAlumnosAsistencia());
            statement.setString(14, reporte.getObservaciones());
            statement.setString(15, reporte.getFaltantes());
            statement.executeUpdate();
            statement.close();
            con.desconectar();
            return true;
        } catch (Exception e) {
            System.out.print("error al insertar:" + e);
            return false;
        }

    }

    public boolean update(Reporte reporte) {
        try {
            String sql = "update reportes set idReporte=?,idPeriodo=?,curp=?,programa=?,entrego=?,aTiempo=?,fecha=?,"
                    + "tipoTutoria=?,noSesiones=?,noCanalizaciones=?,alumnosAsignados=?,alumnosReportados=?,"
                    + "alumnosAsistencia=?,observaciones=?,faltantes=? where idReporte=?";
            con.conectar();
            connection = con.getJdbcConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, reporte.getIdReporte());
                statement.setInt(2, reporte.getIdPeriodo());
                statement.setString(3, reporte.getCurp());
                statement.setInt(4, reporte.getIdPrograma());
                statement.setString(5, reporte.getEntrego());
                statement.setString(6, reporte.getaTiempo());
                statement.setDate(7, reporte.getFecha());
                statement.setString(8, reporte.getTipoTutoria());
                statement.setInt(9, reporte.getNoSesiones());
                statement.setInt(10, reporte.getNoCanalizaciones());
                statement.setInt(11, reporte.getAlumnosAsignados());
                statement.setInt(12, reporte.getAlumnosReportados());
                statement.setInt(13, reporte.getAlumnosAsistencia());
                statement.setString(14, reporte.getObservaciones());
                statement.setString(15, reporte.getFaltantes());
                statement.setInt(16, reporte.getIdReporte());
                statement.executeUpdate();
                con.desconectar();
                return true;
            }

        } catch (SQLException e) {
            System.out.print("error al actualizar:" + e);
            return false;
        }
    }

    public List<Reporte> obtenerReportesByPeriodo(int Periodo) throws SQLException {

        List<Reporte> listaReportes = new ArrayList<Reporte>();
        String sql = "SELECT profesores.nombre,profesores.grado, periodo.idPeriodo, periodo.periodo, programas.idPrograma,"
                + "programas.nombre, reportes.entrego,reportes.aTiempo,"
                + "reportes.fecha,reportes.tipoTutoria,reportes.noSesiones,reportes.noCanalizaciones,"
                + "reportes.alumnosAsignados,reportes.alumnosReportados,reportes.alumnosAsistencia,"
                + "reportes.observaciones,reportes.faltantes FROM profesores inner join reportes inner join"
                + " programas inner join periodo on profesores.curp=reportes.curp and "
                + "reportes.idPeriodo=periodo.idPeriodo and reportes.programa=programas.idPrograma "
                + "where periodo.idPeriodo='" + Periodo + "';";
        connection = con.conectar();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            String nombre = resulSet.getString("profesores.nombre");
            String grado = resulSet.getString("profesores.grado");
            String programa = resulSet.getString("programas.nombre");
            String entrego = resulSet.getString("reportes.entrego");
            String aTiempo = resulSet.getString("reportes.aTiempo");
            String periodo = resulSet.getString("periodo.periodo");
            Date fecha = resulSet.getDate("fecha");
            String faltantes = resulSet.getString("reportes.faltantes");
            String tipoTutoria = resulSet.getString("reportes.tipoTutoria");
            int noSesiones = resulSet.getInt("reportes.noSesiones");
            int noCanalizaciones = resulSet.getInt("reportes.noCanalizaciones");
            int alumnosAsignados = resulSet.getInt("reportes.alumnosAsignados");
            int alumnosReportados = resulSet.getInt("reportes.alumnosReportados");
            int alumnosAsistencia = resulSet.getInt("reportes.alumnosAsistencia");
            String observaciones = resulSet.getString("reportes.observaciones");

            Reporte reporte = new Reporte(0, null, nombre, grado, programa, 0, 0, periodo, entrego, aTiempo, fecha, tipoTutoria,
                    noSesiones, noCanalizaciones, alumnosAsignados, alumnosReportados, alumnosAsistencia, observaciones, faltantes);
            listaReportes.add(reporte);
        }
        con.desconectar();
        return listaReportes;
    }

}
