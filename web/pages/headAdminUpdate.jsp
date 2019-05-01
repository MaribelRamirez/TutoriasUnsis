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

                <div class="clearfix"> </div>
            </div>
            <div class="drop-men" >
                <ul class=" nav_1">


                    <li class="dropdown">
                        <form action="login" method="get" onsubmit="return confirm('¿Realmente desea cerrar sesión?')">
                            <div class="form-group">
                                <!--                        <input type="hidden" name = "action" value="close">-->
                                <!--<button name="action" type="submit" value="close"> </button>-->
                                <input type=image src="resources/images/cerrarSesion.png" width="30" height="30"  >

                            </div>

                        </form>
                    </li>

                </ul>
            </div>
            <div class="clearfix">

            </div>

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse"><br>
                    <img src="resources/images/escudo.jpg" width="90" height="90" class="center-block">
                    <ul class="nav" id="side-menu">
                        <a href="pages/indexAdmin.jsp" class=" hvr-bounce-to-right">
                            <i class="fa fa-file-o nav_icon"></i>Profesores
                        </a>
                        </li>
                        <li>
                            <a href="pages/ListarAlumnos.jsp" class=" hvr-bounce-to-right">
                                <i class="fa fa-file-o nav_icon"></i>Alumnos
                            </a>
                        </li>
                        <li>
                            <a href="pages/ListarTutorados.jsp" class=" hvr-bounce-to-right">
                                <i class="fa fa-file-o nav_icon"></i>Tutorados
                            </a>
                        </li>
                        <li>
                            <a href="pages/ListarProgramas.jsp" class=" hvr-bounce-to-right">
                                <i class="fa fa-file-o nav_icon"></i>Programas educativos</a>
                        </li>

                        <li>
                            <a href="pages/ListarGrupos.jsp" class=" hvr-bounce-to-right">
                                <i class="fa fa-file-o nav_icon"></i>Grupos
                            </a>
                        </li>
                        <li>
                            <a href="pages/loadAlumnos.jsp" class=" hvr-bounce-to-right">
                                <i class="fa fa-file-o nav_icon"></i>Actualizar listas
                            </a>
                        </li>

                        <li>
                            <a href="pages/generarReportes.jsp" class=" hvr-bounce-to-right">
                                <i class="fa fa-clipboard nav_icon">  </i>Generar documentos
                            </a>
                        </li>
                        <li>
                            <a href="pages/cargarArchivos.jsp" class=" hvr-bounce-to-right">
                                <i class="fa fa-file-o nav_icon">  </i>Cargar archivos
                            </a>
                        </li>

                        <li>
                            <a href="pages/ListarPeriodos.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Periodos</a>
                        </li>
                        <li>
                            <a href="pages/ListarReportes.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Entrega de reportes tutorias</a>
                        </li>
                    </ul>
                </div>
            </div>
    </nav>
