<%@ page import="entities.Administrador" %>
<%@ page import="DAO.noticiaImpl" %>
<%@ page import="entities.Noticia" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%!
    Administrador administrador = new Administrador();
    noticiaImpl noticiaDao = new noticiaImpl();
    Noticia noticia = new Noticia();
    List<Noticia> listaNoticias = noticiaDao.getAll(administrador.getIdAdministrador());
 %>

<html>
<head>
   <title>NOTICIAS</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
   <link rel="stylesheet" href="styles/styles.css">
</head>
<body>
<header>
<nav>

    <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#staticBackdrop" aria-controls="staticBackdrop">
      SECCIONES
    </button>
    <div class="offcanvas offcanvas-start" data-bs-backdrop="static" tabindex="-1" id="staticBackdrop" aria-labelledby="staticBackdropLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="staticBackdropLabel">NOTICIAS</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <div>
          <ul><li><a class="seccion" href="deportes.jsp?categoria=deportes">DEPORTES</a></li></ul>
          <ul><li><a class="seccion" href="politica.jsp?categoria=politica">POLITICA</a></li></ul>
          <ul><li><a class="seccion" href="economia.jsp?categria=economia">ECONOMIA</a></li></ul>
          <ul><li><a class="seccion" href="tecnologia.jsp?categoria=tecnologia">TECNOLOGIA</a></li></ul>
        </div>
      </div>
    </div>
    <a class="titulo" href="index.jsp">NOTICIAS</a>
    <div><a class="btnIniciarSesion" href="login.jsp">INICIAR SESION</a></div>
      </nav>
    </div>
</nav>
</header>
<main>

   <% for(Noticia n : listaNoticias) { %>

      <div class="card" style="width: 18rem;">
        <div class="card-body">
          <a class="tituloNoticia" href=""><%=n.getTitulo() %></a>
          <p class="card-text"><%=n.getDescripcion() %></p>
          <p class="autor">Por <span><%=n.getAutor() %></span></p>
        </div>
      </div>
           <% }  %>

   <a class="btn" href="formNoticia.jsp">+</a>


</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
