/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GrupoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grupo;
import model.Licenciatura;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerGrupo", urlPatterns = {"/ControllerGrupo"})
public class ControllerGrupo extends HttpServlet {
private static final String edit = "pages/actualizarGrupo.jsp";
    String forward = "";
   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public GrupoDAO grupodao;

    public ControllerGrupo() {
        super();
        grupodao = new GrupoDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        processRequest(request, response);

        Grupo grupo = new Grupo();
        String action = request.getParameter("action");
         if (action.equalsIgnoreCase("update")) {
         
                 try {
                forward = edit;
                Grupo grp = grupodao.obtenerGrupoById(Integer.parseInt(request.getParameter("id")));
                
                request.setAttribute("grp", grp);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }
         }
         else if (action.equalsIgnoreCase("delete")) {
          int id = Integer.parseInt(request.getParameter("id"));
            try {
                grupodao.eliminar(id);
                 response.sendRedirect("pages/ListarGrupos.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerGrupo.class.getName()).log(Level.SEVERE, null, ex);
            } 
         }
         else if (action.equalsIgnoreCase("add")) {
          String nomGrup = request.getParameter("nomGrup");
        grupo.setNombre(nomGrup);

        try {
            grupodao.insertar(grupo);
            response.sendRedirect("pages/ListarGrupos.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
           else if (action.equalsIgnoreCase("edit")) {
           
           int id = Integer.parseInt(request.getParameter("id"));
            String nombreGrp = request.getParameter("nombreGrp");
            grupo.setIdGrupo(id);
            grupo.setNombre(nombreGrp);
            grupodao.updateGrp(grupo);
            response.sendRedirect("pages/ListarGrupos.jsp");
       
           }
       
        
        
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
