/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import dao.GrupoDAO;
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
import model.Alumno;
import model.Grupo;
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
import org.apache.poi.ss.usermodel.RichTextString;
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
        Sheet sheet = null;
        Workbook book = new XSSFWorkbook();
        //poner negrita a la cabecera
        CellStyle style = book.createCellStyle();
        CellStyle style2 = book.createCellStyle();
        CellStyle style3 = book.createCellStyle();
        CellStyle style4 = book.createCellStyle();

        Font font = book.createFont();
        font.setBold(true);

        style.setFont(font);
        style3.setFont(font);
        style4.setFont(font);
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

        //style3.setBorderBottom(BorderStyle.MEDIUM);
        style3.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style3.setBorderLeft(BorderStyle.MEDIUM);
        style3.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style3.setBorderRight(BorderStyle.MEDIUM);
        style3.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style3.setBorderTop(BorderStyle.MEDIUM);
        style3.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style3.setAlignment(HorizontalAlignment.CENTER);
        style3.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style4.setBorderBottom(BorderStyle.MEDIUM);
        style4.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style4.setBorderLeft(BorderStyle.MEDIUM);
        style4.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style4.setBorderRight(BorderStyle.MEDIUM);
        style4.setRightBorderColor(IndexedColors.BLACK.getIndex());
        //style4.setBorderTop(BorderStyle.MEDIUM);
        style4.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style4.setAlignment(HorizontalAlignment.CENTER);
        style4.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        int carreras = 0;
        try {

            LicenciaturaDAO obj_Read_lic = new LicenciaturaDAO();
            List<Licenciatura> list_lic = obj_Read_lic.listarLicenciaturas();
            Iterator<Licenciatura> it_list_lic = list_lic.iterator();

            while (it_list_lic.hasNext()) {

                Licenciatura ob = new Licenciatura();
                ob = it_list_lic.next();
                System.out.println("nombre de la carrera para hoja " + ob.getNombre());

                
                sheet = book.createSheet(ob.getNombre());
                //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
                sheet.getPrintSetup().setLandscape(false);
                //indicando el tamaño de la hoja
                sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

                int j = 0;

                Row rowT1 = sheet.createRow(j);
                j++;
                Row rowT2 = sheet.createRow(j);
                Cell cellT2 = rowT2.createCell(0);
                cellT2.setCellValue("ESTATUS");
                cellT2.setCellStyle(style3);

                cellT2 = rowT2.createCell(1);
                cellT2.setCellValue("NOMBRE");
                cellT2.setCellStyle(style3);

                cellT2 = rowT2.createCell(2);
                cellT2.setCellValue("GRUPO");
                cellT2.setCellStyle(style3);

                cellT2 = rowT2.createCell(3);
                cellT2.setCellValue("LICENCIATURA");
                cellT2.setCellStyle(style);

                j++;

                Row rowT3 = sheet.createRow(j);
                Cell cellT3 = rowT3.createCell(0);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style4);

                cellT3 = rowT3.createCell(1);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style4);

                cellT3 = rowT3.createCell(2);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style4);

                ///Contar cuantas carreras existen registradas
                LicenciaturaDAO obj_lic = new LicenciaturaDAO();
                carreras = obj_lic.countLicenciaturas();

                //sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
                // sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
                //sssheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
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

                    Licenciatura obj = new Licenciatura();
                    obj = it_list.next();

                    cellT3 = rowT3.createCell(l);
                    cellT3.setCellValue(obj.getNombre());
                    cellT3.setCellStyle(style);
                    l++;

                }

                sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, carreras));

                carreras = carreras + 1;

                cellT2 = rowT2.createCell(l);
                cellT2.setCellValue("TUTORADOS");
                cellT2.setCellStyle(style3);

                cellT3 = rowT3.createCell(l);
                cellT3.setCellValue("");
                cellT3.setCellStyle(style4);

                l++;
                j++;

                // sheet.addMergedRegion(new CellRangeAddress(1, 2, carreras, carreras));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, carreras));

                /**
                 * *****
                 */
                ProfesorDAO obj_Read = new ProfesorDAO();
                List<Profesor> list_tutor = obj_Read.obtenerProfesorTutor(ob.getNombre());
                System.out.println("2");
                Iterator<Profesor> it_list_tutor = list_tutor.iterator();

                System.out.println("ante del whileee 2");

                while (it_list_tutor.hasNext()) {
                    System.out.println("entre al segundo while");
                    l = 0;
                    Profesor Obt = new Profesor();
                    Obt = it_list_tutor.next();

                    Row rowT4 = sheet.createRow(j);
                    Cell cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue(Obt.getEstatus());
                    cellT4.setCellStyle(style2);

                    l++;

                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue(Obt.getNombre());
                    cellT4.setCellStyle(style2);

                    l++;
                   
                    
                    
                   if(Obt.getTipoTutoria()==1 ){
                       cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue("NA");
                    cellT4.setCellStyle(style2);
                    l++;
                    
                   /* for(int m=1;m<=carreras-3;m++){
                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue("-");
                    cellT4.setCellStyle(style2);
                    l++;
                    }*/
                    
                     }
                     else{
                        
                    if("Inactivo".equals(Obt.getEstatus())||"Licencia".equals(Obt.getEstatus())||"Sabatico".equals(Obt.getEstatus())){
                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue("NA");
                    cellT4.setCellStyle(style2);
                    l++;
                    }else{
                        GrupoDAO grup_read=new GrupoDAO();
                        Grupo grp=new Grupo();
                        grp=grup_read.obtenerGrupobyProf(Obt.getCurp());
                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue(grp.getGrupo());
                    cellT4.setCellStyle(style2);
                    l++;
                    }
                       //obtener grupo
                    }
                   
                          AlumnoDAO alum_read=new AlumnoDAO();
                       
                       
                 List<Licenciatura> list2 = obj_Read_Values.listarLicenciaturas();
                Iterator<Licenciatura> it_list2 = list2.iterator();
                
                while (it_list2.hasNext()) {

                    Licenciatura obj = new Licenciatura();
                    obj = it_list2.next();

                    
                   
                     
                     
                    if(alum_read.countAlumnosTutoradosByCarrera(Obt.getCurp(),obj.getNombre())==0){
                         
                    if(Obt.getEstatus()=="Inactivo"||Obt.getEstatus()=="Licencia"||Obt.getEstatus()=="Sabatico"){
                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue("NA");
                    cellT4.setCellStyle(style2);
                    l++;
                    }else{
                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue( "-");
                    cellT4.setCellStyle(style2);
                    l++;
                    }
                    
                     
                    }else{
                     cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue( alum_read.countAlumnosTutoradosByCarrera(Obt.getCurp(),obj.getNombre()));
                    cellT4.setCellStyle(style2);
                    l++;
                    }
                    

                }
                if(Obt.getEstatus()=="Inactivo"||Obt.getEstatus()=="Licencia"||Obt.getEstatus()=="Sabatico"){
                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue("NA");
                    cellT4.setCellStyle(style2);
                    l++;
                    }else{
                
                    cellT4 = rowT4.createCell(l);
                    cellT4.setCellValue( alum_read.countAlumnosTutorados(Obt.getCurp()));
                    cellT4.setCellStyle(style2);
                    l++;
                }
                   
                    
                   j++;
                }
                 for (int k = 0; k < carreras + 1; k++) {
                    sheet.autoSizeColumn((short) k);
                }
            }
         
            
                sheet = book.createSheet("Estadística");
                //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
                sheet.getPrintSetup().setLandscape(false);
                //indicando el tamaño de la hoja
                sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

                List<Licenciatura> list_lic2 = obj_Read_lic.listarLicenciaturas();
                Iterator<Licenciatura> it_list_lic2 = list_lic2.iterator();
