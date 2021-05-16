/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.EstudiobdFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Estudiobd;
import grupo10.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manul
 */
@WebServlet(name = "ServletGuardar", urlPatterns = {"/ServletGuardar"})
public class ServletGuardar extends HttpServlet {

    @EJB
    EstudiobdFacade estudiobdFacade;
    
    @EJB
    UsuarioFacade usuarioFacade;
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
       Usuario usuario =(Usuario) request.getSession().getAttribute("usuarioenvio");
       Estudiobd estudio =(Estudiobd) request.getSession().getAttribute("estudioenvio");
       List<Usuario> list =(List) request.getSession().getAttribute("listaenvio");
       request.getSession().removeAttribute("usuarioenvio");
       request.getSession().removeAttribute("estudioenvio");
       request.getSession().removeAttribute("listaenvio");
       String nombre = request.getParameter("nombre");
       estudio.setNombre(nombre);
       estudio.setUsuarioList(list); 
       this.estudiobdFacade.create(estudio);
       usuario.getEstudiobdList1().add(estudio);
       this.usuarioFacade.edit(usuario);
       //request.setAttribute("id", usuario.getId());
       
       response.sendRedirect("AnalistaServletInicial?id="+usuario.getId());
       //RequestDispatcher rd = request.getRequestDispatcher("AnalistaServletInicial");
       //rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletGuardar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletGuardar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
