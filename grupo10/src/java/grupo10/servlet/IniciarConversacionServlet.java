package grupo10.servlet;

import grupo10.dao.ConversacionFacade;
import grupo10.dao.EventoFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Conversacion;
import grupo10.entity.Evento;
import grupo10.entity.Usuario;
import grupo10.util.SessionUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dperez
 */
@WebServlet(name = "IniciarConversacionServlet", urlPatterns = {"/IniciarConversacionServlet"})
public class IniciarConversacionServlet extends HttpServlet {

    @EJB
    private ConversacionFacade conversacionFacade1;

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
        
        Usuario otraPersona = null;
        Evento evento = null;
        Usuario yo = SessionUtils.getUsuarioLogueado(request);
        
        try {
            int id = Integer.parseInt(request.getParameter("usuario"));
            otraPersona = usuarioFacade.find(id);
        } catch (NumberFormatException | NullPointerException ex) { }
        try {
            int id = Integer.parseInt(request.getParameter("evento"));
            evento = eventoFacade.find(id);
        } catch (NumberFormatException | NullPointerException ex) { }
        
        if (SessionUtils.puedoIniciarChat(yo, otraPersona, evento)) {
            
            Conversacion nueva = new Conversacion();
            
            nueva.setIdevento(evento);
            nueva.setIdteleoperador(yo);
            nueva.setIdusuario(otraPersona);
            nueva.setIdevento(evento);
            
            conversacionFacade.create(nueva);
            response.sendRedirect("ChatServlet?conversacion=" + nueva.getId());
            
        } else {
            response.sendRedirect("ConversacionesServlet");
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
