<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<title>PROYUTS</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<body>
<div class="layout"><aside><a href="dashboard">Dashboard</a><a href="courses">Cursos</a></aside>
<main>
<button type="button" onclick="toggleTheme()" class="small-btn" style="float:right;">Modo claro/oscuro</button>
<h1>PROYUTS (CRUD)</h1>
<form method="post" action="proyuts" class="card">
<input type="hidden" name="action" value="create"/>
<input name="title" placeholder="Título" required/>
<input name="category" placeholder="Categoría" required/>
<input name="score" type="number" min="1" max="10" placeholder="Puntaje" required/>
<input name="completedDate" type="date" required/>
<textarea name="description" placeholder="Descripción"></textarea>
<button>Guardar PROYUT</button>
</form>
<input id="filter" placeholder="Filtrar por categoría" onkeyup="filterTable()"/>
<table id="proyutTable"><tr><th>Título</th><th>Categoría</th><th>Puntaje</th><th>Fecha</th><th>Acción</th></tr>
<c:forEach var="p" items="${proyuts}">
<tr><td>${p.title}</td><td>${p.category}</td><td>${p.score}</td><td>${p.completedDate}</td><td>
<form method="post" action="proyuts"><input type="hidden" name="action" value="delete"/><input type="hidden" name="id" value="${p.id}"/><button class="small-btn">Eliminar</button></form>
</td></tr>
</c:forEach>
</table>

<h2>Top 10 PROYUTS con mejor puntaje</h2>
<table><tr><th>#</th><th>Título</th><th>Categoría</th><th>Puntaje</th><th>Fecha</th></tr>
<c:forEach var="tp" items="${topProyuts}" varStatus="st">
<tr><td>${st.index + 1}</td><td>${tp.title}</td><td>${tp.category}</td><td>${tp.score}</td><td>${tp.completedDate}</td></tr>
</c:forEach>
</table>

</main></div>
<script src="assets/js/app.js"></script>
</body>
</html>