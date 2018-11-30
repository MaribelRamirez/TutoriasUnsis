/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfesorDAO;
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
import model.Profesor;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerProfesor", urlPatterns = {"/ControllerProfesor"})
public class ControllerProfesor extends HttpServlet {
 private static final String edit = "pages/actualizarProfesor.jsp";
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
    public ProfesorDAO profesordao;

    public ControllerProfesor() {
        super();
        profesordao = new ProfesorDAO();
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
   response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        Profesor pro = new Profesor();
 String action = request.getParameter("action");
        if (action.equalsIgnoreCase("update")) {
        
        try {
                forward = edit;
                Profesor prf = profesordao.obtenerProfesorById(Integer.parseInt(request.getParameter("idprf")));
                request.setAttribute("prf", prf);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }
        }
        else if (action.equalsIgnoreCase("delete")) {
        
      int idprf = Integer.parseInt(request.getParameter("idprf"));
            try {
                profesordao.eliminar(idprf);
                 out.print("<html><head></head><body "
                           + "onload=\"alert('Profesor eliminado de forma correcta');"
                           + " window.location='pages/indexAdmin.jsp'\"><body></html>");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
            //response.sendRedirect("pages/indexAdmin.jsp");

        }
        
        else if (action.equalsIgnoreCase("add")) {
        
        
        String curp = request.getParameter("curp");
        String nombre = request.getParameter("nombre");
        String grado_acad = request.getParameter("grado_acad");
        String status = request.getParameter("status");
        int lic = Integer.parseInt(request.getParameter("lic"));
        pro.setCurp(curp);
        pro.setNombre(nombre);
        pro.setGrado(grado_acad);
        pro.setEstatus(status);
        pro.setIdLicenciatura(lic);

        try {
            profesordao.insertar(pro);
             out.print("<html><head></head><body "
                           + "onload=\"alert('Profesor agregado de forma correcta');"
                           + " window.location='pages/indexAdmin.jsp'\"><body></html>");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else if (action.equalsIgnoreCase("edit")) {
            try {
                int idprf = Integer.parseInt(request.getParameter("idprf"));
            String curp = request.getParameter("curp");
            String nombre = request.getParameter("nombre");
            String grado =request.getParameter("grado");
            String estatus =request.getParameter("estatus");
            int lic = Integer.parseInt(request.getParameter("lic"));
            pro.setIdProfesor(idprf);
            pro.setCurp(curp);
            pro.setNombre(nombre);
            pro.setGrado(grado);
            pro.setEstatus(estatus);
            pro.setIdLicenciatura(lic);
            profesordao.updateProfesor(pro);
            
                out.print("<html><head></head><body "
                           + "onload=\"alert('Profesor actualizado de forma correcta');"
                           + " window.location='pages/indexAdmin.jsp'\"><body></html>");
            } catch (Exception e) {
            }
            

           
        
        
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
