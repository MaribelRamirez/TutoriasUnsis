package dao;

import model.ConnectionClass;
import VO.PdfVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PdfDAO {

    /*Metodo listar*/
    public ArrayList<PdfVO> Listar_PdfVOReportes() {
        ArrayList<PdfVO> list = new ArrayList<PdfVO>();
        ConnectionClass conec = new ConnectionClass();
        String sql = "SELECT * FROM archivos where categoria='1';";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setNombrepdf(rs.getString(2));
                vo.setArchivopdf2(rs.getBytes(3));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }
    public ArrayList<PdfVO> Listar_PdfVOMaterial() {
        ArrayList<PdfVO> list = new ArrayList<PdfVO>();
        ConnectionClass conec = new ConnectionClass();
        String sql = "SELECT * FROM archivos where categoria='2';";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setNombrepdf(rs.getString(2));
                vo.setArchivopdf2(rs.getBytes(3));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }


    /*Metodo agregar*/
    public void Agregar_PdfVO(PdfVO vo) {
        ConnectionClass conec = new ConnectionClass();
        String sql = "INSERT INTO archivos (idArchivo, nombre, categoria, archivo) VALUES(?, ?, ?,? );";
        System.err.println("Estoy agregaar datos " + vo.getNombrepdf()+vo.getCategoria()+vo.getCodigopdf());
        PreparedStatement ps = null;
        try {
            ps = conec.conectar().prepareStatement(sql);
            ps.setInt(1, vo.getCodigopdf());
            ps.setString(2, vo.getNombrepdf());
            ps.setString(3, vo.getCategoria());
            ps.setBlob(4, vo.getArchivopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }


    /*Metodo Modificar*/
    public void Modificar_PdfVO(PdfVO vo) {
        ConnectionClass conec = new ConnectionClass();
        String sql = "UPDATE pdf SET nombrepdf = ?, archivopdf = ? WHERE codigopdf = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.conectar().prepareStatement(sql);
            ps.setString(1, vo.getNombrepdf());
            ps.setBlob(2, vo.getArchivopdf());
            ps.setInt(3, vo.getCodigopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    /*Metodo Modificar*/
    public void Modificar_PdfVO2(PdfVO vo) {
        ConnectionClass conec = new ConnectionClass();
        String sql = "UPDATE pdf SET nombrepdf = ? WHERE codigopdf = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.conectar().prepareStatement(sql);
            ps.setString(1, vo.getNombrepdf());
            ps.setInt(2, vo.getCodigopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    /*Metodo Eliminar*/
    public void Eliminar_PdfVO(int id) {
        ConnectionClass conec = new ConnectionClass();
        String sql = "DELETE FROM pdf WHERE codigopdf = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }


    /*Metodo Consulta id*/
    public PdfVO getPdfVOById(int studentId) {
        PdfVO vo = new PdfVO();
        ConnectionClass db = new ConnectionClass();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM archivos WHERE idArchivo = ?;";
        try {
            preparedStatement = db.conectar().prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                vo.setCodigopdf(rs.getInt(1));
                vo.setNombrepdf(rs.getString(2));
                vo.setArchivopdf2(rs.getBytes(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                rs.close();
                preparedStatement.close();
                db.desconectar();
            } catch (Exception ex) {
            }
        }
        return vo;
    }

}
