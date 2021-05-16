<%-- 
    Document   : vistalistaevento
    Created on : 15-may-2021, 11:30:25
    Author     : manul
--%>

<%@page import="java.util.Date"%>
<%@page import="grupo10.entity.Evento"%>
<%@page import="grupo10.entity.Estudiobd"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
        <link rel ="stylesheet" href="css/estiloanalista.css">
    </head>
    <%
        List<Evento> lista;
        String salida = "no vacia";
        lista =(List)request.getAttribute("listaEventos");
        if(lista.isEmpty() || lista == null){
            salida = "vacia";
        }
        String modo =(String) request.getAttribute("modo");
        Usuario usuario;
        usuario = (Usuario)request.getAttribute("usuario");
        Estudiobd estudio = (Estudiobd) request.getAttribute("estudio");
        session.setAttribute("usuarioenvio", usuario);
        session.setAttribute("estudioenvio", estudio);
        session.setAttribute("listaenvio", lista);

    %>
    <body>
        <h1>Lista de eventos del estudio:<%=salida%></h1>
        <form action="AnalistaServletInicial" method="POST">
            <input type="hidden" value="<%=usuario.getId()%>"/>
            <input type="submit" value="Volver a la vista principal sin guardar"/>
        </form>
            <%
                if(modo.equals("escritura")){
                %>
        <form action="ServletGuardarEvento" method="POST">
            Introduce el nombre con el que guardar el estudio: <br/><input type="text" name="nombre" id="nombre"/>
            <input type="submit" value="Guardar el estudio"/>
        </form>
            <%
                }
                %>
        <table border ="1" name="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>DESCRIPCION</th>
                    <th>FECHA DEL EVENTO</th>
                    <th>FECHA LIMITE DE LAS ENTRADAS</th>
                    <th>PRECIO DE LAS ENTRADAS</th>
                    <th>AFORO</th>
                    <th>Nº MAXIMO DE ENTRADAS</th>
                    <th>Nº DE FILAS</th>
                    <th>ASIENTOS POR FILA</th>
                </tr>
            </thead>
            <%
                Integer id;
                String nombre;
                String descripcion;
                Date fecha;
                Date fechalimite;
                Double precio;
                Integer aforo;
                Integer maximoentradas;
                Integer filas;
                Integer asientos;
                for(Evento e: lista){
                    id = e.getId();
                    nombre = e.getNombre();
                    descripcion = e.getDescripcion();
                    fecha = e.getFechaevento();
                    fechalimite=e.getFechalimiteentradas();
                    precio=e.getPrecioentrada();
                    aforo=e.getAforo();
                    maximoentradas=e.getMaximoentradasusuario();
                    filas=e.getNfilas();
                    asientos=e.getAsientosfila();
                %>
            <tr>
                <td><%=id%></td>
                <td><%=nombre%></td>
                <td><%=descripcion%></td>
                <td><%=fecha%></td>
                <td><%=fechalimite%></td>
                <td><%=precio%></td>
                <td><%=aforo%></td>
                <td><%=maximoentradas%></td>
                <td><%=filas%></td>
                <td><%=asientos%></td>
            </tr>
            <%
                }
                %>
        </table>
        
    </body>
</html>
