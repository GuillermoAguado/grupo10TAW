<%-- 
    Document   : crearEstudio
    Created on : 14-may-2021, 12:19:05
    Author     : manul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear un nuevo análisis de usuario:</title>
        <link rel ="stylesheet" href="css/estiloanalista.css">
    </head>
    <%
        Integer id = Integer.parseInt(request.getParameter("idusuario")) ;
    %>
    <body>
        <h1>Crear un nuevo análisis de usuario:</h1>
        <form action="ServletCrear" method="POST">
            Restriccion por edad:(mayor que) <input type="text" name="edadmayor" id="edadmayor" size="10" maxlength="10">
            <br/>
            Restriccion por edad:(menor que) <input type="text" name="edadmenor" id="edadmenor" size="10" maxlength="10">
            <br/>
            Restriccion por sexo: <br/><input type="radio" name="sexo" id="sexo" value="H">Hombre
            <br/>
                                  <input type="radio" name="sexo" id="sexo" value="M">Mujer
                                  <br/>
                                  
                                  <input type="radio" name="sexo" id="sexo" value="">Sin Restricción
            <br/>
            <br/>
            Restriccion por ciudad: <input type="text" name="ciudad" id="ciudad" size="50" maxlength="50">
            <br/>
            <input type="hidden" name="idusuario2" value="<%=id%>"/>
            <input type="submit" value="Crear el estudio"/>
        </form>
    </body>
</html>
