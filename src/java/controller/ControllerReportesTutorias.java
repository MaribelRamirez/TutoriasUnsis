/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import dao.LicenciaturaDAO;
import dao.ProfesorDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.Licenciatura;
import model.Profesor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerReportesTutorias", urlPatterns = {"/ControllerReportesTutorias"})
public class ControllerReportesTutorias extends HttpServlet {

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
        try {
            processRequest(request, response);
            Sheet sheet = null;

            LicenciaturaDAO obj_Read_Values_lic = new LicenciaturaDAO();
            List<Licenciatura> list_lic = obj_Read_Values_lic.listarLicenciaturas();
            Iterator<Licenciatura> it_list_lic = list_lic.iterator();

            Workbook book = new XSSFWorkbook();
            sheet = book.createSheet("ENTREGA DE REPORTE");
            //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
            sheet.getPrintSetup().setLandscape(false);
            //indicando el tamaño de la hoja
            sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

            
            //poner negrita a la cabecera
		CellStyle style = book.createCellStyle();
        Font font = book.createFont();
        font.setBold(true);
        style.setFont(font);
        
            int j = 0;
            int cont = 1;

            
            Row row1 = sheet.createRow(j);
            
            Cell cell= row1.createCell(0);//se crea las celdas para la cabecera, junto con la posición
	   cell.setCellStyle(style); // se añade el style crea anteriormente 
	cell.setCellValue("N/P");//se añade el contenido
        
            row1.createCell(1).setCellValue("PROFESOR");
            row1.createCell(2).setCellValue("LIC");
            row1.createCell(3).setCellValue("ENTREGÓ REPORTE");
            row1.createCell(4).setCellValue("ENTREGADO A TIEMPO");
            row1.createCell(5).setCellValue("FECHA DE ENTREGA");
            row1.createCell(6).setCellValue("FALTANTES");
            row1.createCell(7).setCellValue("TIPO DE TUTORIA");
            row1.createCell(8).setCellValue("N°. SESIONES");
            row1.createCell(9).setCellValue("N°. CANALIZACIONES");
            row1.createCell(10).setCellValue("ALUMNOS ASIGNADOS");
            row1.createCell(11).setCellValue("ALUMNOS REPORTADOS");
            row1.createCell(12).setCellValue("ALUMNOS CON ASISTENCIA");
            row1.createCell(13).setCellValue("OBSERVACIONES");
            j++;
            while (it_list_lic.hasNext()) {

                Licenciatura ob_lic = new Licenciatura();
                ob_lic = it_list_lic.next();

                ProfesorDAO obj_prof_read = new ProfesorDAO();
                List<Profesor> list_prof = obj_prof_read.obtenerProfesorByCarrera(ob_lic.getNombre());
                Iterator<Profesor> it_prof = list_prof.iterator();

                while (it_prof.hasNext()) {

                    Profesor obj_prof = new Profesor();
                    obj_prof = it_prof.next();

                    Row row2 = sheet.createRow(j);

                    row2.createCell(0).setCellValue("" + cont);
                    row2.createCell(1).setCellValue("" + obj_prof.getNombre());
                    row2.createCell(2).setCellValue("" + obj_prof.getLicenciatura());

                    AlumnoDAO obj_alum_read = new AlumnoDAO();
                    List<Alumno> list_alum = obj_alum_read.listarAlumnosTutorados(obj_prof.getCurp());

                    row2.createCell(10).setCellValue("" + list_alum.size());
                    j++;
                    cont++;

                }

            }

            try {
                try (FileOutputStream elFichero
                        = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Registro de Reportes de Tutorías" + ".xlsx")) {
                    for (int k = 0; k < j; k++) {
                        sheet.autoSizeColumn((short) k);

                    }

                    book.write(elFichero);

                    elFichero.close();

                }
            } catch (IOException e) {
            }
            response.sendRedirect("pages/generarReportes.jsp");

        } catch (SQLException ex) {
            Logger.getLogger(ControllerReportesTutorias.class.getName()).log(Level.SEVERE, null, ex);
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
