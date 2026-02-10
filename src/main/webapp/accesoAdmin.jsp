<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
   <title>NOTICIAS</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
   <link rel="stylesheet" href="styles/styleAccesoAdmin.css">
</head>
<body>
<header>
<nav>
           <a class="titulo" href="seNoticia">NOTICIAS</a>

    <div class="divAcciones">

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
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="seEditarNoticia?operacion=actualizar&id=${n.idNoticia}">Actualizar</a></li>
                    <li><a class="dropdown-item" href="seAccesoAdmin?operacion=eliminar&id=${n.idNoticia}">Eliminar</a></li>
                </ul>
            </div>

            <img src="${pageContext.request.contextPath}/img/${n.imagen}" class="card-img-top" alt="imagen noticia" style="width: 100%;">

            <div class="card-body">
                <a class="tituloNoticia" href="noticiaCompleta.jsp?id=${n.idNoticia}">${n.titulo}</a>
                <p class="card-text">${n.descripcion}</p>
                <p class="autor">Por <span>${n.autor}</span></p>
            </div>
        </div>
    </c:forEach>

    <a class="btnAgregarNoticia" href="seFormNoticia">+</a>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>