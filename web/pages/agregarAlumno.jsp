<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


<%@page import="model.Grupo"%>
<%@page import="dao.GrupoDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Licenciatura"%>
<%@page import="java.util.List"%>
<%@page import="dao.LicenciaturaDAO"%>
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
                out.print("<a href='login.jsp?cerrar=true'><h5>cerrar Sesion" + usuario + "</h5>");

            } else {
                out.print("<script>location.replace('login.jsp');</script>");
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
                            <span>Agregar alumno</span><br>
                        </h2>
                    </div>
                    <div class="blank">

                        <div class="blank-page">
                            <div class="grid-form1">
                                <h3 id="forms-example" class="">Datos del alumno</h3>
                                <form id="formulario" action="../ControllerAlumno" method="post" onsubmit="return confirm('Realmente desea guardar los datos')">
                                    <input type="hidden" name = "action" value="add">
                                    <div class="form-group">
                                        <label for="matricula">Matricula</label>
                                        <input  required class="form-control" type="number" id="matricula" name="matricula" placeholder="Introduce la matricula del alumno">

                                    </div>
                                    <div class="form-group">
                                        <label for="nombre">Nombre completo</label>
                                        <input  required class="form-control" id="nombre" name="nombre" placeholder="Introduce el nombre del alumno">

                                    </div>
                                    <%
                                        GrupoDAO obj_Read_grp = new GrupoDAO();
                                        List<Grupo> listGrup = obj_Read_grp.listarGrupos();
                                        Iterator<Grupo> list_Grup = listGrup.iterator();

                                    %>
                                    <div class = "form-group">
                                        <label>Grupos</label>	      
                                        <select class="form-control " id="grupo" name="grupo" >
                                            <%                                        while (list_Grup.hasNext()) {
                                                    Grupo ob = new Grupo();
                                                    ob = list_Grup.next();
                                            %>
                                            <option value="<%= ob.getIdGrupo()%>"> <%=ob.getNombre()%></option>\n\
                                            <% }

                                            %>   
                                        </select>
                                    </div>
                                    <%                         LicenciaturaDAO obj_Read_Lic = new LicenciaturaDAO();
                                        List<Licenciatura> listLic = obj_Read_Lic.listarLicenciaturas();
                                        Iterator<Licenciatura> list_Lic = listLic.iterator();

                                    %>
                                    <div class = "form-group">
                                        <label>Licenciatura</label>	      
                                        <select class="form-control " id="lic" name="lic" >
                                            <%                                        while (list_Lic.hasNext()) {
                                                    Licenciatura ob = new Licenciatura();
                                                    ob = list_Lic.next();
                                            %>
                                            <option value="<%= ob.getIdLicenciatura()%>"> <%=ob.getNombre()%></option>\n\
                                            <% }

                                            %>   
                                        </select>
                                    </div>

                                    <button type="submit" class="bl btn btn-danger">Guardar</button>
                                </form>
                            </div>
                        </div>
                    </div>
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

