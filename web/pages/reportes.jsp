<%-- 
    Document   : reportes
    Created on : 09-ago-2018, 21:23:41
    Author     : Marifer
--%>

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

          if (sesion.getAttribute("user") != null && sesion.getAttribute("nivel") != null) {
             usuario = sesion.getAttribute("user").toString();
             nivel = sesion.getAttribute("nivel").toString();
             out.print("<a href='login.jsp?cerrar=true'><h5>cerrar Sesion" + usuario + "</h5>");
          } else {
             out.print("<script>location.replace('login.jsp');</script>");
          }
      %>
      <div id="wrapper">

         <nav class="navbar-default navbar-static-top" role="navigation">
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
               </button>
               <h1> <a class="navbar-brand" >Tutorias UNSIS</a></h1>         
            </div>
            <div class=" border-bottom">
               <div class="full-left">
                  <section class="full-top">
                     <button id="toggle"><i class="fa fa-arrows-alt"></i></button>	
                  </section>
                  <form class=" navbar-left-right">
                     <input type="text"  value="Search..." onfocus="this.value = '';" onblur="if (this.value == '') {
                       this.value = 'Search...';
                    }">
                     <input type="submit" value="" class="fa fa-search">
                  </form>
                  <div class="clearfix"> </div>
               </div>


               <!-- Brand and toggle get grouped for better mobile display -->

               <!-- Collect the nav links, forms, and other content for toggling -->
               <div class="drop-men" >
                  <ul class=" nav_1">

                     <li class="dropdown at-drop">
                        <a href="#" class="dropdown-toggle dropdown-at " data-toggle="dropdown"><i class="fa fa-globe"></i> <span class="number">5</span></a>
                        <ul class="dropdown-menu menu1 " role="menu">
                           <li><a href="#">

                                 <div class="user-new">
                                    <p>New user registered</p>
                                    <span>40 seconds ago</span>
                                 </div>
                                 <div class="user-new-left">

                                    <i class="fa fa-user-plus"></i>
                                 </div>
                                 <div class="clearfix"> </div>
                              </a></li>
                           <li><a href="#">
                                 <div class="user-new">
                                    <p>Someone special liked this</p>
                                    <span>3 minutes ago</span>
                                 </div>
                                 <div class="user-new-left">

                                    <i class="fa fa-heart"></i>
                                 </div>
                                 <div class="clearfix"> </div>
                              </a></li>
                           <li><a href="#">
                                 <div class="user-new">
                                    <p>John cancelled the event</p>
                                    <span>4 hours ago</span>
                                 </div>
                                 <div class="user-new-left">

                                    <i class="fa fa-times"></i>
                                 </div>
                                 <div class="clearfix"> </div>
                              </a></li>
                           <li><a href="#">
                                 <div class="user-new">
                                    <p>The server is status is stable</p>
                                    <span>yesterday at 08:30am</span>
                                 </div>
                                 <div class="user-new-left">

                                    <i class="fa fa-info"></i>
                                 </div>
                                 <div class="clearfix"> </div>
                              </a></li>
                           <li><a href="#">
                                 <div class="user-new">
                                    <p>New comments waiting approval</p>
                                    <span>Last Week</span>
                                 </div>
                                 <div class="user-new-left">

                                    <i class="fa fa-rss"></i>
                                 </div>
                                 <div class="clearfix"> </div>
                              </a></li>
                           <li><a href="#" class="view">View all messages</a></li>
                        </ul>
                     </li>
                     <li class="dropdown">
                        <a href="#" class="dropdown-toggle dropdown-at" data-toggle="dropdown"><span class=" name-caret">Coria olgin<i class="caret"></i></span><img src="../resources/images/in1.jpg"></a>
                        <ul class="dropdown-menu " role="menu">
                           <li><a href="profile.html"><i class="fa fa-user"></i>Edit Profile</a></li>
                           <li><a href="inbox.html"><i class="fa fa-envelope"></i>Inbox</a></li>
                           <li><a href="calendar.html"><i class="fa fa-calendar"></i>Calender</a></li>
                           <li><a href="inbox.html"><i class="fa fa-clipboard"></i>Tasks</a></li>
                        </ul>
                     </li>

                  </ul>
               </div><!-- /.navbar-collapse -->
               <div class="clearfix">

               </div>

               <div class="navbar-default sidebar" role="navigation">
                  <div class="sidebar-nav navbar-collapse">
                     <img src="../resources/images/escudo.png" width="90" height="90"> <br><br>
                     <ul class="nav" id="side-menu">

                        <li>
                           <a href="indexProfesor.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Tutorados</a>
                        </li>
                        <li>
                           <a href="reportes.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Reportes</a>
                        </li>
                        <li>
                           <a href="materialDidac.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Material didactico</a>
                        </li>
                        <li>
                           <a href="asistencia.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Lista de asistencia</a>
                        </li>
                     </ul>
                  </div>
               </div>
         </nav>
         <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="content-main">

               <!--banner-->	
               <div class="banner">
                  <h2>
                     <a href="index.html">Home</a>
                     <i class="fa fa-angle-right"></i>
                     <span>Reportes</span><br>
                  </h2>
               </div>
               <!--//banner-->
               <!--faq-->
               <div class="blank">


                  <div class="blank-page">

                     <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                  </div>
               </div>

               <!--//faq-->
               <!---->

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


