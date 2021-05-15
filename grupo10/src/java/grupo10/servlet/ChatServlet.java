package grupo10.servlet;

import grupo10.dao.ConversacionFacade;
import grupo10.dao.MensajeFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Conversacion;
import grupo10.entity.Usuario;
import grupo10.util.SessionUtils;
import java.io.IOException;
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
@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends HttpServlet {

    @EJB
    private MensajeFacade mensajeFacade;

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
        
        int idConversacion = -1;
        try {
            idConversacion = Integer.parseInt(request.getParameter("conversacion"));
        } catch (NumberFormatException | NullPointerException ex) {
            response.sendRedirect("ConversacionesServlet");
        }
        
        Usuario yo = SessionUtils.getUsuarioLogueado(request);
        Conversacion conversacion = conversacionFacade.find(idConversacion);
        
        
        if (SessionUtils.pertenezcoAConversacion(yo, conversacion)) {
            
            Usuario otraPersona = conversacion.getIdusuario();
            if (otraPersona.equals(yo)) {
                otraPersona = conversacion.getIdteleoperador();
            }
            
            request.setAttribute("yo", yo);
            request.setAttribute("otro", otraPersona);
            request.setAttribute("conversacion", conversacion);
            request.setAttribute("mensajes", mensajeFacade.findConversacionOrdenada(conversacion));
            
            RequestDispatcher rd = request.getRequestDispatcher("chat.jsp");
            rd.forward(request, response);
            
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
