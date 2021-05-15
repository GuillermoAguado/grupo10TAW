package grupo10.util;

import grupo10.dao.UsuarioFacade;
import grupo10.entity.Conversacion;
import grupo10.entity.Evento;
import grupo10.entity.Usuario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Métodos de utilidad para manejar sesiones (p. ej. redirigir al login si no hemos hecho login).
 * @author Daniel Pérez Porras
 */
public class SessionUtils {
    
    /**
     * Devuelve el usuario actualmente logueado.
     * @param request La petición HTTP
     * @param facade La fachada de usuarios a utilizar para buscar el usuario
     * (vendrá del servlet, no sé si es buena idea pasarla así)
     * @return El usuario logueado, null si no estamos logueados
     * @todo debería depender de la sesión, no del getParameter.
     */
    public static Usuario getUsuarioLogueado(HttpServletRequest request) {
        return (Usuario)request.getSession().getAttribute("usuario");
    }
    
    public static boolean puedoChatear(Usuario yo) {
        boolean resultado = false;
        if (yo != null) {
            int tipo = yo.getTipousuario();
            resultado = tipo == 1 || tipo == 2 || tipo == 4 || tipo == 5;
        }
        return resultado;
    }
    
    public static boolean pertenezcoAConversacion(Usuario yo, Conversacion conversacion) {
        return yo != null && conversacion != null
                && (conversacion.getIdusuario().equals(yo)
                || (conversacion.getIdteleoperador().equals(yo)));
    }
    
    public static boolean puedoIniciarChat(Usuario yo, Usuario otro, Evento evento) {
        // Condiciones:
        // - Debo tener un rol que me permita chatear
        // - Si soy asistente, solo puedo chatear con creadores.
        // - Si soy creador de eventos, solo puedo crear chats para mis eventos.
        return yo != null && otro != null && evento != null
              && puedoChatear(yo)
              && (yo.getTipousuario() != 1 || otro.getTipousuario() == 2)
              && (yo.getTipousuario() != 2 || evento.getIdcreador().equals(yo));
    }
    
    public static void redirigirLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("LoginServlet");
    }

}
