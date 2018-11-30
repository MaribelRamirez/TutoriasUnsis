/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LicenciaturaDAO;
import dao.ProfesorDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Licenciatura;
import model.Profesor;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerConcentradoAsignaciones", urlPatterns = {"/ControllerConcentradoAsignaciones"})
public class ControllerConcentradoAsignaciones extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
Sheet sheet= null;
            Sheet sheet2 =null;
        try {

            
            Workbook book = new XSSFWorkbook();
            // sheet = book.createSheet("");
            sheet = book.createSheet("ConcentradoAsignaciones");
            //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
            sheet.getPrintSetup().setLandscape(false);
            //indicando el tamaño de la hoja
            sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

            //poner negrita a la cabecera
            CellStyle style = book.createCellStyle();
            CellStyle style2 = book.createCellStyle();

            Font font = book.createFont();
            font.setBold(true);

            style.setFont(font);

            style.setBorderBottom(BorderStyle.MEDIUM);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.MEDIUM);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(BorderStyle.MEDIUM);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.MEDIUM);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            style2.setBorderBottom(BorderStyle.MEDIUM);
            style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style2.setBorderLeft(BorderStyle.MEDIUM);
            style2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style2.setBorderRight(BorderStyle.MEDIUM);
            style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style2.setBorderTop(BorderStyle.MEDIUM);
            style2.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style2.setAlignment(HorizontalAlignment.CENTER);

            /// style3.setAlignment(HorizontalAlignment.CENTER);
            int j = 0;

            Row rowT1 = sheet.createRow(j);
            j++;
            Row rowT2 = sheet.createRow(j);
            Cell cellT2 = rowT2.createCell(0);
            cellT2.setCellValue("ESTATUS");
            cellT2.setCellStyle(style);

            cellT2 = rowT2.createCell(1);
            cellT2.setCellValue("NOMBRE");
            cellT2.setCellStyle(style);

            cellT2 = rowT2.createCell(2);
            cellT2.setCellValue("GRUPooooooooooooO");
            cellT2.setCellStyle(style);

            cellT2 = rowT2.createCell(3);
            cellT2.setCellValue("LICENCIATURA");
            cellT2.setCellStyle(style);

            j++;

            Row rowT3 = sheet.createRow(j);
            Cell cellT3 = rowT3.createCell(0);
            cellT3.setCellValue("");
            cellT3.setCellStyle(style);

            cellT3 = rowT3.createCell(1);
            cellT3.setCellValue("");
            cellT3.setCellStyle(style);

            cellT3 = rowT3.createCell(2);
            cellT3.setCellValue("");
            cellT3.setCellStyle(style);

            ///Contar cuantas carreras existen registradas
            int carreras = 0;

            LicenciaturaDAO obj_lic = new LicenciaturaDAO();
            carreras = obj_lic.countLicenciaturas();

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
            carreras = carreras + 2;

            Cell cellT1 = rowT1.createCell(0);
            cellT1.setCellValue("CONCENTRADO DE ASIGNACIONES");
            cellT1.setCellStyle(style2);

            LicenciaturaDAO obj_Read_Values = new LicenciaturaDAO();
            List<Licenciatura> list = obj_Read_Values.listarLicenciaturas();
            Iterator<Licenciatura> it_list = list.iterator();

            int l = 3;

            while (l < carreras) {
                l++;
                cellT2 = rowT2.createCell(l);
                cellT2.setCellValue("");
                cellT2.setCellStyle(style);
            }
            
            l = 3;

            while (it_list.hasNext()) {

                Licenciatura ob = new Licenciatura();
                ob = it_list.next();

                cellT3 = rowT3.createCell(l);
                cellT3.setCellValue(ob.getNombre());
                cellT3.setCellStyle(style);
                l++;

            }

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, carreras));

            carreras = carreras + 1;
            
            cellT2 = rowT2.createCell(l);
            cellT2.setCellValue(" TUTORADOooooooooooooooS");
            cellT2.setCellStyle(style);
            
            cellT3 = rowT3.createCell(l);
            cellT3.setCellValue("");
            cellT3.setCellStyle(style);
            
            
            
           
            
             l++;
             j++;
           
                     sheet.addMergedRegion(new CellRangeAddress(1, 2, carreras, carreras));
                     
                     sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, carreras));

            
         //   while (it_list.hasNext()) {

               // Licenciatura ob = new Licenciatura();
              //  ob = it_list.next();
              //  sheet2 = book.createSheet("Cokgvfh");
              //  sheet = book.createSheet(ob.getNombre());
               // System.out.println("nombre de la carrera para hoja"+ob.getNombre());
               // ProfesorDAO obj_Read = new ProfesorDAO();
                //List<Profesor> list_tutor = obj_Read.obtenerProfesorTutor(ob.getIdLicenciatura());
                //Iterator<Profesor> it_list_tutor = list_tutor.iterator();

                /*while (it_list_tutor.hasNext()) {
                    l = 0;
                    Profesor Obt = new Profesor();
                    Obt = it_list_tutor.next();

                    Row rowT4 = sheet.createRow(j);
                    Cell cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue(Obt.getEstatus());
                    cellT4.setCellStyle(style);

                    l++;

                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue(Obt.getNombre());
                    cellT4.setCellStyle(style);

                    j++;*/
                    //if(Obt.getTipoTutoria()==1){

                    //  }
                    //  else{
                    // }
                //}

          //  }

           for (int k = 0; k < carreras+1; k++) {
                    sheet.autoSizeColumn((short) k);
System.out.println("valor de k"+k);
                }

            try {
                try (FileOutputStream elFichero
                        = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Concentrado_Asignaciones" + ".xlsx")) {

                    book.write(elFichero);

                    elFichero.close();

                }
            } catch (IOException e) {
            }
            out.print("<html><head></head><body "
                    + "onload=\"alert('Reportes generados con exito');"
                    + " window.location='pages/generarReportes.jsp'\"><body></html>");
        } catch (Exception e) {
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
