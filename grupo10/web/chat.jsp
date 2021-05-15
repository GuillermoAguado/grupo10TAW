<%-- 
    Document   : chat
    Created on : 09-may-2021, 13:47:44
    Author     : Daniel Pérez Porras
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="grupo10.entity.Conversacion"%>
<%@page import="grupo10.entity.Mensaje"%>
<%@page import="java.util.List"%>
<%@page import="grupo10.entity.Usuario"%>
<%@page import="grupo10.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Usuario yo = (Usuario)request.getAttribute("yo");
Usuario otro = (Usuario)request.getAttribute("otro");
Conversacion conversacion = (Conversacion)request.getAttribute("conversacion");
Evento evento = conversacion.getIdevento();
Usuario iniciador = conversacion.getIdteleoperador();
List<Mensaje> mensajes = (List)request.getAttribute("mensajes");
DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo.css" rel="stylesheet" />
        <link href="css/chat.css" rel="stylesheet" />
        <title>Chat</title>
    </head>
    <body>
        
        <div id="cabecera-chat">
            <h1 class="titulo-chat">Chat con <%= otro.getNombre() %> <%= otro.getApellidos() %> (<%= evento.getNombre() %>)</h1>
            <p>Iniciada por <%= iniciador.getNombre() %> <%= iniciador.getApellidos() %></p>
        </div>
        
        <div id="cuerpo-chat">
            <div id="mensajes"></div>
        </div>
           
        <div id="pie-chat">
            <input id="campo-mensaje" onkeypress="enterToSend(event)" type="text" maxlength="200" name="mensaje" autofocus />
            <button id="boton-enviar" onclick="postMessage()">Enviar</button>
        </div>
                
        <script>
            
    const contenedorMensajes = document.getElementById("mensajes");
    const campoMensaje = document.getElementById("campo-mensaje");
    var messagesWaiting = false;   
    
    function postMessage() {

        const messageText = encodeURIComponent(campoMensaje.value);
        if (messageText.length > 0) {
            
            const xmlhttp = new XMLHttpRequest();

            xmlhttp.open("POST", "EnviaRecibeChatServlet", false);
            xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            campoMensaje.value = "";
            xmlhttp.send("conversacion=<%= conversacion.getId() %>&mensaje=" + messageText);

        }
        
    }

    function getMessages(){
        if (!messagesWaiting) {
            messagesWaiting = true;
            
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    messagesWaiting = false;
                    contenedorMensajes.innerHTML = xmlhttp.responseText;
                }
            };
            
            xmlhttp.open("GET", "EnviaRecibeChatServlet?conversacion=<%= conversacion.getId() %>", true);
            xmlhttp.send();
        }
    }
    
    function enterToSend(event) {
        if (event.key === "Enter") {
            postMessage();
        }
    }
    
    getMessages();
    setInterval(getMessages, 1000);
            
        </script>
            
    </body>
</html>
