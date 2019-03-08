<%@page import="java.io.File"%>
<div class="blank-page col-xs-12 col-sm-12 col-md-12 col-lg-12">


    <nav class="nav-sidebar">
        <table id="example" class="table table-striped table-bordered" style="width:100%">

            <thead>

                <tr>

                    <th>Archivo</th>
                    <th>Abrir</th>
                    <th>Eliminar</th>


                </tr>
            </thead>

            <tbody>
                <%                                        File carpetas = new File("C:\\TutoriasUnsis\\Documentos\\");
                    carpetas.mkdirs();
                    String path = "C:\\TutoriasUnsis\\Documentos\\";
                    File directorio = new File(path);
                    String[] ficheros = directorio.list();
                    String line;
                    for (int i = 0; i < ficheros.length; i++) {

                %>
                <tr>
                    <td><%=ficheros[i]%></td>




                    <td>
                        <form id="formularioAct" name="formularioAbr" action="../ControllerArchivos" method="post" onsubmit="return confirm('¿Realmente desea abrir el documento?')" >
                            <input type="hidden" name = "nombre" id="id" value="<%=ficheros[i]%>">
                            <input type="hidden" name = "action" id="action" value="abrir">
                            <button type="submit"  class="btn btn-link">Abrir</button>
                        </form>


                    </td>
                    <td >
                        <form id="formularioElim" name="formularioElim" action="../ControllerArchivos" method="post" onsubmit="return confirm('¿Realmente desea eliminar el documento?')" >
                            <input type="hidden" name = "nombre" id="id" value="<%=ficheros[i]%>">
                            <input type="hidden" name = "action" id="action" value="eliminar">
                            <button type="submit"  class="btn btn-link">Eliminar</button>
                        </form>


                    </td>


                </tr>

                <%

                    }
                %>
            </tbody>
        </table>

    </nav>
    <div>
    </div>

</div>