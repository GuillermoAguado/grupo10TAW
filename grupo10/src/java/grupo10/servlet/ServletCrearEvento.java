package grupo10.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import grupo10.dao.EventoFacade;
import grupo10.dao.UsuarioFacade;
import grupo10.entity.Estudiobd;
import grupo10.entity.Evento;
//import static grupo10.entity.Inscripcion_.usuario;
import grupo10.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/ServletCrearEvento"})
public class ServletCrearEvento extends HttpServlet {

 @EJB
 EventoFacade eventoFacade;
 
 @EJB
 UsuarioFacade usuarioFacade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String aforo;
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        
        aforo = request.getParameter("aforo");
        Integer id =Integer.parseInt(request.getParameter("idusuario2"));
        Estudiobd estudio = new Estudiobd();
        String fechastring = request.getParameter("fecha");
        Date date=null;
        if(fechastring.equals("")==false){
         date = fecha.parse(fechastring);
        if(date!=null){
        estudio.setAnyofiltro(date);
        }else{
            estudio.setAnyofiltro(fecha.parse("1001-01-01"));
        }
        }else{
            estudio.setAnyofiltro(fecha.parse("1001-01-01"));
        }
        if(aforo.equals("")==false){
        if(Integer.parseInt(aforo)>0){
        estudio.setAforofiltro(new Integer(aforo));
        }
        }else{
            estudio.setAforofiltro(-27);
        }
        Usuario usuario = this.usuarioFacade.find(id);
        List<Evento> lista = new ArrayList<>();
        estudio.setIdautor(usuario);
        if(aforo.equals("")==false && date==null){
            lista=this.eventoFacade.encontrarAforo(estudio);
        }else if(aforo.equals("")==true && date!=null){
            lista=this.eventoFacade.encontrarFecha(estudio);
        }else if(aforo.equals("")==false || date!=null){
        lista = this.eventoFacade.encontrarEventos(estudio);
        }else{
        lista = this.eventoFacade.findAll();
        }
        
        estudio.setUsuarioList(null);
        estudio.setEdadfiltro(-26);
        estudio.setSexofiltro("J");
        estudio.setCiudadusuariofiltro("ninguna2");
        estudio.setLocalizacionfiltro("ninguna2");
        estudio.setEdadmenorquefiltro(-26);
        
        if(lista==null){
            lista = new ArrayList<>();
        }
        String modo = "escritura";
        request.setAttribute("listaEventos", lista);
        request.setAttribute("usuario", usuario);
        request.setAttribute("estudio", estudio);
        request.setAttribute("modo", modo);
        
        RequestDispatcher rd = request.getRequestDispatcher("vistalistaevento.jsp");
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
     try {
         processRequest(request, response);
     } catch (ParseException ex) {
         Logger.getLogger(ServletCrearEvento.class.getName()).log(Level.SEVERE, null, ex);
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
         Logger.getLogger(ServletCrearEvento.class.getName()).log(Level.SEVERE, null, ex);
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
