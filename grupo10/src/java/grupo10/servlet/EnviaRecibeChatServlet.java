package grupo10.servlet;

import grupo10.dao.ConversacionFacade;
import grupo10.dao.MensajeFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Conversacion;
import grupo10.entity.Mensaje;
import grupo10.entity.Usuario;
import grupo10.util.SessionUtils;
import grupo10.util.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dperez
 */
@WebServlet(urlPatterns = {"/EnviaRecibeChatServlet"}, asyncSupported=true)
public class EnviaRecibeChatServlet extends HttpServlet {

    @EJB
    private MensajeFacade mensajeFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private ConversacionFacade conversacionFacade;

    // RECIBIR MENSAJES
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario yo = SessionUtils.getUsuarioLogueado(request);
        Conversacion conversacion = getConversacion(request);
        
        if (SessionUtils.pertenezcoAConversacion(yo, conversacion)) {
        
            List<Mensaje> mensajes = mensajeFacade.findConversacionOrdenada(conversacion);
            
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("text/html;charset=UTF-8");
                DateFormat formatoFechas = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                for (Mensaje m : mensajes) {
                    
                    if (m.getIdemisor().equals(yo)) {
                        out.write("<div class=\"mensaje-mio\">");
                    } else {
                        out.write("<div class=\"mensaje\">");
                    }
                    
                    out.write("<p class=\"mensaje-texto\">" + StringUtils.escaparHtml(m.getContenido()) + "</p>");
                    out.write("<p class=\"mensaje-fecha\">" + formatoFechas.format(m.getFechahora()) + "</p>");
                    
                    out.write("</div>");
                    
                }
            }
            
        }
    }

    // ENVIAR MENSAJES
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario yo = SessionUtils.getUsuarioLogueado(request);
        Conversacion conversacion = getConversacion(request);
        
        byte[] bytesMensaje = request.getParameter("mensaje").getBytes();
        String mensaje = new String(bytesMensaje, StandardCharsets.UTF_8);
        
        if (mensaje == null) {
            mensaje = "";
        } else if (mensaje.length() > 200) {
            // máximo 200 caracteres
            mensaje = mensaje.substring(0, 200);
        }
        
        // Si tenemos permiso, enviamos
        if (SessionUtils.pertenezcoAConversacion(yo, conversacion)) {
            
            Mensaje nuevo = new Mensaje();
            
            nuevo.setContenido(mensaje);
            nuevo.setFechahora(new Date());
            nuevo.setIdconversacion(conversacion);
            nuevo.setIdemisor(yo);
            
            mensajeFacade.create(nuevo);
            
        }
        
    }
    
    private Conversacion getConversacion(HttpServletRequest request) {
        int idConversacion = -1;
        try {
            idConversacion = Integer.parseInt(request.getParameter("conversacion"));
        } catch (NumberFormatException | NullPointerException ex) { }
        return conversacionFacade.find(idConversacion);
    }

}
