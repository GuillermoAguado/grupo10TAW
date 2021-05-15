/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.EtiquetasFacade;
import grupo10.dao.EventoFacade;
import grupo10.entity.Etiquetas;
import grupo10.entity.EtiquetasPK;
import grupo10.entity.Evento;
import grupo10.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
 * @author Hielito
 */
@WebServlet(name = "CreaEventoServlet", urlPatterns = {"/CreaEventoServlet"})
public class CreaEventoServlet extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;
    @EJB
    private EtiquetasFacade etiquetasFacade;
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
        response.setContentType("text/html;charset=UTF-8");
        
        String idEvento = request.getParameter("idEvento");
        String idCreador = request.getParameter("idCreador");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String fechaEvento = request.getParameter("fechaevento");
        String fechaLimite = request.getParameter("fechalimite");
        String strPrecioEntrada = request.getParameter("precio");
        String strAforo = request.getParameter("aforo");
        String strMaximoEntradasUsuario = request.getParameter("maximoentradas");
        String strNFilas = request.getParameter("filas");
        String strAsientosFila = request.getParameter("columnas");
        
        String[] etiquetasAntiguas = request.getParameterValues("Etiquetas");
        String etNuevas = request.getParameter("etiquetasnuevas");
        List<String> etiquetas = new ArrayList<>();
        
        if (etiquetasAntiguas != null) {
            for (String s : etiquetasAntiguas) {
                etiquetas.add(s);
            }
        }        
        if (etNuevas != null) {
            String[] etiquetasNuevas = etNuevas.split("[,]+");
            if (etiquetasNuevas.length > 1 || etiquetasNuevas.length == 1 && !etiquetasNuevas[0].equals("")) {
                for (String s : etiquetasNuevas) {
                    etiquetas.add(s);
                }
            }
        }
        

        
        HttpSession session = request.getSession();
        Usuario creador = (Usuario) session.getAttribute("usuario");
        Evento evento = new Evento();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<Etiquetas> etiquetasEvento = new ArrayList<>();
        
        try {
            double precioEntrada = Double.parseDouble(strPrecioEntrada);
            int aforo = Integer.parseInt(strAforo);
            int maximoEntradasUsuario = Integer.parseInt(strMaximoEntradasUsuario);
            int nFilas;
            int asientosFila;
            
            
            
            evento.setIdcreador(creador);
            evento.setNombre(nombre);
            evento.setDescripcion(descripcion);
            
            Date fEvento = format.parse(fechaEvento);
            Date fLimite = format.parse(fechaLimite);
            
            if (fEvento.compareTo(fLimite) <= 0) {
                throw new IllegalArgumentException("La fecha del evento debe ser posterior a la fecha límite");
            }
            
            evento.setFechaevento(fEvento);
            evento.setFechalimiteentradas(fLimite);
            evento.setPrecioentrada(precioEntrada);
            evento.setAforo(aforo);
            evento.setMaximoentradasusuario(maximoEntradasUsuario);
            
            if (!("").equals(strNFilas) && !("").equals(strAsientosFila)) {
                nFilas = Integer.parseInt(strNFilas);
                asientosFila = Integer.parseInt(strAsientosFila);
                if (nFilas < 0 || asientosFila < 0) {
                    throw new IllegalArgumentException("La fecha del evento debe ser posterior a la fecha límite");
                }
                evento.setNfilas(nFilas);
                evento.setAsientosfila(asientosFila);
            } else {
                evento.setNfilas(-1);
                evento.setAsientosfila(-1);
            }
            
            
            
            
            eventoFacade.create(evento);
            
            for (String s : etiquetas) {
                Etiquetas e = new Etiquetas();
                e.setEvento(evento);
                EtiquetasPK epk = new EtiquetasPK();
                epk.setEtiqueta(s);
                epk.setIdevento(evento.getId());
                e.setEtiquetasPK(epk);
                
                etiquetasFacade.create(e);
                etiquetasEvento.add(e);
            }
            
            evento.setEtiquetasList(etiquetasEvento);
            eventoFacade.edit(evento);
            
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("EditaEventoServlet");
            rd.forward(request, response);
        }
        
        response.sendRedirect("CargaMisEventosServlet");
        
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
