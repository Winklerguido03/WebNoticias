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

   Noticia noticia =new Noticia();
   noticiaImpl noticiaDao=new noticiaImpl();

   public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{

    String accion = request.getParameter("operacion");

    switch (accion){
     case "eliminar":
      int id = Integer.parseInt(request.getParameter("id"));
      noticiaDao.delete(id);
      response.sendRedirect("accesoAdmin.jsp");
    }


   }


   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String accion= request.getParameter("operacion");


    switch (accion){
     case "nuevo":
      String titulo = request.getParameter("txtTitulo");
      String descripcion = request.getParameter("txtDescripcion");
      String fechaStr = request.getParameter("txtFecha");
      String autor = request.getParameter("txtAutor");
      Categoria categoria = Categoria.valueOf(request.getParameter("lstCategoria"));
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
      break;

     case "actualizar":
      int id = Integer.parseInt(request.getParameter("id"));
       titulo = request.getParameter("txtTitulo");
       descripcion = request.getParameter("txtDescripcion");
       fecha= Date.valueOf(request.getParameter("txtFecha"));
       autor = request.getParameter("txtAutor");
       categoria= Categoria.valueOf(request.getParameter("lstCategoria"));

       noticia = new Noticia(id,titulo,descripcion,categoria,autor,fecha);
      noticiaDao.update(noticia);
      response.sendRedirect("accesoAdmin.jsp");
      break;
    }



  }

}
