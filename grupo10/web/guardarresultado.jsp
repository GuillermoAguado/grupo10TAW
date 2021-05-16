<%-- 
    Document   : guardarresultado
    Created on : 14-may-2021, 22:55:01
    Author     : manul
--%>

<%@page import="grupo10.entity.Estudiobd"%>
<%@page import="java.util.List"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel ="stylesheet" href="css/estiloanalista.css">
    </head>
    <%
        List<Usuario> lista;
        lista =(List)request.getAttribute("lista");
        Usuario usuario;
        usuario = (Usuario)request.getAttribute("usuario");
        String salida = "no vacia";
        Estudiobd estudio = (Estudiobd) request.getAttribute("estudio");
        if(lista.isEmpty() || lista == null){
            salida = "vacia";
        }
    %>
     <body>
        <h1>Lista de usuarios del estudio2:</h1>
        <form action="analista.jsp" method="POST">
            <input type="hidden" name="idusuario" id="idusuario" value="<%=usuario.getId()%>"/>
            <input type="submit" value="Volver a la vista principal sin guardar"/>
        </form>
        <form action="ServletGuardar" method="POST">
            <input type="hidden" name="usuarioguardar" id="usuarioguardar" value="<%=usuario%>"/>
            <input type="hidden" name="estudioguardar" id="estudioguardar" value="<%=estudio%>"/>
            <input type="hidden" name="listaguardar" id="listaguardar" value="<%=lista%>"/>
            <input type="submit" value="Guardar el estudio"/>
        </form>
        <table border ="1" name="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>EMAIL</th>
                    <th>NOMBRE COMPLETO</th>
                    <th>DOMICILIO</th>
                    <th>CIUDAD DE RESIDENCIA</th>
                    <th>EDAD</th>
                    <th>SEXO</th>
                </tr>
            </thead>
            <%
                int i=0;
                Integer id;
                String nombre;
                String email;
                String domicilio;
                String ciudad_res;
                Integer edad;
                String sexo;
                for(Usuario u: lista){
                    i++;
                    id = u.getId();
                    nombre = u.getNombre()+" "+u.getApellidos();
                    email = u.getEmail();
                    domicilio = u.getDomicilio();
                    ciudad_res=u.getCiudadRes();
                    edad=u.getEdad();
                    sexo=u.getSexo();
                %>
            <tr>
                <td><%=id%></td>
                <td><%=nombre%></td>
                <td><%=email%></td>
                <td><%=domicilio%></td>
                <td><%=ciudad_res%></td>
                <td><%=edad%></td>
                <td><%=sexo%></td>
            </tr>
            <%
                }
                %>
        </table>
        
    </body>
</html>
