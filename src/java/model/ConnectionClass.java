/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marifer
 */
public class ConnectionClass {

    String driver;
    String url;
    String user;
    String pass;
    private Connection conn;

    public ConnectionClass() {
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/tutoriasunsis?autoReconnect=true&useSSL=false";
       //url = "jdbc:mysql://localhost:3306/tutoriasUnsis";
       //user = "root";
      // pass = "Kingston12.";
        user = "root";
        pass = "root";
    }
    /**funcion para loguear*/

    public ConnectionClass(String url, String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); /**To change body of generated methods, choose Tools | Templates.*/
    }

    public usuario loguear(String us, String pass) {
        usuario user= new usuario();
        PreparedStatement pst;
        ResultSet rs;
        int nivel = 0;
        String sql = "select nivel from usuarios where user='" + us + "'and pass='" + pass + "';";
        System.out.println("" + sql);
        try {
            System.out.println("dentro de try");
	 Class.forName(this.driver);
         System.out.println("this.driver");
	 conn = DriverManager.getConnection(
	         this.url,
	         this.user,
	         this.pass
	 );
         
	 pst = conn.prepareStatement(sql);
	 rs = pst.executeQuery();
	 while (rs.next()) {
	     user.setNivel(rs.getInt(1));
             user.setPassw(pass);
             user.setUsuario(us);
	 }
	 conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("nnnnn"+ e);
        }
        return user;

    }

    public Connection conectar() throws SQLException {
               
        if (conn == null || conn.isClosed()) {
	 try {
	     Class.forName(this.driver);
	     conn = DriverManager.getConnection(
	         this.url,
	         this.user,
	         this.pass);
	     return conn;
	 } catch (ClassNotFoundException e) {
	     throw new SQLException(e);
	 }
	 
        }
        return null;
    }

    public void desconectar() throws SQLException {
        if (conn != null && !conn.isClosed()) {
	 conn.close();
        }
    }

    public Connection getJdbcConnection() {
        return conn;
    }
}
