/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LicenciaturaDAO;
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
import model.Licenciatura;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerLicenciatura", urlPatterns = {"/ControllerLicenciatura"})
public class ControllerLicenciatura extends HttpServlet {

    private static final String edit = "pages/actualizarLicenciatura.jsp";
    // private static final String list="pages/ListarLicenciaturas.jsp";
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
    public LicenciaturaDAO licenciaturadao;

    public ControllerLicenciatura() {
        super();
        licenciaturadao = new LicenciaturaDAO();

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
        Licenciatura Lic = new Licenciatura();
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("update")) {
            try {
                forward = edit;
                Licenciatura lic = licenciaturadao.obtenerLicenciaturaById(Integer.parseInt(request.getParameter("id")));
                
                request.setAttribute("lic", lic);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }
        } else if (action.equalsIgnoreCase("delete")) {
             
            int id = Integer.parseInt(request.getParameter("id"));
            
            try {
                licenciaturadao.eliminar(id);
                response.sendRedirect("pages/ListarLicenciaturas.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("add")) {

            String nomLic = request.getParameter("nombreLic");
            Lic.setNombre(nomLic);
            try {
                licenciaturadao.insertar(Lic);
                response.sendRedirect("pages/ListarLicenciaturas.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equalsIgnoreCase("edit")) {
       int id = Integer.parseInt(request.getParameter("id"));
            String nomLic = request.getParameter("nombreLic");
            Lic.setIdLicenciatura(id);
            Lic.setNombre(nomLic);
            licenciaturadao.updateLic(Lic);
            response.sendRedirect("pages/ListarLicenciaturas.jsp");
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
