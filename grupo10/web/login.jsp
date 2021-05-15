<%-- 
    Document   : login
    Created on : 01-may-2021, 19:22:33
    Author     : Fran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesión</title>
        <link rel='stylesheet' href='css/estilo.css' />
    </head>
    <%
        String strError = (String)request.getAttribute("error");
        if (strError == null){ strError = ""; }
    %>
    <body>
        <form method="POST" action="ValidarLoginServlet">
            <label for='email'>Email:</label>
            <input type="text" name="email" placeholder="Introduce tu correo electrónico" class="rellenar"/><br/>
            <label for='contrasenia'>Clave:</label>
            <input type="password" name="contrasenia" placeholder="Introduce tu contraseña" /><br/>
            <input type="submit" value="Iniciar sesión" /><br/>
        </form>
        <a href="registro.jsp">¿No dispones de una cuenta?</a>
        <br/>
        <br/>
            <h3><%= strError%><h3/>
    </body>
</html>
