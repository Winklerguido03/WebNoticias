<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
   <title>NOTICIAS</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
   <link rel="stylesheet" type="text/css" href="styles/styles.css">
   <meta charset="UTF-8">
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
          <ul><li><a class="seccion" href="seNoticia?categoria=deportes">DEPORTES</a></li></ul>
          <ul><li><a class="seccion" href="seNoticia?categoria=politica">POLITICA</a></li></ul>
          <ul><li><a class="seccion" href="seNoticia?categoria=economia">ECONOMIA</a></li></ul>
          <ul><li><a class="seccion" href="seNoticia?categoria=tecnologia">TECNOLOGIA</a></li></ul>
        </div>
      </div>
    </div>
    <a class="titulo" href="seNoticia">NOTICIAS</a>

    <div class="acciones">

        <c:if test="${sessionScope.usuario == null}">
            <a class="btnIniciarSesion" href="login.jsp">
                INICIAR SESION
            </a>
        </c:if>

        <c:if test="${sessionScope.usuario != null}">
            <a class="btnIniciarSesion" href="seAccesoAdmin">
                MIS NOTICIAS
            </a>

            <a class="btnIniciarSesion" href="seLogin?cerrarSesion=true">
                CERRAR SESION
            </a>
        </c:if>

    </div>
      </nav>
</header>
<main>
   <c:forEach var="n" items="${listaNoticias}">

      <div class="card" style="width: 18rem;">
       <img src="${pageContext.request.contextPath}/img/${n.imagen}" class="card-img-top" alt="imagen noticia">
        <div class="card-body">
          <a class="tituloNoticia" href="seNoticiaCompleta?id=${n.idNoticia}">${n.titulo}</a>
          <p class="card-text">${n.descripcion}</p>
          <p class="autor">Por <span>${n.autor}</span></p>
        </div>
      </div>
           </c:forEach>
</main>
<footer>
  <p>
  © 2026, Noticias.com
  </p>
  </footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
