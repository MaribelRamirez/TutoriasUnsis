<%-- 
    Document   : reportes
    Created on : 09-ago-2018, 21:23:41
    Author     : Marifer
--%>
<%@page import="java.io.File"%>
<%@page import="VO.PdfVO"%>
<%@page import="dao.PdfDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Tutorias universitarias</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Minimal Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <link href="../resources/css/bootstrap.min.css" rel='stylesheet' type='text/css' />

        <!-- tabla de pdf -->
        <link href="../resources/css/stylePDF.css" rel='stylesheet' type='text/css' />
        <!-- Custom Theme files -->
        <link href="../resources/css/style.css" rel='stylesheet' type='text/css' />
        <link href="../resources/css/font-awesome.css" rel="stylesheet"> 
        <script src="../resources/js/jquery.min.js"></script>
        <script src="../resources/js/bootstrap.min.js"></script>

        <!-- Mainly scripts -->
        <script src="../resources/js/jquery.metisMenu.js"></script>
        <script src="../resources/js/jquery.slimscroll.min.js"></script>
        <!-- Custom and plugin javascript -->
        <link href="../resources/css/custom.css" rel="stylesheet">
        <script src="../resources/js/custom.js"></script>
        <script src="../resources/js/screenfull.js"></script>
        <script>
            $(function () {
                $('#supported').text('Supported/allowed: ' + !!screenfull.enabled);

                if (!screenfull.enabled) {
                    return false;
                }



                $('#toggle').click(function () {
                    screenfull.toggle($('#container')[0]);
                });



            });
        </script>



    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            String usuario;
            String nivel;

            if (sesion.getAttribute("user") != null && sesion.getAttribute("nivel") != null) {
                usuario = sesion.getAttribute("user").toString();
                nivel = sesion.getAttribute("nivel").toString();
            } else {
                out.print("<script>location.replace('/TutoriasUnsis');</script>");
            }
        %>
        <jsp:include page="headProfesor.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">

                <div class="banner">
                    <h2>
                        <a href="index.html">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Material de apoyo</span><br>
                    </h2>
                </div>
                <div class="blank">


                    <div class="blank-page">
                        <%
                            PdfDAO emp = new PdfDAO();
                            PdfVO pdfvo = new PdfVO();
                            ArrayList<PdfVO> listar = emp.Listar_PdfVOMaterial();
                        %>

                        <div class="datagrid">
                            <table>
                                <thead>
                                    <tr>
                                        
                                        <th>Archivo</th>
                                        <th>Abrir</th>
                                    </tr>
                                </thead>
                              
                                <tbody>
                                    <%
                                        String rutRel = getServletConfig().getServletContext().getRealPath("/resources/Archivos");
                                        String path = rutRel + "/";
                                        File directorio = new File(path);
                                        String[] ficheros = directorio.list();
                                        for (int i = 0; i < ficheros.length; i++) {
                                    %>
                                    <tr>
                                        <td><%=ficheros[i]%></td>
                                        <td>
                                          
                                          
                                            <a href="../resources/Archivos/<%=ficheros[i]%>" target="_blank"><img src="../resources/images/doc.png" title="Abrir" height="50" class="center-block"/></a>
                                                
                                        
                                        </td>
                                        
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                    </div>
                </div>

               
            </div>
            <div class="clearfix"> </div>
        </div>
         <div class="copy">
       <img src="../resources/images/escudo.jpg" width="70" height="70"> <p> Universidad de la Sierra Sur  </p>          
    </div>
        <script src="js/jquery.nicescroll.js"></script>
        <script src="js/scripts.js"></script>
     
    </body>
</html>


