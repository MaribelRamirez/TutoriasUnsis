<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


<%@page import="model.sql"%>
<%@page import="model.Grupo"%>
<%@page import="dao.GrupoDAO"%>
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
                    text: "¿En verdad deseas actualizar al grupo?",
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
                                window.location = 'ListarGrupos.jsp';
                            }
                        });
            }
            ;


            function Eliminar() {
                swal({
                    title: "aviso!!",
                    text: "¿En verdad deseas eliminar al grupo?",
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
                                window.location = 'ListarGrupos.jsp';
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

            sql auto = new sql();
            int perA = auto.auto_increm("SELECT MAX(idPeriodo) FROM tutoriasunsis.periodo") - 1;
        %>

        <jsp:include page="headAdmin.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">
                <div class="banner">
                    <h2>
                        <a href="indexAdmin.jsp">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Grupos</span><br>
                    </h2>
                </div>
                <div class="blank">

                    <div class="blank-page">
                        <%
                            GrupoDAO obj_Read_Values = new GrupoDAO();
                            List<Grupo> list = obj_Read_Values.listarGrupos();
                            Iterator<Grupo> it_list = list.iterator();
                        %>
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            <a href="agregarGrupo.jsp">
                                <img src="../resources/images/add.png" title="Agregar"/> Agregar nuevo grupo</a>
                            <thead>

                                <tr>

                                    <th>Grupo</th>
                                    <th>Periodo</th>
                                    <th>Programa</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>
                                    <th>Agregar Tutor</th>

                                </tr>
                            </thead>

                            <tbody>
                                <%
                                    while (it_list.hasNext()) {
                                        Grupo ob = new Grupo();
                                        ob = it_list.next();
                                %>  
                                <tr>
                                    <td><%=ob.getGrupo()%></td>
                                    <td><%=ob.getPeriodo()%></td>
                                    <td><%=ob.getPrograma() %></td>
                                    <td>
                                        <form id="formularioAct" name="formularioAct" action="../ControllerGrupo" method="post">
                                            <input type="hidden" name = "id" id="id" value="<%=ob.getIdGrupo()%>">
                                            <input type="hidden" name = "action" id="action" value="update">
                                            <button type="submit"  class="btn btn-link center-block">Actualizar</button>
                                        </form>


                                    </td>
                                    <td >
                                        <form id="formularioElim" name="formularioElim" action="../ControllerGrupo" method="post" onsubmit="return confirm('¿Realmente desea eliminar los datos?')" >
                                            <input type="hidden" name = "id" id="id" value="<%=ob.getIdGrupo()%>">
                                            <input type="hidden" name = "action" id="action" value="delete">
                                            <button type="submit"  class="btn btn-link center-block"><img src="../resources/images/delete.jpeg" title="Eliminar" height="40"></button>
                                        </form>


                                    </td>
                                    <td >
                                        <% if (perA == ob.getIdPeriodo()) {%>  
                                        <form id="formulario" action="../ControllerTutores" method="post">
                                            <input type="hidden" name = "id" id="id" value="<%=ob.getIdGrupo()%>">
                                            <input type="hidden" name = "action" id="action" value="addTG">
                                            <button type="submit"  class="btn btn-link center-block">Agregar Tutor</button>
                                        </form>
                                    </td>

                                </tr>
                                <%
                                        }
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
       <img src="../resources/images/escudo.jpg" width="70" height="70"> <p> Universidad de la Sierra Sur  </p>          
    </div>
        <script src="js/jquery.nicescroll.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>

