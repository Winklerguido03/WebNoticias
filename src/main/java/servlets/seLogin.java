package servlets;

import DAO.adminImpl;
import entities.Administrador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class seLogin extends HttpServlet {

  public void doPost(
  HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String usuario = request.getParameter("txtUser");
    String password = request.getParameter("txtPass");

    adminImpl adminDao=new adminImpl();
    if (adminDao.existsByUserPass(usuario,password)){
      HttpSession sesion = request.getSession();
      sesion.setAttribute("usuarioLogueado", usuario);

      // Redirigir a la página principal
      response.sendRedirect("accesoAdmin.jsp");
    }else {
      request.setAttribute("mensajeError", "Usuario o contraseña incorrectos");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }
  }
}
