/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import dao.LicenciaturaDAO;
import dao.PeriodoDAO;
import dao.ProfesorDAO;
import dao.ReporteDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.Licenciatura;
import model.Periodo;
import model.Profesor;
import model.Reporte;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.util.HSSFColor;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerReportesTutorias", urlPatterns = {"/ControllerReportesTutorias"})
public class ControllerReportesTutorias extends HttpServlet {

    private static final String edit = "pages/actualizarReporte.jsp";
    private static final String cargar = "pages/registroReportesTutorias.jsp";

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

    public AlumnoDAO alumnodao;
    public ProfesorDAO profesordao;
    public ReporteDAO reportedao;

    public ControllerReportesTutorias() {
        super();
        reportedao = new ReporteDAO();
        profesordao = new ProfesorDAO();
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
        Reporte rep = new Reporte();
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("reporte")) {
            int IdPeriodo = 0;
            String periodo = request.getParameter("IdPeriodo");
            if (!"todos".equals(periodo)) {
                IdPeriodo=Integer.parseInt(request.getParameter("IdPeriodo"));
            }
            try {

                Sheet sheet = null;

                ReporteDAO obj_Read_Values_rep = new ReporteDAO();
                List<Reporte> list_rep = obj_Read_Values_rep.obtenerReportesByPeriodo(IdPeriodo);
                Iterator<Reporte> it_list_rep = list_rep.iterator();

                ReporteDAO obj_Read_Values_rep2 = new ReporteDAO();
                List<Reporte> list_rep2 = obj_Read_Values_rep2.listarReportes();
                Iterator<Reporte> it_list_rep2 = list_rep2.iterator();
                Workbook book = new XSSFWorkbook();
                sheet = book.createSheet("Reportes_Tutorias");
                //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
                sheet.getPrintSetup().setLandscape(false);
                //indicando el tamaño de la hoja
                sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

                //poner negrita a la cabecera
                CellStyle style = book.createCellStyle();
                CellStyle style2 = book.createCellStyle();
                CellStyle style3 = book.createCellStyle();

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

                style3.setAlignment(HorizontalAlignment.CENTER);
                int j = 0;
                int cont = 1;

                PeriodoDAO per = new PeriodoDAO();
                Periodo pdo;
                pdo = per.obtenerPeriodoById(IdPeriodo);

                Row rowT = sheet.createRow(j);
                Cell cellT = rowT.createCell(0);
                if ("todos".equals(periodo)) {
                    cellT.setCellValue("REGISTRO DE REPORTES DE TUTORÍAS DE TODOS LOS PERIODOS");
                    cellT.setCellStyle(style3);
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
                } else {
                    cellT.setCellValue("REGISTRO DE REPORTES DE TUTORÍAS SEMESTRE  " + pdo.getPeriodo());
                    cellT.setCellStyle(style3);
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
                }

                j++;

                Row rowT1 = sheet.createRow(j);
                Cell cellT1 = rowT1.createCell(0);
                cellT1.setCellValue("UNIVERSIDAD DE LA SIERRA SUR");
                cellT1.setCellStyle(style3);
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 13));

                j++;

                Row rowT2 = sheet.createRow(j);
                Cell cellT2 = rowT2.createCell(0);
                cellT2.setCellValue("");

                j++;

