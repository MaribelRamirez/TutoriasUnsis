/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.AlumnoDAO;
import dao.GrupoDAO;
import dao.ProfesorDAO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import model.Grupo;
import model.Profesor;

/**
 *
 * @author Marifer
 */
@WebServlet(name = "ControllerConstancias", urlPatterns = {"/ControllerConstancias"})
public class ControllerConstancias extends HttpServlet {

    private static final String edit = "pages/generarConstancias.jsp";
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

        String calendario = request.getParameter("datepicker");

        String[] bar = calendario.split("-");
        int j = 1;
        String dia = null;
        String mes = null;
        String año = null;
        String nomMes = null;

        for (String fechas : bar) {

            if (j == 1) {
                año = fechas;
            } else if (j == 2) {
                mes = fechas;
            } else if (j == 3) {
                dia = fechas;
            }
            j++;

        }

        switch (mes) {
            case "01":
                nomMes = "enero";
                break;
            case "02":
                nomMes = "febrero";
                break;
            case "03":
                nomMes = "marzo";
                break;
            case "04":
                nomMes = "abril";
                break;
            case "05":
                nomMes = "mayo";
                break;
            case "06":
                nomMes = "junio";

                break;
            case "07":
                nomMes = "julio";
                break;
            case "08":
                nomMes = "agosto";
                break;
            case "09":
                nomMes = "septiembre";
                break;
            case "10":
                nomMes = "octubre";
                break;
            case "11":
                nomMes = "noviembre";
                break;
            case "12":
                nomMes = "diciembre";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + mes);
        }

        System.out.println("esta es la fecha.." + calendario);
        int i = 1;

        try {
            forward = edit;
            int sizeList = Integer.parseInt(request.getParameter("sizeList"));

            response.setContentType("application/pdf");
            OutputStream outt = response.getOutputStream();
            Document documento = new Document(PageSize.A4, 60, 60, 40, 40);
            PdfWriter.getInstance(documento, outt);
            documento.open();
            for (i = 1; i <= sizeList; i++) {
                String profCurp = request.getParameter("prof".concat(Integer.toString(i)));

                if (profCurp != null) {

                    ProfesorDAO obprof = new ProfesorDAO();
                    Profesor prof = obprof.obtenerProfesorBycurp(profCurp);

                    GrupoDAO ob_grupo = new GrupoDAO();
                    Grupo grupo = ob_grupo.obtenerGrupobyProf(profCurp);

                    AlumnoDAO obj_Read_Values = new AlumnoDAO();
                    List<Alumno> list = obj_Read_Values.listarAlumnosTutoradosIndividual(profCurp);
                    Iterator<Alumno> it_list = list.iterator();

                    AlumnoDAO obj_Read_Values2 = new AlumnoDAO();
                    List<Alumno> list2 = obj_Read_Values2.listarAlumnosTutoradosGrupal(profCurp);
                    Iterator<Alumno> it_list2 = list2.iterator();

                    try {

                        Paragraph par1 = new Paragraph();

                        Image imagenes = Image.getInstance("C:\\Users\\Marifer\\Documents\\NetBeansProjects\\servicioSocial\\TutoriasUnsis\\web\\resources\\images\\escudo.png");
                        imagenes.setAlignment(Element.ALIGN_CENTER);
                        imagenes.scaleToFit(90, 90);
                        documento.add(imagenes);
                        par1.add(new Phrase(Chunk.NEWLINE));

                        //fuente del titulo
                        Font fonttitulo = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.RED);
                        Font text1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
                        Font text2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.BLACK);
                        Font text3 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
                        Font text4 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC, BaseColor.BLACK);
                        Font text5 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
                        //titulo del texto
                        par1.add(new Phrase("La Universidad de la Sierra Sur", fonttitulo));
                        par1.add(new Phrase(Chunk.NEWLINE));

                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("A través de la Coordinación de Tutorías", text1));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("HACE CONSTAR", text2));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        //para hace salto de linea
                        par1.add(new Phrase(Chunk.NEWLINE));
                        //agregar texto anterior al documento
                        documento.add(par1);

                        Paragraph par2 = new Paragraph();

                        int tamaño = 0;
                        if (action.equalsIgnoreCase("individual")) {
                            tamaño = list.size();
                        } else {
                            tamaño = list2.size();
                        }

                        par2.add(new Phrase("Que la Profesora de Tiempo Completo ", text1));
                        par2.add(new Phrase("" + prof.getNombre(), text3));

                        if (action.equalsIgnoreCase("individual")) {

                            par2.add(new Phrase(" impartió Tutorías Académicas Individuales, en el periodo "
                                    + ", a los siguientes alumnos:", text1));
                        } else {
                            par2.add(new Phrase(" impartió Tutorías Académicas Grupales, en el periodo "
                                    + ", al grupo " + grupo.getGrupo() + " conformado por los siguientes alumnos: ", text1));
                        }

                        par2.setAlignment(Element.ALIGN_JUSTIFIED);
                        par2.add(new Phrase(Chunk.NEWLINE));
                        if (tamaño <= 16 && tamaño > 0) {
                            par2.add(new Phrase(Chunk.NEWLINE));
                            par2.add(new Phrase(Chunk.NEWLINE));
                            par2.add(new Phrase(Chunk.NEWLINE));

                        }
                        documento.add(par2);

                        if ((tamaño <= 10) && (tamaño > 0)) {
                            PdfPTable tabla = new PdfPTable(2);
                            tabla.setWidthPercentage(100);
                            tabla.setWidths(new float[]{5, 95});

                            PdfPCell celda1 = new PdfPCell(new Paragraph("N°", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK)));
                            PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK)));

                            tabla.addCell(celda1);
                            tabla.addCell(celda2);
                            int cont = 1;
                            if (action.equalsIgnoreCase("individual")) {

                                while (it_list.hasNext()) {

                                    Alumno ob = new Alumno();
                                    ob = it_list.next();
                                    tabla.addCell("" + cont);
                                    tabla.addCell(ob.getNombre());
                                    cont++;

                                }

                            } else {
                                while (it_list2.hasNext()) {
                                    Alumno ob = new Alumno();
                                    ob = it_list2.next();
                                    tabla.addCell("" + cont);
                                    tabla.addCell(ob.getNombre());
                                    cont++;

                                }
                            }
                            documento.add(tabla);

                        } else {
                            PdfPTable tabla = new PdfPTable(4);
                            tabla.setWidthPercentage(100);
                            tabla.setWidths(new float[]{4, 46, 4, 46});

                            PdfPCell celda1 = new PdfPCell(new Paragraph("N°", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLACK)));
                            PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLACK)));
                            PdfPCell celda3 = new PdfPCell(new Paragraph("N°", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLACK)));
                            PdfPCell celda4 = new PdfPCell(new Paragraph("Nombre", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLACK)));

                            tabla.addCell(celda1);
                            tabla.addCell(celda2);
                            tabla.addCell(celda3);
                            tabla.addCell(celda4);
                            int cont = 1;
                            if (action.equalsIgnoreCase("individual")) {
                                while (it_list.hasNext()) {
                                    Alumno ob = new Alumno();

                                    ob = it_list.next();
                                    tabla.addCell("" + cont);
                                    PdfPCell celda5 = new PdfPCell(new Paragraph(ob.getNombre(), FontFactory.getFont("Arial", 9, Font.NORMAL, BaseColor.BLACK)));

                                    tabla.addCell(celda5);
                                    cont++;

                                }
                            } else {
                                while (it_list2.hasNext()) {
                                    Alumno ob = new Alumno();
                                    ob = it_list2.next();
                                    tabla.addCell("" + cont);
                                    PdfPCell celda6 = new PdfPCell(new Paragraph(ob.getNombre(), FontFactory.getFont("Arial", 9, Font.NORMAL, BaseColor.BLACK)));

                                    tabla.addCell(celda6);
                                    cont++;

                                }
                            }
                            if (tamaño % 2 != 0) {
                                tabla.addCell("");
                                tabla.addCell("");
                            }
                            documento.add(tabla);
                        }

                        Paragraph par3 = new Paragraph();
                        par3.add(new Phrase(Chunk.NEWLINE));
                        par3.add(new Phrase("Se extiende la presente, en la Ciudad de Miahuatlán de Porfirio Díaz, "
                                + "Oaxaca, a los " + dia + " días del mes de " + nomMes + " de " + año + ".", text1));

                        par3.setAlignment(Element.ALIGN_JUSTIFIED);
                        par3.add(new Phrase(Chunk.NEWLINE));
                        par3.add(new Phrase(Chunk.NEWLINE));

                        if (tamaño <= 16 && tamaño > 0) {
                            par3.add(new Phrase(Chunk.NEWLINE));
                            par3.add(new Phrase(Chunk.NEWLINE));
                            par3.add(new Phrase(Chunk.NEWLINE));
                            par3.add(new Phrase(Chunk.NEWLINE));
                            par3.add(new Phrase(Chunk.NEWLINE));
                            par3.add(new Phrase(Chunk.NEWLINE));
                            par3.add(new Phrase(Chunk.NEWLINE));

                        }
                        documento.add(par3);
                        Paragraph par4 = new Paragraph();
                        par4.add(new Phrase("ATENTAMENTE", text1));
                        par4.add(new Phrase(Chunk.NEWLINE));
                        par4.add(new Phrase("Docendo discimus  ", text4));
                        par4.add(new Phrase(Chunk.NEWLINE));
                        par4.add(new Phrase("Iur rluaaia rsëedaa ", text4));
                        par4.add(new Phrase(Chunk.NEWLINE));
                        par4.add(new Phrase(Chunk.NEWLINE));
                        par4.add(new Phrase("LIC. YESENIA ROJAS ALCÁNTARA", text1));
                        par4.add(new Phrase(Chunk.NEWLINE));
                        par4.add(new Phrase("Coordinadora de Tutorías", text5));
                        par4.setAlignment(Element.ALIGN_CENTER);
                        documento.add(par4);
                        documento.newPage();
                    } catch (Exception e) {
                    }
                }

            }

            documento.close();

        } catch (NumberFormatException e) {
            System.out.println("Error en servlet: " + e);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerConstancias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ControllerConstancias.class.getName()).log(Level.SEVERE, null, ex);
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
