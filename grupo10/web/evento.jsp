<%-- 
    Document   : evento
    Created on : 08-may-2021, 18:22:06
    Author     : Fran
--%>

<%@page import="grupo10.entity.Etiquetas"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="grupo10.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles del evento</title>
        <link rel='stylesheet' href='css/estilo.css' />
    </head>
    <body>
        
        <% 
            Evento evento = (Evento)request.getAttribute("evento");
            String opcion = (String)request.getAttribute("opcion");
            Integer aforo = (Integer)request.getAttribute("aforo");
            Integer entradasDisponibles =(Integer)request.getAttribute("entradas");
            Integer filaAsiento = (Integer)request.getAttribute("filaAsiento");
            Integer columnaAsiento = (Integer)request.getAttribute("columnaAsiento");
        %>
        <h1><%= evento.getNombre() %></h1>
        <h2><%= evento.getDescripcion() %></h2>
        <% 
            List<Etiquetas> etiquetas;
            etiquetas = evento.getEtiquetasList();
            String strEtiqueta;
            if (etiquetas != null && !etiquetas.isEmpty()){
                strEtiqueta = "'";
                for (Etiquetas etiqueta : etiquetas){
                    strEtiqueta += etiqueta.getEtiquetasPK().getEtiqueta() + "', '";
                }
                // Eliminamos el último (,')
                strEtiqueta = strEtiqueta.substring(0, strEtiqueta.length() - 3);
            } else {
                strEtiqueta = "";
            }
        %>
        <h2>Etiquetas: <%= strEtiqueta %></h2>
        <%   
            String opcionBoton;
            String goTo;
            if (opcion.equals("registrado")){
                // Boton anular evento
                opcionBoton = "Eliminar reserva evento";
                goTo = "DesuscribirEventoServlet?id=" + evento.getId() + "&asiento=" + filaAsiento + ";" + columnaAsiento;
        %> 
        <h3>Evento reservado</h3>
        Fecha del evento: <%=new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaevento()) %><br/>
        Aforo máximo: <%= evento.getAforo() %><br/>
        <%
            } else {
                // Boton reservar evento
                opcionBoton = "Inscribirse al evento";
                if (filaAsiento != null && columnaAsiento != null){
                    goTo = "InscripcionServlet?id=" + evento.getId() + "&asiento=" + filaAsiento + ";" + columnaAsiento;
                } else {
                    goTo = "InscripcionServlet?id=" + evento.getId();
                }
        %>
        Fecha del evento: <%=new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaevento()) %><br/>
        Fecha máxima para comprar las entradas:<%=new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechalimiteentradas()) %><br/>
        Precio entrada individual: <%= evento.getPrecioentrada() %><br/>
        <br/>
        Aforo: <%= aforo %><br/>
        <%
                if (entradasDisponibles > 0){
        %>
        Número de entradas disponibles: <%= Math.min(entradasDisponibles,aforo) %>
        <% 
                }
                if (evento.getNfilas() > 0){
                    // Se pueden elegir los asientos
                    String goToAsiento = "AsientoServlet?id=" + evento.getId() + "&aforo=" + aforo + "&entradasDisponibles=" + entradasDisponibles; 
       %>
        <form action="<%= goToAsiento %>" method='POST'>
            <input type="submit" value="Elegir asiento" />
        </form>
        <%  
                }
            }
            
            // Mostrar el boton enabled
            if (evento.getNfilas() < 0){
        %>
        <br/>
        <form action="<%= goTo %>" method='POST'>
            <input type="submit" value="<%= opcionBoton %>" />
        </form>
        <% 
            // Mostrar el boton enabled y el asiento seleccionado
            } else if (evento.getNfilas() >= 0){
                if (filaAsiento != null && columnaAsiento != null){
        %>
        Asiento seleccionado: <%= "( Fila " + filaAsiento + ", Butaca " + columnaAsiento + " )" %>
        <br/>
        <form action="<%= goTo %>" method='POST'>
            <input type="submit" value="<%= opcionBoton %>" />
        </form>
        <%
                } else {
        %>
        <input type="submit" disabled =" disabled" value="<%= opcionBoton %>" />
        <%
                }
            }
        %>
    </body>
</html>
