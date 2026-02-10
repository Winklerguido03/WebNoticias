package servlets;

import DAO.noticiaImpl;
import entities.Noticia;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class seNoticiaCompleta extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        noticiaImpl dao = new noticiaImpl();
        Noticia noticia = dao.getById(id);

        request.setAttribute("noticia", noticia);

        request.getRequestDispatcher("noticiaCompleta.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){}

}
