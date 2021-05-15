<%-- 
    Document   : admin
    Created on : 08-may-2021, 14:30:00
    Author     : Usuario
--%>
<%@page import="grupo10.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/estilo.css"/>
        
        <title>Gestor Admin</title>
    </head>
    <%
        HttpSession ses;
        ses = request.getSession();
        Usuario admin = (Usuario) ses.getAttribute("usuario");
        
        List<Usuario> listaUsuarios = (List) request.getAttribute("listaUsuarios");
        
        String nombre = admin.getNombre() + " " + admin.getApellidos();
        
        String usuario_sexo;
            if (admin.getSexo().equals("M")){
                usuario_sexo = "a";
            } else if(admin.getSexo().equals("H")){
                usuario_sexo = "o";
            }else
            {
                usuario_sexo = "e";
            }
    %>
    <body>
        <h1>¡Bienvenid<%= usuario_sexo %> al Gestor Admin, <%= " " + nombre%>&#129331; !</h1>
        <form action="AdminServlet">
            <input type="text" id="busqueda" name="busqueda" placeholder="Correo del usuario..." class="search"/>
            <input type="submit" value="Buscar" />
        </form>
            <table style="width:100%">
                <caption>Lista usuarios</caption>
                <tr>
                  <th>ID</th>
                  <th>Email</th>
                  <th>Contrase&ntilde;a</th>
                  <th>Nombre</th>
                  <th>Apellidos</th>
                  <th>Domicilio</th>
                  <th>Ciudad</th>
                  <th>Sexo</th>
                  <th>Edad</th>
                  <th>Tipo de usuario</th>
                  <th  colspan="2">Opciones</th>
                </tr>
                <%for (Usuario u : listaUsuarios)
                      {%> 
                <tr>   
                  <td><%= u.getId() %></td>
                  <td><%= u.getEmail() %></td>
                  <td><%= u.getContrasenia() %></td>
                  <td><%= u.getNombre() %></td>
                  <td><%= u.getApellidos() %></td>
                  <td><%= u.getDomicilio() %></td>
                  <td><%= u.getCiudadRes() %></td>
                  <%if(u.getSexo().equals("H"))
                  {
                      %>
                      <td>Hombre</td>
                  <% 
                  }else if(u.getSexo().equals("M"))
                  {%>
                     <td>Mujer</td>
                  <%
                  }else
                  {%>
                     <td>Otro</td> 
                  <%
                  }%>
                  <td><%= u.getEdad() %></td>
                  
                  <% switch(u.getTipousuario()) {
                    case 1: %>
                      <td>Asistente</td>
                      <% break;
                    case 2: %>
                      <td>Organizador</td>
                      <% break;
                    case 3: %>
                      <td>Analista</td>
                      <% break;
                    case 4: %>
                      <td>Teleoperador</td>
                      <% break;
                    case 5: %>
                      <td>Admin</td>
                      <% break;
                    default: %>
                      <td>Default</td>
                  <%}%>
                  <td> <a href="EditaUsuarioServlet?idUsuario=<%= u.getId() %>">Editar</a> </td>
                  <%if( u.getId() != admin.getId() )
                  {%>
                  <td> <a href="BorraUsuarioServlet?idUsuario=<%= u.getId() %>">Borrar </a> </td>
                  <%}%>
                </tr>
                <%}%>
            </table>
            <a href="EditaUsuarioServlet?idUsuario=-1">Crear un usuario</a>
            <a href=CargaMisEventosServlet">Editar eventos</a>

        
    </body>
</html>
