<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


<%@page import="model.Licenciatura"%>
<%@page import="dao.LicenciaturaDAO"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
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
                out.print("<script>location.replace('../login.jsp');</script>");
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
                        <span>Documentos</span><br>
                    </h2>
                </div>
                <div class="blank">
                    <div class="blank-page col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <h3>Generar Documentos</h3>
                        <nav class="nav-sidebar">
                            <ul class="nav tabs">
                                <li class="active">
                                    <form id="formulario" action="constanciaGrupal.jsp" method="post">
                                        <button type="submit"  class=" fa fa-file-o btn btn-link">Constancia grupal</button>
                                    </form>
                                </li>
                                <li class="active">
                                    <form id="formulario" action="constanciaIndividual.jsp" method="post">
                                        <button type="submit"  class=" fa fa-file-o btn btn-link">Constancia individual</button>
                                    </form>
                                </li>

                                       <li class="active">
                                    <button type="button"  class=" fa fa-file-o btn btn-link" data-toggle="modal"  data-target="#ModalAsigTuto">Asignación de tutorias</button>
                                    <form id="formulario" action="../ControllerReporteAsignacionTutorias" method="post">
                                        <input type="hidden" name = "action" value="reporte">
                                        <!-- Modal --> <div class="modal" id="ModalAsigTuto" tabindex="-1" role="dialog"
                                                            arialabelledby="exampleModalLabel" aria-hidden="true">   
                                            <div class="modal-dialog" role="document">     
                                                <div class="modal-content">      
                                                    <div class="modal-header">     
                                                        <h5 class="modal-title" id="exampleModalLabel">Selecciona la carrera y el periodo del que 
                                                            deseas generar el reporte</h5> 
                                                    </div>      
                                                    <div class="modal-body">     
                                                        <%                                                            
                                                            PeriodoDAO obj_Read_Values2 = new PeriodoDAO();
                                                            List<Periodo> list2 = obj_Read_Values2.listarPeriodos();
                                                            Iterator<Periodo> it_list2 = list2.iterator();
                                                          
                                                            LicenciaturaDAO obj_Read_lic = new LicenciaturaDAO();
                                                            List<Licenciatura> list_lic = obj_Read_lic.listarLicenciaturas();
                                                            Iterator<Licenciatura> it_list_lic = list_lic.iterator();

                                                        %>
                                                           <div class = "form-group">
                                                            <label>Periodos</label>	    

                                                            <select class="form-control " id="IdPeriodo" name="IdPeriodo">

                                                                <%                                                             
                                                                    while (it_list2.hasNext()) {
                                                                        Periodo ob = new Periodo();
                                                                        ob = it_list2.next();
                                                                %>
                                                                <option value="<%= ob.getIdPeriodo()%>"> <%=ob.getPeriodo()%></option>

                                                                <% }

                                                                %>   
                                                            </select>
                                                        </div>
                                                        <div class = "form-group">
                                                            <label>Licenciaturas</label>	
                                                            <select class="form-control " id="lic" name="lic">

                                                                <%                                                                   
                                                                    while (it_list_lic.hasNext())
                                                                    {
                                                                        Licenciatura ob_lic = new Licenciatura();
                                                                        ob_lic = it_list_lic.next();
                                                                %>
                                                                <option value="<%= ob_lic.getNombre() %>"> <%=ob_lic.getNombre()%></option>

                                                                <% }

                                                                %>   
                                                                <option value="todas"> Todas</option>
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
                                    <button type="button"  class=" fa fa-file-o btn btn-link" data-toggle="modal"  data-target="#exampleModal">Reportes de tutorias entregados</button>

                                    <form id="formulario" action="../ControllerReportesTutorias" method="post">
                                        <input type="hidden" name = "action" value="reporte">
                                                <div class="modal" id="exampleModal" tabindex="-1" role="dialog" arialabelledby="exampleModalLabel" aria-hidden="true">   
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
                                    <button type="button"  class=" fa fa-file-o btn btn-link" data-toggle="modal"  data-target="#ModalConAsig">Concentrado de asignaciones</button>
                                    <form id="formulario" action="../ControllerConcentradoAsignaciones" method="post">
                                        <input type="hidden" name = "action" value="reporte">
                                          <div class="modal" id="ModalConAsig" tabindex="-1" role="dialog"
                                                            arialabelledby="exampleModalLabel" aria-hidden="true">   
                                            <div class="modal-dialog" role="document">     
                                                <div class="modal-content">      
                                                    <div class="modal-header">     
                                                        <h5 class="modal-title" id="exampleModalLabel">Selecciona el periodo del que deseas generar el reporte</h5> 
                                                    </div>      
                                                    <div class="modal-body">     
                                                        <%                                                           
                                                            PeriodoDAO obj_Read_Values3 = new PeriodoDAO();
                                                            List<Periodo> list3 = obj_Read_Values3.listarPeriodos();
                                                            Iterator<Periodo> it_list3 = list3.iterator();

                                                        %>

                                                        <div class = "form-group">
                                                            <label>Periodos</label>	    

                                                            <select class="form-control " id="IdPeriodo" name="IdPeriodo">

                                                                <%                                                                    while (it_list3.hasNext()) {
                                                                        Periodo ob = new Periodo();
                                                                        ob = it_list3.next();
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
                            </ul>
                        </nav>
                    </div>
                  <jsp:include page="verArchivos.jsp" flush="true" />
                    <div class="clearfix"> </div>
                </div>
                <div class="copy">
       <img src="../resources/images/escudo.jpg" width="70" height="70"> <p> Universidad de la Sierra Sur  </p>          
    </div>
                <script src="js/jquery.nicescroll.js"></script>
                <script src="js/scripts.js"></script>
              
                </body>
                </html>

