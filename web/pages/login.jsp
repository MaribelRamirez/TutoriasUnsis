<%-- 
    Document   : login
    Created on : 29-jul-2018, 15:13:18
    Author     : Marifer
--%>

<%@page import="model.ConnectionClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<!DOCTYPE html>
<html lang="zxx">
   <head>
      <title>Unsis</title>
      <!-- Meta-Tags -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta charset="utf-8">
      <script>
         addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
         }, false);

         function hideURLbar() {
            window.scrollTo(0, 1);
         }
      </script>
      <link rel="stylesheet" href="../resources/login/css/style.css" type="text/css" media="all">
      <link href="//fonts.googleapis.com/css?family=Mukta+Mahee:200,300,400,500,600,700,800" rel="stylesheet">
      <link rel="stylesheet" href="../resources/login/css/font-awesome.css" type="text/css" media="all">
   </head>

   <body><br><br><br><br>
      <%
          ConnectionClass op = new ConnectionClass();
          if (request.getParameter("btnIngresar") != null) {

              String nombre = request.getParameter("txtUsuario");
              String contra = request.getParameter("password");

              HttpSession sesion = request.getSession();
              System.out.println("antes del case");
              switch (op.loguear(nombre, contra)) {
                 case 1:

                     sesion.setAttribute("user", nombre);
                     sesion.setAttribute("nivel", "1");
                     response.sendRedirect("indexAdmin.jsp");
                     op.desconectar();
                     break;
                 case 2:

                     sesion.setAttribute("user", nombre);
                     sesion.setAttribute("nivel", "2");
                     response.sendRedirect("indexProfesor.jsp");
                     op.desconectar();
                     break;
                 case 3:
                     sesion.setAttribute("user", nombre);
                     sesion.setAttribute("nivel", "3");
                     response.sendRedirect("indexAlumno.jsp");
                     op.desconectar();
                     break;
                 default:
                     out.write("El usuario no existe, o la contraseña es invalida");
                     break;
              }
          }

          if (request.getParameter("cerrar") != null) {
              session.invalidate();
          }
      %>
      <h1></h1>
      <div class="content-w3ls">
         <div class="agileits-grid">
            <div class="content-top-agile">
               <h2>Tutorias Unsis</h2>
            </div>
            <div class="content-bottom">
               <form action="login.jsp" method="POST">
                  <div class="field_w3ls">
                     <div class="field-group">
                        <input name="txtUsuario" id="text1" type="text" value="" placeholder="username" required>
                     </div>
                     <div class="field-group">
                        
                        <input id="password-field" type="password" class="form-control" name="password" value="" placeholder="Password">
                        <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                     </div>
                  </div>
                  <div class="wthree-field">
                     <input id="saveForm" name="btnIngresar" type="submit" value="Login" />
                  </div>

               </form>
            </div>
            <!-- //content bottom -->
         </div>
      </div>
      <div class="copyright text-center">

      </div>
      <!--//copyright-->
      <script src="../resources/login/js/jquery-2.2.3.min.js"></script>
      <!-- script for show password -->
      <script>
         $(".toggle-password").click(function () {

            $(this).toggleClass("fa-eye fa-eye-slash");
            var input = $($(this).attr("toggle"));
            if (input.attr("type") == "password") {
               input.attr("type", "text");
            } else {
               input.attr("type", "password");
            }
         });
      </script>
      <!-- /script for show password -->

   </body>
   <!-- //Body -->

</html>