                Row rowT3 = sheet.createRow(j);
                Cell cellT3 = rowT3.createCell(0);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(1);
                cellT3.setCellValue("DATOS DEL PROFESOR");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(2);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(3);
                cellT3.setCellValue("DATOS DEL REPORTE");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(4);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(5);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(6);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(7);
                cellT3.setCellValue("DATOS DE LA TUTORIA");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(8);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(9);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(10);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(11);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(12);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                cellT3 = rowT3.createCell(13);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style);

                sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 2));
                sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 6));
                sheet.addMergedRegion(new CellRangeAddress(3, 3, 7, 9));

                j++;

                Row row1 = sheet.createRow(j);

                Cell cell = row1.createCell(0);//se crea las celdas para la cabecera, junto con la posición
                cell.setCellStyle(style); // se añade el style crea anteriormente 
                cell.setCellValue("N/P");//se añade el contenido

                cell = row1.createCell(1);
                cell.setCellStyle(style);
                cell.setCellValue("PROFESOR");

                cell = row1.createCell(2);
                cell.setCellStyle(style);
                cell.setCellValue("LIC");

                cell = row1.createCell(3);
                cell.setCellStyle(style);
                cell.setCellValue("ENTREGÓ REPORTE");

                cell = row1.createCell(4);
                cell.setCellStyle(style);
                cell.setCellValue("ENTREGADO A TIEMPO");

                cell = row1.createCell(5);
                cell.setCellStyle(style);
                cell.setCellValue("FECHA DE ENTREGA");

                cell = row1.createCell(6);
                cell.setCellStyle(style);
                cell.setCellValue("FALTANTES");

                cell = row1.createCell(7);
                cell.setCellStyle(style);
                cell.setCellValue("TIPO DE TUTORIA");

                cell = row1.createCell(8);
                cell.setCellStyle(style);
                cell.setCellValue("N°. SESIONES");

                cell = row1.createCell(9);
                cell.setCellStyle(style);
                cell.setCellValue("N°. CANALIZACIONES");

                cell = row1.createCell(10);
                cell.setCellStyle(style);
                cell.setCellValue("ALUMNOS ASIGNADOS");

                cell = row1.createCell(11);
                cell.setCellStyle(style);
                cell.setCellValue("ALUMNOS REPORTADOS");

                cell = row1.createCell(12);
                cell.setCellStyle(style);
                cell.setCellValue("ALUMNOS CON ASISTENCIA");

                cell = row1.createCell(13);
                cell.setCellStyle(style);
                cell.setCellValue("OBSERVACIONES");

                j++;
                if ("todos".equals(periodo)) {
                    while (it_list_rep2.hasNext()) {

                        Reporte ob_lic_rep2 = new Reporte();
                        ob_lic_rep2 = it_list_rep2.next();

                        Row row2 = sheet.createRow(j);

                        cell = row2.createCell(0);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + cont);

                        cell = row2.createCell(1);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getProfesor());

                        cell = row2.createCell(2);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getLicenciatura());

                        cell = row2.createCell(3);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getEntrego());

                        cell = row2.createCell(4);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getaTiempo());

                        cell = row2.createCell(5);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getFecha());

                        cell = row2.createCell(6);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getFaltantes());

                        cell = row2.createCell(7);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getTipoTutoria());

                        cell = row2.createCell(8);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getNoSesiones());

                        cell = row2.createCell(9);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getNoCanalizaciones());

                        cell = row2.createCell(10);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getAlumnosAsignados());

                        cell = row2.createCell(11);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getAlumnosReportados());

                        cell = row2.createCell(12);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getAlumnosAsistencia());

                        cell = row2.createCell(13);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep2.getObservaciones());

                        j++;
                        cont++;

                    }
                } else {
                    while (it_list_rep.hasNext()) {

                        Reporte ob_lic_rep = new Reporte();
                        ob_lic_rep = it_list_rep.next();

                        Row row2 = sheet.createRow(j);

                        cell = row2.createCell(0);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + cont);

                        cell = row2.createCell(1);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getProfesor());
                        
                        cell = row2.createCell(2);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getLicenciatura());

                        cell = row2.createCell(3);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getEntrego());

                        cell = row2.createCell(4);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getaTiempo());

                        cell = row2.createCell(5);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getFecha());

                        cell = row2.createCell(6);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getFaltantes());

                        cell = row2.createCell(7);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getTipoTutoria());

                        cell = row2.createCell(8);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getNoSesiones());

                        cell = row2.createCell(9);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getNoCanalizaciones());

                        cell = row2.createCell(10);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getAlumnosAsignados());

                        cell = row2.createCell(11);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getAlumnosReportados());

                        cell = row2.createCell(12);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getAlumnosAsistencia());

                        cell = row2.createCell(13);
                        cell.setCellStyle(style2);
                        cell.setCellValue("" + ob_lic_rep.getObservaciones());

                        j++;
                        cont++;

                    }
                }

                try {
                    try (FileOutputStream elFichero
                            = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Registro de Reportes de Tutorías" + ".xlsx")) {
                        for (int k = 0; k <= 15; k++) {
                            sheet.autoSizeColumn((short) k);

                        }

                        book.write(elFichero);

                        elFichero.close();

                    }
                } catch (IOException e) {
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
                        + "text: \"Reporte creado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarReportes.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
                
                /// response.sendRedirect("pages/generarReportes.jsp");

            } catch (SQLException ex) {
                Logger.getLogger(ControllerReportesTutorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("update")) {
            try {
                forward = edit;
                System.out.print(request.getParameter("id"));
                rep = reportedao.obtenerReporteById(Integer.parseInt(request.getParameter("id")));

                request.setAttribute("rep", rep);
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Error en servlet: " + e);
            }

        } else if (action.equalsIgnoreCase("delete")) {
            try {
                int idReporte = Integer.parseInt(request.getParameter("id"));
                reportedao.eliminarById(idReporte); //response.sendRedirect("pages/indexAdmin.jsp");
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
                        + "text: \"Reporte eliminado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarReportes.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
               
            } catch (SQLException ex) {
                Logger.getLogger(ControllerReportesTutorias.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equalsIgnoreCase("cargar")) {

            try {

                forward = cargar;

                System.out.print("este es la curp del profesor" + request.getParameter("prof"));
                Profesor prf = null;
                prf = profesordao.obtenerProfesorRegistroReporte(request.getParameter("prof"));

                int count = alumnodao.countAlumnosTutorados(request.getParameter("prof"));
              
                if (count == 0) {
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
                        + "text: \"El profesor no tiene alumnos asignados...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarReportes.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
                    
                } else {

                    request.setAttribute("count", count);

                    if (prf.getTipoTutoria() == 1) {
                        String tipo = "INDIVIDUAL";
                        request.setAttribute("tipo", tipo);
                    } else {
                        String tipo = "GRUPAL";
                        request.setAttribute("tipo", tipo);
                    }

                    request.setAttribute("prf", prf);

                    RequestDispatcher view = request.getRequestDispatcher(forward);
                    view.forward(request, response);
                }

            } catch (SQLException ex) {
                Logger.getLogger(ControllerReportesTutorias.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equalsIgnoreCase("edit")) {
            java.util.Date fechaRep = new java.util.Date();
            try {

                int idRep = Integer.parseInt(request.getParameter("idRep"));
                String curp = request.getParameter("curp");
                int idLicenciatura = Integer.parseInt(request.getParameter("idLic"));
                int idPeriodo = Integer.parseInt(request.getParameter("idPeriodo"));
                String entrego = request.getParameter("EntRp");
                String aTiempo = request.getParameter("EntT");
                String fecha = request.getParameter("datepicker");
                fechaRep = convertirStrToDate(fecha);
                String tipoTutoria = request.getParameter("tipoTutoria");
                int noSesiones = Integer.parseInt(request.getParameter("numSes"));
                int noCanalizaciones = Integer.parseInt(request.getParameter("numCa"));
                int alumnosAsignados = Integer.parseInt(request.getParameter("asg"));
                int alumnosReportados = Integer.parseInt(request.getParameter("rep"));
                int alumnosAsistencia = Integer.parseInt(request.getParameter("asis"));
                String observaciones = request.getParameter("obs");
                String faltantes = request.getParameter("fal");

                java.sql.Date sqlFec = new java.sql.Date(fechaRep.getTime());
                rep.setIdReporte(idRep);
                rep.setCurp(curp);
                rep.setIdLicenciatura(idLicenciatura);
                rep.setIdPeriodo(idPeriodo);
                rep.setEntrego(entrego);
                rep.setaTiempo(aTiempo);
                rep.setFecha(sqlFec);
                rep.setTipoTutoria(tipoTutoria);
                rep.setNoSesiones(noSesiones);
                rep.setNoCanalizaciones(noCanalizaciones);
                rep.setAlumnosAsignados(alumnosAsignados);
                rep.setAlumnosReportados(alumnosReportados);
                rep.setAlumnosAsistencia(alumnosAsistencia);
                rep.setObservaciones(observaciones);
                rep.setFaltantes(faltantes);

                if (reportedao.update(rep) == true) {
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
                        + "text: \"El reporte a sido actualizado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarReportes.jsp'   \n"
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
                        + "text: \"Error al actualizar...\",\n"
                        + "type: \"warning\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarReportes.jsp'   \n"
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
        } else if (action.equalsIgnoreCase("add")) {
            java.util.Date fechaRep = new java.util.Date();
            try {
                String curp = request.getParameter("curp");
                int idLicenciatura = Integer.parseInt(request.getParameter("idLic"));

                int idPeriodo = Integer.parseInt(request.getParameter("idPeriodo"));
                String entrego = request.getParameter("EntRp");
                String aTiempo = request.getParameter("EntT");
                String fecha = request.getParameter("datepicker");
                fechaRep = convertirStrToDate(fecha);
                String tipoTutoria = request.getParameter("tipoTutoria");
                int noSesiones = Integer.parseInt(request.getParameter("numSes"));
                int noCanalizaciones = Integer.parseInt(request.getParameter("numCa"));
                int alumnosAsignados = Integer.parseInt(request.getParameter("asg"));
                int alumnosReportados = Integer.parseInt(request.getParameter("rep"));
                int alumnosAsistencia = Integer.parseInt(request.getParameter("asis"));
                String observaciones = request.getParameter("obs");
                String faltantes = request.getParameter("fal");

                java.sql.Date sqlFec = new java.sql.Date(fechaRep.getTime());
                rep.setCurp(curp);
                rep.setIdLicenciatura(idLicenciatura);
                rep.setIdPeriodo(idPeriodo);
                rep.setEntrego(entrego);
                rep.setaTiempo(aTiempo);
                rep.setFecha(sqlFec);
                rep.setTipoTutoria(tipoTutoria);
                rep.setNoSesiones(noSesiones);
                rep.setNoCanalizaciones(noCanalizaciones);
                rep.setAlumnosAsignados(alumnosAsignados);
                rep.setAlumnosReportados(alumnosReportados);
                rep.setAlumnosAsistencia(alumnosAsistencia);
                rep.setObservaciones(observaciones);
                rep.setFaltantes(faltantes);
                if (reportedao.insertar(rep) == true) {
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
                        + "text: \"Reporte guardado de forma correcta...\",\n"
                        + "type: \"success\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarReportes.jsp'   \n"
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
                        + "text: \"Error al guardar el reporte verifica tus datos, es posible que el reporte ya exista\",\n"
                        + "type: \"warning\",    \n"
                        + "confirmButtonColor: \"#DD6B55\",\n"
                        + "confirmButtonText: \"Aceptar\",\n"
                        + "closeOnConfirm: false,\n"
                        + "},\n"
                        + "\n"
                        + "function(isConfirm){\n"
                        + "if (isConfirm) {\n"
                        + "window.location='pages/ListarReportes.jsp'   \n"
                        + "} \n"
                        + "});\n"
                        + "}\n"
                        + "EventoAlert();\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>");
                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(ControllerReportesTutorias.class.getName()).log(Level.SEVERE, null, ex);

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

    java.util.Date convertirStrToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date date = null;
        try {

            date = (java.util.Date) formatoDelTexto.parse(fecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }

        System.out.println(date.toString());

        return date;
    }

}
