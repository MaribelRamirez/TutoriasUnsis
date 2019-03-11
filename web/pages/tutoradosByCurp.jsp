<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Alumno"%>
<%@page import="dao.AlumnoDAO"%>
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
        </script>



    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            String usuario;
            String nivel;
            String curp = "";

            if (sesion.getAttribute("user") != null && sesion.getAttribute("nivel") != null) {
                usuario = sesion.getAttribute("user").toString();
                nivel = sesion.getAttribute("nivel").toString();
                curp = sesion.getAttribute("curp").toString();
            } else {
                out.print("<script>location.replace('/TutoriasUnsis');</script>");
            }
            
            if (sesion.getAttribute("curp") != null) {
                curp = sesion.getAttribute("curp").toString();
            } else {
                out.print("<script>location.replace('indexProfesor.jsp');</script>");
            }
        %>
        <jsp:include page="headProfesor.jsp" flush="true" />
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">

                <!--banner-->	
                <div class="banner">
                    <h2>
                        <a href="index.html">Home</a>
                        <i class="fa fa-angle-right"></i>
                        <span>Tutorados</span><br>
                    </h2>
                </div>
                <!--//banner-->
                <!--faq-->
                <div class="blank">


                    <div class="blank-page">
                        <%
                            AlumnoDAO obj_Read_Values = new AlumnoDAO();
                            System.out.println("llego a jsp");
                            List<Alumno> list = obj_Read_Values.listarAlumnosTutorados(curp);
                            Iterator<Alumno> it_list = list.iterator();
                        %>
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Matricula</th>
                                    <th>Nombre</th>
                                    <th>Grupo</th>
                                    <th>Licenciatura</th>
                                    <th>Tutoria</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    String tipo;
                                    while (it_list.hasNext()) {
                                        Alumno ob = new Alumno();
                                        ob = it_list.next();
                                %>  
                                <tr>
                                    <td><%=ob.getMatricula()%></td>  
                                    <td><%=ob.getNombre()%></td>  
                                    <td><%=ob.getGrupo()%></td>  
                                    <td><%=ob.getLicenciatura()%></td>  
                                    <%
                                        if (ob.getTipo() == 1) {
                                            tipo = "Individual";
                                        } else {
                                            tipo = "Grupal";
                                        }
                                    %>
                                    <td><%=tipo%></td>  
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

