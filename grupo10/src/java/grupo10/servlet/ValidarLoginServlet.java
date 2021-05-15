/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.servlet;

import grupo10.dao.UsuarioFacade;
import grupo10.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ValidarLoginServlet", urlPatterns = {"/ValidarLoginServlet"})
public class ValidarLoginServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

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
        String strEmail = request.getParameter("email");
        String strContrasenia = request.getParameter("contrasenia");
        Usuario usuario = null;
        String strError = "";
        String strTo = "";
        HttpSession session = request.getSession();
        
        if (strEmail == null || strEmail.isEmpty() ||
            strContrasenia == null || strContrasenia.isEmpty()){
            // Error autentificacion
            strError = "Existen campos en blanco";
            request.setAttribute("error", strError);
            strTo = "login.jsp";
        } else {
            usuario = this.usuarioFacade.findByEmailAndPassword(strEmail, strContrasenia);
            if (usuario == null){
                // El usuario no está en la BD
                strError = "El usuario con los datos introducidos no existe";
                request.setAttribute("error", strError);
                strTo = "login.jsp";
            } else {
                switch (usuario.getTipousuario()){
                    case 1 : strTo = "ListaEventosServlet";
                                break;
                    case 2 : strTo = "CargaMisEventosServlet";
                                break;
                    case 3 : strTo = "//Manu";
                                break;
                    case 4 : strTo = "ConversacionesServlet";
                                break;
                    case 5 : strTo = "AdminServlet";
                                break;            
                }
                
                session.setAttribute("usuario", usuario);
            }
        }
        
        
        
        if (strTo.equals("login.jsp")){
            RequestDispatcher rd = request.getRequestDispatcher(strTo);
            rd.forward(request, response);
        } else {
            // Redirección al servlet de mostrar eventos
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            response.sendRedirect(strTo);
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
