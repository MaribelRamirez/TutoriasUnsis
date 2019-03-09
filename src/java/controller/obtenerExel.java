/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import dao.GrupoDAO;
import dao.TutorDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.Grupo;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.Tutor;
import model.sql;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mine
 */
@WebServlet(name = "obtenerExel", urlPatterns = {"/obtenerExel"})
@MultipartConfig(maxFileSize = 16177215) 
public class obtenerExel extends HttpServlet {

    public TutorDAO tutordao;
    public obtenerExel() {
        super();
        tutordao = new TutorDAO();

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        PrintWriter out = response.getWriter();
        
        InputStream inFile = null;
        
        //Crea el archivo de destino
        System.err.println("esta es la ruta ...."+ getServletContext().getRealPath("/Documentos"));
        File destino = new File(getServletContext().getRealPath("/Documentos")+"/alumnos.xlsx");//ubicacion en el servidor
        OutputStream outFile = new FileOutputStream(destino);
        // probando obtener ruta absoluta
        
        try {
            //Obtiene el archivo del request
            Part filePart = request.getPart("archivosubido");
            if (filePart.getSize() > 0) {//Si el archivo es valido  hace lo siguiente
                System.out.println("nombre_" + filePart.getName());
                System.out.println("tamaño-" + filePart.getSize());
                System.out.println("tipo-" + filePart.getContentType());
                inFile = filePart.getInputStream();//asigna el file part al inputStream
            }
        } catch (Exception ex) {
            System.out.println("fichero: " + ex.getMessage());
        }
        
        
        //copia lo del inputStream al outputStream
         try {
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = inFile.read(buf)) > 0) {
                                outFile.write(buf, 0, len);
                        }

                        inFile.close();
                        outFile.close();
                } catch (IOException ioe){
                        ioe.printStackTrace();
                }

//        Liata para guardar error al insertar
        List<String> error = new ArrayList<String>();

        int grup = Integer.parseInt(request.getParameter("grupo"));

        String rutaArchivo = getServletContext().getRealPath("/Documentos")+"/alumnos.xlsx";
        String hoja = "Hoja1";

        Alumno alumno = new Alumno();
        Alumno alumno2 = new Alumno();
        Tutor tutor = new Tutor();
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        Grupo grupo = new Grupo();
        GrupoDAO grupodao = new GrupoDAO();
        try {
            grupo = grupodao.obtenerGrupoById(grup);
        } catch (SQLException ex) {
            Logger.getLogger(obtenerExel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FileInputStream file = new FileInputStream(new File(rutaArchivo));
            // leer archivo excel
            XSSFWorkbook worbook = new XSSFWorkbook(file);
            //obtener la hoja que se va leer
            XSSFSheet sheet = worbook.getSheetAt(0);
            //obtener todas las filas de la hoja excel

            Iterator<Row> rowIterator = sheet.iterator();
            //Obtener licenciatura

            Row row;
            //Quitamos la cabecera
            row = rowIterator.next();
            // se recorre cada fila hasta el final
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                //se obtiene las celdas por fila
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell;

                //Matricula
                if (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    if (cell.getCellType() == 0 ) {
                         //matricula
                        alumno.setMatricula(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        System.out.print(NumberToTextConverter.toText(cell.getNumericCellValue()) + " Esta es la matricula ");
                        //NOmbre\
                        cell = cellIterator.next();
                        alumno.setNombre(cell.getStringCellValue());
                        System.out.print(cell.getStringCellValue() + " nombre  ");

                        //Grupo
                        alumno.setIdGrupo(grupo.getIdGrupo());
                        System.out.print(grupo.getIdGrupo() + " grupo ");

                        //IdLicenciatura
                        alumno.setIdLicenciatura(grupo.getIdLicenciatura());
                        System.out.print(grupo.getIdLicenciatura() + "  ");
                        System.err.println("esta es la matricula " + alumno.getMatricula());
                        if (alumnoDAO.obtenerAlumnoByMatricula(alumno.getMatricula()) == null) {
                            alumnoDAO.insertar(alumno);
                        } else {
                            alumnoDAO.updateAlumno(alumno);
                            //Obtenemos el periodo actual
                            sql auto = new sql();
                            int idPeriodo = auto.auto_increm("SELECT MAX(idPeriodo) FROM tutoriasunsis.periodo") - 1;
                            // Codigo para mantener el tutorado de los alumnos
                            if (0 == tutordao.comprobarRegistro(idPeriodo, alumno.getMatricula())) {
//                                tutordao.update(tutor);
                            } else {
                                tutor=tutordao.tuturadoByMatricula(idPeriodo-1, alumno.getMatricula());
                                tutor.setGrupo(grupo.getGrupo());
                                tutor.setPeriodo(idPeriodo);
                                tutordao.insertar(tutor);
                            }
                        }
                        
                    }   else {
                       
                        error.add(", " + (row.getRowNum() + 1));
                    }
                }

            }
            String mensaje;

            if (error.isEmpty()) {
                mensaje = "Alumnos agregados de forma correcta... ";
            } else {
                mensaje = "Algunos alumnos fueron agregados, pero en los registros ";
                for (int i = 0; i <= error.size() - 1; i++) {
                    mensaje = mensaje + error.get(i) + " ";
                }
                mensaje = mensaje + " verifique que los datos sean correctos.";

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
                    + "text: \" " + mensaje + "\",\n"
                    + "type: \"success\",    \n"
                    + "confirmButtonColor: \"#DD6B55\",\n"
                    + "confirmButtonText: \"Aceptar\",\n"
                    + "closeOnConfirm: false,\n"
                    + "},\n"
                    + "\n"
                    + "function(isConfirm){\n"
                    + "if (isConfirm) {\n"
                    + "window.location='pages/loadAlumnos.jsp'   \n"
                    + "} \n"
                    + "});\n"
                    + "}\n"
                    + "EventoAlert();\n"
                    + "</script>"
                    + "</body>\n"
                    + "</html>");
        } catch (IOException e) {
            e.getMessage();
            System.err.println("Error" + e);
        } catch (SQLException ex) {
            Logger.getLogger(obtenerExel.class.getName()).log(Level.SEVERE, null, ex);
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
