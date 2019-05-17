<%-- 
Document   : indexProf
Created on : 29-jul-2018, 15:00:57
Author     : Marifer
--%>

<%@page import="model.Periodo"%>
<%@page import="dao.PeriodoDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProgramaDAO"%>
<%@page import="model.Programa"%>
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
        <script type='text/javascript'>
                       window.onload = function () {
                var fecha = new Date(); //Fecha actual
                var mes = fecha.getMonth() + 1; //obteniendo mes
                var dia = fecha.getDate(); //obteniendo dia
                var ano = fecha.getFullYear(); //obteniendo año
                if (dia < 10)
                    dia = '0' + dia; //agrega cero si el menor de 10
                if (mes < 10)
                    mes = '0' + mes //agrega cero si el menor de 10
                document.getElementById('datepicker').value = ano + "-" + mes + "-" + dia;
                
            }



            function soloNumeros(e) {
                key = e.keyCode || e.which;
                tecla = String.fromCharCode(key).toLowerCase();
                letras = "0123456789";
                especiales = "8-37-39-46";

                tecla_especial = false
                for (var i in especiales) {
                    if (key == especiales[i]) {
                        tecla_especial = true;
                        break;
                    }
                }

                if (letras.indexOf(tecla) == -1 && !tecla_especial) {
                    return false;
                }

            }
        </script>
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
                out.print("<script>location.replace('../login.jsp');</script>");
            }
        %>
        <jsp:include page="headAdminUpdate.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main ">

                <!--banner-->	
                <div class="banner">
                    <h2>
                        <a href="indexAdmin.jsp">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Datos de la tutoria</span><br>
                    </h2>
                </div>
                <form id="formulario"  action="ControllerReportesTutorias" method="post" >
                    <input type="hidden" name = "action" id="action" value="add">
                    <div class="blank">

                        <div class="blank-page col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="grid-form1 col-xs-12 col-sm-12 col-md-12 col-lg-12">

                                <div class="form-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                    <label for="nombre">Nombre completo del profesor</label>
                                    <input type="text" disabled="true" required class="form-control" id="nombre" name="nombre" value="<c:out value="${prf.getNombre()}"/>"/>
                                </div>

                                <div class="form-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                    <label for="curp">Curp</label>
                                    <input type="text" disabled="true" required class="form-control"  value="<c:out value="${prf.getCurp()}"/>"/>
                                    <input type="hidden" class="form-control" id="curp" name="curp" value="<c:out value="${prf.getCurp()}"/>"/>

                                </div>

                                <div class="form-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                    <label for="nombre">Programa educativo</label>
                                    <input type="hidden" name = "idLic" id="idLic" value="<c:out value="${prf.getIdPrograma()}"/>"/>
                                    <input type="text" disabled="true" required class="form-control"  value="<c:out value="${prf.getPrograma()}"/>"/>
                                </div>

                                <%
                                    PeriodoDAO obj_Read_Values = new PeriodoDAO();
                                    List<Periodo> list = obj_Read_Values.listarPeriodos();
                                    Iterator<Periodo> it_list = list.iterator();

                                %>

                                <div class="form-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                    <label>Periodo</label>	    

                                    <select class="form-control " id="idPeriodo" name="idPeriodo">
                                        <%                                        while (it_list.hasNext()) {
                                                Periodo ob = new Periodo();
                                                ob = it_list.next();
                                        %>
                                        <option value="<%= ob.getIdPeriodo()%>"> <%=ob.getPeriodo()%></option>\n\
                                        <% }

                                        %>   
                                    </select>
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="nombre">Número de alumnos asignados</label>
                                    <input type="text" disabled="true" required class="form-control"  value="<c:out value="${count}"/>"/>
                                    <input type="hidden"  id="asg" name="asg" value="<c:out value="${count}"/>"/>
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="nombre">Tipo de tutoria</label>
                                    <input type="text" disabled="true" required class="form-control"  value="<c:out value="${tipo}"/>"/>
                                    <input type="hidden" class="form-control" id="tipoTutoria" name="tipoTutoria" value="<c:out value="${tipo}"/>"/>
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="nombre">Fecha de entrega de reporte</label>
                                    <input name="datepicker" id="datepicker"  type="date" class="form-control" />
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="nombre">¿Entregó el reporte a tiempo?</label>
                                    <select class="form-control " id="EntT" name="EntT">

                                        <option value="SI"> SI</option>
                                        <option value="NO"> NO</option>
                                    </select>
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="nombre">¿Entregó reporte?</label>
                                    <select class="form-control " id="EntRp" name="EntRp">
                                        <option value="SI"> SI</option>
                                        <option value="NO"> NO</option>
                                    </select>
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="numSes">Número de sesiones</label>
                                    <input onkeypress="return soloNumeros(event)" required  min="0" placeholder="Introduce un número"  class="form-control" id="numSes" name="numSes" />
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="numSes">Número de canalizaciones</label>
                                    <input onkeypress="return soloNumeros(event)" required  min="0" placeholder="Introduce un número"  class="form-control"id="numCa" name="numCa" />
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="nombre">Alumnos reportados</label>
                                    <input onkeypress="return soloNumeros(event)" required  min="0" placeholder="Introduce un número"  class="form-control" id="rep" name="rep" />
                                </div>

                                <div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <label for="nombre">Alumnos con asistencia</label>
                                    <input onkeypress="return soloNumeros(event)" required  min="0" placeholder="Introduce un número"  class="form-control" id="asis" name="asis"/>
                                </div>

                                <div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <label for="nombre">Faltantes</label><br>
                                    <textarea class=estilotextarea3 cols="50" rows="5" id="fal" name="fal"></textarea>
                                </div>

                                <div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <label for="nombre">Observaciones</label><br>
                                    <textarea class=estilotextarea4 cols="50" rows="5" id="obs" name="obs"></textarea>
                                </div>

                            </div> 
                            <button type="submit" class="bl btn btn-danger pull-right">Guardar</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>

    </div>
    <div class="clearfix"> </div>
</div>
 <div class="copy">
       <img src="resources/images/escudo.jpg" width="70" height="70"> <p> Universidad de la Sierra Sur  </p>          
    </div>
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>

