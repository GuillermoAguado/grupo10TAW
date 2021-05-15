/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.EventoFacade;
import grupo10.dao.InscripcionFacade;
import grupo10.entity.Evento;
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
 * @author Fran
 */
@WebServlet(name = "InfoEventoServlet", urlPatterns = {"/InfoEventoServlet"})
public class InfoEventoServlet extends HttpServlet {

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
        String opcion = request.getParameter("opcion");
        String asiento = request.getParameter("asiento");
        String fila = request.getParameter("fila");
        String columna = request.getParameter("columna");
        String aforo = request.getParameter("aforo");
        String entradas = request.getParameter("entradas");
        Evento evento = this.eventoFacade.find(Integer.parseInt(idEvento));
        boolean puedeRegistrarse = true;
        
        if (asiento != null){
            // Asiento: F;C
            String[] asientosProcesados = asiento.split(";");
            
            int filaAsiento = Integer.parseInt(String.valueOf(asientosProcesados[0]));
            int columnaAsiento = Integer.parseInt(String.valueOf(asientosProcesados[1]));
            
            request.setAttribute("filaAsiento", filaAsiento);
            request.setAttribute("columnaAsiento", columnaAsiento);
        } else if (fila != null && columna != null){
            int filaAsiento = Integer.parseInt(fila);
            int columnaAsiento = Integer.parseInt(columna);
            
            request.setAttribute("filaAsiento", filaAsiento);
            request.setAttribute("columnaAsiento", columnaAsiento);
        } 
        if (aforo != null && entradas != null){
            int aforoDisponible = Integer.parseInt(aforo);
            int entradasDisponibles = Integer.parseInt(entradas);
            
            request.setAttribute("aforo", aforoDisponible);
            request.setAttribute("entradas", entradasDisponibles);
        }
        
        request.setAttribute("evento", evento);
        request.setAttribute("opcion", opcion);
        
        RequestDispatcher rd = request.getRequestDispatcher("evento.jsp");
        rd.forward(request, response);
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
