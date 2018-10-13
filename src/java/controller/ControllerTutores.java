/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import dao.TutorDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.Tutor;

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

    public ControllerTutores() {
        super();
        tutordao = new TutorDAO();
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
        System.err.println("llego al post");
        Tutor tutor = new Tutor();
        String action = request.getParameter("action");
        
       
        
        
        
        if (action.equalsIgnoreCase("update")) {

//            try {
//                forward = edit;
//                Periodo pdo = periododao.obtenerPeriodoById(Integer.parseInt(request.getParameter("id")));
//
//                request.setAttribute("pdo", pdo);
//                RequestDispatcher view = request.getRequestDispatcher(forward);
//                view.forward(request, response);
//            } catch (NumberFormatException | SQLException e) {
//                System.out.println("Error en servlet: " + e);
//            }
        } else if (action.equalsIgnoreCase("delete")) {
//          int id = Integer.parseInt(request.getParameter("id"));
//            try {
//                periododao.;
//                 response.sendRedirect("pages/ListarGrupos.jsp");
//            } catch (SQLException ex) {
//                Logger.getLogger(ControllerPeriodo.class.getName()).log(Level.SEVERE, null, ex);
//            } 
        } else if (action.equalsIgnoreCase("add")) {
            
            System.err.println("llego al add");
            String tipo = request.getParameter("tipo");
            if(tipo.equalsIgnoreCase("grupal")){
                System.err.println("llego al grupal");
                int idGrupo = Integer.parseInt(request.getParameter("grupo"));
                String curp = request.getParameter("profesor");
                System.err.println("este es el curp "+curp);
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
                        tutordao.insertar(tutor);
                     }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerTutores.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 
                
                
                
                
            }else if(tipo=="individual"){
            
            }
            
            
            
            
     

//            try {
//                periododao.insertar(periodo);
//                response.sendRedirect("pages/ListarPeriodos.jsp");
//            } catch (SQLException ex) {
//                Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else if (action.equalsIgnoreCase("edit")) {

//            int id = Integer.parseInt(request.getParameter("id"));
//            String perio = request.getParameter("periodo");
//            String fechaI = request.getParameter("fechaI");
//            String fechaF = request.getParameter("fechaF");
//            
//            
//            java.util.Date fechaIni = new java.util.Date();
//            java.util.Date fechaFin = new java.util.Date();
//            
//            fechaIni= convertirStrToDate(fechaI);
//            fechaFin=convertirStrToDate(fechaF);
//            
//            
//            
//            java.sql.Date sqlFeI = new java.sql.Date(fechaIni.getTime());
//            java.sql.Date sqlFeF = new java.sql.Date(fechaFin.getTime());
//            
//            System.err.println("Estas son las fechas 1"+sqlFeI);
//            System.err.println("Estas son las fechas 2"+sqlFeF);
//            
//            
//            periodo.setIdPeriodo(id);
//            periodo.setPeriodo(perio);
//            periodo.setFechaInicio( sqlFeI);
//            periodo.setFechaFin(sqlFeF);
//            
//            periododao.updatePeriodo(periodo);
//            response.sendRedirect("pages/ListarPeriodos.jsp");

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
