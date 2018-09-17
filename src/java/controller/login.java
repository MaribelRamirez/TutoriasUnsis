/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectionClass;
import model.usuario;

/**
 *
 * @author Mine
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("llegue a login");
        String usuario = request.getParameter("txtUsuario");
        String contraseña = request.getParameter("password");
        ConnectionClass conn = new ConnectionClass();
        
        usuario user = conn.loguear(usuario, contraseña);
        int nivel = user.getNivel();
        String curp = user.getCurp();
        System.out.println("esto recivo "+ nivel +" "+curp);
        try {
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HttpSession sesion = request.getSession();
        switch (nivel) {
                 case 1:
                             System.err.println("estoy en login" +nivel);

                     sesion.setAttribute("user", usuario);
                     sesion.setAttribute("nivel", "1");
                     sesion.setAttribute("curp", curp);
                     response.sendRedirect("pages/indexAdmin.jsp");
//                     request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
                     break;
                 case 2:

                     sesion.setAttribute("user", usuario);
                     sesion.setAttribute("nivel", "2");
                     sesion.setAttribute("curp", curp);
                     response.sendRedirect("pages/indexProfesor.jsp");
                     break;
                 case 3:
                     sesion.setAttribute("user", usuario);
                     sesion.setAttribute("nivel", "3");
                     response.sendRedirect("pages/indexAlumno.jsp");
                     break;
                 default:
//                     out.write("El usuario no existe, o la contraseña es invalida");
                     break;
              }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
