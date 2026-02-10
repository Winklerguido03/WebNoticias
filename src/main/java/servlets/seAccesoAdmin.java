package servlets;

import DAO.noticiaImpl;
import entities.Administrador;
import entities.Noticia;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class seAccesoAdmin extends HttpServlet{

    noticiaImpl noticiaDao = new noticiaImpl();
    List<Noticia> listaNoticias;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Para obtener sesion de admin
        Administrador administrador = (Administrador) session.getAttribute("usuario");

        if (administrador == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Para obtener noticias depende que admin inició sesion
        listaNoticias = noticiaDao.getByAdministrador(administrador.getIdAdministrador());
        request.setAttribute("listaNoticias", listaNoticias);
        request.getRequestDispatcher("accesoAdmin.jsp").forward(request, response);

        if (session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        //Para eliminar una noticia
        String accion = request.getParameter("operacion");

        switch (accion) {
            case "eliminar":
                int id = Integer.parseInt(request.getParameter("id"));
                noticiaDao.delete(id);
                response.sendRedirect("seAccesoAdmin");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
