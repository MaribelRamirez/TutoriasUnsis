/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.AlumnoDAO;
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
import model.Alumno;

/**
 *
 * @author Marifer
 */         
@WebServlet(name = "ControllerAlumno", urlPatterns = {"/ControllerAlumno"})
public class ControllerAlumno extends HttpServlet {

    private static final String edit = "pages/actualizarAlumno.jsp";
    String forward = "";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public AlumnoDAO alumnodao;

    public ControllerAlumno() {
        super();
        alumnodao = new AlumnoDAO();
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
        PrintWriter out = response.getWriter();

        Alumno alum = new Alumno();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("update")) {

            try {
                forward = edit;
                Alumno alm = alumnodao.obtenerAlumnoByMatricula(request.getParameter("id"));
                request.setAttribute("alm", alm);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }
        } else if (action.equalsIgnoreCase("delete")) {
            String matricula = request.getParameter("id");
            try {
                alumnodao.eliminar(matricula);

                out.print("<html><head></head><body "
                        + "onload=\"alert('Alumno eliminado de forma correcta');"
                        + " window.location='pages/ListarAlumnos.jsp'\"><body></html>");

//response.sendRedirect("ListarAlumnos.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (action.equalsIgnoreCase("add")) {
            String matricula = request.getParameter("matricula");
            System.out.println("esta es la matricula" + matricula);
            String nombre = request.getParameter("nombre");
            int grupo = Integer.parseInt(request.getParameter("grupo"));
            int lic = Integer.parseInt(request.getParameter("lic"));
            alum.setMatricula(matricula);
            alum.setNombre(nombre);
            alum.setIdGrupo(grupo);
            alum.setIdLicenciatura(lic);

            try {
                alumnodao.insertar(alum);
               
                out.print("<html><head></head><body "
                        + "onload=\"alert('Alumno agregado de forma correcta');"
                     + " window.location='pages/ListarAlumnos.jsp'\"><body></html>");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("edit")) {

            try {
                String matricula = request.getParameter("matricula");
                System.out.println("esta es la matricula que llega a edit" + matricula);
                String nombreGrp = request.getParameter("nombreAlum");
                int grupo = Integer.parseInt(request.getParameter("grupo"));
                int lic = Integer.parseInt(request.getParameter("lic"));
                alum.setMatricula(matricula);
                alum.setNombre(nombreGrp);
                alum.setIdGrupo(grupo);
                alum.setIdLicenciatura(lic);
                alumnodao.updateAlumno(alum);

                out.print("<html><head></head><body "
                        + "onload=\"alert('Alumno actualizado de forma correcta');"
                        + " window.location='pages/ListarAlumnos.jsp'\"><body></html>");
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
