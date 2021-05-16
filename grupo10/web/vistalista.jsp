<%-- 
    Document   : vistalista
    Created on : 14-may-2021, 19:42:52
    Author     : manul
--%>

<%@page import="grupo10.entity.Usuario"%>
<%@page import="grupo10.entity.Estudiobd"%>
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
        List<Usuario> lista;
        lista =(List)request.getAttribute("listaUsers");
        Usuario usuario;
        usuario = (Usuario)request.getAttribute("usuario");
        String salida = "no vacia";
        Estudiobd estudio = (Estudiobd) request.getAttribute("estudio");
        String modo =(String) request.getAttribute("modo");
        if(lista == null || lista.isEmpty() ){

        }
        session.setAttribute("usuarioenvio", usuario);
        session.setAttribute("estudioenvio", estudio);
        session.setAttribute("listaenvio", lista);
    %>
    <body>
        <h1>Lista de usuarios del estudio:</h1>
        <form action="AnalistaServletInicial" method="POST">
            <input type="hidden" value="<%=usuario.getId()%>"/>
            <input type="submit" value="Volver a la vista principal sin guardar"/>
        </form>
            <%
                if(modo.equals("escritura")){
                %>
        <form action="ServletGuardar" method="POST">
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
                    <th>EMAIL</th>
                    <th>NOMBRE COMPLETO</th>
                    <th>DOMICILIO</th>
                    <th>CIUDAD DE RESIDENCIA</th>
                    <th>EDAD</th>
                    <th>SEXO</th>
                </tr>
            </thead>
            <%
                if(lista != null && !lista.isEmpty() ){
                int i=0;
                Integer id;
                String nombre;
                String email;
                String domicilio;
                String ciudad_res;
                Integer edad;
                String sexo;
                for(Usuario u: lista){
                    i++;
                    id = u.getId();
                    nombre = u.getNombre()+" "+u.getApellidos();
                    email = u.getEmail();
                    domicilio = u.getDomicilio();
                    ciudad_res=u.getCiudadRes();
                    edad=u.getEdad();
                    sexo=u.getSexo();
                %>
            <tr>
                <td><%=id%></td>
                <td><%=nombre%></td>
                <td><%=email%></td>
                <td><%=domicilio%></td>
                <td><%=ciudad_res%></td>
                <td><%=edad%></td>
                <td><%=sexo%></td>
            </tr>
            <%
                }
               }
                %>
        </table>
        
    </body>
</html>
