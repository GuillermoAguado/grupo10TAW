/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.EventoFacade;
import grupo10.dao.InscripcionFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Evento;
import grupo10.entity.Inscripcion;
import grupo10.entity.InscripcionPK;
import grupo10.entity.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fran
 */
@WebServlet(name = "InscripcionServlet", urlPatterns = {"/InscripcionServlet"})
public class InscripcionServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private InscripcionFacade inscripcionFacade;

    @EJB
    private EventoFacade eventoFacade;

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
        String idEvento = request.getParameter("id");
        String usuarioId = request.getParameter("usuario");
        String strAsiento = request.getParameter("asiento");
        HttpSession session; 
        Usuario usuario; 
        Evento evento = null;
        Inscripcion inscripcion = null;
        InscripcionPK inscripcionPK = null;
        // Variables para los asientos
        Integer filaAsiento;
        Integer columnaAsiento;
                
        // Cargamos el usuario, y el asiento
            if (usuarioId == null){
                session = request.getSession();
                usuario =  (Usuario) session.getAttribute("usuario");
            } else {
                usuario = this.usuarioFacade.find(usuarioId);
            }
            if (strAsiento == null){
                filaAsiento = -1;
                columnaAsiento = -1;
            } else {
                // asientos : F;C 
                String[] asientosProcesados = strAsiento.split(";");
            
                filaAsiento = Integer.parseInt(String.valueOf(asientosProcesados[0]));
                columnaAsiento = Integer.parseInt(String.valueOf(asientosProcesados[1]));
            }
            evento = this.eventoFacade.find(Integer.parseInt(idEvento));
            
            inscripcion = new Inscripcion();
            inscripcionPK = new InscripcionPK();
            inscripcionPK.setColumnaasiento(columnaAsiento);
            inscripcionPK.setFilaasiento(filaAsiento);
            inscripcionPK.setIdevento(Integer.parseInt(idEvento));
            inscripcionPK.setIdusuario(usuario.getId());
            inscripcion.setEvento(evento);
            inscripcion.setUsuario(usuario);
            inscripcion.setInscripcionPK(inscripcionPK);
            this.inscripcionFacade.create(inscripcion);

            response.sendRedirect("ListaEventosServlet");
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
