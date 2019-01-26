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
        } else if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                grupodao.eliminar(id);
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
                        + "text: \"Grupo eliminado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarGrupos.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
               
            } catch (SQLException ex) {
                Logger.getLogger(ControllerGrupo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("add")) {
            String nomGrup = request.getParameter("grupo");
            grupo.setGrupo(nomGrup);
            int periodo = Integer.parseInt(request.getParameter("per"));
            grupo.setIdPeriodo(periodo);
            int lic = Integer.parseInt(request.getParameter("lic"));
            grupo.setIdLicenciatura(lic);
            if(grupodao.verificar(nomGrup)==0)
            {
            try {
                
             if (grupodao.insertar(grupo) == true) {
                
                 
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
                                + "text: \"Error al guardar el grupo \",\n"
                                + "type: \"warning\",    \n"
                                + "confirmButtonColor: \"#DD6B55\",\n"
                                + "confirmButtonText: \"Aceptar\",\n"
                                + "closeOnConfirm: false,\n"
                                + "},\n"
                                + "\n"
                                + "function(isConfirm){\n"
                                + "if (isConfirm) {\n"
                                + "window.location='pages/ListarGrupos.jsp'   \n"
                                + "} \n"
                                + "});\n"
                                + "}\n"
                                + "EventoAlert();\n"
                                + "</script>"
                                + "</body>\n"
                                + "</html>");
             }else
                    {
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
                        + "text: \"Grupo agregado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarGrupos.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
                    }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLicenciatura.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
            
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
                        + "text: \"Error al guardar el grupo, verifica tus datos, es posible que el grupo ya exista\",\n"
                        + "type: \"warning\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarGrupos.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
            
            }
        } else if (action.equalsIgnoreCase("edit")) {
            try {
                   int id = Integer.parseInt(request.getParameter("id"));
            String grup = request.getParameter("grupo");
            int periodo = Integer.parseInt(request.getParameter("per"));
            int lic = Integer.parseInt(request.getParameter("lic"));
            grupo.setIdGrupo(id);
            grupo.setGrupo(grup);
            grupo.setIdLicenciatura(lic);
            grupo.setIdPeriodo(periodo);
            grupodao.updateGrp(grupo);
            
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
                        + "text: \"Grupo actualizado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarGrupos.jsp'   \n"
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

}
