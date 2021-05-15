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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "AsientoServlet", urlPatterns = {"/AsientoServlet"})
public class AsientoServlet extends HttpServlet {

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
        String strEventoId = request.getParameter("id");
        String strAforo = request.getParameter("aforo");
        String entradasDisponibles = request.getParameter("entradasDisponibles");
        List<Inscripcion> listaInscripciones;
        boolean[][] ocupados ;
        Evento evento;
        
        // Calculamos la matriz de asientos
        listaInscripciones = this.inscripcionFacade.findByIdEvento(Integer.parseInt(strEventoId));
        evento = this.eventoFacade.find(Integer.parseInt(strEventoId));
        if (listaInscripciones != null && !listaInscripciones.isEmpty()){
            ocupados = new boolean[evento.getNfilas()][evento.getAsientosfila()];
            for (Inscripcion i : listaInscripciones){
                ocupados[i.getInscripcionPK().getFilaasiento()][i.getInscripcionPK().getColumnaasiento()] = true;
            }
        } else {
            // No hay inscripciones, devolvemos la matriz vacía
            ocupados = new boolean[evento.getNfilas()][evento.getAsientosfila()];
        }
        
        request.setAttribute("ocupados", ocupados);
        request.setAttribute("evento", evento);
        request.setAttribute("aforo", Integer.parseInt(strAforo));
        request.setAttribute("entradas", Integer.parseInt(entradasDisponibles));
        
        RequestDispatcher rd = request.getRequestDispatcher("inscripcion.jsp");
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
