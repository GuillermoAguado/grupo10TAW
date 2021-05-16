<%-- 
    Document   : editarEvento
    Created on : 09-may-2021, 14:02:38
    Author     : Hielito
--%>

<%@page import="grupo10.entity.Etiquetas"%>
<%@page import="java.util.List"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="grupo10.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Evento evento = (Evento) request.getAttribute("evento");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<String> etiquetas = (List<String>) request.getAttribute("etiquetas");
    String error = request.getParameter("error");
    String urlForward;
    if (evento == null) {
        urlForward = "CreaEventoServlet";
    } else {
        urlForward = "ModificaEventoServlet";
    }
    
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilo.css"/>        
        <title>Editar Evento</title>
    </head>
    <body>
        <%
            if (evento == null) {
        %>
             <h1>Crear Evento:</h1>   
        <%
            } else {
        %>
            <h1>Editar evento: <%=evento.getNombre() %></h1>
        <%
            }
        %>
        
        <%
            if (error != null && error.equals("true")) {
            %>
        <h3 style="color:#FF0000">Hay un error en uno de los campos</h3>
            <%
            }
        %>
        
        <form action="<%= urlForward%>" method="POST">
            <input type="hidden" name="idEvento" value="<%= (evento!=null) ? evento.getId() : "" %>" />
            Creador: <input class="rellenar" type="text" name="nombrecreador" value="<%= (evento!=null) ? evento.getIdcreador().getNombre() + " " + evento.getIdcreador().getApellidos()  : usuario.getNombre() + " " + usuario.getApellidos() %>" readonly="readonly" />
            <input type="hidden" name="idCreador" value="<%= (evento!=null) ? evento.getIdcreador().getId() : usuario.getId() %>" />
            Nombre: <input class="rellenar" type="text" name="nombre" value="<%= (evento!=null) ? evento.getNombre() : "" %>" />
            Descripci&oacute;n: <input class="rellenar" type="text" name="descripcion" value="<%= (evento!=null) ? evento.getDescripcion() : "" %>" />
            Fecha Evento (Formato: dd/MM/yyyy): <input class="rellenar" type="text" name="fechaevento" value="<%= (evento!=null) ? format.format(evento.getFechaevento()) : "" %>" />
            Fecha L&iacute;mite (Debe ser anterior a la del evento) (Formato: dd/MM/yyyy): <input class="rellenar" type="text" name="fechalimite" value="<%= (evento!=null) ? format.format(evento.getFechalimiteentradas()) : "" %>" />
            Precio por entrada: <input class="rellenar" type="text" name="precio" value="<%= (evento!=null) ? evento.getPrecioentrada() : "" %>" />
            Aforo: <input class="rellenar" type="text" name="aforo" value="<%= (evento!=null) ? evento.getAforo() : "" %>" />
            M&aacute;ximo de entradas por usuario: <input class="rellenar" type="text" name="maximoentradas" value="<%= (evento!=null) ? evento.getMaximoentradasusuario() : "" %>" />
            
            Filas y columnas:<br />
            Especifica n.&ordm; de filas y n.&ordm; de columnas o deja ambos campos vac&iacute;os.
            <input class="rellenar" type="text" name="filas" placeholder="Sin Asignar" value="<%= (evento!=null) ? ((evento.getNfilas()<0) ? "" : evento.getNfilas()) : "" %>" />
            <input class="rellenar" type="text" name="columnas" placeholder="Sin Asignar" value="<%= (evento!=null) ? ((evento.getAsientosfila()<0) ? "" : evento.getAsientosfila()): "" %>" />
            
            
            Etiquetas: 
            <br/>
            <% 
                int i = 0;
                for (String e : etiquetas) {
                    i = i+1;
                    boolean check = false;
                    if (evento!=null) {
                        for (Etiquetas etiq : evento.getEtiquetasList()) {
                            if (e.equals(etiq.getEtiquetasPK().getEtiqueta())) {
                                check = true;
                                break;
                            }
                        }
                    }
                    
            %>
            <input type="checkbox" name="Etiquetas" id="<%=e %>" value="<%=e %>" <%= (check) ? "checked=\"checked\"" : "" %>/>
            <label for="<%=e %>"><%=e %></label>
            <%
                    if (i%5==0) {
            %>
            <br/>
            <%
                    }

                }
            %>
            <br/>
            <br/>
            ¿No encuentras las etiquetas que buscas?
            Escr&iacute;bela aqu&iacute; separadas por comas y sin espacios
            <input class="rellenar" type="text" name="etiquetasnuevas" value="" />
            
            <input type="submit" value="Guardar Evento" />
        </form>
        
    </body>
</html>

