package grupo10.servlet;

import grupo10.dao.ConversacionFacade;
import grupo10.dao.EventoFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Conversacion;
import grupo10.entity.Evento;
import grupo10.entity.Usuario;
import grupo10.util.SessionUtils;
import grupo10.util.StringUtils;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dperez
 */
@WebServlet(name = "ConversacionesServlet", urlPatterns = {"/ConversacionesServlet"})
public class ConversacionesServlet extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;

    @EJB
    private ConversacionFacade conversacionFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        Usuario yo = SessionUtils.getUsuarioLogueado(request);
        if (SessionUtils.puedoChatear(yo)) {
        
            int tipo = yo.getTipousuario();
            String filtroOtro = request.getParameter("filtroOtro");
            String filtroEvento = request.getParameter("filtroEvento");
            
            List<Conversacion> conversaciones = conversacionFacade.findByUsuario(yo, filtroOtro, filtroEvento);
            
            List<Usuario> asistentes;
            if (tipo == 1) {
                asistentes = usuarioFacade.findAllCreadores();
            } else {
                asistentes = usuarioFacade.findAll();
            }
            
            List<Evento> eventos;
            if (tipo == 2) {
                eventos = eventoFacade.getByCreador(yo);
            } else {
                eventos = eventoFacade.findAll();
            }
            
            // Pasar información mediante atributos
            request.setAttribute("conversaciones", conversaciones);
            request.setAttribute("asistentes", asistentes);
            request.setAttribute("eventos", eventos);
            request.setAttribute("yo", yo);
            request.setAttribute("filtroOtro", filtroOtro != null ? StringUtils.escaparHtml(filtroOtro) : "");
            request.setAttribute("filtroEvento", filtroEvento != null ? StringUtils.escaparHtml(filtroEvento) : "");
            request.setAttribute("algunFiltro", filtroOtro != null || filtroEvento != null);

            // Reenviar petición al JSP
            RequestDispatcher rd = request.getRequestDispatcher("conversaciones.jsp");
            rd.forward(request, response);
        
        } else {
            
            // No autorizado, volver al login
            SessionUtils.redirigirLogin(response);
            
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
