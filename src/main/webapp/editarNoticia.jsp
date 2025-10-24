<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> .
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page import="entities.Noticia" %>
<%@ page import="entities.Categoria" %>
<%@ page import="DAO.noticiaImpl" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    noticiaImpl dao = new noticiaImpl();
    Noticia noticia = dao.getById(id);
%>

<%
   Categoria[] categorias = Categoria.values();
   request.setAttribute("listaCategorias",categorias);

%>

<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
 <link rel="stylesheet" href="styles/styleFormNoticia.css">
</head>
<body>
<header>
    <a class="titulo" href="index.jsp">NOTICIAS</a>
</header>

<main>

   <div class="formulario">

<h2>EDITAR NOTICIA</h2>
<form action="seNoticia" method="POST">

<input type="hidden" name="operacion" value="actualizar">
<input type="hidden" name="id" value="<%= noticia.getIdNoticia() %>">

<label for="txtTitulo">TITULO</label>
<input type="text" name="txtTitulo" id="txtTitulo" placeholder="Titulo" value="<%= noticia.getTitulo() %>" required />
<br>
<label for="txtDescripcion">DESCRIPCION</label>
<textarea name="txtDescripcion" placeholder="Descripcion"><%= noticia.getDescripcion() %></textarea>

<br>
<label for="txtCategoria">CATEGORIA</label>
<select name="lstCategoria" id="lstCategoria" tabindex="1">
        <c:forEach var="categoria" items="${listaCategorias}">
            <option value="${categoria.name()}"
                ${categoria.name() == noticia.getCategoria().name() ? "selected" : ""}>
                ${categoria.name()}
            </option>

        </c:forEach>
    </select>
<br>
<label for="txtAutor">AUTOR</label>
<input type="text" name="txtAutor" id="txtAutor"  placeholder="Autor" value="<%= noticia.getAutor() %>" required />
<br>
<label for="txtFecha">FECHA</label>
<input type="date" name="txtFecha" id="txtFecha"  placeholder="Fecha" value="<%= noticia.getFecha().toString() %>" required />
<br>
<input class="btn" type="submit" value="Enviar" />

</form>

</main>


</body>

</html>
