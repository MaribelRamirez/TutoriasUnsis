<%-- 
    Document   : reportes
    Created on : 09-ago-2018, 21:23:41
    Author     : Marifer
--%>
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
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>

        <style>

            body  {
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
                color: black;
            }
            table {
                font-family: initial ;
            }

            th{

                background-color: #A4E7A5;
            }

            tbody tr:hover {
                background-color: #FFFF99;
                cursor: default;

            }
            tbody td {
                border-bottom: 1px solid #ddd;
            }
            textarea {
                resize: none;
            }
        </style>

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
        <jsp:include page="headAdmin.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">

                <!--banner-->	
                <div class="banner">
                    <h2>
                        <a href="index.html">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Material de apoyo</span><br>
                    </h2>
                </div>
                <!--//banner-->
                <!--faq-->
                <div class="blank">

                    
                    
                    
                    
                    <div class="blank-page">
                        <%
                            PdfDAO emp = new PdfDAO();
                            PdfVO pdfvo = new PdfVO();
                            ArrayList<PdfVO> listar = emp.Listar_PdfVO();
                        %>

                        <a id="mostrar" href="../ControllerPdf?action=insert&id=">Agregar archivo<img src="../resources/images/nuevo.png" title="Nuevo registro"/></a><br>
                        <div class="datagrid">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Pdf</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <!--                                <tfoot>
                                                                    <tr>
                                                                        <td colspan="4">
                                                                            <div id="paging"><ul><li><a href="#"><span>Previous</span></a></li><li><a href="#" class="active"><span>1</span></a></li><li><a href="#"><span>2</span></a></li><li><a href="#"><span>3</span></a></li><li><a href="#"><span>4</span></a></li><li><a href="#"><span>5</span></a></li><li><a href="#"><span>Next</span></a></li></ul>
                                                                            </div>
                                                                    </tr>
                                                                </tfoot>-->
                                <tbody>
                                    <%if (listar.size() > 0) {
                                            for (PdfVO listar2 : listar) {
                                                pdfvo = listar2;
                                    %>
                                    <tr>
                                        <td><%=pdfvo.getCodigopdf()%></td>
                                        <td><%=pdfvo.getNombrepdf()%></td>
                                        <td>
                                            <%
                                                if (pdfvo.getArchivopdf2() != null) {
                                            %>
                                            <a href="../pdf?id=<%=pdfvo.getCodigopdf()%>" target="_blank"><img src="../resources/images/mpdf.png" title="pdf" class="center-block"l/></a>
                                                <%
                                                    } else {
                                                        out.print("Vacio");
                                                    }
                                                %>
                                        </td>
                                        <td>
                                            <a id="mostrar" href="../ControllerPdf?action=insert&id=<%=pdfvo.getCodigopdf()%>"> <img src="../resources/images/nuevo.png" title="Nuevo registro" class="center-block"/></a>
                                            <a href="../ControllerPdf?action=delete&id=<%=pdfvo.getCodigopdf()%>"> <img src="../resources/images/delete.jpeg" title="Eliminar" height="40" class="center-block"/></a>
                                        </td>
                                    </tr>
                                    <%}
                                        }%>
                                </tbody>
                            </table>
                        </div>
                        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                    </div>
                </div>

                <!--//faq-->
                <!---->

            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="copy">
            <p><img src="../resources/images/escudo.jpg" width="70" height="70"> Universidad de la Sierra Sur  </p>          
        </div>
        <!---->
        <!--scrolling js-->
        <script src="js/jquery.nicescroll.js"></script>
        <script src="js/scripts.js"></script>
        <!--//scrolling js-->
    </body>
</html>


