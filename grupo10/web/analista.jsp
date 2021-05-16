<%-- 
    Document   : analista
    Created on : 12-may-2021, 19:10:14
    Author     : manul
--%>

<%@page import="grupo10.entity.Usuario"%>
<%@page import="grupo10.entity.Estudiobd"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista principal del analista</title>
        <link rel ="stylesheet" href="css/estiloanalista.css">
    </head>
    <%
        List<Estudiobd> lista;
        lista =(List)request.getAttribute("listEstudios");
        Usuario usuario;
        usuario = (Usuario)request.getAttribute("usuario");
    %>
    <body>
        <h1>Lista de análisis del usuario <%=usuario.getNombre() + " " + usuario.getApellidos()%>:</h1>
        <form action="crearEstudio.jsp">
            <input type="hidden" name="idusuario" id="idusuario" value="<%=usuario.getId()%>"/>
            <input type="submit" value="Crear un nuevo análisis de usuario"/>
        </form>
        <form action="crearEstudioEvento.jsp">
            <input type="hidden" name="idusuario" id="idusuario" value="<%=usuario.getId()%>"/>
            <input type="submit" value="Crear un nuevo análisis de evento"/>
        </form>
        <table border ="1" name="table">
            <thead>
                <tr>
                    <th>Nº</th>
                    <th>Nombre del análisis</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <%
                int i=0;
                String nombre;
                for(Estudiobd estudio: lista){
                    i++;
                    nombre = estudio.getNombre();
                %>
            <tr>
                <td><%=i%></td>
                <td><%=nombre%></td>
                <td align="center"> <a align="center" href="ServletMostrarEstudio?idestudio=<%=estudio.getId()%>&idanalista=<%=usuario.getId()%>">Mostrar</a> </td>
                <td align="center"><a align="center" href="ServletEditarEstudio?idestudio=<%=estudio.getId()%>&idanalista=<%=usuario.getId()%>">Editar</a></td>
                <td align="center"><a align="center" href="ServletBorrarEstudio?idestudio=<%=estudio.getId()%>&idanalista=<%=usuario.getId()%>">Borrar</a></td>
                <td align="center"><a align="center" href="ServletCopiarEstudio?idestudio=<%=estudio.getId()%>&idanalista=<%=usuario.getId()%>">Copiar</a></td>
            </tr>
            <%
                }
                %>
        </table>
        
    </body>
</html>
