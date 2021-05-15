<%-- 
    Document   : inscripcion
    Created on : 08-may-2021, 20:48:37
    Author     : Fran
--%>

<%@page import="grupo10.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscripcion</title>
        <link rel="stylesheet" href="css/estiloAsientos.css"/>
    </head>
    <body>
        <h1>Seleccione el asiento</h1>
        <% 
            boolean[][] ocupados = (boolean[][])request.getAttribute("ocupados");
            Evento evento = (Evento)request.getAttribute("evento");
        %>
        <table border="1">
            <tbody>
        <%
        for (int fila = 0; fila < evento.getNfilas(); fila++){
        %>
            <tr>
            <% 
                for (int columna = 0; columna < evento.getAsientosfila(); columna++){
                    if (ocupados[fila][columna]){
                    %> 
                        <th><input type="submit" disabled="disabled" value=" "/></th>
                    <%
                    } else {
                    %>
                        <form action="InfoEventoServlet?id=<%= evento.getId() %>&opcion=disponible&aforo=<%= evento.getAforo() %>&entradas=<%= evento.getMaximoentradasusuario() %>&asiento=<%= + fila + ";" + columna %>" method="POST">
                            <th><input type="submit" value=" "/></th>
                        </form> 
            <%
                    } 
                }
            %>
            </tr>
        <% 
        }
        %>
            </tbody>
        </table>

    </body>
</html>
