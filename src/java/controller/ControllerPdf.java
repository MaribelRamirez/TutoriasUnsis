package controller;

import dao.PdfDAO;
import VO.PdfVO;
import java.io.IOException;
import java.io.InputStream;
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
        if (action.equalsIgnoreCase("delete")) {
            int studentId = Integer.parseInt(request.getParameter("id"));
            pdfdao.Eliminar_PdfVO(studentId);
            
            response.sendRedirect("pages/cargarArchivos.jsp");
        }
        if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int studentIdM = Integer.parseInt(request.getParameter("id"));
            id_pdf = studentIdM;
            PdfVO pdfvo = pdfdao.getPdfVOById(studentIdM);
            request.setAttribute("row", pdfvo);
            boolean boo = false;
            if (pdfvo.getArchivopdf2() != null) {
                boo = true;
            }
            request.setAttribute("row2", boo);
            estado = "edit";
            RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
            
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

        PdfVO pdfvo = new PdfVO();
        sql auto = new sql();
        int nuevoid = auto.auto_increm("SELECT MAX(idArchivo) FROM archivos;");
        try {
            String name = request.getParameter("txtname");
            String tipo = request.getParameter("tipo");
            pdfvo.setNombrepdf(name);
            pdfvo.setCategoria(tipo);
        } catch (Exception ex) {
            System.out.println("nombre: " + ex.getMessage());
        }
        InputStream inputStream = null;
        try {
            Part filePart = request.getPart("fichero");
            if (filePart.getSize() > 0) {
                inputStream = filePart.getInputStream();
            }
        } catch (Exception ex) {
            System.out.println("fichero: " + ex.getMessage());
        }
        try {

            if (estado.equalsIgnoreCase("insert")) {

                pdfvo.setCodigopdf(nuevoid);

                if (inputStream != null) {

                    pdfvo.setArchivopdf(inputStream);
                }
                pdfdao.Agregar_PdfVO(pdfvo);
            } else {
                pdfvo.setCodigopdf(id_pdf);
                if (inputStream != null) {
                    pdfvo.setArchivopdf(inputStream);
                    pdfdao.Modificar_PdfVO(pdfvo);
                } else {
                    pdfdao.Modificar_PdfVO2(pdfvo);
                }
            }
        } catch (Exception ex) {
            System.out.println("textos: " + ex.getMessage());
        }
        response.sendRedirect("pages/cargarArchivos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
