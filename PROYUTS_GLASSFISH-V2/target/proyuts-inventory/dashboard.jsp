<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<body>
<div class="layout">
    <aside>
        <h2>PROYUTS</h2>
        <a href="dashboard">Dashboard</a>
        <a href="proyuts">CRUD PROYUTS</a>
        <a href="courses">Cursos</a>
        <a href="profile">Perfil</a>
        <a href="logout">Salir</a>
    </aside>
    <main>
<button type="button" onclick="toggleTheme()" class="small-btn" style="float:right;">Modo claro/oscuro</button>
        <h1>Panel General</h1>
        <div class="grid">
            <div class="metric"><h3>¿Cuántos PROYUTS tengo?</h3><p>${total}</p></div>
            <div class="metric"><h3>PROYUTS faltantes para graduarte (52)</h3><p>${missing}</p></div>
            <div class="metric"><h3>Promedio semanal</h3><p>${weekAvg}</p></div>
            <div class="metric"><h3>Promedio mensual</h3><p>${monthAvg}</p></div>
            <div class="metric"><h3>Promedio anual</h3><p>${yearAvg}</p></div>
            <div class="metric"><h3>Promedio semestral</h3><p>${semesterAvg}</p></div>
        </div>
        <progress value="${total}" max="52"></progress>
        <p>Avance a graduación: ${total}/52 PROYUTS</p>
        <h3>10 funcionalidades adicionales incluidas</h3>
        <ul>
            <li>Filtro por categoría en PROYUTS</li><li>Búsqueda de cursos</li><li>Barra de progreso a meta</li>
            <li>Panel KPI visual</li><li>Semilla de cursos sugeridos</li><li>Control de sesión con filtro</li>
            <li>Variables de entorno para conexión BD</li><li>Diseño responsive</li><li>Navegación lateral</li><li>Script SQL de despliegue</li>
        </ul>
    </main>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>