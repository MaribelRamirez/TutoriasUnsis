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
import model.Grupo;
import model.Licenciatura;
import model.Profesor;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

        try {

            processRequest(request, response);
            //creando el libro de excel

            LicenciaturaDAO obj_Read_Values_lic = new LicenciaturaDAO();
            List<Licenciatura> list_lic = obj_Read_Values_lic.listarLicenciaturas();
            Iterator<Licenciatura> it_list_lic = list_lic.iterator();
            Sheet sheetx = null;
            Sheet sheet = null;
            Row rowGeneral = null;
            Row rowGeneral1 = null;
            int cont = 0, cont2 = 0;
            int i = 0;
            int j = 0;
            while (it_list_lic.hasNext()) {
                j = 0;
                Workbook book = new XSSFWorkbook();
                Licenciatura ob_lic = new Licenciatura();
                ob_lic = it_list_lic.next();

                //creando y asignando nombre a la hoja de excel
                sheet = book.createSheet("" + ob_lic.getNombre());
                //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
                sheet.getPrintSetup().setLandscape(false);
                //indicando el tamaño de la hoja
                sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

                ProfesorDAO obj_Read_Values = new ProfesorDAO();
                List<Profesor> tutor = obj_Read_Values.listarProfesores();
                Iterator<Profesor> it_tutor = tutor.iterator();

                GrupoDAO obj_grupos = new GrupoDAO();

                //creando es estilo de los encabezados de la tabla
                Row titulo1 = sheet.createRow(j);
                titulo1.createCell(0).setCellValue("REGISTRO DE REPORTES DE TUTORÍAS SEMESTRE 17-18 B");
                j++;
                Row titulo2 = sheet.createRow(j);
                titulo2.createCell(0).setCellValue("UNIVERSIDAD DE LA SIERRA SUR");
                j++;
                Row titulo3 = sheet.createRow(j);
                titulo3.createCell(0).setCellValue("");
                j++;

                if ((ob_lic.getNombre().equals("LE"))) {

                    Row titulo4 = sheet.createRow(j);
                    titulo4.createCell(0).setCellValue("tutorias grupales");
                    j++;

                    List<Profesor> listG = obj_Read_Values.tutorGrupal();
                    Iterator<Profesor> it_listG = listG.iterator();

                    List<Profesor> listI = obj_Read_Values.tutorIndividual();
                    Iterator<Profesor> it_listI = listI.iterator();

                    rowGeneral = sheet.createRow(j);
                    //poner encabezados de la tabla
                    rowGeneral.createCell(0).setCellValue("N°");
                    rowGeneral.createCell(1).setCellValue("GRUPO");
                    rowGeneral.createCell(2).setCellValue("TUTOR");
                    j++;

                    while (it_listG.hasNext()) {

                        i = 0;
                        Profesor obG = new Profesor();
                        obG = it_listG.next();

                        AlumnoDAO objALum = new AlumnoDAO();
                        List<Alumno> list2 = objALum.listarAlumnosTutoradosByCarrera(obG.getCurp(), ob_lic.getNombre());
                        Iterator<Alumno> it_list2 = list2.iterator();

                        List<Grupo> list_grupos = obj_grupos.listarGruposTutorados(obG.getCurp(), ob_lic.getNombre());
                        Iterator<Grupo> it_grupos = list_grupos.iterator();

                        if (list2.size() > 0) {
                            //creando y asignando nombre a la hoja de excel
                            sheetx = book.createSheet("" + obG.getNombre());
                            //indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
                            sheetx.getPrintSetup().setLandscape(false);
                            //indicando el tamaño de la hoja
                            sheetx.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

                            Row row1 = sheetx.createRow(i);
                            //poner nombre del profesor

                            row1.createCell(0).setCellValue("" + obG.getNombre());

                            i++;
                            Row row2 = sheetx.createRow(i);
                            //poner encabezados de la tabla
                            row2.createCell(0).setCellValue("N°");
                            row2.createCell(1).setCellValue("MATRICULA");
                            row2.createCell(2).setCellValue("NOMBRE");
                            row2.createCell(3).setCellValue("LICENCIATURA");
                            row2.createCell(4).setCellValue("GRUPO");

                            cont = 0;

                            while (it_list2.hasNext()) {
                                cont++;
                                i++;
                                Alumno obA = new Alumno();

                                obA = it_list2.next();
                                Row row3 = sheetx.createRow(i);
                                //poner datos
                                row3.createCell(0).setCellValue("" + cont);
                                row3.createCell(1).setCellValue(obA.getMatricula());
                                row3.createCell(2).setCellValue(obA.getNombre());
                                row3.createCell(3).setCellValue(obA.getLicenciatura());
                                row3.createCell(4).setCellValue(obA.getGrupo());

                            }
                            cont = 0;
                            while (it_grupos.hasNext()) {
                                cont2++;
                                Grupo ob_grupo = new Grupo();
                                ob_grupo = it_grupos.next();

                                rowGeneral1 = sheet.createRow(j);
                                ////********
                                rowGeneral1.createCell(0).setCellValue("" + cont2);
                                rowGeneral1.createCell(1).setCellValue(ob_grupo.getGrupo());
                                rowGeneral1.createCell(2).setCellValue(obG.getNombre());
                                j++;
                            }
                            i = i + 3;
                            try {
                                try (FileOutputStream elFichero
                                        = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Asignacion Tutorias " + ob_lic.getNombre() + ".xlsx")) {
                                    for (int k = 0; k < i; k++) {
                                        sheetx.autoSizeColumn((short) k);

                                    }

                                    book.write(elFichero);

                                    elFichero.close();

                                }
                            } catch (IOException e) {
                            }
                        }

                    }
                    j = j + 3;
                    rowGeneral1 = sheet.createRow(j);
                    rowGeneral1.createCell(0).setCellValue("TUTORIAS INDIVIDUALES");
                    j++;
                    while (it_listI.hasNext()) {

                        Profesor obI = new Profesor();
                        obI = it_listI.next();

                        AlumnoDAO objALum = new AlumnoDAO();
                        List<Alumno> list2 = objALum.listarAlumnosTutoradosIndividualByCarrera(obI.getCurp(), ob_lic.getNombre());
                        Iterator<Alumno> it_list2 = list2.iterator();

                        if (list2.size() > 0) {

                            i++;

                            rowGeneral1 = sheet.createRow(j);
                            rowGeneral1.createCell(0).setCellValue("");
                            j++;
                            rowGeneral1 = sheet.createRow(j);
                            rowGeneral1.createCell(0).setCellValue(obI.getNombre());
                            j++;

                            rowGeneral1 = sheet.createRow(j);
                            rowGeneral1.createCell(0).setCellValue("N°");
                            rowGeneral1.createCell(1).setCellValue("NOMBRE");
                            rowGeneral1.createCell(2).setCellValue("GRUPO");
                            j++;

                            cont = 0;
                            while (it_list2.hasNext()) {
                                cont++;
                                i++;
                                Alumno obA = new Alumno();

                                obA = it_list2.next();
                                rowGeneral1 = sheet.createRow(j);
                                rowGeneral1.createCell(0).setCellValue("" + cont);
                                rowGeneral1.createCell(1).setCellValue(obA.getNombre());
                                rowGeneral1.createCell(2).setCellValue(obA.getGrupo());
                                j++;
                            }

                            i = i + 3;
                            try {
                                try (FileOutputStream elFichero
                                        = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Asignacion Tutorias " + ob_lic.getNombre() + ".xlsx")) {
                                    for (int k = 0; k < i; k++) {
                                        sheetx.autoSizeColumn((short) k);

                                    }

                                    book.write(elFichero);

                                    elFichero.close();

                                }
                            } catch (IOException e) {
                            }
                        }

                    }

                } else {
                    rowGeneral = sheet.createRow(j);
                    //poner encabezados de la tabla
                    rowGeneral.createCell(0).setCellValue("MATRICULA");
                    rowGeneral.createCell(1).setCellValue("NOMBRE");
                    rowGeneral.createCell(2).setCellValue("TUTOR");
                    rowGeneral.createCell(3).setCellValue("GRUPO");
                    rowGeneral.createCell(4).setCellValue("LICENCIATURA");
                    j++;

                    while (it_tutor.hasNext()) {

                        i = 0;
                        Profesor ob_tutor = new Profesor();
                        ob_tutor = it_tutor.next();

                        AlumnoDAO objALum = new AlumnoDAO();
                        List<Alumno> list2 = objALum.listarAlumnosTutoradosByCarrera(ob_tutor.getCurp(), ob_lic.getNombre());
                        Iterator<Alumno> it_list2 = list2.iterator();

                        if (list2.size() > 0) {
                            //  creando y asignando nombre a la hoja de excel
                            sheetx = book.createSheet("" + ob_tutor.getNombre());
                            //   indicando si es horizintal o vertical de la hoja (false-vertical, true-horizontal)
                            sheetx.getPrintSetup().setLandscape(false);
                            //indicando el tamaño de la hoja
                            sheetx.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

                            Row row1 = sheetx.createRow(i);
                            //poner nombre del profesor

                            row1.createCell(0).setCellValue("" + ob_tutor.getNombre());

                            i++;
                            Row row2 = sheetx.createRow(i);
                            //poner encabezados de la tabla
                            row2.createCell(0).setCellValue("MATRICULA");
                            row2.createCell(1).setCellValue("NOMBRE");
                            row2.createCell(3).setCellValue("LICENCIATURA");
                            row2.createCell(2).setCellValue("GRUPO");

                            while (it_list2.hasNext()) {

                                i++;
                                Alumno obA = new Alumno();

                                obA = it_list2.next();
                                Row row3 = sheetx.createRow(i);
                                //poner datos
                                row3.createCell(0).setCellValue(obA.getMatricula());
                                row3.createCell(1).setCellValue(obA.getNombre());
                                row3.createCell(3).setCellValue(obA.getLicenciatura());
                                row3.createCell(2).setCellValue(obA.getGrupo());

                                Row rowGeneral2 = sheet.createRow(j);
                                rowGeneral2.createCell(0).setCellValue(obA.getMatricula());
                                rowGeneral2.createCell(1).setCellValue(obA.getNombre());
                                rowGeneral2.createCell(2).setCellValue(ob_tutor.getNombre());
                                rowGeneral2.createCell(3).setCellValue(obA.getGrupo());
                                rowGeneral2.createCell(4).setCellValue(obA.getLicenciatura());
                                j++;
                            }
                            i = i + 3;
                            try {
                                try (FileOutputStream elFichero
                                        = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Asignacion Tutorias " + ob_lic.getNombre() + ".xlsx")) {
                                    for (int k = 0; k < i; k++) {
                                        sheetx.autoSizeColumn((short) k);

                                    }

                                    book.write(elFichero);

                                    elFichero.close();

                                }
                            } catch (IOException e) {
                            }
                        }

                    }
                }

                try {
                    try (FileOutputStream elFichero
                            = new FileOutputStream("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\" + "Asignacion Tutorias " + ob_lic.getNombre() + ".xlsx")) {
                        for (int k = 0; k < j; k++) {
                            sheet.autoSizeColumn((short) k);

                        }

                        book.write(elFichero);

                        elFichero.close();

                    }
                } catch (IOException e) {
                }

            }

            response.sendRedirect("pages/generarReportes.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerReporteAsignacionTutorias.class.getName()).log(Level.SEVERE, null, ex);
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
