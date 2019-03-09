<%-- 
    Document   : indexProf
    Created on : 29-jul-2018, 15:00:57
    Author     : Marifer
--%>


<%@page import="model.Licenciatura"%>
<%@page import="model.Licenciatura"%>
<%@page import="dao.LicenciaturaDAO"%>
<%@page import="dao.LicenciaturaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Grupo"%>
<%@page import="java.util.List"%>
<%@page import="dao.GrupoDAO"%>
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
            
            function Guardar() {
                swal({    
                    title: "aviso!!",    
                    text: "¿En verdad deseas guardar los datos?",    
                    type: "warning",    
                    showCancelButton: true,    
                    confirmButtonColor: "#DD6B55",    
                    confirmButtonText: "SI",    
                    cancelButtonText: "NO",    
                    closeOnConfirm: false,    
                    closeOnCancel: false },   

                    function(isConfirm){    
                      if (isConfirm) {  
                          document.getElementById('formularioG').submit();
                      } else {      
                          window.location='pages/ListarAlumnos.jsp';  
                      }  
                    });
                  };
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



        <jsp:include page="headAdminUpdate.jsp" flush="true" />

        <!--include-->

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

                        <div class="blank-page col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="grid-form1 col-xs-12 col-sm-12 col-md-12 col-lg-12">
                             
                <form id="formularioG" name="formularioG" action="ControllerAlumno" method="post" >
                    <input type="hidden" name = "action" value="edit">
                    <input type="hidden" name = "matricula"  value="<c:out value="${alm.getMatricula()}"/>"/> 

                     <h3 id="forms-example" class="">Datos del alumno</h3>
                                <div class="form-group">
                                    <label for="nomLicc">Nombre del alumno</label>

                                    <input  required class="form-control" id="nombreAlum" name="nombreAlum"  value="<c:out value="${alm.getNombre()}"/>"/>
                                </div>
                                <%
                                    GrupoDAO obj_Read_grp = new GrupoDAO();
                                    List<Grupo> listGrup = obj_Read_grp.listarGrupos();
                                    Iterator<Grupo> list_Grup = listGrup.iterator();

                                %>
                                <div class = "form-group">
                                    <label>Grupo</label>	      
                                    <select class="form-control " id="grupo" name="grupo"  >


                                        <%                                           
                                            while (list_Grup.hasNext()) {
                                                Grupo ob = new Grupo();
                                                ob = list_Grup.next();
                                        %>
                                          <c:set var="id" value="<%=ob.getIdGrupo()%>"/>
                                        <c:choose >
                                            <c:when test="${alm.getIdGrupo()== id}">
                                                <option  selected="selected"  value="<%= ob.getIdGrupo()%>"> <%=ob.getGrupo()%></option>

                                            </c:when>
                                            <c:otherwise>
                                                <option    value="<%= ob.getIdGrupo()%>"> <%=ob.getGrupo()%></option>

                                            </c:otherwise>

                                        </c:choose>


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
                                       
                                        <%                                       
                                            while (list_Lic.hasNext()) {
                                                Licenciatura ob = new Licenciatura();
                                                ob = list_Lic.next();
                                               

                                        %>

                                     
                                        <c:set var="id" value="<%=ob.getIdLicenciatura()%>"/>
                                         <c:choose >
                                             <c:when test="${ alm.getIdLicenciatura()==id}">
                                                <option  selected="selected"  value="<%= ob.getIdLicenciatura() %>"> <%=ob.getNombre()%></option>

                                            </c:when>
                                            <c:otherwise>
                                                <option value="<%=ob.getIdLicenciatura()%>"> <%=ob.getNombre() %></option>
                                             </c:otherwise>
                                        </c:choose>
                                        <% }

                                        %>   
                                    </select>
                                </div>

                           
                </form>
                      <button type="button" onclick="Guardar()" class="bl btn btn-danger pull-right">Guardar</button>
                   </div> </div> </div>   
            </div>
        </div>

    </div>
    <div class="clearfix"> </div>
</div>
<div class="copy">
    <p><img src="resources/images/escudo.jpg" width="70" height="70"> Universidad de la Sierra Sur</p>          
</div>
<!---->
<!--scrolling js-->
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!--//scrolling js-->
</body>
</html>

