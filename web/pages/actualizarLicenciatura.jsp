<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Tutorias universitarias</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Minimal Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <link href="resources/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
        <!-- Custom Theme files -->
        <link href="resources/css/style.css" rel='stylesheet' type='text/css' />
        <link href="resources/css/font-awesome.css" rel="stylesheet"> 
        <script src="resources/js/jquery.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <!-- Mainly scripts -->
        <script src="resources/js/jquery.metisMenu.js"></script>
        <script src="resources/js/jquery.slimscroll.min.js"></script>
        <!-- Custom and plugin javascript -->
        <link href="resources/css/custom.css" rel="stylesheet">
        <script src="resources/js/custom.js"></script>
        <script src="resources/js/screenfull.js"></script>

        <script src="resources/tablas/js/jquery-3.3.1.js"></script>  
        <script src="resources/tablas/js/jquery.dataTables.min.js"></script>
        <script src="resources/tablas/js/dataTables.bootstrap.min.js"></script> 

        <link href="resources/tablas/css/dataTables.bootstrap.min.css" rel='stylesheet' type='text/css' />

        <script src="resources/alert/sweetalert.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/alert/sweetalert.css">
        <link rel="stylesheet" type="text/css" href="resources/alert/google.css">

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

            function Guardar() {
                swal({
                    title: "aviso!!",
                    text: "¿En verdad deseas guardar los datos?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "SI",
                    cancelButtonText: "NO",
                    closeOnConfirm: false,
                    closeOnCancel: false},
                        function (isConfirm) {
                            if (isConfirm) {
                                document.getElementById('formularioG').submit();
                            } else {
                                window.location = 'pages/ListarLicenciaturas.jsp';
                            }
                        });
            };
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
                out.print("<a href='login.jsp?cerrar=true'><h5>cerrar Sesion" + usuario + "</h5>");

            } else {
                out.print("<script>location.replace('login.jsp');</script>");
            }
        %>

        <jsp:include page="headAdminUpdate.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">
                <div class="banner">
                    <h2>
                        <a href="indexAdmin.jsp">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Agregar licenciatura</span><br>
                    </h2>
                </div>

                <div class="blank">

                    <div class="blank-page col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="grid-form1 col-xs-12 col-sm-12 col-md-12 col-lg-12">

                            <form id="formularioG" name="formularioG" action="ControllerLicenciatura" method="post" >
                                <input type="hidden" name = "action" value="edit">
                                <input type="hidden" name = "id"  value="<c:out value="${lic.getIdLicenciatura()}"/>"/> 

                                <h3 id="forms-example" class="">Datos de la licenciatura</h3>

                                <div class="form-group">
                                    <label for="nomLicc">Nombre de la licenciatura</label>
                                    <input  required class="form-control" id="nombreLic" name="nombreLic"  value="<c:out value="${lic.getNombre()}"/>"/>
                                </div>  
                            </form>
                            <button type="button" onclick="Guardar()" class="bl btn btn-danger pull-right">Guardar</button>
                        </div> </div> </div>                   
            </div>
        </div>

    </div>
    <div class="clearfix"> </div>
</div>
<div class="copy">
    <p><img src="resources/images/escudo.jpg" width="70" height="70"> Universidad de la Sierra Sur  </p>          
</div>
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>

