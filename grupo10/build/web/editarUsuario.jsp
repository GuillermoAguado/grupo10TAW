<%-- 
    Document   : editarUsuario
    Created on : 08-may-2021, 19:39:37
    Author     : Usuario
--%>

<%@page import="grupo10.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/estilo.css"/>
        
        <title>Editar Usuario</title>
    </head>
    <%
        Usuario usuario = (Usuario) request.getAttribute("usuarioEdit");
        String nombreUsuario = "";
        if(usuario != null)
        {
            nombreUsuario = usuario.getNombre() + " " + usuario.getApellidos();
        }
        String error = (String) request.getAttribute("error");
    %>
    <body>
        <%if(usuario != null)
        {%>
        <h1>P&aacute;gina de edici&oacute;n de <%= nombreUsuario%></h1>
        <%}else
          {%>
        <h1>P&aacute;gina de creaci&oacute;n de un nuevo usuario</h1>
        <% }
        if(error != null && !error.isEmpty())
        {%>
        <h3 style="color:#FF0000"><%= error %><h3>
        <%}%>
        <form action="ActualizarUsuarioServlet" method="POST">
            <input type="hidden" id="idUsuario" name="idUsuario" value= <%= (usuario == null)?"-1":usuario.getId() %> />
            <label for="correo">Correo</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="correo" name="correo" value= <%=usuario.getEmail() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="correo" name="correo" placeholder="Su correo..." class="rellenar"/>
              <%}%>
            <label for="password">Contrase&ntilde;a</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="password" name="password" value= <%= usuario.getContrasenia()%> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="password" name="password" placeholder="Su contrase&ntilde;a..." class="rellenar"/>
              <%}%>
            <label for="nombre">Nombre</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="nombre" name="nombre" value= <%= usuario.getNombre() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="nombre" name="nombre" placeholder="Su nombre..." class="rellenar"/>
              <%}%>
            <label for="apellidos">Apellidos</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="apellidos" name="apellidos" value= <%= usuario.getApellidos() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="apellidos" name="apellidos" placeholder="Su apellido..." class="rellenar"/>
              <%}%>
            <label for="domicilio">Domicilio</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="domicilio" name="domicilio" value= <%= usuario.getDomicilio() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="domicilio" name="domicilio" placeholder="Su domicilio..." class="rellenar"/>
              <%}%>
            <label for="ciudad">Ciudad</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="ciudad" name="ciudad" value= <%= usuario.getCiudadRes() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="ciudad" name="ciudad" placeholder="Su ciudad..." class="rellenar"/>
              <%}%>
            <label for="sexo">Sexo (H=Hombre, M=Mujer, O=Otro)</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="sexo" name="sexo" value= <%= usuario.getSexo() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="sexo" name="sexo" placeholder="Su sexo..." class="rellenar"/>
              <%}%>
            <label for="edad">Edad</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="edad" name="edad" value= <%= (usuario == null)?"":usuario.getEdad() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="edad" name="edad" placeholder="Su edad..." class="rellenar"/>
              <%}%>
            <label for="tipo">Tipo (Asistente-1, Organizador-2, Analista-3, Teleoperador-4, Admin-5)</label>
            <%if(usuario != null)
            {%>
            <input type="text" id="tipo" name="tipo" value= <%= (usuario == null)?"":usuario.getTipousuario() %> class="rellenar"/>
            <%}else
              {%>
              <input type="text" id="tipo" name="tipo" placeholder="El tipo de usuario..." class="rellenar"/>
              <%}%>
            <input type="submit" value=<%= (usuario == null)?"Crear":"Editar"%>>
        </form>

    </body>
</html>
