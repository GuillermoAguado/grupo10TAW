/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.EstudiobdFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Estudiobd;
import grupo10.entity.Evento;
import grupo10.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ServletMostrarEstudio", urlPatterns = {"/ServletMostrarEstudio"})
public class ServletMostrarEstudio extends HttpServlet {

    @EJB
    EstudiobdFacade estudiobdFacade;
    
    @EJB
    UsuarioFacade usuarioFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String id = request.getParameter("idestudio");
       Estudiobd estudio;
       estudio=this.estudiobdFacade.find(new Integer(id));
       String idusuario = request.getParameter("idanalista");
       Usuario usuario = this.usuarioFacade.find(new Integer(idusuario));
       String modo = "lectura";
       boolean tipoestudiousuario=false;
       if(estudio.getAforofiltro()==-1){
            tipoestudiousuario=true;
        }
       
       if(tipoestudiousuario){
           List<Usuario> lista=new ArrayList<>();
           lista=estudio.getUsuarioList();
           request.setAttribute("listaUsers", lista);
            request.setAttribute("usuario", usuario);
            request.setAttribute("estudio", estudio);
            request.setAttribute("modo", modo);
            RequestDispatcher rd = request.getRequestDispatcher("vistalista.jsp");
            rd.forward(request, response);
       }else{
           List<Evento> lista=new ArrayList<>();
           lista=estudio.getEventoList();
            request.setAttribute("listaEventos", lista);
            request.setAttribute("usuario", usuario);
            request.setAttribute("estudio", estudio);
            request.setAttribute("modo", modo);
            RequestDispatcher rd = request.getRequestDispatcher("vistalistaevento.jsp");
            rd.forward(request, response);
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