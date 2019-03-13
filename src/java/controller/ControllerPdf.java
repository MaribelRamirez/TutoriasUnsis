package controller;

import dao.PdfDAO;
import VO.PdfVO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.sql;

@WebServlet(name = "ControllerPdf", urlPatterns = {"/ControllerPdf"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB

public class ControllerPdf extends HttpServlet {

    public static final String lIST = "/pages/cargarArchivos.jsp";
    public static final String INSERT_OR_EDIT = "/pages/altaPdf.jsp";

    String estado = null;
    PdfDAO pdfdao;
    int id_pdf = -1;

    public ControllerPdf() {
        pdfdao = new PdfDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String forward = "";
        String action = request.getParameter("action");
        String nombreArchivo = request.getParameter("nombre");
        if (action.equalsIgnoreCase("delete")) {
           try {
                String rutRel = getServletConfig().getServletContext().getRealPath("/resources/Archivos");
                System.err.println(rutRel + "/" + nombreArchivo);

                File archivo = new File(rutRel + "/" + nombreArchivo);
                archivo.delete();
            } catch (Exception e) {
                System.err.println("Error" + e);
            }
            
            response.sendRedirect("pages/cargarArchivos.jsp");
        }
        if (action.equalsIgnoreCase("edit")) {
            
            
        } else if (action.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
            estado = "insert";
            RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("f1t1");
                        System.err.println("Este es el nombre"+name);
        InputStream inputStream = null;
       
        
         try {
            Part filePart = request.getPart("fichero");
            
            if (filePart.getSize() > 0) {
                inputStream = filePart.getInputStream();
            }
        } catch (Exception ex) {
            System.out.println("fichero: " + ex.getMessage());
        }
          File destino = new File(getServletContext().getRealPath("/resources/Archivos")+"/" +name);//ubicacion en el servidor
        OutputStream outFile = new FileOutputStream(destino);
         try {
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outFile.write(buf, 0, len);
            }

            inputStream.close();
            outFile.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
         
        } catch (Exception ex) {
            System.out.println("nombre: " + ex.getMessage());
        }
        
     
        
        response.sendRedirect("pages/cargarArchivos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
