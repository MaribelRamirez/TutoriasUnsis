/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProgramaDAO;
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
import model.Programa;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerPrograma", urlPatterns = {"/ControllerPrograma"})
public class ControllerPrograma extends HttpServlet {

    private static final String edit = "pages/actualizarPrograma.jsp";
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
    public ProgramaDAO programadao;

    public ControllerPrograma() {
        super();
        programadao = new ProgramaDAO();

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

        Programa Prg = new Programa();
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("update")) {
            try {
                forward = edit;
                Programa prg = programadao.obtenerProgramaById(Integer.parseInt(request.getParameter("id")));

                request.setAttribute("prg", prg);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }
        } else if (action.equalsIgnoreCase("delete")) {

            int id = Integer.parseInt(request.getParameter("id"));

            try {
                programadao.eliminar(id);
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
                        + "text: \"Programa eliminado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarProgramas.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerPrograma.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("add")) {

            String nomPrg = request.getParameter("nombrePrg");
            Prg.setNombre(nomPrg);
            if (programadao.verificar(nomPrg) == 0) {
                try {
                    if (programadao.insertar(Prg) == true) {

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
                                + "text: \"Programa agregado de forma correcta...\",\n"
                                + "type: \"success\",    \n"
                                + "confirmButtonColor: \"#DD6B55\",\n"
                                + "confirmButtonText: \"Aceptar\",\n"
                                + "closeOnConfirm: false,\n"
                                + "},\n"
                                + "\n"
                                + "function(isConfirm){\n"
                                + "if (isConfirm) {\n"
                                + "window.location='pages/ListarProgramas.jsp'   \n"
                                + "} \n"
                                + "});\n"
                                + "}\n"
                                + "EventoAlert();\n"
                                + "</script>"
                                + "</body>\n"
                                + "</html>");
                    } else {
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
                                + "text: \"Error al guardar el programa\",\n"
                                + "type: \"warning\",    \n"
                                + "confirmButtonColor: \"#DD6B55\",\n"
                                + "confirmButtonText: \"Aceptar\",\n"
                                + "closeOnConfirm: false,\n"
                                + "},\n"
                                + "\n"
                                + "function(isConfirm){\n"
                                + "if (isConfirm) {\n"
                                + "window.location='pages/ListarProgramas.jsp'   \n"
                                + "} \n"
                                + "});\n"
                                + "}\n"
                                + "EventoAlert();\n"
                                + "</script>"
                                + "</body>\n"
                                + "</html>");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerPrograma.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
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
                        + "text: \"Error al guardar el programa, verifica tus datos, es posible que el programa ya exista\",\n"
                        + "type: \"warning\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarProgramas.jsp'   \n"
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
                String nomPrg = request.getParameter("nombrePrg");
                Prg.setIdPrograma(id);
                Prg.setNombre(nomPrg);

                if (programadao.updatePrg(Prg) == true) {
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
                            + "text: \"Programas actualizado de forma correcta...\",\n"
                            + "type: \"success\",    \n"
                            + "confirmButtonColor: \"#DD6B55\",\n"
                            + "confirmButtonText: \"Aceptar\",\n"
                            + "closeOnConfirm: false,\n"
                            + "},\n"
                            + "\n"
                            + "function(isConfirm){\n"
                            + "if (isConfirm) {\n"
                            + "window.location='pages/ListarProgramas.jsp'   \n"
                            + "} \n"
                            + "});\n"
                            + "}\n"
                            + "EventoAlert();\n"
                            + "</script>"
                            + "</body>\n"
                            + "</html>");
                } else {
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
                            + "text: \"Error al actualizar el programa\",\n"
                            + "type: \"warning\",    \n"
                            + "confirmButtonColor: \"#DD6B55\",\n"
                            + "confirmButtonText: \"Aceptar\",\n"
                            + "closeOnConfirm: false,\n"
                            + "},\n"
                            + "\n"
                            + "function(isConfirm){\n"
                            + "if (isConfirm) {\n"
                            + "window.location='pages/ListarProgramas.jsp'   \n"
                            + "} \n"
                            + "});\n"
                            + "}\n"
                            + "EventoAlert();\n"
                            + "</script>"
                            + "</body>\n"
                            + "</html>");
                }

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
