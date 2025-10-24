<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> .
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
   <title>NOTICIAS</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
   <link rel="stylesheet" href="styles/styleLogin.css">
</head>
<body>
<header>
    <a class="titulo" href="index.jsp">NOTICIAS</a>
</header>
<main>

    <div class="formulario">
        <h2>INICIAR SESIÓN</h2>
    <form action="seLogin" method="POST">

    <label for="txtUser">USUARIO</label>
    <br>
    <input type="text" name="txtUser" id="txtUser" placeholder="Nombre de usuario" required />
    <br>
    <label for="txtPass">CONTRASEÑA</label>
    <br>
    <input type="password" name="txtPass" id="txtPass" placeholder="Contraseña" required />
    <br>
    <input class="btn" type="submit" value="Enviar" />

    </form>
    </div>

</main>

</body>
</html>