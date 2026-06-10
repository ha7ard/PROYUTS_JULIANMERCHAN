<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>PROYUTS - Login</title><link rel="stylesheet" href="assets/css/styles.css"></head>
<body>
<div style="position:fixed;top:10px;right:10px;z-index:99;"><button type="button" onclick="toggleTheme()" class="small-btn">Modo claro/oscuro</button></div>
<div class="container">
    <h1 style="text-align:center;color:#1b5e20;">PROYUTS Inventory</h1>
    <form method="post" action="auth" class="card">
        <input type="hidden" name="action" value="login"/>
        <h2 style="margin:0;color:#1565c0;">Ingreso al sistema</h2>
        <input name="email" type="email" placeholder="Correo" required/>
        <input name="password" type="password" placeholder="Contraseña" required/>
        <button type="submit">Entrar</button>
        <p>¿No tienes cuenta? <a href="register.jsp">Regístrate</a></p>
    </form>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>
