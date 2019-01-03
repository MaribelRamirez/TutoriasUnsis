 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GrupoDAO;
import dao.PeriodoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import model.Periodo;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerPeriodo", urlPatterns = {"/ControllerPeriodo"})
public class ControllerPeriodo extends HttpServlet {

    private static final String edit = "pages/actualizarPeriodo.jsp";
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
    public PeriodoDAO periododao;

    public ControllerPeriodo() {
        super();
        periododao = new PeriodoDAO();
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
        
        Periodo periodo = new Periodo();
        String action = request.getParameter("action");
        
       
        
        
        
        if (action.equalsIgnoreCase("update")) {

            try {
                forward = edit;
                Periodo pdo = periododao.obtenerPeriodoById(Integer.parseInt(request.getParameter("id")));

                request.setAttribute("pdo", pdo);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }
        } else if (action.equalsIgnoreCase("delete")) {
//          int id = Integer.parseInt(request.getParameter("id"));
//            try {
//                periododao.;
//                 response.sendRedirect("pages/ListarGrupos.jsp");
//            } catch (SQLException ex) {
//                Logger.getLogger(ControllerPeriodo.class.getName()).log(Level.SEVERE, null, ex);
//            } 
        } else if (action.equalsIgnoreCase("add")) {
            String perio = request.getParameter("periodo");
            periodo.setPeriodo(perio);
            
            java.util.Date fechaIni = new java.util.Date();
            java.util.Date fechaFin = new java.util.Date();
            
            
            String fechaI = request.getParameter("fechaI");
            String fechaF = request.getParameter("fechaF");
            
            fechaIni= convertirStrToDate(fechaI);
            fechaFin=convertirStrToDate(fechaF);
            
            
            
            java.sql.Date sqlFeI = new java.sql.Date(fechaIni.getTime());
            java.sql.Date sqlFeF = new java.sql.Date(fechaFin.getTime());
            
            
            periodo.setFechaInicio(sqlFeI);
            periodo.setFechaFin(sqlFeF);

            try {
                periododao.insertar(periodo);
                   out.print("<html>"
                        + "<head>"
                        + "<script src=\"resources/alert/sweetalert.min.js\"></script>\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/alert/sweetalert.css\">\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/alert/google.css\">"
                        + "</head>"
                        + "<body >"
                        + "<script>\n"
                        + "function EventoAlert(){\n"
                        + "  swal({\n"
                        + "title: \"Aviso!!\",\n"
                        + "text: \"Periodo agregado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarPeriodos.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
               
               
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("edit")) {

            try {
                int id = Integer.parseInt(request.getParameter("id"));
            String perio = request.getParameter("periodo");
            String fechaI = request.getParameter("fechaI");
            String fechaF = request.getParameter("fechaF");
            
            
            java.util.Date fechaIni = new java.util.Date();
            java.util.Date fechaFin = new java.util.Date();
            
            fechaIni= convertirStrToDate(fechaI);
            fechaFin=convertirStrToDate(fechaF);
            
            
            
            java.sql.Date sqlFeI = new java.sql.Date(fechaIni.getTime());
            java.sql.Date sqlFeF = new java.sql.Date(fechaFin.getTime());
            
            System.err.println("Estas son las fechas 1"+sqlFeI);
            System.err.println("Estas son las fechas 2"+sqlFeF);
            
            
            periodo.setIdPeriodo(id);
            periodo.setPeriodo(perio);
            periodo.setFechaInicio( sqlFeI);
            periodo.setFechaFin(sqlFeF);
            
            periododao.updatePeriodo(periodo);
             out.print("<html>"
                        + "<head>"
                        + "<script src=\"resources/alert/sweetalert.min.js\"></script>\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/alert/sweetalert.css\">\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/alert/google.css\">"
                        + "</head>"
                        + "<body >"
                        + "<script>\n"
                        + "function EventoAlert(){\n"
                        + "  swal({\n"
                        + "title: \"Aviso!!\",\n"
                        + "text: \"Periodo actualizado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarPeriodos.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
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
    
    
    Date convertirStrToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date = null;
        try {

            date =  (Date) formatoDelTexto.parse(fecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }

        System.out.println(date.toString());

        
        return date;
    }

}
