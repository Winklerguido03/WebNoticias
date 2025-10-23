package servlets;

import DAO.noticiaImpl;
import entities.Categoria;
import entities.Noticia;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

public class seNoticia extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String titulo = request.getParameter("txtTitulo");
    String descripcion = request.getParameter("txtDescripcion");
    String fechaStr = request.getParameter("txtFecha");
    String autor = request.getParameter("txtAutor");
    String categoriaStr = request.getParameter("lstCategoria");


    Categoria categoria = Categoria.valueOf(categoriaStr);
    Date fecha = Date.valueOf(fechaStr);

    Noticia noticia = new Noticia();
    noticia.setTitulo(titulo);
    noticia.setDescripcion(descripcion);
    noticia.setFecha(fecha);
    noticia.setAutor(autor);
    noticia.setCategoria(categoria);

    noticiaImpl dao = new noticiaImpl();
    dao.insert(noticia);

    response.sendRedirect("accesoAdmin.jsp");
  }

}
