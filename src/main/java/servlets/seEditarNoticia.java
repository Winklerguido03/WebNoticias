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

@WebServlet
@MultipartConfig
public class seEditarNoticia extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Para editar noticia
        int id = Integer.parseInt(request.getParameter("id"));
        noticiaImpl noticiaDao = new noticiaImpl();
        Noticia noticia = noticiaDao.getById(id);

        request.setAttribute("noticia", noticia);

        Categoria[] categorias = Categoria.values();
        request.setAttribute("listaCategorias", categorias);

        request.getRequestDispatcher("editarNoticia.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        noticiaImpl noticiaDao = new noticiaImpl();

        String accion = request.getParameter("operacion");

        switch (accion) {

            case "actualizar":

                Administrador admin = (Administrador) request.getSession().getAttribute("usuario");
                if (admin == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                int id = Integer.parseInt(request.getParameter("id"));
                String titulo = request.getParameter("txtTitulo");
                String descripcion = request.getParameter("txtDescripcion");
                Date fecha = Date.valueOf(request.getParameter("txtFecha"));
                String autor = request.getParameter("txtAutor");
                Categoria categoria = Categoria.valueOf(request.getParameter("lstCategoria"));

                String imagenFinal = request.getParameter("imagenActual");

                Part filePart = request.getPart("Img");

                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String nombreImagen = System.currentTimeMillis() + "_" + fileName;

                    String uploadPath = getServletContext().getRealPath("/img");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    filePart.write(uploadPath + File.separator + nombreImagen);

                    imagenFinal = nombreImagen;
                }

                Noticia noticia = new Noticia(id, titulo, descripcion, categoria, imagenFinal, autor, fecha, admin.getIdAdministrador());
                noticiaDao.update(noticia);
                response.sendRedirect("seAccesoAdmin");
                break;


        }

    }
}
