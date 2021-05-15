<%-- 
    Document   : eventos
    Created on : 08-may-2021, 17:12:38
    Author     : Fran
--%>

<%@page import="grupo10.entity.Etiquetas"%>
<%@page import="grupo10.entity.Inscripcion"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="grupo10.entity.Evento"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos</title>
        <link rel='stylesheet' href='css/estilo.css' />
    </head>
    <body>
        <% 
            Usuario usuario = (Usuario)request.getAttribute("usuario");
            List<Evento> listaEventosDisponibles = (List)request.getAttribute("eventosDisponibles");
            List<Inscripcion> listaInscripciones = (List)request.getAttribute("inscripciones");
            
            String usuario_sexo;
            if (usuario.getSexo().equals("M")){
                usuario_sexo = "a";
            } else if (usuario.getSexo().equals("H")) {
                usuario_sexo = "o";
            } else {
                usuario_sexo = "e";
            }
        %>
        <h1>¡Bienvenid<%= usuario_sexo %> a tu gestor de eventos, <%= usuario.getNombre() %>!</h1>
        Dispones de una tabla con los eventos a los que puedes registrarte y otra para visualizar aquellos a los que
        te has registrado.<br/>
        Si quieres filtrar los eventos que has reservado, o aquellos finalizados, utiliza el buscador de "Filtrar eventos".<br/>
        Para cerrar sesión, sólo tienes que pulsar el botón "Cerrar sesión" que se sitúa en la parte baja del navegador.
        
        <br/>
        <h2>Mis reservas:</h2>
        <form action="ListaEventosServlet" method="POST">
            <b>Filtrar eventos:</b><br/>
            <td><input type="text" name="filtro" placeholder="Evento..." class="search" /> <input type="submit" value="Filtrar" /></td>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Fecha</th>
                    <th>Precio entrada</th>
                    <th>Asiento</th>
                    <th>Etiquetas</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    String strAsiento;
                    if (listaInscripciones != null && !listaInscripciones.isEmpty()){
                        for (Inscripcion i : listaInscripciones){
                            Evento e;
                            e = i.getEvento();
                            List<Etiquetas> etiquetas;
                            etiquetas = e.getEtiquetasList();
                            if (i.getInscripcionPK().getFilaasiento() < 0 && i.getInscripcionPK().getColumnaasiento() < 0){
                                strAsiento = "";
                            } else {
                                strAsiento = "(Fila " + i.getInscripcionPK().getFilaasiento()  + ", Butaca " + i.getInscripcionPK().getColumnaasiento() + ")";
                            }
                %>
                <tr>
                    <td><a href="InfoEventoServlet?id=<%= e.getId() %>&opcion=registrado&fila=<%= i.getInscripcionPK().getFilaasiento()%>&columna=<%= i.getInscripcionPK().getColumnaasiento() %>"><%= e.getNombre()%><a/></td>
                    <td><%= e.getDescripcion()%></td>
                    <td><%=new SimpleDateFormat("dd/MM/yyyy").format(e.getFechaevento()) %></td>
                    <td><%= e.getPrecioentrada() %></td>
                    <td><%= strAsiento %></td>
                    <td>
                    <%  
                            for (Etiquetas etiqueta: etiquetas){
                    %>
                    <a href="" class="etiqueta"><%= etiqueta.getEtiquetasPK().getEtiqueta() %></a>
                    <%
                            }
                    %>   
                    </td>
                <% 
                        }
                    }
                %>
            </tbody>
        </table>

        <br/>
        <h2>Eventos disponibles:</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Fecha</th>
                    <th>Fecha limite entradas</th>
                    <th>Precio entrada</th>
                    <th>Aforo</th>
                    <th>Num. entradas por persona</th>
                    <th>Etiquetas</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (listaEventosDisponibles != null && !listaEventosDisponibles.isEmpty()){
                        for (Evento evento : listaEventosDisponibles){
                            List<Etiquetas> etiquetas;
                            etiquetas = evento.getEtiquetasList();
                %>
                <tr>
                    <td><a href="InfoEventoServlet?id=<%= evento.getId() %>&opcion=disponible&aforo=<%= evento.getAforo() %>&entradas=<%= evento.getMaximoentradasusuario() %>"><%= evento.getNombre() %><a/></td>
                    <td><%= evento.getDescripcion()%></td>
                    <td><%=new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaevento()) %></td>
                    <td><%=new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechalimiteentradas()) %></td>
                    <td><%= evento.getPrecioentrada() %></td>
                    <td><%= evento.getAforo() %></td>
                    <td><%= evento.getMaximoentradasusuario() %></td>
                    <td>
                    <%  
                            for (Etiquetas etiqueta: etiquetas){
                    %>
                    <a href="" class="etiqueta"><%= etiqueta.getEtiquetasPK().getEtiqueta() %></a>
                    <%
                            }
                    %>   
                    </td>
                </tr>
                <% 
                        }
                    }
                %>
            </tbody>
        </table>
        <br>
        <a href="ConversacionesServlet">Conversaciones</a>
        <a href="CerrarSesionServlet">Cerrar sesión</a>
    </body>
</html>
