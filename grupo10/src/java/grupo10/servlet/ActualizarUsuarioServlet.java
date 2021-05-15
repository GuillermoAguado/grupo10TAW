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

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ActualizarUsuarioServlet", urlPatterns = {"/ActualizarUsuarioServlet"})
public class ActualizarUsuarioServlet extends HttpServlet {

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
        String correoForm = request.getParameter("correo");
        String passForm = request.getParameter("password");
        String nombreForm = request.getParameter("nombre");
        String apellForm = request.getParameter("apellidos");
        String domicForm = request.getParameter("domicilio");
        String ciudadForm = request.getParameter("ciudad");
        String sexoForm = request.getParameter("sexo");
        String edadForm = request.getParameter("edad");
        String tipoForm = request.getParameter("tipo");
        String idUsuario = request.getParameter("idUsuario");
        int tipo = -1;
        if(tipoForm != null && !tipoForm.equals(""))
        {
            tipo = new Integer(tipoForm);
        }
        
        Usuario usuarioBase;
        if(!idUsuario.equals("-1"))
        {
            usuarioBase = this.usuarioFacade.findById(new Integer(idUsuario));
        }else
        {
            usuarioBase = new Usuario();
        }
        
        if (correoForm == null || correoForm.isEmpty() || passForm == null || passForm.isEmpty() || nombreForm == null || nombreForm.isEmpty()|| apellForm == null || apellForm.isEmpty() 
                || domicForm == null || domicForm.isEmpty()|| ciudadForm == null || ciudadForm.isEmpty() || sexoForm == null || sexoForm.isEmpty() || edadForm == null || edadForm.isEmpty()
                || tipoForm == null || tipoForm.isEmpty()){
            request.setAttribute("error", "Falta algún campo sin completar");
            request.setAttribute("usuarioEdit", (usuarioBase.getNombre()==null)?null:usuarioBase);
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
        } else if ((!sexoForm.equals("H") && !sexoForm.equals("M") && !sexoForm.equals("O")) || Integer.parseInt(edadForm) <= 0 || (tipo < 1) || (tipo > 5)){
            request.setAttribute("error", "Error en el formato introducido");
            request.setAttribute("usuarioEdit", (usuarioBase.getNombre()==null)?null:usuarioBase);
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
        }else if((idUsuario.equals("-1") && tipo==1))
        {
            request.setAttribute("error", "No se pueden crear usuarios de tipo Asistente");
            request.setAttribute("usuarioEdit", (usuarioBase.getNombre()==null)?null:usuarioBase);
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
        }else {                    


            if(!correoForm.equals(usuarioBase.getEmail()))
            {
                usuarioBase.setEmail(correoForm);
            }

            if(!passForm.equals(usuarioBase.getContrasenia()))
            {
                usuarioBase.setContrasenia(passForm);
            }

            if(!nombreForm.equals(usuarioBase.getNombre()))
            {
                usuarioBase.setNombre(nombreForm);
            }

            if(!apellForm.equals(usuarioBase.getApellidos()))
            {
                usuarioBase.setApellidos(apellForm);
            }

            if(!domicForm.equals(usuarioBase.getDomicilio()))
            {
                usuarioBase.setDomicilio(domicForm);
            }

            if(!ciudadForm.equals(usuarioBase.getCiudadRes()))
            {
                usuarioBase.setCiudadRes(ciudadForm);
            }

            if(!sexoForm.equals(usuarioBase.getSexo()))
            {
                usuarioBase.setSexo(sexoForm);
            }

            if(!edadForm.equals(usuarioBase.getEdad()))
            {
                usuarioBase.setEdad(new Integer(edadForm));
            }

            if(!tipoForm.equals(usuarioBase.getTipousuario()))
            {
                usuarioBase.setTipousuario(new Integer(tipoForm));
            }

            if(idUsuario.equals("-1"))
            {
                usuarioFacade.create(usuarioBase);
            }else
            {
                usuarioFacade.edit(usuarioBase);
            }


            RequestDispatcher rd = request.getRequestDispatcher("AdminServlet");
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
