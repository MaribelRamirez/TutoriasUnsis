/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerArchivos", urlPatterns = {"/ControllerArchivos"})

public class ControllerArchivos extends HttpServlet {

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

        String action = request.getParameter("action");
        String nombreArchivo = request.getParameter("nombre");

       if (action.equalsIgnoreCase("eliminar")) {

            try {
                String rutRel = getServletConfig().getServletContext().getRealPath("/resources/Documentos");

                File archivo = new File(rutRel + "/" + nombreArchivo);
                archivo.delete();
            } catch (Exception e) {
                System.err.println("Error" + e);
            }

        }else  if (action.equalsIgnoreCase("eliminarTodo")) 
       
       {
           String rutRel=getServletConfig().getServletContext().getRealPath("/resources/Documentos");
                    String path = rutRel+"/";
                    File directorio = new File(path);
                    String[] ficheros = directorio.list();
                    for (int i = 0; i < ficheros.length; i++) {
                    File archivo = new File(rutRel + "/" + ficheros[i]);
                          archivo.delete();
                    }
       
       }
        response.sendRedirect("pages/generarReportes.jsp");
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
