<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>Perfil</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<body>
<div style="position:fixed;top:10px;right:10px;z-index:99;"><button type="button" onclick="toggleTheme()" class="small-btn">Modo claro/oscuro</button></div>
<div class="container card">
<h1>Perfil del usuario</h1>
<p><b>Nombre:</b> ${profile.fullName}</p>
<p><b>Correo:</b> ${profile.email}</p>
<p><b>Carrera:</b> ${profile.career}</p>
<p><b>Semestre:</b> ${profile.semester}</p>
<a href="dashboard">Volver</a>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>