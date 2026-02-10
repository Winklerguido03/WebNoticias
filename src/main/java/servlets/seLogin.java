package servlets;

import DAO.adminImpl;
import entities.Administrador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.PasswordUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class seLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private adminImpl adminDAO = new adminImpl();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cerrarSesionParam = request.getParameter("cerrarSesion");

        if ("true".equals(cerrarSesionParam)) {
            cerrarSesion(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      iniciarSesion(request,response);
  }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            Administrador administrador =
                    (Administrador) session.getAttribute("usuario");
            session.invalidate();
        }

        response.sendRedirect("seNoticia");
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("txtCorreo");
        String clave = request.getParameter("txtPass");

        String destino = validarCredenciales(correo, clave, request);

        if ("seAccesoAdmin".equals(destino)) {
            response.sendRedirect(destino);
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(destino);
            dispatcher.forward(request, response);
        }
    }

    private String validarCredenciales(String correo, String clave, HttpServletRequest request) {
        if (correo == null || clave == null || correo.trim().isEmpty() || clave.trim().isEmpty()) {
            request.setAttribute("mensajeError", "Correo y contraseña son obligatorios");
            return "login.jsp";
        }

        correo = correo.trim();


        Administrador administrador = adminDAO.getByCorreo(correo);

        if (administrador == null) {
            request.setAttribute("mensajeError", "Credenciales no válidas");
            return "login.jsp";
        }

        // Verificar contraseña con BCrypt - con manejo de errores
        try {
            if (!PasswordUtil.verifyPassword(clave, administrador.getPassword())) {
                request.setAttribute("mensajeError", "Credenciales no válidas");
                return "login.jsp";
            }
        } catch (Exception e) {
            System.err.println("Error verificando contraseña: " + e.getMessage());
            request.setAttribute("mensajeError", "Error en el sistema de autenticación");
            return "login.jsp";
        }

        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", administrador);

        request.setAttribute("mensajeExito", "Sesión iniciada correctamente");
        return "seAccesoAdmin";
    }

    private void redirigirA(String destino, HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }
}

