/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.EventoFacade;
import grupo10.dao.InscripcionFacade;
import grupo10.entity.Evento;
import grupo10.entity.Inscripcion;
import grupo10.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Collections.list;
import java.util.List;
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
@WebServlet(name = "DesuscribirEventoServlet", urlPatterns = {"/DesuscribirEventoServlet"})
public class DesuscribirEventoServlet extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;

    @EJB
    private InscripcionFacade inscripcionFacade;

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
        String asiento = request.getParameter("asiento");
        Integer filaAsiento;
        Integer columnaAsiento;
        Inscripcion inscripcion;
        HttpSession session = request.getSession();
        
        if (asiento == null){
            // Fila = -1 Columna = -1
            filaAsiento = -1;
            columnaAsiento = -1;
        }  else {
            // asientos : F;C
            String[] asientosProcesados = asiento.split(";");
            
            filaAsiento = Integer.parseInt(String.valueOf(asientosProcesados[0]));
            columnaAsiento = Integer.parseInt(String.valueOf(asientosProcesados[1]));
        }        
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        inscripcion = this.inscripcionFacade.findByAll(Integer.parseInt(idEvento), usuario.getId(), filaAsiento, columnaAsiento);
        
        // Eliminamos la inscripcion
        this.inscripcionFacade.remove(inscripcion);
        
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
