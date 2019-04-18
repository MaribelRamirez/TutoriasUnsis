<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


<%@page import="model.sql"%>
<%@page import="model.Tutor"%>
<%@page import="dao.TutorDAO"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="model.Alumno"%>
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
                        <a href="indexAdmin.jsp">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Tutorados</span><br>
                    </h2>
                </div>
                <div class="blank">

                    <div class="blank-page">
                        <%
                            sql auto = new sql();
                            int perA = auto.auto_increm("SELECT MAX(idPeriodo) FROM tutoriasunsis.periodo") - 1;
                            TutorDAO obj_Read_Values = new TutorDAO();
                            List<Tutor> list = obj_Read_Values.listarTutorados(perA);
                            Iterator<Tutor> it_list = list.iterator();
                        %>
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Matricula</th>
                                    <th>Alumno</th>
                                    <th>Grupo</th>
                                    <th>Programa</th>
                                    <th>Tutor</th>
                                    <th>Tipo de tutoria</th>
                                </tr>
                            </thead>

                            <tbody>
                                <%
                                    while (it_list.hasNext()) {
                                        Tutor ob = new Tutor();
                                        ob = it_list.next();
                                %>  
                                <tr>
                                    <td><%=ob.getMatricula()%></td>
                                    <td><%=ob.getAlumno()%></td>
                                    <td><%=ob.getGrupo()%></td>
                                    <td><%=ob.getPrg() %></td>
                                    <td><%=ob.getProfesor()%></td>
                                    <% if (ob.getTipo() == 1) { %>
                                    <td>Individual</td>
                                    <% } else { %>
                                    <td>Grupal</td>
                                    <% } %>


                                </tr>
                                <%
                                    }

                                    List<Tutor> listST = obj_Read_Values.listarAlumnosSinTutor(perA);
                                    Iterator<Tutor> it_listST = listST.iterator();
                                    while (it_listST.hasNext()) {
                                        Tutor ob = new Tutor();
                                        ob = it_listST.next();
                                %>     
                                <tr>
                                    <td><%=ob.getMatricula()%></td>
                                    <td><%=ob.getAlumno()%></td>
                                    <td><%=ob.getGrupo()%></td>
                                    <td><%=ob.getPrg() %></td>
                                    <td></td>
                                    <td>
                                        <form id="formulario" action="../ControllerTutores" method="post" onsubmit="return confirm('¿Realmente desea agregar tutor?')">
                                            <input type="hidden" name = "id" id="id" value="<%=ob.getMatricula()%>">
                                            <input type="hidden" name = "action" id="action" value="addTI">
                                            <input type="hidden" name = "ruta" id="ruta" value="listTu">
                                            <button type="submit"  class="btn btn-link">Agregar tutor</button>
                                        </form>
                                    </td>
                                    <td></td>
                                </tr>
                                <% }%>
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

