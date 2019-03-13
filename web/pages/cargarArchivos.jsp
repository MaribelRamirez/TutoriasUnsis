<%@page import="VO.PdfVO"%>
<%@page import="dao.PdfDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
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
                <div class="banner">
                    <h2>
                        <a href="index.html">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Material de apoyo</span><br>
                    </h2>
                </div>
                <div class="blank">
                    <div class="blank-page">


                        <a id="mostrar" href="../ControllerPdf?action=insert&id=">Agregar archivo<img src="../resources/images/nuevo.png" title="Nuevo registro"/></a><br>
                        <nav class="nav-sidebar">
                            <table id="example" class="table table-striped table-bordered" style="width:100%">
                                <thead>
                                    <tr>
                                        <th>Archivo</th>
                                        <th>Abrir</th>
                                        <th>Eliminar</th>
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
                                          
                                            <a href="../resources/Archivos/<%=ficheros[i]%>" target="_blank"><img src="../resources/images/doc.png" title="pdf" class="center-block"l/></a>
                                                
                                        </td>
                                        <td >
                                            <form id="formularioElim" name="formularioElim" action="../ControllerPdf" method="get" onsubmit="return confirm('¿Realmente desea eliminar el documento?')" >
                                                <input type="hidden" name = "nombre" id="id" value="<%=ficheros[i]%>">
                                                <input type="hidden" name = "action" id="action" value="delete">
                                                <button type="submit"  class="btn btn-link center-block"><img src="../resources/images/delete.jpeg" title="Eliminar" height="40" /></button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>

                        </nav>
                        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                    </div>
                </div>

            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="copy">
            <p><img src="../resources/images/escudo.jpg" width="70" height="70"> Universidad de la Sierra Sur  </p>          
        </div>
        <script src="js/jquery.nicescroll.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>


