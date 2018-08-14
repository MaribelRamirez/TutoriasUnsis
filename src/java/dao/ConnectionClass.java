/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
   String uss;
   String contra;
   private Connection conn;
   public ConnectionClass()
   {
      driver = "com.mysql.jdbc.Driver";
      url="jdbc:mysql://localhost:3306/tutoriasUnsis";
      uss="root";
      contra="admin";
   }
/*funcion para loguear*/
   
   public int loguear(String us, String pass)
  {
  
     PreparedStatement pst;
     ResultSet rs;
     int nivel =0;
     String sql= "select nivel from usuarios where user='"+us+"'and pass='"+pass+ "';";
      System.out.println(""+sql);
     try {
        Class.forName(this.driver);
        
        conn=DriverManager.getConnection(
                this.url,
                this.uss,
                this.contra
        );
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next()){
         nivel=rs.getInt(1);
          System.out.println(""+nivel);
        }
        conn.close();
     } catch (ClassNotFoundException | SQLException e) {
     }
            return nivel;
  
  }
    public void desconectar() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
