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
                <%                        
                    String rutRel=getServletConfig().getServletContext().getRealPath("/resources/Documentos");
                    String path = rutRel+"/";
                    File directorio = new File(path);
                    String[] ficheros = directorio.list();
                    for (int i = 0; i < ficheros.length; i++) {

                %>
                <tr>
                    <td><%=ficheros[i]%></td>
                    <td>
                       <a href="../resources/Documentos/<%=ficheros[i]%>" target="_blank" type="application/vnd.ms-excel">Abrir </a>
                    </td>
                    <td >
                        <form id="formularioElim" name="formularioElim" action="../ControllerArchivos" method="post" onsubmit="return confirm('¿Realmente desea eliminar el documento?')" >
                            <input type="hidden" name = "nombre" id="id" value="<%=ficheros[i]%>">
                            <input type="hidden" name = "action" id="action" value="eliminar">
                            <button type="submit"  class="btn btn-link"><img src="../resources/images/delete.jpeg" title="Eliminar" height="40"/></button>
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