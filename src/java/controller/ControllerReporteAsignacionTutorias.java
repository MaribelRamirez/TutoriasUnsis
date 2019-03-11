/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import dao.GrupoDAO;
import dao.LicenciaturaDAO;
import dao.PeriodoDAO;
import dao.ProfesorDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.Grupo;
import model.Licenciatura;
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
@WebServlet(name = "ControllerAsignacionTutorias", urlPatterns = {"/ControllerAsignacionTutorias"})

public class ControllerReporteAsignacionTutorias extends HttpServlet {

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

        try {
            int idPeriodo = Integer.parseInt(request.getParameter("IdPeriodo"));
            //creando el libro de excel
            PeriodoDAO per = new PeriodoDAO();
            Periodo pdo;
            pdo = per.obtenerPeriodoById(idPeriodo);

            LicenciaturaDAO obj_Read_Values_lic = new LicenciaturaDAO();
            List<Licenciatura> list_lic = obj_Read_Values_lic.listarLicenciaturas();
            Iterator<Licenciatura> it_list_lic = list_lic.iterator();
            Sheet sheetx = null;
            Sheet sheet = null;
            Row rowGeneral = null;
            Row rowGeneral1 = null;
            int cont = 0;
            int cont2 = 0;
            int i = 0;
            int j = 0;

            while (it_list_lic.hasNext()) {

                j = 0;
                Workbook book = new XSSFWorkbook();
                Licenciatura ob_lic = new Licenciatura();
                ob_lic = it_list_lic.next();
                //creando y asignando nombre a la hoja de excel
                sheet = book.createSheet("" + ob_lic.getNombre());
                cont2 = 0;
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

                ProfesorDAO obj_Read_Values = new ProfesorDAO();
                List<Profesor> tutor = obj_Read_Values.listarProfesoresActivos();
                Iterator<Profesor> it_tutor = tutor.iterator();

                GrupoDAO obj_grupos = new GrupoDAO();

                //creando es estilo de los encabezados de la tabla
                Row titulo1 = sheet.createRow(j);

                Cell cell = titulo1.createCell(0);
                cell.setCellStyle(styleTitle);

                cell.setCellValue("REGISTRO DE REPORTES DE TUTORÍAS SEMESTRE " + pdo.getPeriodo());

                j++;
                Row titulo2 = sheet.createRow(j);

                cell = titulo2.createCell(0);
                cell.setCellStyle(styleTitle);
                cell.setCellValue("UNIVERSIDAD DE LA SIERRA SUR");
                j++;

                Row titulo3 = sheet.createRow(j);

                cell = titulo3.createCell(0);
                cell.setCellValue("");
                j++;

                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
                Row titulo4 = sheet.createRow(j);

                sheet.addMergedRegion(new CellRangeAddress(j, j, 0, 2));

                cell = titulo4.createCell(0);
                cell.setCellValue("TUTORIAS GRUPALES");
                cell.setCellStyle(styleColumn);
                cell = titulo4.createCell(1);
                cell.setCellStyle(styleColumn);
                cell = titulo4.createCell(2);
                cell.setCellStyle(styleColumn);

                j++;

                List<Profesor> listG = obj_Read_Values.tutorGrupal(idPeriodo);
                Iterator<Profesor> it_listG = listG.iterator();

                List<Profesor> listI = obj_Read_Values.tutorIndividual(idPeriodo);
                Iterator<Profesor> it_listI = listI.iterator();

                rowGeneral = sheet.createRow(j);
                //poner encabezados de la tabla
                cell = rowGeneral.createCell(0);

                cell.setCellStyle(styleHead);
                cell.setCellValue("N°");

                cell = rowGeneral.createCell(1);

                cell.setCellStyle(styleHead);
                cell.setCellValue("GRUPO");

                cell = rowGeneral.createCell(2);

                cell.setCellStyle(styleHead);
                cell.setCellValue("TUTOR");
                j++;

                while (it_listG.hasNext()) {

                    i = 0;
                    Profesor obG = new Profesor();
                    obG = it_listG.next();

                    AlumnoDAO objALum = new AlumnoDAO();
                    List<Alumno> list2 = objALum.listarAlumnosTutoradosByCarrera(obG.getCurp(), ob_lic.getNombre(), idPeriodo);
                    Iterator<Alumno> it_list2 = list2.iterator();

                    List<Grupo> list_grupos = obj_grupos.listarGruposTutorados(obG.getCurp(), ob_lic.getNombre(), idPeriodo);
                    Iterator<Grupo> it_grupos = list_grupos.iterator();

                    if (list2.size() > 0) {

                        //creando y asignando nombre a la hoja de excel
                        sheetx = book.createSheet(obG.getGrado() + " " + obG.getNombre());
                        //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
                        sheetx.getPrintSetup().setLandscape(false);
                        //indicando el tamaño de la hoja
                        sheetx.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

                        Row row1 = sheetx.createRow(i);
                        cell = row1.createCell(0);
                        cell.setCellValue("");
                        i++;
                        row1 = sheetx.createRow(i);
                        //poner nombre del profesor

                        sheetx.addMergedRegion(new CellRangeAddress(i, i, 0, 4));

                        cell = row1.createCell(0);
                        cell.setCellValue(obG.getGrado() + " " + obG.getNombre());
                        cell.setCellStyle(styleColumn);

                        cell = row1.createCell(1);
                        cell.setCellStyle(styleColumn);

                        cell = row1.createCell(2);
                        cell.setCellStyle(styleColumn);

                        cell = row1.createCell(3);
                        cell.setCellStyle(styleColumn);
                        cell = row1.createCell(4);
                        cell.setCellStyle(styleColumn);

                        i++;
                        Row row2 = sheetx.createRow(i);
                        //poner encabezados de la tabla
                        cell = row2.createCell(0);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("N°");

                        cell = row2.createCell(1);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("MATRICULA");

                        cell = row2.createCell(2);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("NOMBRE");

                        cell = row2.createCell(3);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("LICENCIATURA");

                        cell = row2.createCell(4);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("GRUPO");

                        cont = 0;

                        while (it_list2.hasNext()) {
                            cont++;
                            i++;
                            Alumno obA = new Alumno();

                            obA = it_list2.next();
                            Row row3 = sheetx.createRow(i);
                            //poner datos
                            cell = row3.createCell(0);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue("" + cont);

                            cell = row3.createCell(1);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(obA.getMatricula());

                            cell = row3.createCell(2);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(obA.getNombre());

                            cell = row3.createCell(3);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(obA.getLicenciatura());

                            cell = row3.createCell(4);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(obA.getGrupo());

                        }
                        for (int k = 0; k < i; k++) {
                            sheetx.autoSizeColumn((short) k);

                        }
                        cont = 0;

                        while (it_grupos.hasNext()) {
                            cont2++;
                            Grupo ob_grupo = new Grupo();
                            ob_grupo = it_grupos.next();

                            rowGeneral1 = sheet.createRow(j);
                            ////********
                            cell = rowGeneral1.createCell(0);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue("" + cont2);

                            cell = rowGeneral1.createCell(1);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(ob_grupo.getGrupo());

                            cell = rowGeneral1.createCell(2);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(obG.getGrado() + " " + obG.getNombre());
                            j++;

                        }

                        i = i + 3;

                    }
                    /**/
                }

                j = j + 3;

                while (it_listI.hasNext()) {

                    Profesor obI = new Profesor();
                    obI = it_listI.next();

                    AlumnoDAO objALum = new AlumnoDAO();
                    List<Alumno> list2 = objALum.listarAlumnosTutoradosIndividualByCarrera(obI.getCurp(), ob_lic.getNombre(), idPeriodo);
                    Iterator<Alumno> it_list2 = list2.iterator();

                    if (list2.size() > 0) {

                        sheet.addMergedRegion(new CellRangeAddress(j, j, 0, 2));
                        rowGeneral1 = sheet.createRow(j);

                        rowGeneral1.createCell(0).setCellValue("TUTORIAS INDIVIDUALES");
                        j++;
                        i++;

                        rowGeneral1 = sheet.createRow(j);
                        cell = rowGeneral1.createCell(0);
                        cell.setCellValue("");
                        j++;

                        rowGeneral1 = sheet.createRow(j);

                        sheet.addMergedRegion(new CellRangeAddress(j, j, 0, 2));
                        cell = rowGeneral1.createCell(0);
                        cell.setCellValue(obI.getGrado() + " " + obI.getNombre());
                        cell.setCellStyle(styleColumn);
                        cell = rowGeneral1.createCell(1);
                        cell.setCellStyle(styleColumn);
                        cell = rowGeneral1.createCell(2);
                        cell.setCellStyle(styleColumn);

                        j++;

                        rowGeneral1 = sheet.createRow(j);

                        cell = rowGeneral1.createCell(0);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("N°");

                        cell = rowGeneral1.createCell(1);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("NOMBRE");

                        cell = rowGeneral1.createCell(2);
                        cell.setCellStyle(styleHead);
                        cell.setCellValue("GRUPO");
                        j++;

                        cont = 0;
                        while (it_list2.hasNext()) {
                            cont++;
                            i++;
                            Alumno obA = new Alumno();

                            obA = it_list2.next();
                            rowGeneral1 = sheet.createRow(j);

                            cell = rowGeneral1.createCell(0);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue("" + cont);

                            cell = rowGeneral1.createCell(1);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(obA.getNombre());

                            cell = rowGeneral1.createCell(2);
                            cell.setCellStyle(styleColumn);
                            cell.setCellValue(obA.getGrupo());
                            j++;
                        }

                        i = i + 3;

                    }
                }

                for (int k = 0; k < j; k++) {
                    sheet.autoSizeColumn((short) k);

                }

                try {
                    String rutRel = getServletConfig().getServletContext().getRealPath("/resources/Documentos");
                    try (FileOutputStream elFichero
                            = new FileOutputStream(rutRel + "/Asignacion_Tutorias " + ob_lic.getNombre() + "-" + pdo.getPeriodo() + ".xlsx")) {
                        book.write(elFichero);
                        elFichero.close();
                    }
                } catch (IOException e) {
                }

            }
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
                    + "text: \"Reporte generado de forma correcta...\",\n"
                    + "type: \"success\",    \n"
                    + "confirmButtonColor: \"#DD6B55\",\n"
                    + "confirmButtonText: \"Aceptar\",\n"
                    + "closeOnConfirm: false,\n"
                    + "},\n"
                    + "\n"
                    + "function(isConfirm){\n"
                    + "if (isConfirm) {\n"
                    + "window.location='pages/generarReportes.jsp'   \n"
                    + "} \n"
                    + "});\n"
                    + "}\n"
                    + "EventoAlert();\n"
                    + "</script>"
                    + "</body>\n"
                    + "</html>");
        } catch (SQLException ex) {
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
                    + "text: \"Error al generar el reporte\",\n"
                    + "type: \"warning\",    \n"
                    + "confirmButtonColor: \"#DD6B55\",\n"
                    + "confirmButtonText: \"Aceptar\",\n"
                    + "closeOnConfirm: false,\n"
                    + "},\n"
                    + "\n"
                    + "function(isConfirm){\n"
                    + "if (isConfirm) {\n"
                    + "window.location='pages/generarReportes.jsp'  \n"
                    + "} \n"
                    + "});\n"
                    + "}\n"
                    + "EventoAlert();\n"
                    + "</script>"
                    + "</body>\n"
                    + "</html>");
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
