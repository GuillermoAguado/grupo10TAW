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
@WebServlet(name = "RegistrarUsuarioServlet", urlPatterns = {"/RegistrarUsuarioServlet"})
public class RegistrarUsuarioServlet extends HttpServlet {

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
        String correo, password, nombre, apellidos, domicilio, ciudad, sexo, edad;
        Usuario usuario;
        HttpSession session;
        correo = request.getParameter("correo");
        password = request.getParameter("password");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        domicilio = request.getParameter("domicilio");
        ciudad = request.getParameter("ciudad");
        sexo = request.getParameter("sexo");
        edad = request.getParameter("edad");
        
        if (correo == null || correo.isEmpty() || password == null || password.isEmpty() || nombre == null || nombre.isEmpty()|| apellidos == null || apellidos.isEmpty() 
                || domicilio == null || domicilio.isEmpty()|| ciudad == null || ciudad.isEmpty() || sexo == null || sexo.isEmpty() || edad == null || edad.isEmpty()){
            request.setAttribute("error", "Falta algún campo sin completar");
            RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
            rd.forward(request, response);
        } else if ((!sexo.equals("H") && !sexo.equals("M") && !sexo.equals("O")) || Integer.parseInt(edad) <= 0){
            request.setAttribute("error", "Error en el formato introducido");
            RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
            rd.forward(request, response);
        } else {
            usuario = new Usuario();
            usuario.setEmail(correo);
            usuario.setContrasenia(password);
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setDomicilio(domicilio);
            usuario.setCiudadRes(ciudad);
            usuario.setSexo(sexo);
            usuario.setEdad(Integer.parseInt(edad));
            usuario.setTipousuario(1);
            usuarioFacade.create(usuario);
            
            session = request.getSession();
            session.setAttribute("usuario", usuario);
            
            response.sendRedirect("ListaEventosServlet");
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
