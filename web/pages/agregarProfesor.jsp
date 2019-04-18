<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


<%@page import="java.util.Iterator"%>
<%@page import="model.Programa"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProgramaDAO"%>
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
                        <span>Agregar profesor</span><br>
                    </h2>
                </div>
                <form id="formulario"  action="../ControllerProfesor" method="post" >
                    <input type="hidden" name = "action" value="add">
                    <div class="blank">
                        <div class="blank-page col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="grid-form1 col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <h3 id="forms-example" class="">Datos del profesor</h3>
                                <div class="form-group">
                                    <label for="curp">Curp</label>
                                    <input  required class="form-control" id="curp" name="curp" placeholder="Introduce la curp del profesor">
                                </div>
                                <div class="form-group">
                                    <label for="nombre">Nombre completo</label>
                                    <input  required class="form-control" id="nombre" name="nombre" placeholder="Introduce el nombre del profesor">
                                </div>
                                <div class = "form-group">
                                    <label>Perfil academico</label>
                                    <input  required class="form-control" id="grado_acad" name="grado_acad" placeholder="Introduce el perfil del profesor">
                                </div>
                                <div class = "form-group">
                                    <label>Status</label>	      
                                    <select class="form-control " id="status" name="status">

                                        <option value="ACTIVO">ACTIVO</option>
                                        <option value="INACTIVO">INACTIVO</option>
                                        <option value="LICENCIA">LICENCIA</option>
                                        <option value="SABATICO">SABATICO</option>
                                    </select>
                                </div>
                                <%
                                    ProgramaDAO obj_Read_Values = new ProgramaDAO();
                                    List<Programa> list = obj_Read_Values.listarProgramas();
                                    Iterator<Programa> it_list = list.iterator();

                                %>
                                <div class = "form-group">
                                    <label>Programa educativo</label>	      
                                    <select class="form-control " id="prg" name="prg">
                                        <%                                        while (it_list.hasNext()) {
                                                Programa ob = new Programa();
                                                ob = it_list.next();
                                        %>
                                        <option value="<%= ob.getIdPrograma()%>"> <%=ob.getNombre()%></option>\n\
                                        <% }

                                        %>   
                                    </select>
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
       <img src="../resources/images/escudo.jpg" width="70" height="70"> <p> Universidad de la Sierra Sur  </p>          
    </div>
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>

