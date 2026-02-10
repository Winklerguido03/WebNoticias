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
import java.io.IOException;
import java.sql.Date;

@WebServlet
@MultipartConfig
public class seFormNoticia extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Categoria[] categorias = Categoria.values();
        request.setAttribute("listaCategorias", categorias);

        request.getRequestDispatcher("formNoticia.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String accion = request.getParameter("operacion");

        switch (accion) {
            case "nuevo":

                Administrador admin = (Administrador) request.getSession().getAttribute("usuario");

                if (admin == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                String titulo = request.getParameter("txtTitulo");
                String descripcion = request.getParameter("txtDescripcion");
                String fechaStr = request.getParameter("txtFecha");
                Categoria categoria = Categoria.valueOf(request.getParameter("lstCategoria"));
                String autor = request.getParameter("txtAutor");
                Part imagen = request.getPart("Img");
                Date fecha = Date.valueOf(fechaStr);

                String nombreImagen = null;

                if (imagen != null && imagen.getSize() > 0) {
                    String tipo = imagen.getContentType();
                    if (!tipo.startsWith("image/")) {
                        throw new ServletException("Archivo inválido");
                    }

                    nombreImagen = imagen.getSubmittedFileName();
                    String ruta = getServletContext().getRealPath("/img");
                    imagen.write(ruta + java.io.File.separator + nombreImagen);
                }

                Noticia noticia = new Noticia();
                noticia.setTitulo(titulo);
                noticia.setDescripcion(descripcion);
                noticia.setFecha(fecha);
                noticia.setAutor(autor);
                noticia.setCategoria(categoria);
                noticia.setAdministrador_idAdministrador(admin.getIdAdministrador());
                noticia.setImagen(nombreImagen);

                noticiaImpl dao = new noticiaImpl();
                dao.insert(noticia);

                response.sendRedirect("seAccesoAdmin");
                break;
        }
    }

}
