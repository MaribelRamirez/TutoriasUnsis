/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import dao.PeriodoDAO;
import dao.ProfesorDAO;
import java.io.File;
import java.io.FileOutputStream;
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
import model.Periodo;
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
@WebServlet(name = "ControllerInformeAsignaciones", urlPatterns = {"/ControllerInformeAsignaciones"})

public class ControllerInformeAsignaciones extends HttpServlet {

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
        Sheet sheet = null;

        int i = 1, j = 0;
        Workbook book = new XSSFWorkbook();
        sheet = book.createSheet("Informe de Asignaciones");
        //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
        sheet.getPrintSetup().setLandscape(false);
        //indicando el tamaño de la hoja
        sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
        Font font = book.createFont();
        font.setBold(true);

        CellStyle styleHead = book.createCellStyle();
        styleHead.setBorderBottom(BorderStyle.MEDIUM);
        styleHead.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleHead.setBorderLeft(BorderStyle.MEDIUM);
        styleHead.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleHead.setBorderRight(BorderStyle.MEDIUM);
        styleHead.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleHead.setBorderTop(BorderStyle.MEDIUM);
        styleHead.setTopBorderColor(IndexedColors.BLACK.getIndex());
        styleHead.setAlignment(HorizontalAlignment.CENTER);
        styleHead.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        styleHead.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleHead.setFont(font);

        CellStyle styleColumn = book.createCellStyle();
        styleColumn.setBorderBottom(BorderStyle.MEDIUM);
        styleColumn.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleColumn.setBorderLeft(BorderStyle.MEDIUM);
        styleColumn.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleColumn.setBorderRight(BorderStyle.MEDIUM);
        styleColumn.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleColumn.setBorderTop(BorderStyle.MEDIUM);
        styleColumn.setTopBorderColor(IndexedColors.BLACK.getIndex());
        styleColumn.setAlignment(HorizontalAlignment.CENTER);

        CellStyle styleTitle = book.createCellStyle();
        styleTitle.setAlignment(HorizontalAlignment.CENTER);
        styleTitle.setFont(font);

        //creando es estilo de los encabezados de la tabla
        Row titulo1 = sheet.createRow(j);

        Cell cell = titulo1.createCell(0);
        cell.setCellStyle(styleTitle);

        cell.setCellValue("INFORME TUTORES");

        j++;
        Row titulo2 = sheet.createRow(j);

        cell = titulo2.createCell(0);
        cell.setCellStyle(styleTitle);
        cell.setCellValue("CONCENTRADO DE TUTORES CON NÚMERO DE TUTORADOS");

        j++;
        Row titulo3 = sheet.createRow(j);

        cell = titulo3.createCell(0);
        cell.setCellStyle(styleTitle);
        cell.setCellValue("SOCIALES");
        sheet.addMergedRegion(new CellRangeAddress(j, j, 0, 3));
        j++;

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
        Row titulo4 = sheet.createRow(j);

        ////sheet.addMergedRegion(new CellRangeAddress(j, j, 0, 2));
        Row rowGeneral = sheet.createRow(j);
        //poner encabezados de la tabla
        cell = rowGeneral.createCell(0);

        cell.setCellStyle(styleHead);
        cell.setCellValue("N°");

        cell = rowGeneral.createCell(1);

        cell.setCellStyle(styleHead);
        cell.setCellValue("ESTATUS");

        cell = rowGeneral.createCell(2);

        cell.setCellStyle(styleHead);
        cell.setCellValue("PROFESOR");

        cell = rowGeneral.createCell(3);

        cell.setCellStyle(styleHead);
        cell.setCellValue("LIC");
        j = 3;
        int j2 = j;
        int sizeList = Integer.parseInt(request.getParameter("sizeList"));

        response.setContentType("application/pdf");

        Document documento = null;
        File destino = null;
        int cont = 1;

        if (sizeList >= 1) {
            for (i = 1; i <= sizeList; i++) {

                String pr = request.getParameter("pr".concat(Integer.toString(i)));
                int pr2;
                String periodoAct;

                if (pr != null) {
                    try {
                        pr2 = Integer.parseInt(pr);//ID de periodos seleccionados
                        PeriodoDAO ppr = new PeriodoDAO();
                        Periodo pdo;
                        pdo = ppr.obtenerPeriodoById(pr2);

                        ProfesorDAO obprof = new ProfesorDAO();

                        List<Profesor> list = null;
                        list = obprof.listarProfesoresSociales();
                        Iterator<Profesor> it_list = list.iterator();

                        cell = rowGeneral.createCell(j + 1);
                        // sheet.addMergedRegion(new CellRangeAddress(j, j, 4, 5));
                        j++;
                        cell.setCellStyle(styleHead);
                        cell.setCellValue(pdo.getPeriodo());

//j2++;
                        while (it_list.hasNext()) {
                            System.out.println(cont);
                            Profesor ob = new Profesor();
                            ob = it_list.next();

                            Profesor prf;
                            int numero = obprof.countTutoradosByPeriodo(pr2, ob.getCurp());

                            rowGeneral = sheet.createRow(j2 + 1);
                            //poner encabezados de la tabla
                            cell = rowGeneral.createCell(0);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(cont);

                            cell = rowGeneral.createCell(1);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(ob.getEstatus());

                            cell = rowGeneral.createCell(2);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(ob.getGrado() + " " + ob.getNombre());

                            cell = rowGeneral.createCell(3);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(ob.getPrograma());

                            cell = rowGeneral.createCell(4);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(numero);
                            j2++;
                            cont++;
                        }
                        Row titulo5 = sheet.createRow(j2);

                        cell = titulo5.createCell(0);
                        cell.setCellStyle(styleTitle);
                        cell.setCellValue("SALUD");
                        sheet.addMergedRegion(new CellRangeAddress(j2, j2, 0, 3));
                        j2++;

                        list = null;
                        list = obprof.listarProfesoresSalud();
                        it_list = list.iterator();

                        while (it_list.hasNext()) {
                            System.out.println(cont);

                            Profesor ob = new Profesor();
                            ob = it_list.next();

                            Profesor prf;
                            int numero = obprof.countTutoradosByPeriodo(pr2, ob.getCurp());

                            rowGeneral = sheet.createRow(j2);
                            //poner encabezados de la tabla
                            cell = rowGeneral.createCell(0);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(cont);

                            cell = rowGeneral.createCell(1);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(ob.getEstatus());

                            cell = rowGeneral.createCell(2);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(ob.getGrado() + " " + ob.getNombre());

                            cell = rowGeneral.createCell(3);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(ob.getPrograma());

                            cell = rowGeneral.createCell(4);

                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(numero);
                            j2++;
                            cont++;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerInformeAsignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }

        for (int k = 0; k < j; k++) {
            sheet.autoSizeColumn((short) k);

        }
        try {
            String rutRel = getServletConfig().getServletContext().getRealPath("/resources/Documentos");
            try (FileOutputStream elFichero
                    = new FileOutputStream(rutRel + "/Informe_Asignaciones " + ".xlsx")) {
                book.write(elFichero);
                elFichero.close();
                response.sendRedirect("pages/generarReportes.jsp");
            }

        } catch (IOException e) {
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
