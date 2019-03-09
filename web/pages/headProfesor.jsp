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

                   
                    <li class="dropdown">
                        
                        <form action="../login" method="get" onsubmit="return confirm('¿Realmente desea cerrar sesión?')">
<!--                        <input type="hidden" name = "action" value="close">-->
<input type=image src="../resources/images/cerrarSesion.png" width="30" height="30">
                            <!--<input  name="action" type="submit" value="close" >--> 
                       

                    </form>
                    </li>

                </ul>
            </div><!-- /.navbar-collapse -->
            </div>

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <img src="../resources/images/escudo.jpg" width="90" height="90"> <br><br>
                    <ul class="nav" id="side-menu">

                        <li>
                            <a href="indexProfesor.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Tutorados</a>
                        </li>
                        <li>
                            <a href="reportes.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Reportes</a>
                        </li>
                        <li>
                            <a href="materialApoyo.jsp" class=" hvr-bounce-to-right"><i class="fa fa-file-o nav_icon"></i>Material de apoyo</a>
                        </li>
                    </ul>
                </div>
            </div>
    </nav>
</div>
<a href="login.jsp"></a>
