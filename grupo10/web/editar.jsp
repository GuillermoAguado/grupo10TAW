<%-- 
    Document   : editar
    Created on : 15-may-2021, 17:05:21
    Author     : manul
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="grupo10.entity.Estudiobd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar el analisis</title>
                <link rel ="stylesheet" href="css/estiloanalista.css">
    </head>
    <%
        Estudiobd estudio;
        estudio=(Estudiobd)request.getAttribute("estudio");
        Usuario usuario;
        usuario = (Usuario)request.getAttribute("usuario");
        boolean tipoestudiousuario=false;
        String tipoestudio = "evento";
        if(estudio.getAforofiltro()==-1){
            tipoestudiousuario=true;
            tipoestudio="usuario";
        }
        Integer id=estudio.getId();
    %>
    <body>
        <h1>Lista de análisis del usuario <%=usuario.getNombre() + " " + usuario.getApellidos()%>:</h1>
        <form action="AnalistaServletInicial" method="POST">
            <input type="hidden" value="<%=usuario.getId()%>"/>
            <input type="submit" value="Volver a la vista principal sin guardar"/>
        </form>
        <form action="ServletModificar" method="POST">
            <input type="hidden" name="idusuario" id="idusuario" value="<%=usuario.getId()%>"/>
            <input type="hidden" name="tipoestudio" id="tipoestudio" value="<%=tipoestudio%>"/>
            <input type="hidden" name="idestudio" id="idestudio" value="<%=id%>"/>
            <input type="submit" value="Guardar cambios"/>

        <table border ="1" name="table">
            <thead>
                <%
                    if(tipoestudiousuario){
                %>
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>EDADMAYORQUE</th>
                    <th>EDADMENORQUE</th>
                    <th>SEXO</th>
                    <th>CIUDAD</th>
                </tr>
                <%
                    }else{
                            %>
                            
                  <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>FECHA</th>
                    <th>AFORO</th>
                </tr>          
                <%
                    }
                %>
            </thead>
            <%                
                
                String nombre=estudio.getNombre();
                String edadmayor=""; 
                if(estudio.getEdadfiltro()>0){
                    Integer i = new Integer(estudio.getEdadfiltro());
                    edadmayor= i.toString();
                }
                String edadmenor="";
                if(estudio.getEdadmenorquefiltro()>0){
                edadmenor=estudio.getEdadmenorquefiltro().toString();
                }
                String sexo = "";
                if(estudio.getSexofiltro().equals("N")==false){
                    sexo=estudio.getSexofiltro();
                }
                
                String ciudad="";
                if(estudio.getCiudadusuariofiltro().equals("ninguna")==false){
                ciudad=estudio.getCiudadusuariofiltro();
                }
                Date fecha = null;
                DateFormat fechaformat = new SimpleDateFormat("yyyy-MM-dd");
                Date pruebafecha = fechaformat.parse("1001-01-01");
                String fecha2="";
                if(estudio.getAnyofiltro().compareTo(pruebafecha)!=0){
                    fecha=estudio.getAnyofiltro();
                    fecha2=fechaformat.format(fecha);
                }
                String aforo="";
                if(estudio.getAforofiltro()>0){
                    Integer i2 = new Integer(estudio.getAforofiltro());
                    aforo=i2.toString();
                }
                %>
                
                <%
                    if(tipoestudiousuario){
                %>
            <tr>
                <td><%=id%></td>
                 <td><input type="text" name="nombre" id="nombre" value="<%=nombre%>"/></td>
                  <td><input type="text" name="edadmayor" id="edadmayor" value="<%=edadmayor%>"/></td>
                   <td><input type="text" name="edadmenor" id="edadmenor" value="<%=edadmenor%>"/></td>
                    <td><input type="text" name="sexo" id="sexo" value="<%=sexo%>"/></td>
                     <td><input type="text" name="ciudad" id="ciudad" value="<%=ciudad%>"/></td>
            </tr>
            <%
                }else{
                %>
                <tr>
                <td><%=id%></td>
                 <td><input type="text" name="nombre" id="nombre" value="<%=nombre%>"/></td>
                      <td><input type="text" name="fecha" id="fecha" value="<%=fecha2%>"/></td>
                       <td><input type="text" name="aforo" id="aforo" value="<%=aforo%>"/></td>
            </tr>
            <%
                }
                %>
        </table>
        </form>
    </body>
</html>
