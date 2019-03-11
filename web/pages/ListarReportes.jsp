<%-- 
    Document   : ListarReportes
    Created on : 17-nov-2018, 21:52:54
    Author     : Marifer
--%>
<%@page import="model.Profesor"%>
<%@page import="dao.ProfesorDAO"%>
<%@page import="model.Reporte"%>
<%@page import="dao.ReporteDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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

        <script src="../resources/tablas/js/jquery-3.3.1.js"></script>  
        <script src="../resources/tablas/js/jquery.dataTables.min.js"></script>
        <script src="../resources/tablas/js/dataTables.bootstrap.min.js"></script> 

        <link href="../resources/tablas/css/dataTables.bootstrap.min.css" rel='stylesheet' type='text/css' />

        <script src="../resources/alert/sweetalert.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../resources/alert/sweetalert.css">
        <link rel="stylesheet" type="text/css" href="../resources/alert/google.css">

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


            function Actualizar() {
                swal({
                    title: "aviso!!",
                    text: "¿En verdad deseas actualizar el reporte?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "SI",
                    cancelButtonText: "NO",
                    closeOnConfirm: false,
                    closeOnCancel: false},
                        function (isConfirm) {
                            if (isConfirm) {
                                document.getElementById('formularioAct').submit();
                            } else {
                                window.location = 'ListarReportes.jsp';
                            }
                        });
            }
            ;


            function Eliminar() {
                swal({
                    title: "aviso!!",
                    text: "¿En verdad deseas eliminar el reporte?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "SI",
                    cancelButtonText: "NO",
                    closeOnConfirm: false,
                    closeOnCancel: false},
                        function (isConfirm) {
                            if (isConfirm) {
                                document.getElementById('formularioElim').submit();
                            } else {
                                window.location = 'ListarReportes.jsp';
                            }
                        });
            }
            ;
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

            } else {
                out.print("<script>location.replace('/TutoriasUnsis');</script>");
            }
        %>

        <jsp:include page="headAdmin.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">
                <div class="banner">
                    <h2>
                        <a href="indexAdmin.jsp">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Reportes</span><br>
                    </h2>
                </div>
                <div class="blank">
                    <div class="blank-page">
                        <a data-toggle="modal" data-target="#exampleModal">
                            <img src="../resources/images/add.png" title="Agregar"/> Agregar nuevo reporte</a>
                        <form id="formulario" action="../ControllerReportesTutorias" method="post">
                            <input type="hidden" name = "action" value="cargar">
                            <!-- Modal --> <div class="modal" id="exampleModal" tabindex="-1" role="dialog"
                                                arialabelledby="exampleModalLabel" aria-hidden="true">   
                                <div class="modal-dialog" role="document">     
                                    <div class="modal-content">      
                                        <div class="modal-header">     
                                            <h5 class="modal-title" id="exampleModalLabel">Selecciona el nombre del profesor del que 
                                                deseas guardar los datos de sus tutorias</h5>      
                                        </div>      
                                        <div class="modal-body">     
                                            <%
                                                ProfesorDAO obj_Read_Values2 = new ProfesorDAO();
                                                List<Profesor> list2 = obj_Read_Values2.listarProfesoresActivos();
                                                Iterator<Profesor> it_list2 = list2.iterator();

                                            %>

                                            <div class = "form-group">
                                                <label>Profesores</label>	    

                                                <select class="form-control " id="prof" name="prof">
                                                    <%                                        while (it_list2.hasNext()) {
                                                            Profesor ob = new Profesor();
                                                            ob = it_list2.next();
                                                    %>
                                                    <option value="<%= ob.getCurp()%>"> <%=ob.getNombre()%></option>\n\
                                                    <% }

                                                    %>   
                                                </select>
                                            </div>


                                        </div>     
                                        <div class="modal-footer">    
                                            <div class="col-lg-6">
                                                <button type="submit" class="btn btn-primary">Aceptar</button> 
                                            </div>

                                            <div class="col-lg-6">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>   
                                            </div>
                                        </div>   
                                    </div>   
                                </div>
                            </div> 

                        </form>
                        <table id="example" class="table table-striped table-bordered" style="width:100%">



                            <thead>
                                <tr>
                                    <th>Profesor</th>
                                    <th>periodo</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>

                                </tr>
                            </thead>

                            <tbody>
                                <%                                    ReporteDAO obj_Read_Values = new ReporteDAO();
                                    List<Reporte> list = obj_Read_Values.listarReportes();
                                    Iterator<Reporte> it_list = list.iterator();
                                %>

                                <%
                                    while (it_list.hasNext()) {
                                        Reporte ob = new Reporte();
                                        ob = it_list.next();
                                %>  
                                <tr>
                                    <td><%=ob.getProfesor()%></td>
                                    <td><%=ob.getPeriodo()%></td>
                                    <td>
                                        <form id="formularioAct" name="formularioAct" action="../ControllerReportesTutorias" method="post" onsubmit="return confirm('¿Realmente desea actualizar los datos?')">
                                            <input type="hidden" name = "id" id="id" value="<%=ob.getIdReporte()%>">
                                            <input type="hidden" name = "action" id="action" value="update">
                                            <button type="submit"  class="btn btn-link center-block">Actualizar</button>
                                        </form>                                          

                                    </td> 
                                    <td >
                                        <form id="formularioElim" name="formularioElim"  action="../ControllerReportesTutorias" method="post" onsubmit="return confirm('¿Realmente desea eliminar los datos?')">
                                            <input type="hidden" name = "id" id="id" value="<%=ob.getIdReporte()%>">
                                            <input type="hidden" name = "action" id="action" value="delete">
                                            <button type="submit"  class="btn btn-link center-block"><img src="../resources/images/delete.jpeg" title="Eliminar" height="40"></button>
                                        </form>                                           

                                    </td>

                                </tr>
                                <%
                                    }
                                %>     


                            </tbody>
                        </table>
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

