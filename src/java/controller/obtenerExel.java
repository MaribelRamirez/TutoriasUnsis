/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlumnoDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mine
 */
@WebServlet(name = "obtenerExel", urlPatterns = {"/obtenerExel"})
public class obtenerExel extends HttpServlet {

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
        String nombreArchivo = "alumnos2018.xlsx";
		String rutaArchivo = "C:\\Users\\Marifer\\Documents\\NetBeansProjects\\TutoriasUnsis\\bd\\" + nombreArchivo;
		String hoja = "Hoja1";
                System.err.println("antes del try");
                
                Alumno alumno = new Alumno();
                AlumnoDAO alumnoDAO = new AlumnoDAO();
                
		try  {
                        System.err.println("en el try");
                        FileInputStream file = new FileInputStream(new File(rutaArchivo));
                        System.err.println("Antes de leer el archivo");
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
                        System.err.println("despues de leer el archivo");
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
                        System.err.println("despues de obtener la hoja");
			//obtener todas las filas de la hoja excel
                        
			Iterator<Row> rowIterator = sheet.iterator();

			Row row;
                        //Quitamos la cabecera
                        row = rowIterator.next();
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
//				while (cellIterator.hasNext()) {
//					// se obtiene la celda en espec�fico y se la imprime
//					cell = cellIterator.next();
//					System.out.print(cell.getStringCellValue()+"  ");
//				}
                                //Matricula
                                cell = cellIterator.next();
                                alumno.setMatricula((int) cell.getNumericCellValue());
                                System.out.print(cell.getStringCellValue()+"  ");
                                //NOmbre
                                cell = cellIterator.next();
                                alumno.setNombre(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue()+"  ");
                                
                                //Grupo
                                cell = cellIterator.next();
                                alumno.setGrupo( cell.getStringCellValue());
//                                System.out.print(cell.getNumericCellValue()+"  ");
                                //IdLicenciatura
                                cell = cellIterator.next();
                                alumno.setIdLicenciatura((int)cell.getNumericCellValue());
//                                System.out.print(cell.getNumericCellValue()+"  ");
                                
                                
                                alumnoDAO.insertar(alumno);
                                
                                
                                
				System.out.println();
			}
		} catch (IOException e) {
			e.getMessage();
                        System.err.println("Error"+e);
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
