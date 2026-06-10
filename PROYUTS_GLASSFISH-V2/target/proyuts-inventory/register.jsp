<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Registro</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<body>
<div style="position:fixed;top:10px;right:10px;z-index:99;"><button type="button" onclick="toggleTheme()" class="small-btn">Modo claro/oscuro</button></div>
<div class="container">
    <form method="post" action="auth" class="card">
        <input type="hidden" name="action" value="register"/>
        <h2 style="margin:0;color:#1b5e20;">Registro de estudiante UTS</h2>
        <input name="fullName" placeholder="Nombre completo" required/>
        <input type="email" name="email" placeholder="Correo" required/>
        <input type="password" name="password" placeholder="Contraseña" required/>
        <input name="career" placeholder="Carrera" required/>
        <input name="semester" placeholder="Semestre actual" required/>
        <button type="submit">Crear cuenta</button>
        <a href="login.jsp">Volver al login</a>
    </form>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>