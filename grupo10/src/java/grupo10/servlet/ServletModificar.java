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
@WebServlet(name = "ServletModificar", urlPatterns = {"/ServletModificar"})
public class ServletModificar extends HttpServlet {

    @EJB
    EstudiobdFacade estudiobdFacade;
    
    @EJB
    UsuarioFacade usuarioFacade;
    
     @EJB
     EventoFacade eventoFacade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String modoguardado = request.getParameter("tipoestudio");
        String idUsuario = request.getParameter("idusuario");
        String nombreestudio = request.getParameter("nombre");
        String idestudio = request.getParameter("idestudio");
        
        Estudiobd estudio = this.estudiobdFacade.find(new Integer(idestudio));
        if(nombreestudio.equals("")){
            nombreestudio = "Estudio sin nombre";
        }
        

        if(modoguardado.equals("evento")){
            String fecha = request.getParameter("fecha");
            String aforo = request.getParameter("aforo");
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha2=formato.parse("1001-01-01");
            if(!fecha.equals("") && fecha!=null){
                fecha2=formato.parse(fecha);
            }
            
            
            
            
            
            Integer aforo2 = -27;
            if(!aforo.equals("") && aforo!=null){
                aforo2=Integer.parseInt(aforo);
            }
            //this.estudiobdFacade.actualizarValores(fecha2,aforo2,nombreestudio,idestudio);
            estudio.setAforofiltro(aforo2);
            estudio.setAnyofiltro(fecha2);
            estudio.setNombre(nombreestudio);
            List<Evento> lista = new ArrayList<>();
            if(aforo.equals("")==false && fecha.equals("")){
            lista=this.eventoFacade.encontrarAforo(estudio);
            }else if(aforo.equals("")==true && !fecha.equals("")){
                lista=this.eventoFacade.encontrarFecha(estudio);
            }else if(aforo.equals("")==true && fecha.equals("")){
            lista = this.eventoFacade.findAll();
            }else{
            lista = this.eventoFacade.encontrarEventos(estudio);
            }
            //lista=this.eventoFacade.encontrarEventos(estudio);
            estudio.setEventoList(lista);
            this.estudiobdFacade.edit(estudio);
        }else{
            
            String edadmayor = request.getParameter("edadmayor");
            String edadmenor= request.getParameter("edadmenor");
            String sexo = request.getParameter("sexo");
            String ciudad = request.getParameter("ciudad");
            Integer edadmayor2=-25;
            if(!edadmayor.equals("") && Integer.parseInt(edadmayor)>0){
                edadmayor2=Integer.parseInt(edadmayor);
            }
            Integer edadmenor2=-25;
            if(!edadmenor.equals("") && Integer.parseInt(edadmenor)>0){
                edadmenor2=Integer.parseInt(edadmenor);
            }
            
            estudio.setEdadfiltro(edadmayor2);
            estudio.setEdadmenorquefiltro(edadmenor2);
            if(sexo!=null){
            estudio.setSexofiltro(sexo);
            }
            if(ciudad!=null){
            estudio.setCiudadusuariofiltro(ciudad);
            }
            
            
            List<Usuario> lista = new ArrayList<>();
            if(edadmayor.equals("") && edadmenor.equals("") && (sexo==null || sexo.equals("")) && ciudad.equals("")){
            lista=this.usuarioFacade.findAll();
            }else{
            lista = this.usuarioFacade.encontrarUsuarios(estudio);
            }
            estudio.setUsuarioList(lista);
            
            
            if(sexo==null || sexo.equals("")){
            estudio.setSexofiltro("N");
            }
            if(ciudad==null || ciudad.equals("")){
            estudio.setCiudadusuariofiltro("ninguna");
            }
            estudio.setNombre(nombreestudio);
            this.estudiobdFacade.edit(estudio);    
        }
        
        
        response.sendRedirect("AnalistaServletInicial?id="+idUsuario);
        
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
            Logger.getLogger(ServletModificar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletModificar.class.getName()).log(Level.SEVERE, null, ex);
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
