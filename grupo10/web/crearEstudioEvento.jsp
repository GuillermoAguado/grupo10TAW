<%-- 
    Document   : crearEstudioEvento
    Created on : 14-may-2021, 12:50:54
    Author     : manul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear un nuevo análisis de evento:</title>
        <link rel ="stylesheet" href="css/estiloanalista.css">
    </head>
    <%
        Integer id = Integer.parseInt(request.getParameter("idusuario")) ;
    %>
    <body>
        <h1>Crear un nuevo análisis de evento:</h1>
        <form action="ServletCrearEvento" method="POST">
            Restriccion por fecha de evento:(Año-Mes-Día) <input type="text" name="fecha" id="fecha" size="15" maxlength="15">
            <br/>
            Restriccion por aforo: <input type="text" name="aforo" id="aforo" size="10" maxlength="10">
            <br/>
            <input type="hidden" name="idusuario2" value="<%=id%>"/>
            <input type="submit" value="Crear el estudio"/>
        </form>
    </body>
</html>
