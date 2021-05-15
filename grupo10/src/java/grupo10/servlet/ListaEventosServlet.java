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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "ListaEventosServlet", urlPatterns = {"/ListaEventosServlet"})
public class ListaEventosServlet extends HttpServlet {

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
        String filtro = request.getParameter("filtro");
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        List<Inscripcion> listaInscripciones;
        List<Inscripcion> listaInscripcionesTotales;
        List<Evento> listaEventosDisponibles;
        List<Evento> listaEventosEliminados = new ArrayList();
        Evento evento;
        
        // Eliminamos los eventos cuyos plazos de inscripción han finalizado
        listaEventosDisponibles = this.eventoFacade.findAll();
        if (listaEventosDisponibles != null && !listaEventosDisponibles.isEmpty()){
            Date fechaActual = new Date();
            for (Evento e : listaEventosDisponibles){
                if (e.getFechalimiteentradas().compareTo(fechaActual) < 1){
                    listaEventosEliminados.add(e);
                }
            }
            listaEventosDisponibles.removeAll(listaEventosEliminados);
        }
        
        // Reducimos el aforo por cada inscripción
        listaInscripcionesTotales = this.inscripcionFacade.findAll();
        if (listaInscripcionesTotales != null && !listaInscripcionesTotales.isEmpty() && listaEventosDisponibles != null && !listaEventosDisponibles.isEmpty()){
            for (Inscripcion i : listaInscripcionesTotales){
                if (listaEventosDisponibles.contains(i.getEvento())){
                    evento = listaEventosDisponibles.get(listaEventosDisponibles.indexOf(i.getEvento()));
                    listaEventosDisponibles.remove(evento);
                    evento.setAforo(evento.getAforo() - 1);

                    // Si el usuario es el del evento reducimos el número de entradas disponible
                    if (i.getUsuario().equals(usuario)){
                        evento.setMaximoentradasusuario(evento.getMaximoentradasusuario()- 1);
                    }
                    if (evento.getAforo() > 0 && evento.getMaximoentradasusuario() > 0){
                            // Si tiene aforo y asientos disponibles lo volvemos a introducir
                        listaEventosDisponibles.add(evento);
                    }
                }
            }  
        } 
        request.setAttribute("eventosDisponibles", listaEventosDisponibles);  
        
        // Conseguimos la lista de las inscripciones del usuario
        if (filtro != null && filtro.length() > 0){
            // Filtramos 
            listaInscripciones = this.inscripcionFacade.findByIdUsuarioYFiltro(usuario.getId(), filtro);
        } else {
            listaInscripciones = this.inscripcionFacade.findByIdUsuario(usuario.getId());
        } 
        if (listaInscripciones != null && !listaInscripciones.isEmpty()){
                request.setAttribute("inscripciones", listaInscripciones);
        }
        
        
        request.setAttribute("usuario", usuario);
        
        RequestDispatcher rd = request.getRequestDispatcher("eventos.jsp");
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
