<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> .
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page import="entities.Administrador" %>
<%@ page import="DAO.noticiaImpl" %>
<%@ page import="entities.Noticia" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
    Administrador administrador = new Administrador();
    noticiaImpl noticiaDao = new noticiaImpl();
    Noticia noticia = new Noticia();
    List<Noticia> listaNoticias = noticiaDao.getAll(administrador.getIdAdministrador());
    request.setAttribute("listaNoticias", listaNoticias);
 %>

<html>
<head>
   <title>NOTICIAS</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
   <link rel="stylesheet" href="styles/styleAccesoAdmin.css">
</head>
<body>
<header>
    <a class="titulo" href="index.jsp">NOTICIAS</a>
</header>
<main>

 <% for(Noticia n : listaNoticias) { %>

       <div class="card" style="width: 18rem;">
         <div class="btn-group" role="group">
           <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
           </button>
           <ul class="dropdown-menu">
             <li> <a class="dropdown-item" href="editarNoticia.jsp?id=<%= n.getIdNoticia() %>">Actualizar</a></li>
             <li><a class="dropdown-item" href="seNoticia?operacion=eliminar&id=<%= n.getIdNoticia() %>">Eliminar</a></li>
           </ul>
       </div>
         <div class="card-body">
           <a class="tituloNoticia" href=""><%=n.getTitulo() %></a>
           <p class="card-text"><%=n.getDescripcion() %></p>
           <p class="autor">Por <span><%=n.getAutor() %></span></p>
         </div>
       </div>
            <% }  %>

   <a class="btnAgregarNoticia" href="formNoticia.jsp">+</a>


</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>