<%-- 
    Document   : registro
    Created on : 10-may-2021, 11:14:12
    Author     : Fran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/estilo.css"/>
        
        <title>Página de registro</title>
    </head>
    <body>
        <h1>P&aacute;gina de registro de un nuevo usuario</h1>
        <%
            String error = (String)request.getAttribute("error");
            if (error != null && !error.isEmpty()){
        %> 
        <h3 style="color:#FF0000"><%= error %></h3>
        <%        
            }
        %>
        <form action="RegistrarUsuarioServlet" method="POST">
            <label for="correo">Correo</label>
            <input type="text" id="correo" name="correo" placeholder="Su correo..." class="rellenar"/>
            <label for="password">Contrase&ntilde;a</label>
            <input type="text" id="password" name="password" placeholder="Su contrase&ntilde;a..." class="rellenar"/>
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" placeholder="Su nombre..." class="rellenar"/>
            <label for="apellidos">Apellidos</label>
            <input type="text" id="apellidos" name="apellidos" placeholder="Su apellido..." class="rellenar"/>
            <label for="domicilio">Domicilio</label>
            <input type="text" id="domicilio" name="domicilio" placeholder="Su domicilio..." class="rellenar"/>
            <label for="apellidos">Ciudad</label>
            <input type="text" id="ciudad" name="ciudad" placeholder="Su ciudad..." class="rellenar"/>
            <label for="apellidos">Sexo (H=Hombre, M=Mujer, O=Otro)</label>
            <input type="text" id="sexo" name="sexo" placeholder="Su sexo..." class="rellenar"/>
            <label for="edad">Edad</label>
            <input type="text" id="edad" name="edad" placeholder="Su edad..." class="rellenar"/>
            <input type="submit" value="Crear">
        </form>

    </body>
</html>

