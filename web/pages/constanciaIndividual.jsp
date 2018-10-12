<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


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




        <script src="../resources/calendario/js/bootstrap.min.js"></script>
        <script src="../resources/calendario/js/jquery-1.8.3.js"></script>

        <link href="../resources/calendario/css/bootstrap-datepicker3.min.css" rel='stylesheet' type='text/css'/>
        <script src="../resources/calendario/js/bootstrap-datepicker.min.js"></script>
        <!--link href="../resources/calendario/css/bootstrap.min.css" rel="stylesheet" rel='stylesheet' type='text/css'/-->
        <script type='text/javascript'>
            $(function () {
                $('.input-group.date').datepicker({
                    calendarWeeks: true,
                    todayHighlight: true,
                    autoclose: true
                }).datepicker("setDate", new Date());
            });
            
            
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
                        <span>Constancias individuales</span><br>
                    </h2>
                </div>


                <div class="blank">


                    <div class="blank-page">
                        
                        
                        
        



                        
                        <%
                            ProfesorDAO obj_Read_Values = new ProfesorDAO();
                            List<Profesor> list = obj_Read_Values.tutorIndividual();
                            Iterator<Profesor> it_list = list.iterator();
                            int cont = list.size();

                        %>
                        <form id="formulario" action="../ControllerConstancias" method="post" target="_blank">
                                  

<div class="container">
  <br />
  <div class="row">
    <div class='col-sm-3'>
      <div class="form-group">
        <div id="filterDate2">
          
          <!-- Datepicker as text field -->         
          <div class="input-group date" data-date-format="dd-mm-yyyy">
              <input  required readonly="readonly" type="text" class="form-control" name="datepicker" id="datepicker" >
            <div class="input-group-addon" >
              <span class="glyphicon glyphicon-th"></span>
            </div>
          </div>
          
        </div>    
      </div>
    </div>
  </div>
</div>
                            <input type="hidden" name = "action" value="individual">
                            <input type="hidden" name = "sizeList" value="<%=cont%>">
                            <table id="example" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Nombre</th></th> 
                                        <th>Grado</th>
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
                            
                            <button type="submit" class="bl btn btn-danger">Generar Constancias</button>
                        </form> 
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

