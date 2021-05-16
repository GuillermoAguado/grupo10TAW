/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.EstudiobdFacade;
import grupo10.dao.EventoFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Estudiobd;
import grupo10.entity.Evento;
import grupo10.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manul
 */
@WebServlet(name = "ServletGuardarEvento", urlPatterns = {"/ServletGuardarEvento"})
public class ServletGuardarEvento extends HttpServlet {

    @EJB
    EstudiobdFacade estudiobdFacade;
    
    @EJB
    EventoFacade eventoFacade;
    
    @EJB
    UsuarioFacade usuarioFacade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario =(Usuario) request.getSession().getAttribute("usuarioenvio");
       Estudiobd estudio =(Estudiobd) request.getSession().getAttribute("estudioenvio");
       List<Evento> list =(List) request.getSession().getAttribute("listaenvio");
       request.getSession().removeAttribute("usuarioenvio");
       request.getSession().removeAttribute("estudioenvio");
       request.getSession().removeAttribute("listaenvio");
       String nombre = request.getParameter("nombre");
       estudio.setNombre(nombre);
       estudio.setEventoList(list);
       this.estudiobdFacade.create(estudio);
       usuario.getEstudiobdList1().add(estudio);
       this.usuarioFacade.edit(usuario);
       //request.setAttribute("id", usuario.getId());*/
       
       response.sendRedirect("AnalistaServletInicial?id="+usuario.getId());
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
