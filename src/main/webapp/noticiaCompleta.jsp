<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<head>

    <title>
        <c:if test="${not empty noticia}">
        ${noticia.titulo}
        </c:if>
    </title>


  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="styles/styles.css">
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

<div class="container mt-5">
 <c:if test="${not empty noticia}">
    <h1>${noticia.titulo}</h1>

    <p class="text-muted">
        Por <strong>${noticia.autor}</strong> —
        ${noticia.fecha}
    </p>

    <img src="${pageContext.request.contextPath}/img/${noticia.imagen}"
         class="img-fluid my-4" alt="imagen noticia">

    <p class="fs-5">
        ${noticia.descripcion}
    </p>
 </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