int j=0;
int l=0;
Row rowT = sheet.createRow(j);
                    Cell cellT4 = rowT.createCell(l);
                    cellT4.setCellValue("LIC");
                    cellT4.setCellStyle(style3);
                    l++;
                while (it_list_lic2.hasNext()) {
                
                  Licenciatura obj = new Licenciatura();
                  obj = it_list_lic2.next();
                  

                cellT4 = rowT.createCell(l);
                    cellT4.setCellValue(obj.getNombre());
                    cellT4.setCellStyle(style3);
                    l++;
                 }
            cellT4 = rowT.createCell(l);
                    cellT4.setCellValue("TOTAL");
                    cellT4.setCellStyle(style3);
                   
                      
                      List<Licenciatura> list_licTut= obj_Read_lic.listarLicenciaturas();
                Iterator<Licenciatura> it_list_licTut = list_licTut.iterator();
                AlumnoDAO alum_read=new AlumnoDAO();
                while (it_list_licTut.hasNext()) {
                     j++;
                           l=0;
                    Licenciatura objTut= new Licenciatura();
                                objTut = it_list_licTut.next();
                  rowT = sheet.createRow(j);
                  cellT4 = rowT.createCell(l);
                                cellT4.setCellValue(objTut.getNombre());
                                cellT4.setCellStyle(style3);
                                l++;
                             List<Licenciatura> list_licAlum = obj_Read_lic.listarLicenciaturas();
                            Iterator<Licenciatura> it_list_licAlumno = list_licAlum.iterator();
                           while (it_list_licAlumno.hasNext()) {
                
                                Licenciatura objAlm = new Licenciatura();
                                objAlm = it_list_licAlumno.next();
                                
                                 System.out.println(objAlm.getNombre());
                                 System.out.println(objTut.getNombre());
                                 
                                
                                cellT4 = rowT.createCell(l);
                                cellT4.setCellValue(alum_read.countEstadistica(objTut.getIdLicenciatura(), objAlm.getIdLicenciatura()));
                                cellT4.setCellStyle(style2);
                                l++;
                              }
                           
                                cellT4 = rowT.createCell(l);
                                cellT4.setCellValue(alum_read.countEstadistica2(objTut.getIdLicenciatura()));
                                cellT4.setCellStyle(style2);
                                
                          
                           
                }
                j++;
              
                                rowT = sheet.createRow(j);
                               
                                cellT4 = rowT.createCell(l);
                                cellT4.setCellValue(alum_read.countEstadistica3() );
                                cellT4.setCellStyle(style2);
                                sheet.addMergedRegion(new CellRangeAddress(j, j, l-4, l-1));
                                 for (int k = 0; k < j; k++) {
                    sheet.autoSizeColumn((short) k);

                }
            try {
               
                try (FileOutputStream elFichero
                        = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Concentrado_Asignaciones" + ".xlsx")) {

                    book.write(elFichero);

                    elFichero.close();

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
                    + "text: \"Reporte generado con exito...\",\n"
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

            } catch (IOException e) {
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
                    + "text: \"Error al generar el reporte...\",\n"
                    + "type: \"warning\",    \n"
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

            }

            
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