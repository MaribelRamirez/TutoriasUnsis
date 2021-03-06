/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import dao.GrupoDAO;
import dao.TutorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.Grupo;
import model.Tutor;
import model.sql;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerTutores", urlPatterns = {"/ControllerTutores"})
public class ControllerTutores extends HttpServlet {

    private static final String edit1 = "pages/agregarTutoresG.jsp";
    private static final String edit2 = "pages/agregarTutoresI.jsp";
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
    public TutorDAO tutordao;
    public GrupoDAO grupodao;
    public AlumnoDAO alumnodao;

    public ControllerTutores() {
        super();
        tutordao = new TutorDAO();
        grupodao = new GrupoDAO();
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Tutor tutor = new Tutor();
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("addTG")) {

            try {
                forward = edit1;
                Grupo grp = grupodao.obtenerGrupoById(Integer.parseInt(request.getParameter("id")));

                request.setAttribute("grp", grp);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }
        } else if (action.equalsIgnoreCase("addTI")) {

            try {
                forward = edit2;
                String ruta = request.getParameter("ruta");
                Alumno alm = alumnodao.obtenerAlumnoByMatricula(request.getParameter("id"));
                request.setAttribute("alm", alm);
                request.setAttribute("ruta", ruta);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }

        } else if (action.equalsIgnoreCase("add")) {

            String tipo = request.getParameter("tipo");
            String grupo = request.getParameter("grupoN");
            if (tipo.equalsIgnoreCase("grupal")) {

                int idGrupo = Integer.parseInt(request.getParameter("grupo"));
                String curp = request.getParameter("profesor");
                int idPeriodo = Integer.parseInt(request.getParameter("per"));
                AlumnoDAO alumnoDao = new AlumnoDAO();
                List<Alumno> listaAlumnos;
                try {
                    listaAlumnos = alumnoDao.listarAlumnosByGrupo(idGrupo);
                    Iterator<Alumno> it_list = listaAlumnos.iterator();
                    while (it_list.hasNext()) {
                        Alumno ob = new Alumno();
                        ob = it_list.next();
                        tutor.setMatricula(ob.getMatricula());
                        tutor.setCurp(curp);
                        tutor.setPeriodo(idPeriodo);
                        tutor.setTipo(2);
                        tutor.setGrupo(grupo);
                        if (0 != tutordao.comprobarRegistro(idPeriodo, ob.getMatricula())) {
                            tutordao.update(tutor);
                        } else {
                            tutordao.insertar(tutor);
                        }

                    }
                    response.sendRedirect("pages/ListarGrupos.jsp");

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerTutores.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if ("individual".equals(tipo)) {
                
                String ruta = request.getParameter("ruta");
                String matricula = request.getParameter("matricula");
                String curp = request.getParameter("profesor");
                sql auto = new sql();
                int idPeriodo = auto.auto_increm("SELECT MAX(idPeriodo) FROM tutoriasunsis.periodo") - 1;

                tutor.setMatricula(matricula);
                tutor.setCurp(curp);
                tutor.setPeriodo(idPeriodo);
                tutor.setTipo(1);
                tutor.setGrupo(grupo);
                try {
                    if (0 != tutordao.comprobarRegistro(idPeriodo, matricula)) {
                        tutordao.update(tutor);
                    } else {
                        tutordao.insertar(tutor);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerTutores.class.getName()).log(Level.SEVERE, null, ex);
                }
                if("listTu".equals(ruta)){
                    response.sendRedirect("pages/ListarTutorados.jsp");
                }else{
                    response.sendRedirect("pages/ListarAlumnos.jsp");
                }
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
