<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


<%@page import="model.Periodo"%>
<%@page import="dao.PeriodoDAO"%>
<%@page import="model.Profesor"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProfesorDAO"%>
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


        <jsp:include page="headAdmin.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">

                <!--banner-->	
                <div class="banner">
                    <h2>
                        <a href="indexAdmin.jsp">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Documentos</span><br>
                    </h2>
                </div>


                <div class="blank">


                    <div class="blank-page">






                        <nav class="nav-sidebar">
                            <ul class="nav tabs">

                                <li class="active">

                                    <form id="formulario" action="constanciaGrupal.jsp" method="post">

                                        <button type="submit"  class=" fa fa-file-o btn btn-link">  Constancia grupal</button>
                                    </form>
                                </li>
                                <li class="active">

                                    <form id="formulario" action="constanciaIndividual.jsp" method="post">

                                        <button type="submit"  class=" fa fa-file-o btn btn-link"> Constancia individual</button>
                                    </form>
                                </li>
                                <li class="active">
                                    <button type="button"  class=" fa fa-file-o btn btn-link" data-toggle="modal"  data-target="#exampleModal">Reportes de Tutorias</button>


                                    <form id="formulario" action="../ControllerReportesTutorias" method="post">
                                        <input type="hidden" name = "action" value="reporte">
                                        <!-- Modal --> <div class="modal" id="exampleModal" tabindex="-1" role="dialog"
                                                            arialabelledby="exampleModalLabel" aria-hidden="true">   
                                            <div class="modal-dialog" role="document">     
                                                <div class="modal-content">      
                                                    <div class="modal-header">     
                                                        <h5 class="modal-title" id="exampleModalLabel">Selecciona el periodo del que 
                                                            deseas generar el reporte</h5>      

                                                    </div>      
                                                    <div class="modal-body">     


                                                        <%
                                                            PeriodoDAO obj_Read_Values = new PeriodoDAO();
                                                            List<Periodo> list = obj_Read_Values.listarPeriodos();
                                                            Iterator<Periodo> it_list = list.iterator();

                                                        %>

                                                        <div class = "form-group">
                                                            <label>Periodos</label>	    

                                                            <select class="form-control " id="IdPeriodo" name="IdPeriodo">
                                                                <option value="todos">Todos</option>
                                                                <%                                                                    while (it_list.hasNext()) {
                                                                        Periodo ob = new Periodo();
                                                                        ob = it_list.next();
                                                                %>
                                                                <option value="<%= ob.getIdPeriodo()%>"> <%=ob.getPeriodo()%></option>

                                                                <% }

                                                                %>   
                                                            </select>
                                                        </div>


                                                    </div>     
                                                    <div class="modal-footer"> 


                                                        <button type="submit" class="btn btn-primary">Aceptar</button> 



                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>   



                                                    </div>   
                                                </div>   
                                            </div>
                                        </div> 

                                    </form>

                                </li>


                                <li class="active">
                                    <form id="formulario" action="../ControllerReporteAsignacionTutorias" method="post">

                                        <button type="submit"  class=" fa fa-file-o btn btn-link">Asignación de tutorias</button>
                                    </form>
                                </li>
                                <li class="active">
                                    <form id="formulario" action="../ControllerConcentradoAsignaciones" method="post">

                                        <button type="submit"  class=" fa fa-file-o btn btn-link">Concentrado de asignaciones</button>
                                    </form>
                                </li>
                            </ul>
                        </nav>
                                     
                    </div>
                </div>

            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="copy">
            <p><img src="../resources/images/escudo.png" width="70" height="70"> Universidad de la Sierra Sur  </p>          
        </div>


        <!---->
        <!--scrolling js-->
        <script src="js/jquery.nicescroll.js"></script>
        <script src="js/scripts.js"></script>
        <!--//scrolling js-->


    </body>
</html>

