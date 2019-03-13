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

        <script src="../resources/tablas/js/jquery-3.3.1.js"></script>  
        <script src="../resources/tablas/js/jquery.dataTables.min.js"></script>
        <script src="../resources/tablas/js/dataTables.bootstrap.min.js"></script> 

        <link href="../resources/tablas/css/dataTables.bootstrap.min.css" rel='stylesheet' type='text/css' />


        <script>
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
                <div class="banner">
                    <h2>
                        <a href="indexAdmin.jsp">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Constancias grupales</span><br>
                    </h2>
                </div>
                <div class="blank">
                    <div class="blank-page col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <%
                            PeriodoDAO obj_Read_periodo = new PeriodoDAO();
                            ProfesorDAO obj_Read_Values = new ProfesorDAO();
                            ////int periodo = Integer.parseInt();
                            List<Profesor> list = obj_Read_Values.tutorGrupal();
                            Iterator<Profesor> it_list = list.iterator();
                            int cont = list.size();

                        %>
                        <form id="formulario" action="../ControllerConstancias" method="post" >
                            <div class="container">
                                <br />
                                <div class="row">
                                    <div class='col-xs-6 col-sm-6 col-md-6 col-lg-6'>
                                        <div class="form-group">
                                            <div id="filterDate2">
                                                <label for="nombre">Elegir periodo y fecha para generar la constancia</label>
                                                <%  PeriodoDAO obj_Read_Values2 = new PeriodoDAO();
                                                    List<Periodo> list2 = obj_Read_Values2.listarPeriodos();
                                                    Iterator<Periodo> it_list2 = list2.iterator();

                                                %>
                                                <br>
                                                <div class = "form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                                    <select class="form-control " id="IdPeriodo" name="IdPeriodo">

                                                        <%                    while (it_list2.hasNext()) {
                                                                Periodo ob = new Periodo();
                                                                ob = it_list2.next();
                                                        %>
                                                        <option value="<%= ob.getIdPeriodo()%>"> <%=ob.getPeriodo()%></option>

                                                        <% }

                                                        %>   
                                                    </select>

                                                </div>
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                                    <input name="datepicker" id="datepicker"  type="date" class="form-control" value="" />
                                                </div>  
                                            </div>         
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name = "action" value="grupal">
                            <input type="hidden" name = "sizeList" value="<%=cont%>">
                            <table id="example" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Nombre</th></th> 
                                        <th>Perfil</th>
                                        <th>Status</th>
                                        <th>Licenciatura</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% int i = 0;
                                        while (it_list.hasNext()) {
                                            i++;
                                            Profesor ob = new Profesor();
                                            ob = it_list.next();

                                    %>  
                                    <tr>
                                        <td><input type="checkbox" id="prof<%=i%>" name="prof<%=i%>" value="<%=ob.getCurp()%>"/> </td>
                                        <td><%=ob.getNombre()%></td>
                                        <td><%=ob.getGrado()%></td>
                                        <td><%=ob.getEstatus()%></td>
                                        <td><%=ob.getLicenciatura()%></td>
                                    </tr>
                                    <%
                                        }
                                    %>  

                                </tbody>
                            </table><br>
                            <button type="submit" class="bl btn btn-danger pull-right">Generar Constancias</button>
                        </form> 
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="copy">
            <p><img src="../resources/images/escudo.jpg" width="70" height="70"> Universidad de la Sierra Sur </p>          
        </div>
        <script src="js/jquery.nicescroll.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>

