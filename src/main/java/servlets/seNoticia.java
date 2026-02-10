package servlets;

import DAO.noticiaImpl;

import entities.Administrador;
import entities.Categoria;
import entities.Noticia;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@WebServlet
@MultipartConfig
public class seNoticia extends HttpServlet {

    Noticia noticia = new Noticia();
    noticiaImpl noticiaDao = new noticiaImpl();
    List<Noticia> listaNoticias;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Para obtener categoria y decidir que categoria mostrar
        String categoria = request.getParameter("categoria");

        noticiaDao = new noticiaImpl();
        List<Noticia> listaNoticias;

        if (categoria != null && !categoria.isEmpty()) {
            listaNoticias = noticiaDao.getByCategory(categoria);
        } else {
            listaNoticias = noticiaDao.getAll();
        }

        request.setAttribute("listaNoticias", listaNoticias);
        request.setAttribute("categoria", categoria);

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




    }

}
