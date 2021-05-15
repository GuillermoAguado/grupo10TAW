<%-- 
    Document   : conversaciones
    Created on : 08-may-2021, 13:51:57
    Author     : dperez
--%>

<%@page import="grupo10.entity.Evento"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="grupo10.entity.Conversacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Conversacion> conversaciones = (List)request.getAttribute("conversaciones");
    List<Usuario> asistentes = (List)request.getAttribute("asistentes");
    List<Evento> eventos = (List)request.getAttribute("eventos");
    Usuario yo = (Usuario)request.getAttribute("yo");
    String filtroOtro = (String)request.getAttribute("filtroOtro");
    String filtroEvento = (String)request.getAttribute("filtroEvento");
    Boolean algunFiltro = (Boolean)request.getAttribute("algunFiltro");
%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="css/estilo.css" rel="stylesheet" />
        
        <title>Mis conversaciones</title>
        
    </head>
    <body>
        <h1>Mis conversaciones</h1>
        <%
            if (conversaciones.size() > 0) {
        %>
        <table border="1" cellpadding="4">
            <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Iniciado por</th>
                    <th>Evento</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                if (algunFiltro) {
                    %><p>Número de conversaciones que cumplen los filtros: <%= conversaciones.size() %></p><%
                }
                
                for (Conversacion c : conversaciones) {
                    Usuario otraPersona = c.getIdusuario();
                    Usuario iniciador = c.getIdteleoperador();
                    if (otraPersona.equals(yo)) {
                        otraPersona = c.getIdteleoperador();
                    }
                %>
                <tr>
                    <td><%= otraPersona.getNombre() %> <%= otraPersona.getApellidos() %></td>
                    <td><%= iniciador.getNombre() %> <%= iniciador.getApellidos() %></td>
                    <td><%= c.getIdevento().getNombre() %></td>
                    <td><a href="ChatServlet?conversacion=<%= c.getId() %>">Entrar</a></td>
                    <td><a href="BorrarConversacionServlet?conversacion=<%= c.getId() %>">Borrar</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <%
            } else if (algunFiltro) {
                %>No tienes ninguna conversación que cumpla los filtros.<%
            } else {
                %>No tienes ninguna conversación.<%
            }
        %>
        
        <h2>Filtrar conversaciones</h2>
        <form action="ConversacionesServlet" method="get">
            
            <label for="campo-filtro-usuario">Nombre de usuario contiene:</label>
            <input type="text" id="campo-filtro-usuario" name="filtroOtro" value="<%= filtroOtro %>"/>
            
            <label for="campo-filtro-evento">Nombre del evento contiene:</label>
            <input type="text" id="campo-filtro-evento" name="filtroEvento" value="<%= filtroEvento %>"/>
            
            <input type="submit" value="Filtrar" />
            
        </form>
        
        <h2>Iniciar una conversación</h2>
        <form action="IniciarConversacionServlet" method="post">
            
            <label for="campo-usuario">Usuario:</label>
            <select id="campo-usuario" name="usuario">
                <option value="-1">Seleccionar...</option>
                <% for (Usuario a : asistentes) { %>
                <option value="<%= a.getId() %>"><%= a.getNombre() %> <%= a.getApellidos() %></option>
                <% } %>
            </select>
            
            <label for="campo-evento">Evento:</label>
            <select id="campo-evento" name="evento">
                <option value="-1">Seleccionar...</option>
                <% for (Evento e : eventos) { %>
                <option value="<%= e.getId() %>"><%= e.getNombre() %></option>
                <% } %>
            </select>
            
            <input type="submit" value="Crear chat" />
            <a href="CerrarSesionServlet">Cerrar sesión</a>
        </form>
    </body>
</html>
