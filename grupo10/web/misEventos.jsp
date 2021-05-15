<%-- 
    Document   : misEventos
    Created on : 01-may-2021, 17:59:27
    Author     : Hielito
--%>
<%@page import="java.util.StringJoiner"%>
<%@page import="grupo10.entity.Etiquetas"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="grupo10.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilo.css"/>
        <title>Mis Eventos</title>
    </head>
    <% 
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Evento> listaEventos = (List<Evento>) request.getAttribute("eventos");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String buscador = (request.getParameter("busqueda") ==null) ? "" : request.getParameter("busqueda") ;
    %>
    <body>
        <h1>Lista de Eventos:</h1>
        <form action="CargaMisEventosServlet">
            <input type="text" class="search" placeholder="Buscador..."  name="busqueda" value="<%=buscador %>" />
            <input type="submit" value="Filtrar por nombre o etiqueta" />
        </form>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Fecha Evento</th>
                    <th>Fecha Reserva</th>
                    <th>Precio</th>
                    <th>Aforo</th>
                    <th>Etiquetas</th>
                </tr>
            </thead>
            <tbody>
                
                <%
                    for (Evento e : listaEventos) {
                        boolean filtro = false;
                        StringJoiner etiquetas = new StringJoiner(",","","");
                        filtro = filtro || e.getNombre().toUpperCase().contains(buscador.toUpperCase());
                        
                        for (Etiquetas etiq : e.getEtiquetasList()) {
                            if (!filtro) filtro = filtro || etiq.getEtiquetasPK().getEtiqueta().toUpperCase().contains(buscador.toUpperCase());
                            etiquetas.add(etiq.getEtiquetasPK().getEtiqueta());
                        }
                        
                        if (filtro && ( e.getIdcreador().getId() == usuario.getId() || usuario.getTipousuario() == 5)) {
                
                %>
                        
                <tr>
                    <th><%=e.getNombre()%></th>
                    <td><%=e.getDescripcion()%></td>
                    <td><%=format.format(e.getFechaevento()) %></td>
                    <td><%=format.format(e.getFechalimiteentradas()) %></td>
                    <td><%=e.getPrecioentrada()%></td>
                    <td><%=e.getAforo() %></td>
                    <td>
                        <%
                            if (e.getEtiquetasList().size() > 0) {
                                String[] etiquetasArray = etiquetas.toString().split(",");
                                for (String etiq : etiquetasArray) {
                                    %><a href="" class="etiqueta"><%= etiq %></a><%
                                }
                            }
                        %>
                    </td>
                    <td><a href="EditaEventoServlet?id=<%=e.getId() %>">Editar...</a></td>
                    <td><a href="BorraEventoServlet?id=<%=e.getId() %>">Borrar...</a></td>
                    
                </tr>
                <%      }
                    }
                %>
            </tbody>
        </table>
       <br/>
       <br/>
       <br/>
       <a href="EditaEventoServlet">Nuevo evento...</a>
       <br/><br/>
       <%
           if (usuario.getTipousuario()==5) 
           %><a href="AdminServlet">Volver a Admin</a><%
       %>
       <a href="ConversacionesServlet">Conversaciones</a>
       <a href="CerrarSesionServlet">Cerrar sesión</a>
    </body>
</html>
