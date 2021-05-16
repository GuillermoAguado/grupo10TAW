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
import java.util.ArrayList;
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
@WebServlet(name = "ServletCrear", urlPatterns = {"/ServletCrear"})
public class ServletCrear extends HttpServlet {
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    EstudiobdFacade estudiobdFacade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String edadmayor, edadmenor, sexo, ciudad;
        edadmayor = request.getParameter("edadmayor");
        edadmenor = request.getParameter("edadmenor");
        sexo = request.getParameter("sexo");
        ciudad = request.getParameter("ciudad");
        Integer id =Integer.parseInt(request.getParameter("idusuario2"));
        Estudiobd estudio = new Estudiobd();
        if(edadmayor.equals("")==false){
        if(Integer.parseInt(edadmayor)>0){
        estudio.setEdadfiltro(new Integer(edadmayor));
        }
        }else{
            estudio.setEdadfiltro(-25);
        }
        if(edadmenor.equals("")==false){
        if(Integer.parseInt(edadmenor)>0){
        estudio.setEdadmenorquefiltro(new Integer(edadmenor));
        }
        }else{
            estudio.setEdadmenorquefiltro(-25);
        }
        if(sexo!=null){
        estudio.setSexofiltro(sexo);
        }
        if(ciudad!=null && !ciudad.equals("")){
        estudio.setCiudadusuariofiltro(ciudad);
        }
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        estudio.setAnyofiltro(fecha.parse("1000-01-01"));
        estudio.setLocalizacionfiltro("ninguna");
        estudio.setAforofiltro(-1);
        
        Usuario usuario = this.usuarioFacade.find(id);
        estudio.setIdautor(usuario);
        List<Usuario> lista = new ArrayList<>();
        if(edadmayor.equals("") && edadmenor.equals("") && (sexo==null || sexo=="") && ciudad.equals("")){
            lista=this.usuarioFacade.findAll();
        }else{
        lista = this.usuarioFacade.encontrarUsuarios(estudio);
        }
        
        if(ciudad==null || ciudad.equals("")){
        estudio.setCiudadusuariofiltro("ninguna");
        }
        if(sexo==null){
        estudio.setSexofiltro("N");
        }
        
        estudio.setEventoList(null);
        
        String modo = "escritura";
        
        request.setAttribute("listaUsers", lista);
        request.setAttribute("usuario", usuario);
        request.setAttribute("estudio", estudio);
        request.setAttribute("modo", modo);
        
        RequestDispatcher rd = request.getRequestDispatcher("vistalista.jsp");
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
            Logger.getLogger(ServletCrear.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletCrear.class.getName()).log(Level.SEVERE, null, ex);
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
