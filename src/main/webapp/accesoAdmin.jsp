<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> .
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page import="entities.Administrador" %>
<%@ page import="DAO.noticiaImpl" %>
<%@ page import="entities.Noticia" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!-- abro bloque de declaracion java -->
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
   <link rel="stylesheet" href="css/styleAccesoAdmin.css">
</head>
<body>
<header>
    <a class="titulo" href="index.jsp">NOTICIAS</a>
</header>
<main>

   <% for(Noticia n : listaNoticias) { %>
   <div>
            <img src="<%= n.getImagen() %>" alt="<%= n.getTitulo() %>" class="imagen-noticia">
            <h2> <%=n.getTitulo() %> </h2>
            <p><%=n.getDescripcion() %> </p>
            <p><%=n.getCategoria() %> </p>
            <p><%=n.getAutor() %> </p>
        <% }  %>
   </div>

   <a class="btn" href="formNoticia.jsp">+</a>


</main>

</body>
</html>